import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.lang.model.type.PrimitiveType;
import javax.swing.ImageIcon;


public class Monster extends GameObject{

	private ArrayList <Point> route, temp_route;
	private Image monster;
	char right;
	char left;
	char up;
	char down;
	char[][] grid;
	int endRow, endCol, temp_index;
	GameEngine ge;
	Point currentTile;
	int currentMove;
	ArrayList<ArrayList<Point>> recordedRoutes;
	TimerTask tasknew;
	Timer timer;
	MoveFlag mf = new MoveFlag();
	private static Monster monsterInstance = null;
	
	private Monster(final GameEngine ge){

		route = new ArrayList<Point>();
		monster = new ImageIcon("earthMonster.png").getImage();
		height = 40;
		width = 40;
		x = 40;
		y = 120;
		currentTile = new Point();
		rectangle.x = x;
		rectangle.y = y;
		currentTile.x = (int)x/40;
		currentTile.y = (int)y/40;

		this.ge = ge;
		grid = new char[ge.getMap().length][ge.getMap()[0].length];
		route = new ArrayList<Point>();	
		recordedRoutes = new ArrayList<ArrayList<Point>>();
		move();

	}
	
	public static Monster getInstance(GameEngine ge){
		if(monsterInstance == null)
			monsterInstance = new Monster(ge);
		return monsterInstance;
	}
	
	public char toChar(){
		return 'O';
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(monster, currentTile.x*40, currentTile.y*40, null);

	}

	/*public void print_grid () {

		for (int row=0; row < grid.length; row++) {
			for (int column=0; column < grid[row].length; column++)
				System.out.print (grid[row][column]);
			System.out.println();
		}
	}*/

	public boolean solve (int row, int column) {

		boolean done = false;
		if (valid (row, column)) {

			grid[row][column] = '*';  // cell has been tried
			if (row == endRow && column == endCol)
				done = true;  // maze is solved
			else {
				done = solve (row+1, column);  // down
				if (!done)
					done = solve (row, column+1);  // right
				if (!done)
					done = solve (row-1, column);  // up
				if (!done)
					done = solve (row, column-1);  // left
			}
			if (done){  // part of the final path
				grid[row][column] = '+';
				route.add(new Point(column, row));
			}
		}
		return done;

	} 

	private boolean valid (int row, int column) {

		boolean result = false;

		// check if cell is in the bounds of the matrix
		if (row >= 0 && row < grid.length &&
				column >= 0 && column < grid[0].length)

			//  check if cell is not blocked and not previously tried
			if (grid[row][column] == '1' || grid[row][column] == 'M' || grid[row][column] == 'O')
				result = true;

		return result;

	}

	public Point findStart() {

		for (int row=0; row < grid.length; row++) {
			for (int column=0; column < grid[row].length; column++){
				if (grid[row][column] == 'O') {
					return new Point(row, column);
				}
			}
		}
		return null;
	}

	public void createMaze(char[][] map){
		for (int row=0; row < grid.length; row++) {
			for (int col=0; col < grid[row].length; col++){
				if(map[row][col] == 'R')
					grid[row][col] = '1';
				else
					grid[row][col] = map[row][col];
			}
		}
	}

	public Point findMiner(){
		for (int row=0; row < grid.length; row++) {
			for (int column=0; column < grid[row].length; column++){
				if (grid[row][column] == 'M') {
					endRow = row;
					endCol = column;
					return new Point(endRow, endCol);
				}
			}
		}
		return null;
	}
	public boolean startSolving(){
		Point temp_start = findStart();
		route.clear();
		findMiner();
		return solve(temp_start.x, temp_start.y);
	}

	public Point getCurrentTile(){
		return currentTile;
	}

	public ArrayList<Point> getRoute(){
		return route;
	}

