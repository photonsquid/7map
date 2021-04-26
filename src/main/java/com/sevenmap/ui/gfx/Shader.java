package com.sevenmap.ui.gfx;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import com.sevenmap.ui.math.Matrix4f;
import com.sevenmap.ui.math.Vector2f;
import com.sevenmap.ui.math.Vector3f;
import com.sevenmap.ui.utils.FileUtils;

public class Shader {
    private String vertexFile;
    private String fragmentFile;

    /**pointers to actual elements */
    private int vertexShaderID;
    private int fragmentShaderID;
    private int programID;

    public Shader(String vertexPath, String fragmentPath) {
        vertexFile = FileUtils.loadFile(vertexPath);
        fragmentFile = FileUtils.loadFile(fragmentPath);
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
    }

    /**
     * Get the uniform's location.
     */
    public int getUniformLoc(String name) {
        return GL20.glGetUniformLocation(programID, name);
    }

    public void setUniform(String name, float value) {
        GL20.glUniform1f(getUniformLoc(name), value);
    }
    public void setUniform(String name, int value) {
        GL20.glUniform1i(getUniformLoc(name), value);
    }
    public void setUniform(String name, boolean value) {
        GL20.glUniform1i(getUniformLoc(name), value ? 1 : 0);
    }
    public void setUniform(String name, Vector2f value) {
        GL20.glUniform2f(getUniformLoc(name), value.getX(), value.getY());
    }
    public void setUniform(String name, Vector3f value) {
        GL20.glUniform3f(getUniformLoc(name), value.getX(), value.getY(), value.getZ());
    }
    public void setUniform(String name, Matrix4f value) {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4f.SIZE * Matrix4f.SIZE);
        matrix.put(value.getContent()).flip();
        GL20.glUniformMatrix4fv(getUniformLoc(name), true, matrix);
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
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }
}
