import java.awt.Image;

import javax.swing.ImageIcon;



import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameThemesScreen{
	
	protected Image image;
	
	Button diggerUnlimited;
	Button halloween;
	Button christmas;
	Button diggerOriginal;
	Button back;
	
	private int theme;

	

	GameEngine ge;
	ArrayList<Button> themeButtons;
	
	public GameThemesScreen(GameEngine g){
	
		
		themeButtons  = new ArrayList<Button>();
		
		image = new ImageIcon("theme-change.png").getImage();
		
		diggerUnlimited = new Button("Digger Unlimited",440,235,300,70);
		halloween = new Button("Halloween",440,335,300,70);
		christmas = new Button("Christmas",440,435,300,70);
		diggerOriginal = new Button("Digger Original",440,535,300,70);
		back = new Button("Back Button", 1040,640,140,35);
		ge = g;
		
		theme =0;
	}
	
	
	public void paint(Graphics2D g){
		
		for(int i = 0; i < themeButtons.size(); i++)
			themeButtons.get(i).paint(g);
		g.drawImage(image,0,0,null);
		
	}
	public void mouseClicked(String whichButton){
		if(whichButton.equals("Digger Unlimited")){
			
			SoundEffect.gameplay.play();
		
			ge.mGame.setGuiState(GameGUI.State.game);
			
		}
		
		else if(whichButton.equals("Halloween")){
			
			SoundEffect.gameplay.play();
			
			ge.mGame.setGuiState(GameGUI.State.game);
		}
			
		else if(whichButton.equals("Christmas")){
			
			SoundEffect.gameplay.play();

			ge.mGame.setGuiState(GameGUI.State.game);
		}
			
		
		else if(whichButton.equals("Digger Original")){
			
			SoundEffect.gameplay.play();
		
			ge.mGame.setGuiState(GameGUI.State.game);
		}	
		
		else if(whichButton.equals("Back Button"))
			ge.mGame.setGuiState(GameGUI.State.menu);
		
	}
	

	
	
		
		
	

}

