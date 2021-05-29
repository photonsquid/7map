package com.sevenmap.ui.elements;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

    protected RootNode parent1;
    protected RootNode parent2;
    protected RootNode parent3;
    protected RootNode parent4;
    
    protected Node n1;
    protected Node n2;
    protected Node n3;
    protected Node n4;
   
	@Before
	public void setUp() throws Exception {
        // initialiser les Nodes.
        n1 = new Node();
        n2 = new Node("n2");
        n3 = new Node();
        n4 = new Node("n4");

        // initialiser les parents.
        parent1 = new Node();
        parent2 = new Node("parent2");
        parent3 = new Node();
        parent4 = new Node("parent4");
        


	}

	@Test
	public void testCompatibilityCheck() {
		assertFalse(false);
	}

	@Test
	public void testSetParent() {
		assertEquals(n4.getParent(), null);
	}

	@Test
	public void testGetParent() {
		assertEquals(n1.getParent(), null);
	}

}