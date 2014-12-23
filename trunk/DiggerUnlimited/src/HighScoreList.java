
import java.awt.*;
import java.io.*;

import javax.swing.*;

import java.awt.Graphics2D;

public class HighScoreList extends Menu{
	
	Button back;

	private String names[];
	private int scores;
	
	public HighScoreList(GameEngine g){
		super(g);
		
		image= new ImageIcon("High-Score-List.png").getImage();
		back = new Button("Back Button", 1040,640,140,35);
		ge = g;
	}
	
	public void paint(Graphics2D g){
		
		back.paint(g);
		g.drawImage(image,0,0,null);
		//g.setFont(new Font("Comic Sans", Font.BOLD, 60));
		//g.setColor(Color.RED);
		
		//g.drawString("back",1050,680);
		//g.drawString("High Score List",300,60);
		
	}
	
	public void mouseClicked(String whichButton){
		if(whichButton.equals("Back Button"))
			ge.mGame.setGuiState(GameGUI.State.menu);
	}
}