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
	}
	
	@Test
	public void facingNorth_notMovingHorizontally() {
		assertFalse(explorer.movingHorizontally());
	}

	@Test
	public void facingSouth_movingDiagonally() throws PlanetExplorerException {
		explorer.executeCommand("rr");
		assertTrue(explorer.movingDiagonally());
	}
	
	@Test
	public void facingSouth_notMovingHorizontally() throws PlanetExplorerException {
		explorer.executeCommand("rr");
		assertFalse(explorer.movingHorizontally());
	}
	
	@Test
	public void facingEast_movingHorizontally() throws PlanetExplorerException {
		explorer.executeCommand("r");
		assertTrue(explorer.movingHorizontally());
	}

	@Test
	public void facingWest_movingHorizontally() throws PlanetExplorerException {
		explorer.executeCommand("l");
		assertTrue(explorer.movingHorizontally());
	}
	
	@Test
	public void moveTwoStepsToEast_position_x2y0() throws PlanetExplorerException {
		assertEquals("(2,0,E)", explorer.executeCommand("rff"));
	}
	
	@Test
	public void moveTwoStepsToEastAndOneBack_position_x1y0() throws PlanetExplorerException {
		assertEquals("(2,0,E)", explorer.executeCommand("rffb"));
	}
}
