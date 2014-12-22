import javax.swing.JFrame;

public class MainContainer extends JFrame{
	
	public MainContainer(){
		
		GameEngine ge = new GameEngine();
		add(ge.mGame);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
