import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGUI extends JPanel {

	private GameObject[][] gameObjects;
	public static enum State {game, menu ,settings, howToPlay, credits,theme,highScoreList,name,exit,pause, gameOver};
	private State guiState;
	private GameEngine ge;
	
	private MainMenu menu;
	private SettingsMenu settingsMenu;
	private HowToPlayMenu howToPlayMenu;
	private CreditsMenu creditsMenu;
	private GameThemesMenu themeMenu;
	private HighScoreList highScoreList;
	private PauseMenu pauseMenu;
	private ScoreMenu scoreMenu;
	JTextField scoreArea;  
	public GameGUI(GameEngine ge) {
		this.ge = ge;
		gameObjects = ge.getGameObjects();
		menu = ge.getMainMenu();
		settingsMenu = ge.getSettingsMenu();
		howToPlayMenu = ge.getHowToPlayMenu();
		creditsMenu = ge.getCreditsMenu();
		themeMenu = ge.getGameThemesMenu();
		highScoreList = ge.getHighScoreList();
		pauseMenu = ge.getPauseMenu();
		addKeyListener(ge.getInputManager());
		addMouseListener(ge.getInputManager());
		setFocusable(true);
		setVisible(true);
		guiState = State.menu;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if(guiState == State.game){
			for(int i = 0; i < gameObjects.length; i++){
				for(int j = 0; j < gameObjects[i].length; j++){
					gameObjects[i][j].paint(g2d);
				}
			}
			//g.setFont(new Font("Calibri", Font.PLAIN, 20));
			//g.drawString("Score:" + ((Miner)gameObjects[15][10]).getScore(), 50, 50);
			//g.drawString("Life:"+((Miner)gameObjects[15][10]).getLife(), 100,100);
			
		}else if(guiState == State.menu){
			menu.paint(g2d);
		}else if(guiState == State.settings){
			settingsMenu.paint(g2d);
		}else if(guiState == State.howToPlay){
			howToPlayMenu.paint(g2d);
		}else if(guiState == State.credits){
			creditsMenu.paint(g2d);
		} else if(guiState == State.theme){
			themeMenu.paint(g2d);  
		}else if(guiState == State.highScoreList){
			highScoreList.paint(g2d); 
		}else if(guiState == State.pause){
			pauseMenu.paint(g2d);  
		}else if (guiState == State.gameOver){
			scoreMenu.paint(g2d);  
		}
	}

	public State getGuiState() {
		return guiState;
	}

	public void setGuiState(State guiState) {
		this.guiState = guiState;
	}
	public void printCurrentGameObjects(){
		for(int i = 0; i < gameObjects.length; i++){
			for(int j = 0; j < gameObjects[0].length; j++){
				System.out.print(gameObjects[i][j].toChar());
			}
			System.out.println();
		}
	}
}
