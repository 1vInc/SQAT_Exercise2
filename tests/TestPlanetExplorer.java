import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

	@Test
	public void test_startingPosition_x0y0N() {
		PlanetExplorer explorer = new PlanetExplorer(0,0,null);
		
		assertEquals("(0,0,E)", executeCommand("r"));
		fail("Not yet implemented");
	}
	
	
}
