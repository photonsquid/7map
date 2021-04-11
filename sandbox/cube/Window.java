import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import exceptions.GLFailure;

import org.lwjgl.assimp.AICamera.*;

import java.io.PrintStream;
import java.lang.Thread;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    
    private long windowHandle;

	/**
	 * Create a new Window object.
	 * @param sizeX
	 * @param sizeY
	 */
    public Window(int sizeX, int sizeY) {
        try (GLFWErrorCallback glErr = GLFWErrorCallback.createPrint(System.err)) { // try-with-resources
    		glErr.set();
        }

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        
        // create window
        windowHandle = glfwCreateWindow(sizeX, sizeY, "Test application", NULL, NULL);
		if ( windowHandle == NULL )
			throw new RuntimeException("Failed to create GLFW window");


		// setup key callback (exiting program)
		glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});
		
		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(windowHandle, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				windowHandle,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(windowHandle);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(windowHandle);
    
    }

	public long getWindowHandle() {
		return this.windowHandle;
	}

	public void destroy() {
		glfwFreeCallbacks(windowHandle); // free all the passed callbacks
		glfwDestroyWindow(windowHandle); // destroy the window
	}

	public void swap() {
		glfwSwapBuffers(windowHandle);
	}
	
    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }
}
