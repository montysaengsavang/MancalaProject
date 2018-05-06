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
public class ChristmasStone implements Icon
{
   private int numOfStones; // total number of stones in the pit
   
   /**
    * Constructor 
    * @param totalStones takes the number of default stones to be added to each pit and assigns that value to the object's "totalStones" variable
    */
   public ChristmasStone (int totalStones) 
   {
      this.numOfStones = totalStones;
   }

   
   /**
    * Paint the stone icon with different colors each stone
    * @param c, Component
    * @param g, Graphics object
    * @param x,y location
    */
   @Override
   public void paintIcon (Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      Color cRed = new Color (127, 0, 0);          //Christmassy red color
      Color cGreen1 = new Color (0, 51, 0);        //Christmassy green color
      Color cGreen2 = new Color (102, 178, 102);   //Alternate Christmassy green color
      Color cYellow = new Color (255, 255, 204);   //The color we get from the glow of decorative lights used on Christmas
      
      
      for (int i = 0; i < numOfStones; ++i) 
      {
         int stoneWidth = 18;
         int width = i * stoneWidth; //18 is width of stone
         int height = 0;
         
         //while width is greater than the pit width, increase 
         while(width >= 80) //80 is width of pit
         {
            height = height + 20; //add 20 to height to move down a row
            width = width - 80; //reset width back to beginning of row
         }
         
         g2.setColor (cRed);
         g2.draw(new Ellipse2D.Double(width, height, stoneWidth, stoneWidth));
         
         int n = i % 3;
         
         switch (n) {
            
            case 0:
               g2.setColor(cGreen1);
               break;
            
            case 1:
               g2.setColor(cGreen2);
               break;
            
            case 2:
               g2.setColor(cYellow);
               break;
            
            default:
               System.out.println("Error in ChristmasStone color mutator and renderer switch case statement");
               break;
         }
         
         g2.fill(new Ellipse2D.Double(width, height, stoneWidth, stoneWidth));
      }
   }
   
   /**
    * Return the stone icon Height
    */
   @Override
   public int getIconHeight () 
   {
      return 0;
   }
   
   /**
    * Return the stone icon Width
    */
   @Override
   public int getIconWidth()
   {
      return 0;
   }
}
