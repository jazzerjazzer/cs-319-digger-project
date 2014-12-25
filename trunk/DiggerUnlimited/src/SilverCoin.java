
import java.awt.Image;

import javax.swing.ImageIcon;


public class SilverCoin extends Coin{

	public SilverCoin(int x, int y) {
		super(x, y);
		image = new ImageIcon("CCandy3.png").getImage();
		score = 50;
	}
	
	public void setCoinImage(Image image){
		this.image= image;
	}
	
	public Image getCoinImage(){
		return image;
	}
}
