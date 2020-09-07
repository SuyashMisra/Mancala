import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PitPanel extends JPanel implements ChangeListener, MouseListener
{
   private int stoneAmount;
   private char pitSide;
   private int pitNumber;
   private MancalaModel mancalaModel;
   private Interface format;
  
   public PitPanel(MancalaModel mancalaModel, char pitSide, int pitNumber)
   {
      this.mancalaModel = mancalaModel;
      this.pitSide = pitSide;
      this.pitNumber = pitNumber;
      
      //Only add mouse listener for player A 
      if(pitSide == 'a')
        this.addMouseListener(this);
      
      stoneAmount = mancalaModel.getPitValue(pitSide, pitNumber);
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
      g2.draw(format.getPitShape());
      g2.setColor(format.getFillColor());
      for (Shape s : format.getPitStoneShapes(stoneAmount))
    	  g2.fill(s);
   }
  
   public void stateChanged(ChangeEvent e)
   {
      stoneAmount = mancalaModel.getPitValue(pitSide, pitNumber);
   }
   
   public void mouseClicked(MouseEvent arg0)
   {
      
   }

   public void mouseEntered(MouseEvent e)
   {
     
   }

   public void mouseExited(MouseEvent e)
   {

   }

   public void mousePressed(MouseEvent e)
   {
       if(mancalaModel.currentPlayer=='a')
           if(mancalaModel.getPitValue(pitSide, pitNumber) != 0)
            mancalaModel.doTurn(pitSide, pitNumber);
   }

   public void mouseReleased(MouseEvent e)
   {
      
   }
}