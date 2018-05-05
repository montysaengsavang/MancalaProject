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
   private int totalStones; // total number of stones in the pit
   private static final int pitWidth = 72; //standard pit width
   
   /**
    * Constructor 
    * @param totalStones takes the number of default stones to be added to each pit and assigns that value to the object's "totalStones" variable
    */
   public ChristmasStone (int totalStones) 
   {
      this.totalStones = totalStones;
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
   
   /**
    * Paint the stone icon
    */
   @Override
   public void paintIcon (Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      Color cRed = new Color (127, 0, 0);          //Christmassy red color
      Color cGreen1 = new Color (0, 51, 0);        //Christmassy green color
      Color cGreen2 = new Color (102, 178, 102);   //Alternate Christmassy green color
      Color cYellow = new Color (255, 255, 204);   //The color we get from the glow of decorative lights used on Christmas
      
      final int WID = 18;  
      
      for (int i = 0; i < totalStones; ++i) {
         int height = 0;
         int width = i * WID;
      
         
         while (width >= pitWidth) {
            height += 20;
            width -= pitWidth;
         }
         
         g2.setColor (cRed);
         g2.draw(new Ellipse2D.Double(width, height, WID, WID));
         
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
         
         g2.fill(new Ellipse2D.Double(width, height, WID, WID));
      }
   }
}
