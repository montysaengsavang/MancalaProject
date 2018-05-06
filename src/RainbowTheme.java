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
	public RainbowTheme (Image aImage){ img = aImage; }
	
	/**
	 * Set the Jbuttons background color.
	 */
	@Override
	public void setBackgroundColor(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);	
	}
	
	/**
	 * Add the stones into the pit, which is a JButton
	 */
	@Override
	public void addStone(JButton button, int totalStones) {
		button.setIcon(new RainbowStone(totalStones));
	}
	
	/**
	 * @return the background image.
	 */
	@Override
	public Image getBackgroundImage() { return img; }
	
	/**
	 * Set the top and bottom Jpanel colors.
	 * @param south is a JPanel at the bottom of the gameFrame
	 * @param north is a JPanel at the top of the gameFrame
	 */
	@Override
	public void setPanelColor(JPanel south, JPanel north) {
		south.setBackground(Color.RED);
		north.setBackground(Color.ORANGE);
	}
	
}