package com.sevenmap.spinel.maths;

import static org.junit.Assert.assertEquals;

import com.sevenmap.spinel.math.Vector3f;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
//import org.lwjgl.system.CallbackI.B;

public class Vector3fTest {
    private Vector3f v1, v2;
    private float a, b, c;

    @Before
    public void init() {
        a = 2.0f;
        b = 3.0f;
        c = 5.0f;

        v1 = new Vector3f(a, b, c);
        v2 = new Vector3f(b, c, a);
    }

    @Ignore
    @Test
    public void multiplyTest() {
        assertEquals(v1.dot(v2), new Vector3f(6.0f, 15.0f, 10.0f));
    }

    @Ignore
    @Test
    public void dotTest() {
        assertEquals(v1.dot(v2), 31.0f);
    }

    @Ignore
    @Test
    public void divideTest() {
        assertEquals(v1.divide(v2), 2 / 3 + 3 / 5 + 5 / 2);
    }

    @Test
    public void normalizeTest() {
        assertEquals(v1.normalize(), new Vector3f(a / v1.norm(), b / v1.norm(), c / v1.norm()));
    }

}
