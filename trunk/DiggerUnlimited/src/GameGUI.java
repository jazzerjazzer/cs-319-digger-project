import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameGUI extends JPanel {

	private GameObject[][] gameObjects;
	public static enum State {game, menu ,settings, howToPlay, credits,theme,highScoreList,name,exit,pause};
	private State guiState;
	private MainMenu menu;
	private Settings settingsMenu;
	private HowToPlay howToPlayMenu;
	private Credits creditsMenu;
	private GameThemesScreen themeMenu;
	private HighScoreList highScoreList;
	private NameScreen nameScreen;
	private PauseMenu pauseMenu;
	private JPanel panel;


	public GameGUI(GameObject[][] go, MainMenu m,Settings s,HowToPlay h, Credits c, GameThemesScreen gt,HighScoreList hs,NameScreen n,PauseMenu p,InputManager im) {
		gameObjects = go;
		menu = m;
		settingsMenu = s;
		howToPlayMenu = h;
		creditsMenu = c;
		themeMenu = gt;
		highScoreList = hs;
		nameScreen = n;
		pauseMenu = p;
		addKeyListener(im);
		addMouseListener(im);
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
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
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
		
		}else if(guiState == State.exit){
			// System.exit(0);  // bu burada olmamalı
		}else if(guiState == State.name){
			nameScreen.paint(g2d);
		}else if(guiState == State.pause){
			pauseMenu.paint(g2d);  
		}
	}

	public State getGuiState() {
		return guiState;
	}

	public void setGuiState(State guiState) {
		this.guiState = guiState;
	}
}
