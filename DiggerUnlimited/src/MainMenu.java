
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MainMenu extends Menu{

	Button playButton;
	Button howToPlayButton;
	Button settings;
	Button credits;
	Button exitButton;
	Button highScoreList;
		
	public MainMenu(GameEngine g){
		super(g);
		playButton = new Button("Play Game", 800, 80,300,70);
		highScoreList = new Button("High Score List",800,180,300,70);
		settings = new Button("Settings",800,280,300,70);
		howToPlayButton = new Button("How to Play", 800, 380, 300,70);
		credits = new Button("Credits",800,480,300,70);
		exitButton = new Button("Exit", 800, 580, 300,70);
		
		image= new ImageIcon("main-menu.png").getImage();
		
		ge = g;
	}
	
	public void paint(Graphics2D g){
		
		g.drawImage(image,0,0,null);
		
	}
	
	public void mouseClicked(String whichButton){
		if(whichButton.equals("Play Game"))
			ge.mGame.setGuiState(GameGUI.State.theme);
		else if(whichButton.equals("Settings")){
			ge.mGame.setGuiState(GameGUI.State.settings);
		}
		else if(whichButton.equals("High Score List"))
			ge.mGame.setGuiState(GameGUI.State.highScoreList);	
		else if(whichButton.equals("How To Play"))
			ge.mGame.setGuiState(GameGUI.State.howToPlay);
		else if(whichButton.equals("Credits"))
			ge.mGame.setGuiState(GameGUI.State.credits);
		else if(whichButton.equals("Exit"))
			ge.mGame.setGuiState(GameGUI.State.exit);
		
	}
}