	public void move(){
		tasknew = new TimerTask() {

			@Override
			public void run() {
				if(ge.getMiner().isFirstMove()){
					createMaze(ge.getMaze());
					if(findStart() == null){
						createMaze(ge.getMaze());
					}
					if(currentMove == 0)
						mf.nextMove = true;
					if(mf.nextMove || mf.firstTime){
						route.clear();
						createMaze(ge.getMaze());

						startSolving();
						//print_grid();

						if(recordedRoutes.size() > 0){
							for(int i = 0; i < route.size(); i++){
								if(route.get(i).x != recordedRoutes.get(recordedRoutes.size()-1).get(i).x 
										|| route.get(i).y != recordedRoutes.get(recordedRoutes.size()-1).get(i).y){
									recordedRoutes.add(route);
									break;
								}
							}
						}else{
							recordedRoutes.add(route);
						}

						if(recordedRoutes.size() > 2){
							if(recordedRoutes.get(recordedRoutes.size()-1).size() > recordedRoutes.get(recordedRoutes.size()-2).size())
								temp_index = recordedRoutes.size()-2;
							else
								temp_index = recordedRoutes.size()-1;
						}else{
							temp_index = recordedRoutes.size()-1;
						}
						temp_route = recordedRoutes.get(temp_index);
						
						currentMove = temp_route.size()-1;
						mf.firstTime = false;
						mf.nextMove = false;
					} 
					if(currentMove > 0){
						try{
							currentMove--;
							currentTile.x = temp_route.get(currentMove).x;
							currentTile.y = temp_route.get(currentMove).y;
						}catch(Exception e){
							System.out.println("EXCEPTION " + e );

						}finally{
							mf.nextMove = true;
						}
					}
				}
			}
		};
		timer = new Timer();

		// scheduling the task at interval
		timer.schedule(tasknew,1000, 1000);
	}
	private class MoveFlag{
		public boolean nextMove = false;
		public boolean firstTime = true;
	}

	public void restart(){
		x = 40;
		y = 120;
		currentTile.x = (int)x/40;
		currentTile.y = (int)y/40;
		currentMove = 0;
		tasknew = null;
		tasknew = new TimerTask() {

			@Override
			public void run() {
				/*if(ge.getMiner().isFirstMove()){
					createMaze(ge.getMaze());
					if(findStart() == null){
						createMaze(ge.getMaze());
					}
					if(currentMove == 0)
						mf.nextMove = true;
					if(mf.nextMove || mf.firstTime){
						route.clear();

						createMaze(ge.getMaze());
						startSolving();
						if(recordedRoutes.size() > 0){
							if(recordedRoutes.get(recordedRoutes.size()-1).size() == route.size()){
								for(int i = 0; i < route.size(); i++){
									if(route.get(i).x != recordedRoutes.get(recordedRoutes.size()-1).get(i).x 
											|| route.get(i).y != recordedRoutes.get(recordedRoutes.size()-1).get(i).y){
										recordedRoutes.add(route);
										break;
									}
								}
							}
						}else{
							recordedRoutes.add(route);
						}
						if(recordedRoutes.size() > 2){
							if(recordedRoutes.get(recordedRoutes.size()-1).size() > recordedRoutes.get(recordedRoutes.size()-2).size())
								temp_index = recordedRoutes.size()-2;
							else
								temp_index = recordedRoutes.size()-1;
						}else{
							temp_index = recordedRoutes.size()-1;
						}
						temp_route = recordedRoutes.get(temp_index);

						currentMove = temp_route.size()-1;
						mf.firstTime = false;
						mf.nextMove = false;
					} 
					if(currentMove > 0){
						try{
							currentMove--;
							currentTile.x = temp_route.get(currentMove).x;
							currentTile.y = temp_route.get(currentMove).y;
						}catch(Exception e){
							System.out.println("EXCEPTION " + e );

						}finally{
							mf.nextMove = true;
						}
					}
				}*/
				if(ge.getMiner().isFirstMove()){
					createMaze(ge.getMaze());
					if(findStart() == null){
						createMaze(ge.getMaze());
					}
					if(currentMove == 0)
						mf.nextMove = true;
					if(mf.nextMove || mf.firstTime){
						route.clear();
						createMaze(ge.getMaze());

						startSolving();
						if(recordedRoutes.size() > 0){
							for(int i = 0; i < route.size(); i++){
								if(route.get(i).x != recordedRoutes.get(recordedRoutes.size()-1).get(i).x 
										|| route.get(i).y != recordedRoutes.get(recordedRoutes.size()-1).get(i).y){
									recordedRoutes.add(route);
									break;
								}
							}
						}else{
							recordedRoutes.add(route);
						}

						if(recordedRoutes.size() > 2){
							if(recordedRoutes.get(recordedRoutes.size()-1).size() > recordedRoutes.get(recordedRoutes.size()-2).size())
								temp_index = recordedRoutes.size()-2;
							else
								temp_index = recordedRoutes.size()-1;
						}else{
							temp_index = recordedRoutes.size()-1;
						}
						temp_route = recordedRoutes.get(temp_index);

						currentMove = temp_route.size()-1;
						mf.firstTime = false;
						mf.nextMove = false;
					} 
					if(currentMove > 0){
						try{
							currentMove--;
							currentTile.x = temp_route.get(currentMove).x;
							currentTile.y = temp_route.get(currentMove).y;
						}catch(Exception e){
							System.out.println("EXCEPTION " + e );

						}finally{
							mf.nextMove = true;
						}
					}
				}
			}
		};
		//timer.schedule(tasknew, 1000,1000);	
	}
}
