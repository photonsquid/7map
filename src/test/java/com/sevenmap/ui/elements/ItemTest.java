package com.sevenmap.ui.elements;

import static org.junit.Assert.assertEquals;

import com.sevenmap.spinel.elements.Item;
import com.sevenmap.spinel.gfx.Mesh;
import com.sevenmap.spinel.gfx.Vertex;
import com.sevenmap.spinel.math.Vector3f;

import org.junit.Before;
import org.junit.Test;

public class ItemTest{
    private Vector3f v1,v2,v3;
    private Mesh mesh;
    private Vertex[] vertexTest;
    private Item i;
    private int[] ListeTest;
    

    @Before
    public void init(){
        //Declaring the Vector3f test
         v1 = new Vector3f(0.2f, 2.5f,1.2f);
         v2 = new Vector3f(6.2f, 5.0f, 3.2f);
         v3 = new Vector3f(3.6f, 8.4f, 6.1f);
        //Declaring the mesh
        vertexTest = new Vertex[] {
            new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)), // texture coordinates must be defined counter clockwise
            new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f))};
        ListeTest = new int[]{ 2, 3, 3, 4};
        mesh = new Mesh(vertexTest, ListeTest);
        //Declaring the item 
        i = new Item(v1, v2, v3, mesh);
    }
    @Test
    public void Construction_Test(){
      
      Vector3f pos = i.getPos();
      Vector3f rot = i.getRot();
      Vector3f sca = i.getScale();
      assertEquals("this is a good constructor-pos-", i.getPos(), pos);
      assertEquals("this is a good constructor-rot-", i.getRot(), rot);
      assertEquals("this is a good constructor-sca-", i.getScale(), sca);
      
      
  }
    @Test
    public void Set_Test(){
      i.setPos(0.2f, 2.5f,1.2f);
      i.setRot(6.2f, 5.0f, 3.2f);
      i.setScale(3.6f, 8.4f, 6.1f);
      assertEquals( i.getPos(), new Vector3f(0.2f, 2.5f,1.2f));
      assertEquals( i.getRot(), new Vector3f(6.2f, 5.0f, 3.2f));
      assertEquals( i.getScale(), new Vector3f(3.6f, 8.4f, 6.1f));
  }

   
}
