import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This interface creates a layout for the functions needed to implement a theme
 * @author Tran, Harry; Mansahia, Shahbaz Singh; Saengsavang, Monty;
 *
 */
public interface BoardStrategy {
	
	/**
	 * Sets color of buttons
	 * @param button
	 */
	public void setBackgroundColor(JButton button);
	
	/**
	 * Adds stone icons to Jbuttons
	 * @param buttons
	 * @param totalStones
	 */
	public void addStone(JButton buttons, int totalStones);
	
	/**
	 * Returns the background image.
	 * @return the background image.
	 */
	public Image getBackgroundImage();
	
	/**
	 * Set the top and bottom color of panels of the board frame
	 * @param south is the bottom panel
	 * @param north is the top panel 
	 */
	public void setPanelColor (JPanel south, JPanel north);
}
