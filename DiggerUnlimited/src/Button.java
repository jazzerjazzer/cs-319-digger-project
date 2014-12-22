import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public class Button {

	private Rectangle rect; 
	private String buttonName;
	private int x,y, width, height;
	private Color textColor;
	private Font font;
	

	public Button(String buttonName, int x, int y, int width, int height){
		
		this.buttonName = buttonName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textColor = textColor;
		this.font = font;
		rect = new Rectangle(x, y, width, height);
	}
	
	public void paint(Graphics2D g){
		
		g.draw(rect);
		
	}
}
