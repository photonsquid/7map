package com.sevenmap.ui.elements;

import static org.junit.Assert.assertEquals;

import com.sevenmap.ui.math.Vector3f;

import org.junit.Before;
import org.junit.Test;

public class CameraTest {

    private Camera cam0;
    private Camera cam1;
    private Vector3f v0, v1, v2, v3;


    @Before
    public void init() {
        // Declaring vectors
        v0 = new Vector3f(3.5f, 2.5f, 1.5f);
        v1 = new Vector3f(2.0f, 3.0f, 4.0f);
        v2 = new Vector3f(0.0f, 0.0f, 0.0f);
        v3 = new Vector3f(0.0f, 0.0f, 0.0f);

        // Declaring Cameras
        cam0 = new Camera(v0 , v1);
        cam1 = new Camera(v2 , v3);
    }
    
    @Test
    public void testPos(){
        assertEquals(cam0.position, v0);
        assertEquals(cam1.position, v2);
    }

    @Test
    public void testRot() {
        assertEquals(cam1.rotation, v3);
        assertEquals(cam0.rotation, v1);
    }
    
}
