import javax.swing.ImageIcon;


public class Tree extends Coin{

	public Tree(int x, int y) {
		super(x, y);
		image = new ImageIcon("Tree3.png").getImage();
		score = 100;
		
	}
}