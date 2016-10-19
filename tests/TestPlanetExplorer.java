import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {
	public TestPlanetExplorer () {
		PlanetExplorer explorer = new PlanetExplorer(0,0,null);
	}

	@Test
	public void test_startingPosition_x0y0N() throws PlanetExplorerException {
		
		
		assertEquals("(0,0,E)", explorer.executeCommand("r"));
	}
	
	@Test
	public void test_leftTurnFromStartingPositionWorks() throws PlanetExplorerException {
		
	}
}
