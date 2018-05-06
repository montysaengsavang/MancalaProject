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
    * Returns the background image
    * @return img, the background image
    */
   @Override
   public Image getImage() 
   {
      return img;
   }
   
   
   /**
    * Set the button background Color of Jbuttons
    */
   @Override
   public void changeButtonColor (JButton button) 
   {
      button.setBackground (Color.LIGHT_GRAY);
   }
   
   /**
    * Add the stones to the pits, which are Jbuttons
    */
   @Override
   public void addToPit(JButton button, int numOfStones) 
   {
      button.setIcon(new ChristmasStone(numOfStones));
   }
  
   
   /**
    * Sets the colors of the top and bottom JPanels
    * @param south is the bottom panel of the gameFrame
    * @param north is the top panel of the gameFrame
    */
   @Override
   public void changeBorderPanelColor (JPanel top, JPanel bottom)
   {
      top.setBackground (new Color (170, 0, 0));
      bottom.setBackground (new Color (0, 51, 0));
   }
   
}
