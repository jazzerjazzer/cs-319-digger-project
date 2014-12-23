
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Miner extends GameObject{

	int deltaX = 0, deltaY = 0, score = 0;

	private GameEngine game;
	private Image[] digger;
	private Image currentDigger;
	public static enum State {noBonus, extraLife,loseLife,doubleGold,tripleGold,doubleSilver,tripleSilver,silverToGold,goldToSilver};
	private State bonusState;
	private int life;
	private Point currentTile;
	private boolean firstMove = false;
	public Miner(GameEngine ge) {
		x = 400;
		y = 600;

		currentTile = new Point(16,10);
		height = 40;
		width = 40;

		life = 3;

		game = ge;

		rectangle.x = x;
		rectangle.y = y;

		rectangle.height = 5;
		rectangle.width = 5;

		bonusState = State.noBonus;

		digger = new Image[4];
		digger[0] = new ImageIcon("miner-left.png").getImage();
		digger[1] = new ImageIcon("miner-right.png").getImage();
		digger[2] = new ImageIcon("miner-back.png").getImage();
		digger[3] = new ImageIcon("miner-front.png").getImage();
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
		
		
		if(currentDigger == digger[0] || currentDigger == digger[2]){
			if (x + deltaX > 0 && x + deltaX < game.getWidth()-40)
				currentTile.x = ((x/40) + (deltaX / 40));
			if (y + deltaY > 0 && y + deltaY < game.getHeight()-40)
				currentTile.y = ((y/40) + (deltaY / 40));
		}else{
			if (x + deltaX > 0 && x + deltaX < game.getWidth()-70)
				currentTile.x = ((x/40) + (deltaX / 40));
			if (y + deltaY > 0 && y + deltaY < game.getHeight()-70)
				currentTile.y = ((y/40) + (deltaY / 40));
		}		
	}

	@Override
	public void paint(Graphics g) {
		currentTile.x = (int)x/40;
		currentTile.y = (int)y/40;
		g.drawImage(currentDigger, currentTile.x*40, currentTile.y*40, null);
	}

	public void keyReleased(KeyEvent e) {
		deltaX = 0;
		deltaY = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			deltaX = -2;
			currentDigger = digger[0];
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			deltaX = 2;
			currentDigger = digger[1];

		}
		if (e.getKeyCode() == KeyEvent.VK_UP){
			deltaY = -2;
			currentDigger = digger[2];

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			deltaY = 2;
			currentDigger = digger[3];
		}
		if(!firstMove){
			firstMove = true;
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

	public Point getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(Point currentTile) {
		this.currentTile = currentTile;
	}	
	
	public char toChar(){
		return 'M';
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}
