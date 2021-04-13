package ui.gfx;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import ui.utils.FileUtils;

public class Shader {
    private String vertexFile;
    private String fragmentFile;

    /**pointers to actual elements */
    private int vertexShaderID, fragmentShaderID, programID;

    public Shader(String vertexPath, String fragmentPath) {
        vertexFile = FileUtils.loadToString(vertexPath);
        fragmentFile = FileUtils.loadToString(fragmentPath);
    }

    /**
     * Compile and attach shaders to program, then link it to the application.
     */
    public void create() {
        programID = GL20.glCreateProgram();

        // load & compile vertex shader
        vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        GL20.glShaderSource(vertexShaderID, vertexFile);
        GL20.glCompileShader(vertexShaderID);
        if (GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) { // error checking
            System.err.printf("Vertex Shader compilation : %s", GL20.glGetShaderInfoLog(vertexShaderID));
            System.exit(1);
        }

        // load & compile fragment shader
        fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

        GL20.glShaderSource(fragmentShaderID, fragmentFile);
        GL20.glCompileShader(fragmentShaderID);
        if (GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) { // error checking
            System.err.printf("Fragment Shader compilation : %s", GL20.glGetShaderInfoLog(fragmentShaderID));
            System.exit(1);
        }

        // attach shaders to program
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);

        // link & validate program
        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) { // error checking
            System.err.printf("Program linking : %s", GL20.glGetProgramInfoLog(programID));
            System.exit(1);
        }
        
        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) { // error checking
            System.err.printf("Program validation : %s", GL20.glGetProgramInfoLog(programID));
            System.exit(1);
        }

        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
    }

    /**
     * Bind shader to GL.
     */
    public void bind() {
        GL20.glUseProgram(programID);
    }

    /**
     * Unbind shader.
     */
    public void unbind() {
        GL20.glUseProgram(programID);
    }

    public void destroy() {
        GL20.glDeleteProgram(programID);
    }
}
