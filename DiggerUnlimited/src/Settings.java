import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.util.ArrayList;



public class Settings {
	
	private Image image;
	private Image image2;
	private Image image3;
	private Image image4;
	public boolean FXCheck;
	public boolean musicCheck;
	
	Button FXButton;
	Button musicButton;
	Button back;
	
	
	GameEngine ge;
	ArrayList<Button> settingsButtons;
	
	public Settings(GameEngine g){
		
		
		settingsButtons  = new ArrayList<Button>();
		
		image = new ImageIcon("settings.png").getImage();
		
		FXCheck = true;
		musicCheck =true;
		
		FXButton = new Button("FX Button",440,235,300,70);
		musicButton = new Button("Music Button",440,335,300,70);
		back = new Button("Back Button", 1040,640,140,35);
		ge = g;
		
		
		
	}
	
	
	public void paint(Graphics2D g){
		
		for(int i = 0; i < settingsButtons.size(); i++)
			settingsButtons.get(i).paint(g);
		g.drawImage(image,0,0,null);
		
	}
	
	public void mouseClicked(String whichButton){
		
		if(whichButton.equals("Back Button"))
			ge.mGame.setGuiState(GameGUI.State.menu);
		if(whichButton.equals("FX Button")){
			
			if(FXCheck){
				
				FXCheck = false;
				
				SoundEffect.minerWalk.isClicked=false;
				SoundEffect.coin.isClicked=false;
				SoundEffect.minerWalk.isClicked=false;
				SoundEffect.monsterWalk.isClicked=false;
				SoundEffect.bonus.isClicked=false;
				SoundEffect.eatMiner.isClicked=false;
				
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
				
				
				SoundEffect.minerWalk.isClicked=true;
				SoundEffect.coin.isClicked=true;
				SoundEffect.minerWalk.isClicked=true;
				SoundEffect.monsterWalk.isClicked=true;
				SoundEffect.bonus.isClicked=true;
				SoundEffect.eatMiner.isClicked=true;
				
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
				SoundEffect.gameplay.isClicked= false;
				
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
				SoundEffect.gameplay.isClicked= true;
				
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
