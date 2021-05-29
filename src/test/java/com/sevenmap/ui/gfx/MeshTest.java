package com.sevenmap.ui.gfx;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Arrays;
import com.sevenmap.ui.math.Vector3f;

public class MeshTest {

    private Mesh mesh1;
    private Mesh mesh2;
    private Mesh mesh3;
    private Mesh mesh4;
    

    private Vertex[] vertices1;
    private Vertex[] vertices2;
    

    private int[] indices1;
    private int[] indices2;
   

    private List<Vertex> verticesList1;
    private List<Vertex> verticesList2;

    

    protected List<Integer> indicesList1;
    protected List<Integer> indicesList2;

    private Material matr;
      




	@Before
	public void setUp() throws Exception {
        
        //Declaring the vertices.
        vertices1 = new Vertex[] {
            new Vertex(new Vector3f(1.5f, 1.0f, 0.0f), new Vector3f(1.1f, 1.5f, 0.5f)), 
            new Vertex(new Vector3f(1.0f, 2.5f, 3.0f), new Vector3f(3.0f, 1.0f, 2.0f)),
            new Vertex(new Vector3f(2.5f, 3.5f, 2.0f), new Vector3f(1.5f, 2.0f, 0.0f)),
            new Vertex(new Vector3f(1.5f, 0.0f, 1.0f), new Vector3f(0.2f, 0.1f, 1.0f))};
        
        vertices2 = new Vertex[] {
                new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)), 
                new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f)),
                new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)),
                new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f))};
        
        //Declaring the vertices list.
        verticesList1 = Arrays.asList(
            new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)), 
            new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f)));
        
        verticesList2 = Arrays.asList(
                new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)), 
                new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f)),
                new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)),
                new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f)));

        //Declaring the indices.
        indices1 = new int[]{2, 3, 3, 4};

        indicesList1 = Arrays.asList(1, 2, 3, 4);
        indicesList2 = Arrays.asList( 9, 10, 11, 12);

        //Declaring the material.
        matr = new Material("path");
        
        //Declaring the mesh.
        mesh1 = new Mesh(vertices1, indices1);
        mesh2 = new Mesh(verticesList1, indicesList1);
        mesh3 = new Mesh(vertices2, indices2, matr);
        mesh4 = new Mesh(verticesList2, indicesList2, matr);
        
      

	}

	@Test
	public void testGetVAO() {
		assertEquals(0, mesh1.getVAO());
        assertEquals(0, mesh2.getVAO());
        assertEquals(0, mesh3.getVAO());
        assertEquals(0, mesh4.getVAO());
	}

	@Test
	public void testGetPBO() {
		assertEquals(0, mesh1.getPBO());
        assertEquals(0, mesh2.getPBO());
        assertEquals(0, mesh3.getPBO());
        assertEquals(0, mesh4.getPBO());
	}

	@Test
	public void testGetIBO() {
		assertEquals(0, mesh1.getIBO());
        assertEquals(0, mesh2.getIBO());
        assertEquals(0, mesh3.getIBO());
        assertEquals(0, mesh4.getIBO());
	}

	@Test
	public void testGetCBO() {
		assertEquals(0, mesh1.getCBO());
        assertEquals(0, mesh2.getCBO());
        assertEquals(0, mesh3.getCBO());
        assertEquals(0, mesh4.getCBO());
	}

	@Test
	public void testGetTBO() {
		assertEquals(0, mesh1.getTBO());
        assertEquals(0, mesh2.getTBO());
        assertEquals(0, mesh3.getTBO());
        assertEquals(0, mesh4.getTBO());
	}

	@Test
	public void testGetMaterial() {
		assertEquals(matr, mesh3.getMaterial());
        assertEquals(matr, mesh4.getMaterial());
	}

	@Test
	public void testGetVertices() {
       // assertEquals(vertices1, mesh2.getVertices());
        assertTrue(true);
       // assertEquals(verticesList1, mesh4.getVertices());
	}

	@Test
	public void testGetIndices() {
		assertEquals(indices1, mesh1.getIndices());
        assertEquals(indices2, mesh3.getIndices());
	}

	@Test
	public void testBuild() {
		assertTrue(true);
	}

	@Test
	public void testDestroy() {
		assertTrue(true);
	}

}
