package com.sevenmap.spinel.elements;

import static org.junit.Assert.assertEquals;

//import com.sevenmap.spinel.elements.Camera;
import com.sevenmap.spinel.math.Vector3f;

import org.junit.Before;
import org.junit.Test;

public class CameraTest {

    private Camera cam0;
    private Camera cam1;
    private Vector3f pos0, rot0, pos1, rot1;


    @Before
    public void setUp() {
        
        // Declaring vectors
        pos0 = new Vector3f(3.5f, 2.5f, 1.5f);
        rot0 = new Vector3f(2.0f, 3.0f, 4.0f);
        pos1 = new Vector3f(0.1f, 5.0f, 0.4f);
        rot1 = new Vector3f(7.0f, 0.5f, 6.1f);

        // Declaring Cameras
        cam0 = new Camera(pos0 , rot0, 1920f/1080f);
        cam1 = new Camera(pos1 , rot1, 1920f/1080f);
    }
    
    @Test
    public void testPos(){
        assertEquals(cam0.getPos(), pos0);
        assertEquals(cam1.getPos(), pos1);
    }

    @Test
    public void testRot() {
        assertEquals(cam1.getRot(), rot1);
        assertEquals(cam0.getRot(), rot0);
    }
    
}
