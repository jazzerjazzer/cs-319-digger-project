
import javax.swing.ImageIcon;


public class GoldCoin extends Coin{

	public GoldCoin(int x, int y) {
		super(x, y);
		image = new ImageIcon("gold3.png").getImage();
		score = 100;
		
	}
}
