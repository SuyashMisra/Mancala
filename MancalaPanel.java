import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class MancalaPanel extends JPanel implements ChangeListener
{
   private int stoneAmount;   
   private char playerMancala;   
   private MancalaModel mancalaModel;   
   private Interface format;
   public MancalaPanel(MancalaModel mancalaModel, char playerMancala)
   {
      this.mancalaModel = mancalaModel;
      this.playerMancala = playerMancala;
      
      stoneAmount = mancalaModel.getMancalaValue(playerMancala);
   }
   public void setFormat(Interface format)
   {
      this.format = format;
   }
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(format.getColor());
      g2.draw(format.getMancalaShape());
     // g2.setColor(format.getFillColor());
      for (Shape s : format.getMancalaStoneShapes(stoneAmount))
      	g2.fill(s);
      g2.setColor(Color.BLACK);
      g2.drawString("Player " + Character.toUpperCase(playerMancala), 50,  220);
   }
   public void stateChanged(ChangeEvent e)
   {
      stoneAmount = mancalaModel.getMancalaValue(playerMancala);
   }
}