
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class SettingsMenu extends Menu{
	
	public boolean FXCheck;
	public boolean musicCheck;
	
	Button FXButton;
	Button musicButton;
	Button back;
		
	public SettingsMenu(GameEngine g){
		super(g);
		image = new ImageIcon("settings.png").getImage();
		
		FXCheck = true;
		musicCheck =true;
		
		FXButton = new Button("FX Button",440,235,300,70);
		musicButton = new Button("Music Button",440,335,300,70);
		back = new Button("Back Button", 1040,640,140,35);
		ge = g;
	}
	
	
	public void paint(Graphics2D g){
		
		g.drawImage(image,0,0,null);
	}
	
	public void mouseClicked(String whichButton){
		
		if(whichButton.equals("Back Button"))
			ge.mGame.setGuiState(GameGUI.State.menu);
		if(whichButton.equals("FX Button")){
			
			if(FXCheck){
				
				FXCheck = false;
				
				SoundManager.minerWalk.isClicked=false;
				SoundManager.coin.isClicked=false;
				SoundManager.minerWalk.isClicked=false;
				SoundManager.monsterWalk.isClicked=false;
				SoundManager.bonus.isClicked=false;
				SoundManager.eatMiner.isClicked=false;
				
				if(musicCheck){
					
					image = new ImageIcon("settings2.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
					
				}
				else{
					
					image = new ImageIcon("settings4.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
		
				}
			
			}
			else{
				FXCheck = true;
				
				
				SoundManager.minerWalk.isClicked=true;
				SoundManager.coin.isClicked=true;
				SoundManager.minerWalk.isClicked=true;
				SoundManager.monsterWalk.isClicked=true;
				SoundManager.bonus.isClicked=true;
				SoundManager.eatMiner.isClicked=true;
				
				if(musicCheck){
					
					image = new ImageIcon("settings.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
					
				}
				else{
					
					image = new ImageIcon("settings3.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
		
				}
				
			}
		}	
		if(whichButton.equals( "Music Button")){
			
			if(musicCheck){
				SoundManager.gameplay.isClicked= false;
				
				musicCheck = false;
				if(FXCheck){

					image = new ImageIcon("settings3.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
		
				}else{
					
					image = new ImageIcon("settings4.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
		
				}	
				
			}else{
				
				musicCheck = true;
				SoundManager.gameplay.isClicked= true;
				
				if(FXCheck){

					image = new ImageIcon("settings.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
		
				}else{
					
					image = new ImageIcon("settings2.png").getImage();
					ge.mGame.setGuiState(GameGUI.State.settings);
		
				}	
			}
				
			
		
		}
			
	}
}
