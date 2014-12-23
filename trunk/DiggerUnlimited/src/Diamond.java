import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Diamond extends Coin{

	public Diamond(int x, int y) {
		super(x, y);
		image = new ImageIcon("diamond3.png").getImage();
		score = 100;
		
	}
	@Override
	public void paint(Graphics g){
		if(gameObjectState != State.eaten)
			g.drawImage(image, getX(), getY(), null);
	}
}