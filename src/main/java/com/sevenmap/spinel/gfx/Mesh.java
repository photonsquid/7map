package com.sevenmap.spinel.gfx;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
    private Vertex[] vertices;
    private int[] indices;
    private Material material;
    /**Vertex array object */
    private int vao; 
    /**Position buffer object */
    private int pbo;
    /**Indices buffer object */
    private int ibo;
    /**Color buffer object */
    private int cbo;
    /**Texture buffer object */
    private int tbo;
    
    
    /**
     * Define a mesh with no material.
     * @param vertices an array containing all the vertices of the mesh
     * @param indices an array of indices which is used to link the vertices 
     * and form triangles
     * <p>
     * structure : 
     * [vertex_index1_triangle1, vertex_index2_triangle1, vertex_index3_triangle1,
     * vertex_index1_triangle2, vertex_index2_triangle2, vertex_index3_triangle2,
     * etc...]
     * </p>
     * the vertex indices depend on the order of the vertices provided in the first place
     */
    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }
    
    /**
     * Define a mesh with no material.
     * @param vertices a List of {@link Vertex} objects containing all the vertices of the mesh
     * @param indices a List of Integer ojects containing the indices which are used to link the vertices 
     * @see #Mesh(Vertex[], int[])
     */
    public Mesh(List<Vertex> vertices, List<Integer> indices) {
        this.vertices = vertices.toArray(new Vertex[0]);
        this.indices = indices.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Define a mesh with a material.
     * @param vertices an array containing all the vertices of the mesh
     * @param indices an array of indices which is used to link the vertices 
     * and form triangles
     * <p>
     * structure : 
     * [vertex_index1_triangle1, vertex_index2_triangle1, vertex_index3_triangle1,
     * vertex_index1_triangle2, vertex_index2_triangle2, vertex_index3_triangle2,
     * etc...]
     * </p>
     * the vertex indices depend on the order of the vertices provided in the first place
     * @param material a material which may contain a texture
     */
    public Mesh(Vertex[] vertices, int[] indices, Material material) {
        this(vertices, indices);
        this.material = material;
    }

    /**
     * Define a mesh with a material.
     * @param vertices a List of {@link Vertex} objects containing all the vertices of the mesh
     * @param indices a List of Integers containing the indices which are used to link the vertices 
     * @param material a material which may contain a texture
     * @see #Mesh(Vertex[], int[], Material)
     */
    public Mesh(List<Vertex> vertices, List<Integer> indices, Material material) {
        this(vertices, indices);
        this.material = material;
    }

    // getters and setters

    public int getVAO() {return vao;}
    public int getPBO() {return pbo;}
    public int getIBO() {return ibo;}
    public int getCBO() {return cbo;}
    public int getTBO() {return tbo;}
    public Material getMaterial() {return material;}
    public Vertex[] getVertices() {return vertices;}
    public int[] getIndices() {return indices;}


    // other methods

    /**
     * Generate the mesh from its vertices.
     */
    public void build() {
        if (material != null) {
            material.create();
        }

        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        // position
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3]; // write to array first, then convert to opengl format
        for (int i = 0; i < vertices.length; i++) {
            positionData[3 * i] = vertices[i].getPos().getX();
            positionData[3 * i + 1] = vertices[i].getPos().getY();
            positionData[3 * i + 2] = vertices[i].getPos().getZ();
        }
        positionBuffer.put(positionData).flip(); // write to FloatBuffer object

        pbo = storeData(positionBuffer, 0, 3); // store data for the shaders to use

        // color
        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] colorData = new float[vertices.length * 3]; // write to array first, then convert to opengl format
        for (int i = 0; i < vertices.length; i++) {
            colorData[3 * i] = vertices[i].getColor().getX();
            colorData[3 * i + 1] = vertices[i].getColor().getY();
            colorData[3 * i + 2] = vertices[i].getColor().getZ();
        }
        colorBuffer.put(colorData).flip(); // write to FloatBuffer object

        cbo = storeData(colorBuffer, 1, 3); // store data for the shaders to use

        // texture coord
        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
        float[] textureData = new float[vertices.length * 2]; // write to array first, then convert to opengl format
        for (int i = 0; i < vertices.length; i++) {
            textureData[2 * i] = vertices[i].getTexCoord().getX();
            textureData[2 * i + 1] = vertices[i].getTexCoord().getY();
        }
        textureBuffer.put(textureData).flip(); // write to FloatBuffer object

        cbo = storeData(textureBuffer, 2, 2); // store data for the shaders to use



        // indices
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo); // bind buffer (cf https://youtu.be/1F9shq6KubY)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW); // edit buffer data
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0); // unbind before next usage
    }

    /**
     * Store the data contained in the provided buffer.
     * @param buffer FloatBuffer object containing the data
     * @param index the index at which the data should be stored
     * @param size the size of the data inside the buffer
     * @return buffer ID
     */
    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID); // bind to buffer
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); // store
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0); // cf shader code
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); // unbind

        return bufferID;
    }

    /**
     * Free all the buffers, the vertex array object and the material, 
     * if present.
     */
    public void destroy() {
        GL15.glDeleteBuffers(pbo);
        GL15.glDeleteBuffers(cbo);
        GL15.glDeleteBuffers(ibo);
        GL15.glDeleteBuffers(tbo);
        GL30.glDeleteVertexArrays(vao);
        if (material != null){
            material.destroy();
        }
    }
}
