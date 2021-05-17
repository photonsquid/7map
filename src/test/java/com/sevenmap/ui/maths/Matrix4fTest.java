package com.sevenmap.ui.maths;

//import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.sevenmap.spinel.math.Matrix4f;
import com.sevenmap.spinel.math.Vector3f;

import org.junit.Before;
import org.junit.Test;
//import org.lwjgl.system.CallbackI.I;

public class Matrix4fTest {
    
    private Matrix4f matrixTest;
    private Matrix4f TranslationMatrixTest, RotationMatrixTest;
    private float a , o, a_rot, b_rot, cosx ;
    private float[] listeTest, translationContent, rotationContent;
    private Vector3f translation, rotation;
    @Before
    public void init(){
        a = 2.0f;
        o = 0.0f;
        cosx = (float) Math.sqrt(2);
        a_rot = (a*a) * (1.0f - cosx);
        b_rot = a * cosx;
        for(int i=1 ; i<=4 ; i++){
            matrixTest.set(i, i, a);
        }
        float[] listeTest = {a, o, o, o, o, a, o, o, o, o, a, o, o, o, o, a};
        translationContent = new float[] {1.0f, o, o, a, o, 1.0f ,o , a, o, o, 1.0f, a, o, o, o, 1.0f};
        rotationContent = new float[] {a_rot + cosx, a_rot - b_rot, a_rot + b_rot, o, 
                                        a_rot + b_rot, a_rot + cosx, a_rot - b_rot, o, 
                                        a_rot - b_rot, a_rot + b_rot, a_rot + cosx, o, 
                                        o, o, o, 1.0f};
        TranslationMatrixTest.setContent(translationContent);
        RotationMatrixTest.setContent(rotationContent);
        translation = new Vector3f(a, a, a);
        rotation = new Vector3f(a, a, a);
    }

    @Test
    public void setTest(){
        float m1_1 = matrixTest.get(1, 1);
        assertTrue(m1_1 == a);
        
    }

    @Test
    public void getContentTest() {
        assertEquals(matrixTest.getContent(), listeTest);
    }

    @Test
    public void translateTest() {
        assertEquals(Matrix4f.translate(translation), TranslationMatrixTest );
    }

    @Test
    public void rotateTest() {
        assertEquals(Matrix4f.rotate((float) Math.PI/4, rotation), RotationMatrixTest);
    }

}
