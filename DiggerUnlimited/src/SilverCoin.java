import javax.swing.ImageIcon;


public class SilverCoin extends Coin{

	public SilverCoin(int x, int y) {
		super(x, y);
		image = new ImageIcon("silver.png").getImage();
		score = 50;
	}
}
