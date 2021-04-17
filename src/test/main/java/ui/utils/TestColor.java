import junit.Assert.assertTrue;

import junit.Before;
import junit.Test;

/** Programme de test de la classe Color.
  * @author	@seba1204
  * @version	1.0
  */
public class TestColor {
   private Color hexColor;
    private GLFWWindowSizeCallback sizeCB;
   
   @Before
	public void setUp() {
		hexColor = new Color("#FFFFFF");
	}
   
	@Test
	public void testInitialisation() {
		assertTrue(hexColor != null);
	}
}
