
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Miner extends GameObject{

	int deltaX = 0, deltaY = 0, score = 0;

	private GameEngine game;
	private Image[] digger;
	private Image currentDigger;
	public static enum State {noBonus, collectAllGolds, extraLife};
	private State bonusState;
	private int life;

	public Miner(GameEngine ge) {
		x = 512;
		y = 600;

		height = 60;
		width = 60;

		life = 3;

		game = ge;

		rectangle.x = x;
		rectangle.y = y;

		rectangle.height = 45;
		rectangle.width = 45;

		bonusState = State.noBonus;

		digger = new Image[4];
		digger[0] = new ImageIcon("digger_left.png").getImage();
		digger[1] = new ImageIcon("digger_right.png").getImage();
		digger[2] = new ImageIcon("digger_back.png").getImage();
		digger[3] = new ImageIcon("digger_front.png").getImage();
		currentDigger = digger[0];
	}

	public void move() {
		if(currentDigger == digger[0] || currentDigger == digger[2]){
			if (x + deltaX > 0 && x + deltaX < game.getWidth()-40)
				setX(x + deltaX);
			if (y + deltaY > 0 && y + deltaY < game.getHeight()-40)
				setY(y + deltaY);
		}else{
			if (x + deltaX > 0 && x + deltaX < game.getWidth()-70)
				setX(x + deltaX);
			if (y + deltaY > 0 && y + deltaY < game.getHeight()-70)
				setY(y + deltaY);
		}
	}

	@Override
	public void paint(Graphics g) {

		g.drawImage(currentDigger, getX(), getY(), null);
	}

	public void keyReleased(KeyEvent e) {
		deltaX = 0;
		deltaY = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			deltaX = -1;
			currentDigger = digger[0];
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			deltaX = 1;
			currentDigger = digger[1];

		}
		if (e.getKeyCode() == KeyEvent.VK_UP){
			deltaY = -1;
			currentDigger = digger[2];

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			deltaY = 1;
			currentDigger = digger[3];
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public State getBonusState() {
		return bonusState;
	}

	public void setBonusState(State bonusState) {
		this.bonusState = bonusState;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}	

}
