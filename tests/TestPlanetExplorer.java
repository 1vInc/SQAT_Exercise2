import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {
	private PlanetExplorer explorer;
	
	public TestPlanetExplorer () {
		explorer = new PlanetExplorer(0,0,null);
	}

	@Test
	public void test_startingPosition_x0y0N() {
		assertEquals("(0,0,N)", explorer.getCurrentPosition());
	}
	
	@Test
	public void test_rightTurnFromStartingPositionWorks() throws PlanetExplorerException {
		assertEquals("(0,0,E)", explorer.executeCommand("r"));
	}
	
	@Test
	public void test_leftTurnFromStartingPositionWorks() throws PlanetExplorerException {
		assertEquals("(0,0,W)", explorer.executeCommand("l"));
	}
	
	@Test
	public void facingNorth_movingDiagonally() {
		assertTrue(explorer.movingDiagonally());
		fail("not yet implemented");
	}

	@Test
	public void facingSouth_movingDiagonally() {
		fail("not yet implemented");
	}
	
	@Test
	public void facingEast_movingHorizontally() {
		fail("not yet implemented");
	}

	@Test
	public void facingWest_movingHorizontally() {
		fail("not yet implemented");
	}
}
