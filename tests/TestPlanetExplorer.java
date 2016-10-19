import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {
	private PlanetExplorer explorer;
	
	public TestPlanetExplorer () {
		explorer = new PlanetExplorer(0,0,null);
	}

	@Test
	public void test_startingPosition_x0y0N() {
		explorer
		assertEquals("(0,0,E)", explorer.getCurrentPosition());
		fail("not yet implemented");
	}
	
	@Test
	public void test_rightTurnFromStartingPositionWorks() throws PlanetExplorerException {
		assertEquals("(0,0,E)", explorer.executeCommand("r"));
	}
	
	@Test
	public void test_leftTurnFromStartingPositionWorks() throws PlanetExplorerException {
		fail("not yet implemented");
	}
}
