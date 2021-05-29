package com.sevenmap.ui.elements;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sevenmap.ui.math.Vector3f;
      
public class GeomNodeTest {

    protected Vector3f pos1;
    protected Vector3f pos2;
    protected Vector3f pos3;
    protected Vector3f pos4;
    protected Vector3f pos5;
    protected Vector3f pos6;
    protected Vector3f pos7;
    protected Vector3f pos8;
    protected Vector3f pos9;
    protected Vector3f pos10;

    protected Vector3f rot1;
    protected Vector3f rot2;
    protected Vector3f rot3;
    protected Vector3f rot4;
    protected Vector3f rot5;
    protected Vector3f rot6;
    protected Vector3f rot7;
    protected Vector3f rot8;
    protected Vector3f rot9;
    protected Vector3f rot10;
    
    protected GeomNode n1;
    protected GeomNode n2;
    protected GeomNode n3, n4, n5, n6, n7, n10;
	@Before
	public void setUp() throws Exception {
        // initialize positions.
        pos1 = new Vector3f(1.0f, 1.0f, 1.0f);
        pos2 = new Vector3f(2.0f, 2.0f, 2.0f);
        pos3 = new Vector3f(3.0f, 3.0f, 3.0f);
        pos4 = new Vector3f(4.0f, 4.0f, 4.0f);
        pos5 = new Vector3f(5.0f, 5.0f, 5.0f);
        pos6 = new Vector3f(6.0f, 6.0f, 6.0f);
        pos7 = new Vector3f(7.0f, 7.0f, 7.0f);
        pos8 = new Vector3f(8.0f, 8.0f, 8.0f);
        pos9 = new Vector3f(9.0f, 9.0f, 9.0f);
        pos10 = new Vector3f(10.0f, 10.0f, 10.0f);
       
        // initialize rotations.
        rot1 = new Vector3f(1.0f, 1.0f, 1.0f);
        rot2 = new Vector3f(2.0f, 2.0f, 2.0f);
        rot3 = new Vector3f(3.0f, 3.0f, 3.0f);
        rot4 = new Vector3f(4.0f, 4.0f, 4.0f);
        rot5 = new Vector3f(5.0f, 5.0f, 5.0f);
        rot6 = new Vector3f(6.0f, 6.0f, 6.0f);
        rot7 = new Vector3f(7.0f, 7.0f, 7.0f);
        rot8 = new Vector3f(8.0f, 8.0f, 8.0f);
        rot9 = new Vector3f(9.0f, 9.0f, 9.0f);
        rot10 = new Vector3f(10.0f, 10.0f, 10.0f);

        // initialize nodes.
        n1 = new GeomNode(pos1, rot1);
        n2 = new GeomNode(pos3, rot3);
        n3 = new GeomNode(pos3, rot3);
        n4 = new GeomNode(pos4, rot4, "Node n4");
        n5 = new GeomNode(pos5, rot5, "Node n5");
        n6 = new GeomNode(pos6, rot6, "Node n6");
        n7 = new GeomNode(pos7, rot7, "Node n7");
        
	}

	@Test
    public void gettersTest(){
        // getPos.
        assertEquals(n1.getPos(), pos1);
        assertEquals(n5.getPos(), pos5);
        assertEquals(n7.getPos(), pos7);
        
        // getRot.
        assertEquals(n5.getRot(), rot5);
        assertEquals(n2.getRot(), rot3);
    }

	@Test
    public void settersTest(){
        // setPos.
        n1.setPos(1.1f, 1.5f, 1.0f);
        assertEquals(new Vector3f(1.1f, 1.5f, 1.0f), n1.getPos());

        n2.setPos(new Vector3f(1.0f, 1.0f, 1.0f));
        assertEquals(new Vector3f(1.0f, 1.0f, 1.0f), n2.getPos() );

        // setRot.
        n1.setRot(0.1f, 0.5f, 1.0f);
        assertEquals(new Vector3f(0.1f, 0.5f, 1.0f),n1.getRot() );

        n2.setRot(new Vector3f(1.0f, 1.0f, 1.0f));
        assertEquals(new Vector3f(1.0f, 1.0f, 1.0f),n2.getRot());

	}

}
