import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class InputManager implements KeyListener, MouseListener{

	GameEngine g;
	
	public InputManager(GameEngine g){
		this.g = g;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.g.getMiner().keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.g.getMiner().keyReleased(e);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(g.mGame.getGuiState()== GameGUI.State.menu){
			if(e.getX() > 800 && e.getX() < 1100 && e.getY() > 80 && e.getY() < 150){
				this.g.getMainMenu().mouseClicked("Play Game");
			}
			else if(e.getX() > 800 && e.getX() < 1100 && e.getY() >180  && e.getY() <250 ){
				this.g.getMainMenu().mouseClicked("High Score List");
			}
			else if(e.getX() > 800 && e.getX() < 1100 && e.getY() > 280 && e.getY() < 350){
				this.g.getMainMenu().mouseClicked("Settings");
			}
			else if(e.getX() > 800 && e.getX() < 1100 && e.getY() > 380 && e.getY() < 450){
				this.g.getMainMenu().mouseClicked("How To Play");
			}
			else if(e.getX() > 800 && e.getX() < 1100 && e.getY() > 480 && e.getY() < 550){
				this.g.getMainMenu().mouseClicked("Credits");
			}
			else if(e.getX() > 800 && e.getX() < 1100 && e.getY() > 580 && e.getY() < 650){
				this.g.getMainMenu().mouseClicked("Exit");
			}
		}
		if(g.mGame.getGuiState()== GameGUI.State.settings){
			if(e.getX() > 1040 && e.getX() < 1180 && e.getY() > 640 && e.getY() < 675){
				this.g.getSettingsMenu().mouseClicked("Back Button");
			}
			else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 235 && e.getY() < 305){
				this.g.getSettingsMenu().mouseClicked("FX Button");
			}else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 335 && e.getY() < 405){
				this.g.getSettingsMenu().mouseClicked("Music Button");
			}
		}
		if(g.mGame.getGuiState()== GameGUI.State.credits){
			if(e.getX() > 1040 && e.getX() < 1180 && e.getY() > 640 && e.getY() < 675){
				this.g.getCreditsMenu().mouseClicked("Back Button");
			}
		}
		if(g.mGame.getGuiState()== GameGUI.State.howToPlay){
			if(e.getX() > 1040 && e.getX() < 1180 && e.getY() > 640 && e.getY() < 675){
				this.g.getHowToPlayMenu().mouseClicked("Back Button");
			}
		}
		if(g.mGame.getGuiState()== GameGUI.State.theme){
			if(e.getX() > 1040 && e.getX() < 1180 && e.getY() > 640 && e.getY() < 675){
				this.g.getHowToPlayMenu().mouseClicked("Back Button");
			}
			else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 235 && e.getY() < 305){
				this.g.getGameThemesMenu().mouseClicked("Digger Unlimited");
				
				
			}
			else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 335 && e.getY() < 405){
				this.g.getGameThemesMenu().mouseClicked("Halloween");
				
			}
			else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 435 && e.getY() < 505){
				this.g.getGameThemesMenu().mouseClicked("Christmas");
				
			}
			else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 535 && e.getY() < 605){
				this.g.getGameThemesMenu().mouseClicked("Digger Original");
				
			}
		}
		if(g.mGame.getGuiState()== GameGUI.State.highScoreList){
			if(e.getX() > 1040 && e.getX() < 1180 && e.getY() > 640 && e.getY() < 675){
				this.g.getCreditsMenu().mouseClicked("Back Button");
	 		}
		}
		if(g.mGame.getGuiState()== GameGUI.State.name){
			if(e.getX() > 400 && e.getX() < 500 && e.getY() > 400 && e.getY() < 440){
				
				this.g.getNameScreen().mouseClicked("Skip");
			}
			if(e.getX() > 680 && e.getX() < 800 && e.getY() > 400 && e.getY() < 440){
				this.g.getNameScreen().mouseClicked("Enter");
			}
		}
		if(g.mGame.getGuiState()== GameGUI.State.pause){
			if(e.getX() > 1040 && e.getX() < 1180 && e.getY() > 640 && e.getY() < 675){
				this.g.getPauseMenu().mouseClicked("Retry");
			}
			else if(e.getX() > 440 && e.getX() < 740 && e.getY() > 235 && e.getY() < 305){
				this.g.getPauseMenu().mouseClicked("Resume");
			}
		}
		//this.g.getMainMenu().mouseClicked();
		//System.out.println(e.getX() + " " + e.getY());
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}