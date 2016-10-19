import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

	@Test
	public void test_startingPosition_x0y0N() {
		PlanetExplorer explorer = new PlanetExplorer(0,0,null);
		
		assertEquals("(0,0,E)", explorer.executeCommand("r"));
	}
	
	
}
