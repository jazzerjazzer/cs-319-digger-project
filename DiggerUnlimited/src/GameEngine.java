import java.awt.Point;


public class GameEngine {

	public GameGUI mGame;
	private CollisionDetector cd;

	private Miner miner; 
	private Monster monster;
	private GameObject[][] gameObjects;
	private InputManager im;
	private int width = 1200, height = 720;  
	private char[][] map;
	private MainMenu mainMenu;
	private boolean isPaused, isGameOver;
	private GameMap gm;
	char[][] theMaze;
	public boolean generatingDone = false, monsterMoved = true;
	public GameEngine(){
		gm = new GameMap();
		map = gm.generateMapFromFile("map.txt");
		generateGameObjects();
		im = new InputManager(this);
		mainMenu = new MainMenu(this);
		mGame = new GameGUI(gameObjects, mainMenu, im);
		mGame.setGuiState(GameGUI.State.menu);
		cd = new CollisionDetector(mGame);
		isPaused = false; 
		isGameOver = false;
		theMaze = new char[18][30];
		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				theMaze[i][j] = gameObjects[i][j].toChar();
			}
		}
	}

	public void generateGameObjects(){
		gameObjects = new GameObject[18][30];

		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] == 'E'){
					gameObjects[i][j] = new Tile(j*40, i*40);
				}else if(map[i][j] == 'G'){
					gameObjects[i][j] = new GoldCoin(j*40, i*40);
				}else if (map[i][j] == 'B'){
					gameObjects[i][j] = new ExtraLife(j*40, i*40);
				}else if (map[i][j] == 'R')
					gameObjects[i][j] = new Road (j*40, i*40);
				else if (map[i][j] == 'S')
					gameObjects[i][j] = new Road (j*40, i*40);
				else if (map[i][j] == 'M'){
					miner = new Miner(this);
					gameObjects[i][j] = miner;
				}else if (map[i][j] == 'O'){
					monster = new Monster(this);
					gameObjects[i][j] = monster;
				}
			}
		}
		generatingDone = true;
	}

	public void startGameLoop(){

		while (!isGameOver) {
			move();
			checkCollisions();
			mGame.repaint();

			if(miner.getLife() == 0)
				isGameOver = true;

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void move() {
		
		miner.move();

		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				if(gameObjects[i][j] instanceof Miner || gameObjects[i][j] instanceof Monster){
					gameObjects[i][j] = new Road();
				}
			}
		}
		gameObjects[miner.getCurrentTile().y][miner.getCurrentTile().x] = miner;
		gameObjects[monster.getCurrentTile().y][monster.getCurrentTile().x] = monster;
		if(generatingDone && monsterMoved){
			monster.move();
			monsterMoved = false;
		}
	}

	public void checkCollisions(){

		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				if(!(gameObjects[i][j] instanceof Road)){
					if(cd.checkBonusDiamondCollision(miner, gameObjects[i][j])){

						if(gameObjects[i][j] instanceof Coin){
							miner.setScore(miner.getScore()+ (((Coin)gameObjects[i][j]).getScore()));

						}else if(gameObjects[i][j] instanceof ExtraLife){

							miner.setBonusState(Miner.State.extraLife);
							miner.setLife(miner.getLife()+1);
						}

						//gameObjects[i][j].setGameObjectState(GameObject.State.eaten);
						if(!(gameObjects[i][j] instanceof Miner))
							gameObjects[i][j] = new Road(j*40, i*40);
					}	
				}
			}
		}
		if(cd.checkMonsterCollision(miner, monster)){
			System.out.println("Collided");
			monster.restart();
			miner.setLife(miner.getLife()-1);
			miner.setFirstMove(false);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			miner.setX(400);
			miner.setY(600);
			miner.setCurrentTile(new Point(10,15));
			
		}
	}

	public Miner getMiner() {
		return miner;
	}

	public void setMiner(Miner miner) {
		this.miner = miner;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public void showCurrentMap(){


		System.out.println("****");
		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				System.out.print(theMaze[i][j]);
			}
			System.out.println();
		}
		System.out.println("*****");
		//monster.move();

	}

	public char[][] getMaze(){
		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				theMaze[i][j] = gameObjects[i][j].toChar();
			}
		}
		return theMaze;
	}
}
