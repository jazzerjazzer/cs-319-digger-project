import java.awt.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class NameScreen {
	
	protected Image image1;
	protected Image image2;
	
	Button skip;
	Button enter;
	
	public JPanel namePanel;
	public JTextField name;
	public JLabel nameLabel;
	
	
	GameEngine ge;
	ArrayList<Button> nameButtons;
	
	public NameScreen(GameEngine g){
	
		
		nameButtons  = new ArrayList<Button>();
		
		//image1 = new ImageIcon("Name-Screen-congrat.png").getImage();
		//image2 = new ImageIcon("Name-Screen-game-over.png").getImage();
		
		
		skip = new Button("Skip",400,400,100,40);
		enter = new Button("Enter",680,400,300,40);
		
		
	
		ge = g;
	}
	
	
	public void paint(Graphics2D g){
		
		for(int i = 0; i < nameButtons.size(); i++)
			nameButtons.get(i).paint(g);
		//g.drawImage(image1,0,0,null);
		
		
	}
	
	
	public void mouseClicked(String whichButton){
		if(whichButton.equals("Skip")){
			
			ge.mGame.setGuiState(GameGUI.State.highScoreList);
		}
		
		else if(whichButton.equals("Enter")){
			
			ge.mGame.setGuiState(GameGUI.State.highScoreList);
		}
			
		
	}
	
	public void keyPressed(String whichButton){
		
	}
	
	
	

}
