import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

public interface BoardStrategy {
	/**
	 * Set color of buttons
	 * @param button
	 */
	public void setBackgroundColor(JButton button);
	
	/**
	 * Add stone icons to buttons
	 * @param buttons
	 * @param totalStones
	 */
	public void addStone(JButton buttons, int totalStones);
	
	/**
	 * Get the background image.
	 * @return the background image.
	 */
	public Image setBackgroundImage();
	
	/**
	 * Set the panel's background colors.
	 * @param south is the south panel display the players' turns.
	 * @param north is the north panel display the undo button.
	 */
	public void setPanelColor (JPanel south, JPanel north);
}
