public class GameEngine {
	
	public GameGUI mGame;
	private CollisionDetector cd;

	private Miner miner; 
	private GameObject[][] gameObjects;
	private InputManager im;
	private int width = 1200, height = 720;  
	private char[][] map;
	private MainMenu mainMenu;
	private boolean isPaused, isGameOver;
	private GameMap gm;
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
	}
	
	public void generateGameObjects(){
		gameObjects = new GameObject[18][30];
		miner = new Miner(this);	
		
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] == 'E'){
					gameObjects[i][j] = new Tile(j*40, i*40);
				}else if(map[i][j] == 'G'){
					gameObjects[i][j] = new GoldCoin(j*40, i*40);
				}else if (map[i][j] == 'B'){
					gameObjects[i][j] = new ExtraLife(j*40, i*40);
				}
			}
		}
		
		gameObjects[15][10] = miner;
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
	}
	
	public void checkCollisions(){
		
		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				if(cd.checkBonusDiamondCollision(miner, gameObjects[i][j])){
					
					if(gameObjects[i][j] instanceof Coin){
						miner.setScore(miner.getScore()+ (((Coin)gameObjects[i][j]).getScore()));
					
					}else if(gameObjects[i][j] instanceof ExtraLife){
						
						miner.setBonusState(Miner.State.extraLife);
						miner.setLife(miner.getLife()+1);
					}
					gameObjects[i][j].setGameObjectState(GameObject.State.eaten);
				}	
			}
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
}
