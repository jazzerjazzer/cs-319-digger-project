import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {

	int x, y;
	int height, width;
	Rectangle rectangle;
	enum State{eaten, notEaten};
	State gameObjectState;
	
	public GameObject(){
		rectangle = new Rectangle(x, y, width, height);
		gameObjectState = State.notEaten;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		rectangle.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		rectangle.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public void paint(Graphics g){
		
	}

	public State getGameObjectState() {
		return gameObjectState;
	}

	public void setGameObjectState(State gameObjectState) {
		this.gameObjectState = gameObjectState;
	}
	public char toChar(){
		return 'A';
	}
	
}
