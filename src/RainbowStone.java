import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

/**
 * This class deals with rendering and coloring the stones in the pits with the colors as appropriate to the theme.
 * @author Tran, Harry; Mansahia, Shahbaz Singh; Saengsavang, Monty;
 *
 */

public class RainbowStone implements Icon {
	// Instance variables
	private int totalStones;
	private static final int pitWidth = 72;
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
	
	  // Places white stones into pits
	  for (int i = 0; i < totalStones; i++) {
		int height = 0, width = i*18;
		  
		// Stack the stones to the next row if there are more than 4 stones in the pit.
		while (width >= pitWidth) {
		  height += 20;
		  width -= pitWidth;
		}
		
		
		g2.setColor(Color.RED);
		g2.draw(new Ellipse2D.Double(width, height, 18, 18));
		
		if(i % 6 == 1)
			g2.setColor(Color.ORANGE);
		else if(i % 6 == 2)
			g2.setColor(Color.PINK);
		else if(i % 6 == 3)
			g2.setColor(Color.GREEN);
		else if(i % 6 == 4)
			g2.setColor(Color.BLUE);
		else if(i % 6 == 5)
			g2.setColor(Color.YELLOW);
		else if(i % 6 == 0)
			g2.setColor(Color.MAGENTA);
		
		g2.fill(new Ellipse2D.Double(width, height, 18, 18));
	  }
	}
	
	
}
