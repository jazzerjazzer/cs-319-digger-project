import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class CreditsMenu extends Menu{
	
	Button back;
	
	public CreditsMenu(GameEngine g){
		super(g);
		image= new ImageIcon("Credits-page.png").getImage();
		back = new Button("Back Button", 1040,640,140,35);
		ge = g;
	}
	
	public void paint(Graphics2D g){
		back.paint(g);
		g.drawImage(image,0,0,null);
	}
	
	public void mouseClicked(String whichButton){
		if(whichButton.equals("Back Button"))
			ge.mGame.setGuiState(GameGUI.State.menu);
	}
}
