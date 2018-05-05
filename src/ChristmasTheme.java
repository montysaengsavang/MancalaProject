import java.awt.Color;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class deals with rendering and coloring the Background and pits with the colors as appropriate to the theme
 * @author Tran, Harry; Mansahia, Shahbaz Singh; Saengsavang, Monty;
 *
 */

public class ChristmasTheme implements BoardStrategy
{
   
   private Image img;
   
   /**
    * Constructor
    * @param img  is the image given to the object which is to be assigned as the background image
    */
   
   public ChristmasTheme (Image img)
   {
      this.img = img;
   }
   
   /**
    * Implement the BoardStrategy.
    * Set the button background Color.
    */
   @Override
   public void setBackgroundColor (JButton button) 
   {
      button.setBackground (Color.LIGHT_GRAY);
   }
   
   /**
    * Implement the BoardStrategy.
    * Add the stones to the pit.
    */
   
   @Override
   public void addStone (JButton button, int totalStones) 
   {
      button.setIcon(new ChristmasStone(totalStones));
   }
   
   /**
    * Implement the BoardStrategy.
    * Get the Background Image.
    */
   
   @Override
   public Image getBackgroundImage () 
   {
      return img;
   }
   
   /**
    * Implement the BoardStrategy
    * Set the Panel colors.
    */
   @Override
   public void setPanelColor (JPanel south, JPanel north)
   {
      north.setBackground (new Color (170, 0, 0));
      south.setBackground (new Color (0, 51, 0));
   }
   
}