package com.sevenmap.ui.gfx;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import com.sevenmap.ui.math.Vector3f;

import org.junit.Before;
import org.junit.Test;
public class VertexTest {
	
	private Vertex ver1;
	private Vertex ver2;
	private Vertex ver3;

	private Vector3f pos, pos1, pos2;
    private Vector3f color, color1;
    private Vector2f textureCoord1, textureCoord2;
    
    
    
    @Before
    public void setUp() {

        pos1 = new Vector3f(3.5f, 2.5f, 1.5f);
        pos2 = new Vector3f(2.0f, 3.0f, 4.0f);
        color = new Vector3f(0.0f, 0.0f, 0.0f);
        color1 = new Vector3f(0.0f, 0.0f, 0.0f);
        textureCoord1 = new Vector2f(0.5f, 0.0f);
        textureCoord2 = new Vector2f(0.5f, 0.5f);
        
        
      ver1 = new Vertex(pos, color);
      ver2 = new Vertex(pos1, color1, textureCoord1);
      ver3 = new Vertex(pos2, Color.blue, textureCoord2);
      
    }
    @Test
    public void testGetPos(){
        assertEquals(ver1.getPos(), pos);
        assertEquals(ver2.getPos(), pos1);
        assertEquals(ver3.getPos(), pos2);
    }
    @Test
    public void testGetColor(){
        assertEquals(ver1.getColor(), color);
        assertEquals(ver2.getColor(), Color.BLUE);
        assertEquals(ver3.getColor(), color2);
    }
    @Test
    public void testGetTexCoord() {
    	assertEquals(ver1.getTexCoord(), new Vector2f(0.0f, 0.0f));
    	assertEquals(ver2.getTexCoord(), textureCoord1);
    	assertEquals(ver3.getTexCoord(), textureCoord2);
    }
	
}
