import java.awt.Graphics;
import javax.swing.ImageIcon;

public class ChristmasCandy extends Coin{

	public ChristmasCandy(int x, int y) {
		super(x, y);
		image = new ImageIcon("CCandy3.png").getImage();
		score = 50;
	}
	@Override
	public void paint(Graphics g){
		if(gameObjectState != State.eaten)
			g.drawImage(image, getX(), getY(), null);
	}
}
