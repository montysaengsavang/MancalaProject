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
	private int numOfStones;
		
	/**
	 * Constructor 
	 * @param stones is the amount of the stones to start with the game.
	 */
	public RainbowStone(int stones) 
	{ 
		this.numOfStones = stones; 
	}

	/**
	 * creates a gui of stone icons
	 * @param c, a component
	 * @param g, a Graphics object
	 * @param x,y location
	 * 
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
	  Graphics2D g2 = (Graphics2D) g;
	
	  //iterate for as many stones we need
	  for (int i = 0; i < numOfStones; i++) 
	  {
		int stoneWidth = 18;
		int height = 0;
		int width = i * stoneWidth;
		  
		// if width exceeds the pit width, start on a new line by adding 20 to the height to move down a row
		while(width >= 80) //80 is pit width
		{
		  height = height + 20;
		  width = width - 80; //reset width back to beginning of row
		}
		
		
		g2.setColor(Color.RED);
		g2.draw(new Ellipse2D.Double(width, height, stoneWidth, stoneWidth));
		
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
		
		g2.fill(new Ellipse2D.Double(width, height, stoneWidth, stoneWidth));
	  }
	}
	
	/**
	 * @return the stone icons height.
	 */
	@Override
	public int getIconHeight() 
	{ 
		return 0; 
	}

	/**
	 * @return the stone icons width.
	 */
	@Override
	public int getIconWidth() 
	{ 
		return 0;
	}

}
