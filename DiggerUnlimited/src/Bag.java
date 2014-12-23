import javax.swing.ImageIcon;


public class Bag extends Coin{

	public Bag(int x, int y) {
		super(x, y);
		image = new ImageIcon("Bag3.png").getImage();
		score = 50;
	}
}

