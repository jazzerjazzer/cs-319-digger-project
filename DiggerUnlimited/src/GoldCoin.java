import javax.swing.ImageIcon;


public class GoldCoin extends Coin{

	public GoldCoin(int x, int y) {
		super(x, y);
		image = new ImageIcon("gold.png").getImage();
		score = 100;
	}
	
	public char toChar(){
		return 'G';
	}
}
