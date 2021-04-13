package ui.gfx;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
    private Vertex[] vertices;
    private int[] indices;
    /**Vertex array object */
    private int vao; 
    /**Position buffer object */
    private int pbo;
    /**Indices buffer object */
    private int ibo;

    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    // getters and setters

    public int getVAO() {return vao;}
    public int getPBO() {return pbo;}
    public int getIBO() {return ibo;}
    public Vertex[] getVertices() {return vertices;}
    public int[] getIndices() {return indices;}


    // other methods
    /**
     * Generate the mesh from its vertices.
     */
    public void build() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3]; // write to array first, then convert to opengl format
        for (int i = 0; i < vertices.length; i++) {
            positionData[3 * i] = vertices[i].getPos().getX();
            positionData[3 * i + 1] = vertices[i].getPos().getY();
            positionData[3 * i + 2] = vertices[i].getPos().getY();
        }
        positionBuffer.put(positionData).flip(); // write to FloatBuffer object

        pbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo); // bind to pbo
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW); // store
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0); // cf shader code
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); // unbind

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo); // bind buffer (cf https://youtu.be/1F9shq6KubY)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW); // edit buffer data
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0); // unbind before next usage
    }
}
