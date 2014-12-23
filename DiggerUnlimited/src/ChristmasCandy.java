import javax.swing.ImageIcon;

public class ChristmasCandy extends Coin{

	public ChristmasCandy(int x, int y) {
		super(x, y);
		image = new ImageIcon("CCandy3.png").getImage();
		score = 50;
	}
}
