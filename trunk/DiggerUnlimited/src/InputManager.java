import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class InputManager implements KeyListener, MouseListener{

	GameEngine g;
	
	public InputManager(GameEngine g){
		this.g = g;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.g.getMiner().keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.g.getMiner().keyReleased(e);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(e.getX() > 500 && e.getX() < 800 && e.getY() > 150 && e.getY() < 250){
			this.g.getMainMenu().mouseClicked("PlayGame");
		}else{
			g.showCurrentMap();
		}
		//this.g.getMainMenu().mouseClicked();
		//System.out.println(e.getX() + " " + e.getY());
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}