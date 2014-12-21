import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameGUI extends JPanel {

	private GameObject[][] gameObjects;
	public static enum State {game, menu};
	private State guiState;
	private MainMenu menu;


	public GameGUI(GameObject[][] go, MainMenu m, InputManager im) {
		gameObjects = go;
		menu = m;
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
			g.drawString("Score:" + 0, 50, 50);
		}else if(guiState == State.menu){
			menu.paint(g2d);
		}
	}

	public State getGuiState() {
		return guiState;
	}

	public void setGuiState(State guiState) {
		this.guiState = guiState;
	}
}
