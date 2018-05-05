import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class BackgroundPanel extends JPanel {

	private Image backgroundImg;
	
	public BackgroundPanel(Image img) 
	{
		backgroundImg = img;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, 1000, 700, this);
	}
}
