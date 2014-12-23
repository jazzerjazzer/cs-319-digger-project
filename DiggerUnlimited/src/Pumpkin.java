import javax.swing.ImageIcon;


public class Pumpkin extends Coin{

	public Pumpkin(int x, int y) {
		super(x, y);
		image = new ImageIcon("Pumpkin3.png").getImage();
		score = 100;
		
	}
}