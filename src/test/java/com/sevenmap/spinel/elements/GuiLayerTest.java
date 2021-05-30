package com.sevenmap.spinel.elements;

import static org.junit.Assert.*;

import com.sevenmap.spinel.elements.gui.GuiLayer;

import org.junit.Before;
import org.junit.Test;

public class GuiLayerTest {

	private GuiLayer gl1;
	private GuiLayer gl2;

	@Before
	public void setUp() throws Exception {
		gl1 = new GuiLayer();
		gl2 = new GuiLayer("gl2");
	}

	@Test
	public void test() {
		assertTrue(true);
		// assertEquals(null, gl1.draw());
	}

}