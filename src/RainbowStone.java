import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;


public class RainbowStone implements Icon {
	// Instance variables
	private int totalStones;
	private int widthOfPit = 90;
	/**
	 * Constructor 
	 * @param stones is the amount of the stones to start with the game.
	 */
	public RainbowStone(int stones) { totalStones = stones; }

	/**
	 * Return the stone icon height.
	 */
	@Override
	public int getIconHeight() { return 0; }

	/**
	 * Return the stone icon width.
	 */
	@Override
	public int getIconWidth() { return 0; }

	/**
	 * Paint the stone icon.
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
	  Graphics2D g2 = (Graphics2D) g;
	
	  // Places stones into pit in a row
	  for (int i = 0; i < totalStones; i++) {
		int height = 0, width = i*18;
		  
		// Start the stones on next row 
		while (width >= widthOfPit) {
		  //moves down
		  height = height + 20;
		  
		  //reset width to the beginning
		  width = width - widthOfPit;
		}
		
		
		if(i % 6 == 0)
			g2.setColor(Color.RED);
		else if(i % 6 == 1)
			g2.setColor(Color.ORANGE);
		else if(i % 6 == 2)
			g2.setColor(Color.GREEN);
		else if(i % 6 == 3)
			g2.setColor(Color.YELLOW);
		else if(i % 6 == 4)
			g2.setColor(Color.MAGENTA);
		else if(i % 6 == 5)
			g2.setColor(Color.BLUE);
		
		//create and paint stone
		g2.fill(new Ellipse2D.Double(width, height, 18, 18));
	  }
	}
	
	
}
