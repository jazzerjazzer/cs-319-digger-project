import javax.swing.ImageIcon;


public class LoseLife extends Bonus{

	public LoseLife(int x, int y) {
		super(x, y);
		image = new ImageIcon("lose-life.png").getImage();
	}
}
