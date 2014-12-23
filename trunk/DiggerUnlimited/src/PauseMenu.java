
import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class PauseMenu extends Menu{
		
	Button retry;
	Button resume;
	Button back;
	
	public PauseMenu(GameEngine g){
		super(g);
		image = new ImageIcon("pause-menu.png").getImage();
		retry = new Button("Retry",440,235,300,70);
		resume = new Button("Resume",440,335,300,70);
		ge= g;
	}
	
	public void paint(Graphics2D g){
		g.drawImage(image,0,0,null);
	}
	
	public void mouseClicked(String whichButton){
		
		if(whichButton.equals("Resume")){
			ge.mGame.setGuiState(GameGUI.State.game);
			SoundManager.gameplay.play();
			ge.setPaused(false);
		}
		else if(whichButton.equals("Retry")){
			ge.mGame.setGuiState(GameGUI.State.game);
			SoundManager.gameplay.play();
		}
	}
}
