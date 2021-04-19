package com.sevenmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;   

public abstract class TestFile {
    @Before
    public void init() {
        // TODO
    }

    @org.junit.Test
    public void firstTest(Object other) {
        assertEquals(this,other);
    }

    
}
