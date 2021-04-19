package com.sevenmap.ui.elements;

import static org.junit.Assert.assertEquals;

import com.sevenmap.ui.math.Vector3f;

import org.junit.Test;

public class CameraTest {
    @Test
    public static void main(String[] args){
        //Declaring variables
        float x,y,z,a,b,c,o;
        x=3.5f;
        y=2.5f;
        z=1.5f;
        a= 2f;
        b= 3f;
        c= 4f;
        o = 0f;
        //Declaring vectors
        Vector3f v = new Vector3f(x,y,z);
        Vector3f v1 = new Vector3f(a,b,c);
        Vector3f v2 = new Vector3f(o,o,o);
        Vector3f v3 = new Vector3f(o,o,o);
        //Declaring Cameras
        Camera cam =new Camera(v , v1);
        Camera cam_1 =new Camera(v2 , v3);
        //Testing the camera's constructors
        assertEquals(cam.rotation, v1);
        assertEquals(cam.position, v);
        assertEquals(cam_1.rotation, v3);
        assertEquals(cam_1.position, v2);
        



    }
    
}
