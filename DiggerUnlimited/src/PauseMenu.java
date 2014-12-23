import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class PauseMenu {
	
	protected Image image;
	
	Button retry;
	Button resume;
	Button back;
	
	
	
	GameEngine ge;
	ArrayList<Button> pauseButtons;
	
	public PauseMenu(GameEngine g){
		
		pauseButtons  = new ArrayList<Button>();
		
		image = new ImageIcon("pause-menu.png").getImage();
		
		retry = new Button("Retry",440,235,300,70);
		resume = new Button("Resume",440,335,300,70);
		ge= g;
		
		
	}
	
	public void paint(Graphics2D g){
		
		for(int i = 0; i < pauseButtons.size(); i++)
			pauseButtons.get(i).paint(g);
		g.drawImage(image,0,0,null);
		
	}
	
	public void mouseClicked(String whichButton){
		
		if(whichButton.equals("Resume"))
			ge.mGame.setGuiState(GameGUI.State.game);
		
		else if(whichButton.equals("Retry")){
			ge.mGame.setGuiState(GameGUI.State.game);
		}
		
		
	}

}
