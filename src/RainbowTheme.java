import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RainbowTheme implements BoardStrategy {
	/**
	 * Constructor
	 * @param aImage is the background image
	 */
	public RainbowTheme (Image aImage){ img = aImage; }
	
	/**
	 * Implement the BoardStrategy.
	 * Set the button background color.
	 */
	@Override
	public void setBackgroundColor(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);	
	}
	
	/**
	 * Implement the BoardStrategy.
	 * Add the stones into the pit.
	 */
	@Override
	public void addStone(JButton button, int totalStones) {
		button.setIcon(new RainbowStone(totalStones));
	}
	
	/**
	 * Implement the BoardStrategy.
	 * Get the background image.
	 */
	@Override
	public Image getBackgroundImage() { return img; }
	
	/**
	 * Implement the BoardStrategy.
	 * Set the panel color.
	 */
	@Override
	public void setPanelColor(JPanel south, JPanel north) {
		south.setBackground(Color.PINK);
		north.setBackground(Color.ORANGE);
	}
	
	// Instance variable
	private Image img;
}