package ui.gfx;

import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class Renderer {
    private Shader shader;

    /**
     * Create a new Renderer object.
     * @param shader the shader which will be applied on each render call.
     */
    public Renderer(Shader shader) {
        this.shader = shader;
    }

    /**
     * Render a mesh.
     * @param mesh
     */
    public void renderMesh(Mesh mesh) {
        // this needs some rework
        GL30.glBindVertexArray(mesh.getVAO());
        GL20.glEnableVertexAttribArray(0); // shader data passing
        GL20.glEnableVertexAttribArray(1);        
        GL20.glEnableVertexAttribArray(2);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        
        GL13.glActiveTexture(GL13.GL_TEXTURE0); 
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID()); // bind texture
        
        shader.bind(); // bind before drawing 
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind(); // unbind after drawing (ready for the next shader to be applied)
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
}
