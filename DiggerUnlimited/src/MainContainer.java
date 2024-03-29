import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainContainer extends JFrame{
	
	public MainContainer(){
		
		GameEngine ge = GameEngine.getInstance();
		add(ge.mGame);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JTextField scoreArea = new JTextField("Score: ", 20); 
		//this.add(scoreArea);
		setVisible(true);
		setSize(1200, 720);
		setLocationRelativeTo(null);
		if(ge.generatingDone)
			ge.startGameLoop();	
	}

	public static void main(String[] args) throws InterruptedException {

		new MainContainer();
	}
}
