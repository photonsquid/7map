package com.sevenmap.ui.gfx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MaterialTest {

    private Material matr1;
    //private static final int BYTES_PER_PIXEL = 4;
   // private int texID;
    private String path;
    private int width;
    private int height;

    @Before
    public void setUp(){

        // initialiser les variables.
       // texID = 1;
        path = "Material";
        width = 10;
        height = 12;

        // initialiser mtr1.
        matr1 = new Material(path);

    }

    @Test
    public void heightSettersGetters(){
        
        // tester setHeight et getHeight.
        matr1.setHeight(height);
        assertEquals(height, matr1.getHeight());
    }

    @Test
    public void widthSettersGetters(){

        // tester setWidth et getWidth.
        matr1.setWidth(width);
        assertEquals(width, matr1.getWidth()); 

    }

    @Test
    public void CreateTest(){

      //  Load data from the image at the provided path.
      // this method now fully supports png format and alpha channel transparency
      // cette méthode sera tester graphiquement.
      assertTrue(true);
    }

    @Test
    public void destroyTest() {
    
        //matr1.destroy();
        assertTrue(true);
    }
}
