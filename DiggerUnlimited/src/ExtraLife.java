import java.awt.Image;

import javax.swing.ImageIcon;


public class ExtraLife extends Bonus{
	

	public ExtraLife(int x, int y) {
		super(x, y);
		image = new ImageIcon("extra-life.png").getImage();
			System.out.println(image.getSource().toString());
	}
	
}
