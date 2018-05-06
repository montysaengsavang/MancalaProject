import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * This class creates a subclass that overrides the paintComponent function to paint a background
 * @author Tran, Harry; Mansahia, Shahbaz Singh; Saengsavang, Monty;
 *
 */
public class BackgroundPanel extends JPanel {

	private Image backgroundImg;
	
	/**
	 * Constructor that sets image as the background
	 * @param img is an image object 
	 */
	public BackgroundPanel(Image img) 
	{
		backgroundImg = img;
	}
	
	/**
	 * Overridden paintComponent function
	 * @param g is a Graphics object 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, 1000, 700, this);
	}
}
