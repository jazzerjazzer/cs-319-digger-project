import javax.swing.ImageIcon;


public class HalloweenCandy extends Coin{

	public HalloweenCandy(int x, int y) {
		super(x, y);
		image = new ImageIcon("HCandy3.png").getImage();
		score = 50;
	}
}
