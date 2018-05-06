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
	 * Returns the background image.
	 * @return the background image.
	 */
	public Image getImage();
	
	/**
	 * Adds stone icons to Jbutton
	 * @param buttons
	 * @param totalStones
	 */
	public void addToPit(JButton button, int numOfStones);
	
	/**
	 * Set the top and bottom color of panels of the board frame
	 * @param south is the bottom panel
	 * @param north is the top panel 
	 */
	public void changeBorderPanelColor (JPanel top, JPanel bottom);
	
	/**
	 * Sets color of buttons
	 * @param button
	 */
	public void changeButtonColor(JButton button);
}
