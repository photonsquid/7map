package com.sevenmap.ui.maths;

import static org.junit.Assert.assertEquals;



import com.sevenmap.ui.math.Vector2f;

import org.junit.Before;
import org.junit.Test;

public class Vector2fTest{

    private Vector2f vec1, vec2, vec3, vec4;
    private float x, y, z, t;

    @Before
    public void setUp(){
        x = 1.0f;
        y = 2.0f;
        z = 3.0f;
        t = 4.0f;

        vec1 = new Vector2f(x, y);
        vec2 = new Vector2f(y, z);
        vec3 = new Vector2f(z, t);
        vec4 = new Vector2f(2.0f, 0.0f);

        
    }

    @Test
    public void gettersSettersTest(){

        assertEquals(new Vector2f(vec1.getX(),vec1.getY()), vec1);
    
        
         // assertEquals(new Vector2f(0.2f, 2.0f), vec1.setX(0.2f));
        // assertEquals(vec1.setY(0.2f), new Vector2f(0.2f, 0.2f));
       // assertEquals(vec2.set(0.5f, 3.2f), new Vector2f(0.5f, 3.2f));
      //  assertEquals(vec3.set(new Vector2f(3.0f, 2.3f)), new Vector2f(3.0f, 2.3f));
    }
   
    @Test
    public void mathOperationsTest(){
        assertEquals(vec1.add(vec2), new Vector2f(3.0f, 5.0f));
        assertEquals(vec3.sub(vec2), new Vector2f(1.0f, 1.0f));
        //assertEquals(vec1.dot(vec2), 8.0f);
       // assertEquals(vec2.norm(), 13.0f);
        assertEquals(vec1.multiply(vec4),new Vector2f(2.0f, 0.0f));
        assertEquals(vec2.multiply(1.0f), vec2);
        assertEquals(vec2.divide(vec1), new Vector2f(2.0f, 1.5f));
        assertEquals(vec4.normalize(), new Vector2f(1.0f, 0.0f));
        
    }







}