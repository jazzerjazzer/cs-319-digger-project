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
	private boolean paused, isGameOver;
	private GameMap gm;
	char[][] theMaze;
	public boolean generatingDone = false, monsterMoved = true;
	public int coinAmount;

	private SettingsMenu settingsMenu;
	private HowToPlayMenu howToPlayMenu;
	private CreditsMenu creditsMenu;
	private HighScoreList highScoreListMenu;
	private GameThemesMenu themeMenu;
	private PauseMenu pauseMenu;
	
	private FileManager fm;
	
	int number = 0;
	int score =0;
	
	public static enum ThemeState {diggerUnlimited, halloween, christmas, diggerOriginal};
	public ThemeState currentThemeState;


	public GameEngine(){
		fm = new FileManager();
		gm = new GameMap();
		coinAmount = 10;
		map = gm.generateMapFromFile(fm.getFile("map.txt"));
		generateGameObjects();
		im = new InputManager(this);
		mainMenu = new MainMenu(this);
		settingsMenu = new SettingsMenu(this);
		howToPlayMenu = new HowToPlayMenu(this);
		creditsMenu = new CreditsMenu(this);
		themeMenu = new GameThemesMenu(this);
		highScoreListMenu = new HighScoreList(this);
		pauseMenu = new PauseMenu(this);

		mGame = new GameGUI(this);

		mGame.setGuiState(GameGUI.State.menu);
		cd = new CollisionDetector(mGame);
		paused = false; 
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

				}else if (map[i][j] == 'R')
					gameObjects[i][j] = new Road (j*40, i*40);
				else if (map[i][j] == 'S')
					gameObjects[i][j] = new SilverCoin (j*40, i*40);
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
			if(!paused){
				//System.out.println(coinAmount);
				move();
				checkCollisions();
				mGame.repaint();

				if(miner.getLife() == 0 || coinAmount == 0){
					isGameOver = true;
					mGame.setGuiState(GameGUI.State.menu);
					SoundManager.gameplay.stop();
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(coinAmount);
				//System.out.println(gameObjects[2][9].getClass().getCanonicalName());
				
			}else{
				System.out.println("ELSE");
				mGame.repaint();
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
							SoundManager.coin.play();
							coinAmount--;
							if(miner.getBonusState()==Miner.State.silverToGold){
								((SilverCoin)gameObjects[i][j]).setScore(100);
								miner.setScore(miner.getScore()+ score);
							}
							else if(miner.getBonusState()==Miner.State.doubleSilver){
								((SilverCoin)gameObjects[i][j]).setScore(100);
								miner.setScore(miner.getScore()+ score);

							}else if(miner.getBonusState()==Miner.State.tripleSilver){
								((SilverCoin)gameObjects[i][j]).setScore(150);
								miner.setScore(miner.getScore()+ score);
							}
							else 
								miner.setScore(miner.getScore()+ (((Coin)gameObjects[i][j]).getScore()));

						}else if(gameObjects[i][j] instanceof GoldCoin){
							System.out.println("Miner state: " + miner.getBonusState());
							SoundManager.coin.play();
							coinAmount--;

							if(miner.getBonusState()==Miner.State.goldToSilver){
								((GoldCoin)gameObjects[i][j]).setScore(50);
								miner.setScore(miner.getScore()+ score);

								System.out.println("Gold to Silver");
							}
							else if(miner.getBonusState()==Miner.State.doubleGold){
								((GoldCoin)gameObjects[i][j]).setScore(200);
								miner.setScore(miner.getScore()+ score);
								miner.setScore(miner.getScore()+ score);
							}
							else if(miner.getBonusState()==Miner.State.tripleGold){
								((GoldCoin)gameObjects[i][j]).setScore(300);
								miner.setScore(miner.getScore()+ score);
								miner.setScore(miner.getScore()+ score);
							}
							else 
								miner.setScore(miner.getScore()+ (((Coin)gameObjects[i][j]).getScore()));
						}
						else if(gameObjects[i][j] instanceof ExtraLife){
							miner.setLife(miner.getLife()+1);
							SoundManager.bonus.play();
						}
						else if(gameObjects[i][j] instanceof LoseLife){
							miner.setLife(miner.getLife()-1);
							SoundManager.bonus.play();
						}
						else if(gameObjects[i][j] instanceof SilverToGold){
							miner.setBonusState(Miner.State.silverToGold);
							SoundManager.bonus.play();

						}
						else if(gameObjects[i][j] instanceof GoldToSilver){
							miner.setBonusState(Miner.State.goldToSilver);
							System.out.println("GOLD_TO_SILVER");
							SoundManager.bonus.play();

						}
						else if(gameObjects[i][j] instanceof DoubleGold){
							miner.setBonusState(Miner.State.doubleGold);
							SoundManager.bonus.play();

						}
						else if(gameObjects[i][j] instanceof DoubleSilver){
							miner.setBonusState(Miner.State.doubleSilver);
							SoundManager.bonus.play();

						}
						else if(gameObjects[i][j] instanceof TripleGold ){
							miner.setBonusState(Miner.State.tripleGold);
							SoundManager.bonus.play();

						}
						else if(gameObjects[i][j] instanceof TripleSilver){
							miner.setBonusState(Miner.State.tripleSilver);
							SoundManager.bonus.play();

						}

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

	public SettingsMenu getSettingsMenu(){
		return settingsMenu;

	}

	public void setSettingsMenu(SettingsMenu settingsMenu){
		this.settingsMenu = settingsMenu;
	}

	public HowToPlayMenu getHowToPlayMenu(){
		return howToPlayMenu;
	}

	public void setSettingsMenu(HowToPlayMenu howToPlayMenu){
		this.howToPlayMenu = howToPlayMenu;
	}
	public CreditsMenu getCreditsMenu(){
		return creditsMenu;
	}

	public void setCreditsMenu(CreditsMenu creditsMenu){
		this.creditsMenu = creditsMenu;
	}

	public GameThemesMenu getGameThemesMenu(){
		return themeMenu;
	}

	public void setGameThemesMenu(GameThemesMenu themeMenu){
		this.themeMenu = themeMenu;
	}


	public PauseMenu getPauseMenu(){
		return pauseMenu;
	}

	public void setPauseMenu(PauseMenu pauseMenu){
		this.pauseMenu =pauseMenu;
	}
	public HighScoreList getHighScoreList(){
		return highScoreListMenu;
	}

	public char[][] getMaze(){
		for (int i = 0; i < gameObjects.length; i++){ 
			for(int j = 0; j < gameObjects[i].length; j++){
				theMaze[i][j] = gameObjects[i][j].toChar();
			}
		}
		return theMaze;
	}

	public GameObject[][] getGameObjects(){
		return gameObjects;
	}

	public InputManager getInputManager(){
		return im;
	}

	public void pauseGame(){
		if(paused)
			paused = false;
		else{
			paused = true;
			mGame.setGuiState(GameGUI.State.pause);
			SoundManager.gameplay.stop();
		}
	}
	public void endGame(){
		System.exit(0);
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public GameGUI getGameGUI(){
		return mGame;
	}
	
}
