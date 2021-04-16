package ui.gfx;

import org.lwjgl.opengl.GL30;

import ui.Window;
import ui.elements.Camera;
import ui.elements.Item;
import ui.math.Matrix4f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class Renderer {
    private Shader shader;
    private Window window;

    /**
     * Create a new Renderer object.
     * @param shader the shader which will be applied on each render call.
     */
    public Renderer(Window window, Shader shader) {
        this.shader = shader;
        this.window = window;
    }

    /**
     * Render an element.
     * @param element
     */
    public void render(Item element, Camera camera) {
        // this needs some rework
        GL30.glBindVertexArray(element.getMesh().getVAO());
        GL20.glEnableVertexAttribArray(0); // shader data passing
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element.getMesh().getIBO());
        
        GL13.glActiveTexture(GL13.GL_TEXTURE0); 
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, element.getMesh().getMaterial().getTextureID()); // bind texture
        
        shader.bind(); // bind before drawing 
        shader.setUniform("model", Matrix4f.transform(element.getPos(), element.getRot(), element.getScale()));
        shader.setUniform("view", Matrix4f.view(camera.getPos(), camera.getRot()));
        shader.setUniform("projection", window.getProjector());
        GL11.glDrawElements(GL11.GL_TRIANGLES, element.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind(); // unbind after drawing (ready for the next shader to be applied)
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
}
