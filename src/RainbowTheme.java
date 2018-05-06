import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class deals with rendering and coloring the Background and pits with the colors as appropriate to the theme
 * @author Tran, Harry; Mansahia, Shahbaz Singh; Saengsavang, Monty;
 *
 */
public class RainbowTheme implements BoardStrategy {
	
	private Image img;
	
	/**
	 * Constructor
	 * @param aImage is the background image
	 */
	public RainbowTheme (Image image)
	{ 
		img = image; 
	}
	
	/**
	 * @return the background image.
	 */
	@Override
	public Image getImage() 
	{ 
		return img; 
	}
	
	
	/**
	 * Add the stones into the pit, which is a JButton
	 */
	@Override
	public void addToPit(JButton button, int numOfStones) {
		button.setIcon(new RainbowStone(numOfStones));
	}
	
	/**
	 * Set the top and bottom Jpanel colors.
	 * @param south is a JPanel at the bottom of the gameFrame
	 * @param north is a JPanel at the top of the gameFrame
	 */
	@Override
	public void changeBorderPanelColor(JPanel top, JPanel bottom) {
		top.setBackground(Color.ORANGE);
		bottom.setBackground(Color.RED);
	}
	
	/**
	 * Set the Jbuttons background color.
	 */
	@Override
	public void changeButtonColor(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);	
	}

	
}