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
	
	public SoundEffect soundEffect;
	public static enum GameState{a,b};
	private GameState gameState;

	private Settings settingsMenu;
	private HowToPlay howToPlayMenu;
	private Credits creditsMenu;
	private HighScoreList highScoreListMenu;
	private GameThemesScreen themeMenu;
	private NameScreen nameScreen;
	private PauseMenu pauseMenu;
	int number = 0;
	int score =0;
	
	public GameEngine(){
		gm = new GameMap();
		map = gm.generateMapFromFile("map.txt");
		generateGameObjects();
		im = new InputManager(this);
		
		mainMenu = new MainMenu(this);
		settingsMenu = new Settings(this);
		howToPlayMenu = new HowToPlay(this);
		creditsMenu = new Credits(this);
		themeMenu = new GameThemesScreen(this);
		highScoreListMenu = new HighScoreList(this);
		nameScreen = new NameScreen(this);
		pauseMenu = new PauseMenu(this);
		gameState = GameState.a;

		mGame = new GameGUI(gameObjects, mainMenu,settingsMenu,howToPlayMenu,creditsMenu,themeMenu,highScoreListMenu,nameScreen,pauseMenu, im);
		
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
					number=1;
					//number= (int)Math.floor((Math.random()*12)+1);
					if(number==1){
						gameObjects[i][j] = new ExtraLife(j*40, i*40);
					}
					else if(number==2){
						gameObjects[i][j] = new LoseLife(j*40, i*40);
					}
					else if(number==3){
						gameObjects[i][j] = new SilverToGold(j*40, i*40);
					}
					else if(number==4){
						gameObjects[i][j] = new GoldToSilver(j*40, i*40);
					}
					else if(number==5){
						gameObjects[i][j] = new DoubleGold(j*40, i*40);
					}
					else if(number==6){
						gameObjects[i][j] = new DoubleSilver(j*40, i*40);
					}
					else if(number==7){
						gameObjects[i][j] = new TripleGold(j*40, i*40);
					}
					else if(number==8){
						gameObjects[i][j] = new TripleSilver(j*40, i*40);
					}
					else if(number==9){
						gameObjects[i][j] = new DigAll(j*40, i*40);
					}
					else if(number==10){
						gameObjects[i][j] = new DigBack(j*40, i*40);
					}
					else if(number==11){
						gameObjects[i][j] = new DoubleMonsters(j*40, i*40);
					}
					else if(number==12){
						gameObjects[i][j] = new DestroyMonsters(j*40, i*40);
					}
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
		System.out.println("DONE");
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

						if(gameObjects[i][j] instanceof SilverCoin){
							SoundEffect.coin.play();
							
							if(miner.getBonusState()==Miner.State.silverToGold){
								
								score = (((GoldCoin)gameObjects[i][j]).getScore())*2;
								miner.setScore(miner.getScore()+ score);
								
							}
							else if(miner.getBonusState()==Miner.State.doubleSilver){
								
								score = (((GoldCoin)gameObjects[i][j]).getScore())*2;
								miner.setScore(miner.getScore()+ score);
								
							}else if(miner.getBonusState()==Miner.State.tripleSilver){
								
								score = (((GoldCoin)gameObjects[i][j]).getScore())*3;
								miner.setScore(miner.getScore()+ score);
								
							}
							else 
								miner.setScore(miner.getScore()+ (((Coin)gameObjects[i][j]).getScore()));
							
						}else if(gameObjects[i][j] instanceof GoldCoin){
							SoundEffect.coin.play();
							
							if(miner.getBonusState()==Miner.State.goldToSilver){
								System.out.println("Gold to Silver");
							}
							else if(miner.getBonusState()==Miner.State.doubleGold){
								score = (((GoldCoin)gameObjects[i][j]).getScore())*2;
								miner.setScore(miner.getScore()+ score);
							}
							else if(miner.getBonusState()==Miner.State.tripleGold){
								score = (((GoldCoin)gameObjects[i][j]).getScore())*3;
								miner.setScore(miner.getScore()+ score);
							}
							else 
								miner.setScore(miner.getScore()+ (((Coin)gameObjects[i][j]).getScore()));
						}
						else if(gameObjects[i][j] instanceof ExtraLife){
							miner.setLife(miner.getLife()+1);
							SoundEffect.bonus.play();
						}
						else if(gameObjects[i][j] instanceof LoseLife){
							miner.setLife(miner.getLife()-1);
							SoundEffect.bonus.play();
						}
						else if(gameObjects[i][j] instanceof SilverToGold){
							miner.setBonusState(Miner.State.silverToGold);
						}
						else if(gameObjects[i][j] instanceof GoldToSilver){
							miner.setBonusState(Miner.State.goldToSilver);
						}
						else if(gameObjects[i][j] instanceof DoubleGold){
							miner.setBonusState(Miner.State.doubleGold);
						}
						else if(gameObjects[i][j] instanceof DoubleSilver){
							miner.setBonusState(Miner.State.doubleSilver);
						}
						else if(gameObjects[i][j] instanceof TripleGold ){
							miner.setBonusState(Miner.State.tripleGold);
						}
						else if(gameObjects[i][j] instanceof TripleSilver){
							miner.setBonusState(Miner.State.tripleSilver);
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

	public Settings getSettingsMenu(){
		return settingsMenu;
		
	}
	
	public void setSettingsMenu(Settings settingsMenu){
		this.settingsMenu = settingsMenu;
	}
	
	public HowToPlay getHowToPlayMenu(){
		return howToPlayMenu;
		
		
	}
	
	public void setSettingsMenu(HowToPlay howToPlayMenu){
		this.howToPlayMenu = howToPlayMenu;
	}
	public Credits getCreditsMenu(){
		return creditsMenu;
		
		
	}
	
	public void setCreditsMenu(Credits creditsMenu){
		this.creditsMenu = creditsMenu;
	}
	
	public GameThemesScreen getGameThemesMenu(){
		return themeMenu;
		
		
	}
	
	public void setGameThemesMenu(GameThemesScreen themeMenu){
		this.themeMenu = themeMenu;
	}
	
	public NameScreen getNameScreen(){
		return nameScreen;
		
		
	}
	
	public void setNameScreen(NameScreen nameScreen){
		this.nameScreen = nameScreen;
	}
	
	public PauseMenu getPauseMenu(){
		return pauseMenu;
		
		
	}
	
	public void setPauseMenu(PauseMenu pauseMenu){
		this.pauseMenu =pauseMenu;
	}
	
	public GameState getGameState() {
		return  gameState;
	}

	public void setGameState(GameState  gameState) {
		this. gameState = gameState;
	}
	public void showCurrentMap(){

		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				System.out.print(theMaze[i][j]);
			}
			System.out.println();
		}

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
