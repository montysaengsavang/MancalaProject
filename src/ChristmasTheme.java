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
    * @param img is the image to be assigned as the background image
    */
   public ChristmasTheme (Image img)
   {
      this.img = img;
   }
   
   /**
    * Set the button background Color of Jbuttons
    */
   @Override
   public void setBackgroundColor (JButton button) 
   {
      button.setBackground (Color.LIGHT_GRAY);
   }
   
   /**
    * Add the stones to the pits, which are Jbuttons
    */
   @Override
   public void addStone (JButton button, int totalStones) 
   {
      button.setIcon(new ChristmasStone(totalStones));
   }
   
   /**
    * Returns the background image
    * @return img, the background image
    */
   
   @Override
   public Image getBackgroundImage () 
   {
      return img;
   }
   
   /**
    * Sets the colors of the top and bottom JPanels
    * @param south is the bottom panel of the gameFrame
    * @param north is the top panel of the gameFrame
    */
   @Override
   public void setPanelColor (JPanel south, JPanel north)
   {
      north.setBackground (new Color (170, 0, 0));
      south.setBackground (new Color (0, 51, 0));
   }
   
}
