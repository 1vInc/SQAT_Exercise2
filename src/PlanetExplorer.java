
// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID:
// Finish time:

import java.util.Vector;

public class PlanetExplorer {
	public static final String NORTH	= "N";
	public static final String EAST		= "E";
	public static final String SOUTH	= "S";
	public static final String WEST		= "W";

	private final int N_INDEX = 0;
	private final int E_INDEX = 1;
	private final int S_INDEX = 2;
	private final int W_INDEX = 3;
	
	private final int FACING_DIR_AMOUNT = 4;
	
	private int grid_x_len;
	private int grid_y_len;
	
	private String[] directions = {
			"N",
			"E",
			"S",
			"W",
	};
	
	private String[] obstaclePositions;
	
	private Vector<String> hit_obstacles;
	
	private int position_x; 
	private int position_y;
	private int current_facing;
	
	public PlanetExplorer(int x, int y, String obstacles){
	/*	x and y represent the size of the grid.
	 *  Obstacles is a String formatted as follows: "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white spaces. 
	 *  
		Example use: For a 100x100 grid with two obstacles at coordinates (5,5) and (7,8)
		PlanetExplorer explorer = new PlanetExplorer(100,100,"(5,5)(7,8)")  
		 
	 */
		if (null != obstacles)
			parseObstacles(obstacles);
		
		hit_obstacles = new Vector<String>();
		position_x = x;
		position_y = y;
		grid_x_len = 5;
		grid_y_len = 5;
		current_facing = N_INDEX;
	}
	
	public String executeCommand(String command) throws PlanetExplorerException {
		String result;
		/* The command string is composed of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Example: 
		 * The explorer is on a 100x100 grid at location (0, 0) and facing NORTH. 
		 * The explorer is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the format: "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)" 
		 * Where pos_x and pos_y are the final coordinates, facing is the current direction the explorer is pointing to (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered obstacles. No white spaces.
		 */
		
		
		
		for (int i = 0; i < command.length(); i++) {
			if (isTurn(command.charAt(i))) {
				handleTurn(command.charAt(i));
			} else {
				// move assumed if not turn
				handleMove(command.charAt(i));
			}
		}
		
		result = getCurrentPosition();
		
		return result;
	}
	
	private void parseObstacles(String obstr) {
		int next_end = -1;
		
		// find pos of next ")"
		do {
			for (int i = 0; i < obstr.length(); i++) {
				if (')' == obstr.charAt(i))
					next_end = i;
			}
			
			parseObstacle(obstr.substring(0, next_end));
			obstr = obstr.substring(0, next_end);
		} while (obstr.length() > 0);
		
		//System.out.println(obstr);
	}
	
	private void parseObstacle(String obstr) {
		//obstr = obstr.substring(3);
	}
	
	public void setGridXLen(int new_x_len) {
		grid_x_len = new_x_len;
	}
	
	public void setGridYLen(int new_y_len) {
		grid_y_len = new_y_len;
	}
	
	public String getCurrentPosition() {
		String ret = "";
		
		ret = "(";
		ret += position_x + "," + position_y + "," + directions[current_facing];
		ret += ")";
		
		if (!hit_obstacles.isEmpty()) {
			ret += obstacleString();
		}
		
		return ret;
	}
	
	private String obstacleString() {
		String ret = "";
		
		for (int i = 0; i < hit_obstacles.size(); i++) {
			ret += hit_obstacles.get(i);
		}
		
		return ret;
	}
	
	private boolean isTurn(char cmd) {
		if ('r' == cmd || 'l' == cmd)
			return true;
			
		return false;
	}
	
	
	private void handleTurn(char cmd) throws PlanetExplorerException {
		if ('r' == cmd) {
			current_facing = (current_facing + 1) % FACING_DIR_AMOUNT;
			return;
		}
		
		if ('l' == cmd) {
			current_facing = current_facing + (FACING_DIR_AMOUNT - 1) % FACING_DIR_AMOUNT;
			return;
		}
		
		throw new PlanetExplorerException("Command " + "(" + cmd + ")" +  " was not a turn command");
	}
	
	public boolean movingHorizontally() {
		if (WEST == directions[current_facing] || EAST == directions[current_facing])
			return true;

		return false;
	}
	
	public boolean movingDiagonally() {
		if (NORTH == directions[current_facing] || SOUTH == directions[current_facing])
			return true;
		
		return false;
	}
	
	private void handleMove(char cmd) throws PlanetExplorerException {
		if (movingHorizontally() && movingDiagonally())
			throw new PlanetExplorerException("Incorrect state. Cannot be simultaneously moving diagonally and horizontally");
		
		if (movingHorizontally()) {
			moveHorizontally(cmd);
		}
		
		if (movingDiagonally()) {
			moveDiagonally(cmd);
		}
	}
	
	private void moveHorizontally(char move_dir) {
		if (EAST == directions[current_facing] && 'f' == move_dir)
			increment_x();

		if (EAST == directions[current_facing] && 'b' == move_dir)
			decrement_x();
		
		if (WEST == directions[current_facing] && 'f' == move_dir)
			decrement_x();
			
		if (WEST == directions[current_facing] && 'b' == move_dir)
			increment_x();
	}
	
	private void moveDiagonally(char move_dir) {
		if (NORTH == directions[current_facing] && 'f' == move_dir)
			increment_y();

		if (NORTH == directions[current_facing] && 'b' == move_dir)
			decrement_y();
		
		if (SOUTH == directions[current_facing] && 'f' == move_dir)
			decrement_y();
			
		if (SOUTH == directions[current_facing] && 'b' == move_dir)
			increment_y();
	}
	
	private void increment_x() {
		if (!isObstructed((position_x + 1) % grid_x_len, position_y))
			position_x = (position_x + 1) % grid_x_len;
	}
	
	private void decrement_x() {
		if (!isObstructed((position_x + (grid_x_len - 1)) % grid_x_len, position_y))
			position_x = (position_x + (grid_x_len - 1)) % grid_x_len;
	}
	
	private void increment_y() {
		if (!isObstructed((position_y + 1) % grid_y_len, position_y))
			position_y = (position_y + 1) % grid_y_len;
	}
	
	private void decrement_y() {
		if (!isObstructed((position_y + (grid_y_len - 1)) % grid_y_len, position_y))
			position_y = (position_y + (grid_y_len - 1)) % grid_y_len;
	}
	
	private boolean isObstructed(int x, int y) {
		return false;
	}
	
	// facingNorth
	
	/*private void  moveForward() {
		
	}*/
}
