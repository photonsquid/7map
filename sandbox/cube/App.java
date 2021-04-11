import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;


import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class App {

	// The window handle
	private long window;

    // App loop settings
    private double secsPerUpdate = 1.0d / 30.0d; // interval between two ingame time ticks
    private double steps = 0.0;
	private float loopSlot = 1f / 50; // game iteration length

    // Cube mesh variables
    private FloatBuffer light_diffuse = BufferUtils.createFloatBuffer(4).put(new float[] {1.0f, 0.0f, 0.0f, 1.0f}).flip();  /* Red diffuse light. */
    private FloatBuffer light_position = BufferUtils.createFloatBuffer(4).put(new float[]{1.0f, 1.0f, 1.0f, 0.0f}).flip();  /* Infinite light location. */
    private FloatBuffer[] n = new FloatBuffer[]{  /* Normals for the 6 faces of a cube. */
                BufferUtils.createFloatBuffer(4).put(new float[] {-1.0f, 0.0f, 0.0f}).flip(), 
                BufferUtils.createFloatBuffer(4).put(new float[] {0.0f, 1.0f, 0.0f}).flip(), 
                BufferUtils.createFloatBuffer(4).put(new float[] {1.0f, 0.0f, 0.0f}).flip(),
                BufferUtils.createFloatBuffer(4).put(new float[] {0.0f, -1.0f, 0.0f}).flip(), 
                BufferUtils.createFloatBuffer(4).put(new float[] {0.0f, 0.0f, 1.0f}).flip(), 
                BufferUtils.createFloatBuffer(4).put(new float[] {0.0f, 0.0f, -1.0f}).flip() 
    };

    // private IntBuffer[] faces = new IntBuffer[]{  /* Vertex indices for the 6 faces of a cube. */
    //             BufferUtils.createIntBuffer(4).put(new int[] {0, 1, 2, 3}), 
    //             BufferUtils.createIntBuffer(4).put(new int[] {3, 2, 6, 7}), 
    //             BufferUtils.createIntBuffer(4).put(new int[] {7, 6, 5, 4}),
    //             BufferUtils.createIntBuffer(4).put(new int[] {4, 5, 1, 0}), 
    //             BufferUtils.createIntBuffer(4).put(new int[] {5, 6, 2, 1}), 
    //             BufferUtils.createIntBuffer(4).put(new int[] {7, 4, 0, 3}) 
    // };
    int faces[][] = new int[][] {  /* Vertex indices for the 6 faces of a cube. */
        {0, 1, 2, 3}, {3, 2, 6, 7}, {7, 6, 5, 4},
        {4, 5, 1, 0}, {5, 6, 2, 1}, {7, 4, 0, 3} };
    private FloatBuffer[] v = new FloatBuffer[8];  /* Will be filled in with X,Y,Z vertexes. */ // not sure about that syntax in java jwgl


	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback glErr = GLFWErrorCallback.createPrint(System.err);
        glErr.set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(1600, 900, "Hey there!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);

	}

    private void initScene() {
        // Enable a single OpenGL light
        glLightfv(GL_LIGHT0, GL_DIFFUSE, new float[] {1.0f, 0.0f, 0.0f, 1.0f});
        glLightfv(GL_LIGHT0, GL_POSITION, new float[] {1.0f, 1.0f, 1.0f, 0.0f});
        glEnable(GL_LIGHT0);
        glEnable(GL_LIGHTING);

        glEnable(GL_DEPTH_TEST);
        // glMatrixMode(GL_PROJECTION);
        // // gluPerspective
        glMatrixMode(GL_MODELVIEW);

        glTranslatef(0.0f, 0.0f, -1.0f);
        glRotatef(60f, 1.0f, 0.0f, 0.0f);
        glRotatef(-20f, 0.0f, 0.0f, 1.0f);
    }

    private void drawBox() {
        
        // for (int i = 0; i < 6; i++) {
        //     glBegin(GL_QUADS);
        //     glNormal3fv(n[i]);
        //     glVertex3fv(v[faces[i][0]]);
        //     glVertex3fv(v[faces[i][1]]);
        //     glVertex3fv(v[faces[i][2]]);
        //     glVertex3fv(v[faces[i][3]]);
        //     glEnd();
        // }
        glBegin(GL_QUADS);
        glNormal3fv(new float[] {-1.0f, -1.0f, 0.0f});
        glVertex3fv(new float[] {1.0f, -1.0f, 0.0f});
        glVertex3fv(new float[] {1.0f, 1.0f, 0.0f});
        glVertex3fv(new float[] {-1.0f, 1.0f, 0.0f});
        glEnd();
    }
    /**
     * Main render routine
     */
    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        drawBox();

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }

    private void sync(double loopStartTime) {
        double endTime = loopStartTime + loopSlot;
        while(glfwGetTime() < endTime) { // wait until we reach the end time for the frame
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {}
        }
    }

    /**
     * Game state updating routine
     */
    private void updateGameState() {
        // TODO : game state updating function
    }

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

        initScene();

        double previous = glfwGetTime();

		// Set the clear color
		glClearColor(0.1f, 0.1f, 0.1f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			double loopStartTime = glfwGetTime();
            double elapsed = loopStartTime - previous; // time elapsed on the last frame (response time)
            previous = loopStartTime;
            steps += elapsed;

            while (steps >= secsPerUpdate) {
                updateGameState();
                steps -= secsPerUpdate;
            }

            render();
            sync(loopStartTime); // wait until the end of the frame
		}
	}

	public static void main(String[] args) {
		new App().run();
	}

}