import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class MainMenu {

	Button playButton;
	Button tutorialButton;
	Button exitButton;
	Font f;
	GameEngine ge;
	ArrayList<Button> buttons;
	
	public MainMenu(GameEngine g){
		buttons  = new ArrayList<Button>();
		
		f = new Font("arial", Font.BOLD, 50);
		
		playButton = new Button("Play Game", 500, 150, 300, 100, Color.BLACK,f);
		tutorialButton = new Button("How to Play", 500, 270, 300, 100, Color.BLACK, f);
		exitButton = new Button("Exit", 500, 390, 300, 100, Color.BLACK, f);
		
		buttons.add(playButton);
		buttons.add(tutorialButton);
		buttons.add(exitButton);
		
		ge = g;
	}
	
	public void paint(Graphics2D g){
		
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).paint(g);
		
	}
	
	public void mouseClicked(String whichButton){
		if(whichButton.equals("PlayGame"))
			ge.mGame.setGuiState(GameGUI.State.game);
		
	}
}
