import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameBoard extends JFrame implements ChangeListener
{
   public static final int DEFAULT_WIDTH = 1100;   
   public static final int DEFAULT_HEIGHT = 600;
   
   private MancalaGame game;
   private MancalaModel mancalaModel;
   boolean winCheck = false;
   
  
   public GameBoard(MancalaGame game, MancalaModel mancalaModel, Interface format)
   {
	   this.game = game;
	   this.mancalaModel = mancalaModel;
	   
	   mancalaModel.attach(this);
	   
	
	   TopPanel top = new TopPanel(game, mancalaModel);
	   mancalaModel.attach(top);
	   top.setBackground(Color.yellow);
     
     
       MancalaPanel mancalaA = new MancalaPanel(mancalaModel, 'a');
       mancalaA.setFormat(format);
       mancalaA.setBackground(Color.ORANGE);
       mancalaModel.attach(mancalaA);
       PitPanel[] a = new PitPanel[6];
       for (int i = 0; i < 6; i++)
       {
          a[i] = new PitPanel(mancalaModel, 'a', i);
          a[i].setFormat(format);
          a[i].setBackground(Color.ORANGE);
          mancalaModel.attach(a[i]);
       }
       
       MancalaPanel mancalaB = new MancalaPanel(mancalaModel, 'b');
       mancalaB.setFormat(format);
      mancalaB.setBackground(Color.GREEN);
       mancalaModel.attach(mancalaB);
       PitPanel[] b = new PitPanel[6];
       for (int i = 0; i < 6; i++)
       {
          b[i] = new PitPanel(mancalaModel, 'b', i);
          b[i].setFormat(format);
          b[i].setBackground(Color.GREEN);
          mancalaModel.attach(b[i]);
       }
       
      
       JPanel middlePits = new JPanel();
       GridLayout middleLayout = new GridLayout(2,6);
       middleLayout.setHgap(17);
       middlePits.setLayout(middleLayout);
       for (int i = 5; i >= 0; i--)
          middlePits.add(b[i]);
             for (int i = 0; i < 6; i++)
          middlePits.add(a[i]);       
       
       JPanel mainPanel = new JPanel();
       mainPanel.setLayout(new BorderLayout());
       top.setPreferredSize(new Dimension(800, 100));
       mainPanel.add(top, BorderLayout.PAGE_START);
       mancalaB.setPreferredSize(new Dimension(150, 500));
       mainPanel.add(mancalaB, BorderLayout.LINE_START);
       middlePits.setPreferredSize(new Dimension(600, 500));
       middlePits.setBackground(Color.yellow);
       mainPanel.add(middlePits, BorderLayout.CENTER);
       mancalaA.setPreferredSize(new Dimension(150, 400));
       mainPanel.add(mancalaA, BorderLayout.LINE_END);
       
       add(mainPanel);    
       
	   setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	   setTitle("Mancala");
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setResizable(false);
	   setVisible(true);	   
   }
  


   public void stateChanged(ChangeEvent e) 
   {
		repaint();  
                
		if (!winCheck)
		{
			winCheck = true;
			char winner = mancalaModel.checkWinner();
			if (winner =='a' || winner =='b')
			{
				repaint();
				game.endGame(winner);
			}
                        else if(winner == 'd'){
                            repaint();
                            game.endGame(winner);
                        }
			else
				winCheck = false;
		}
   }
}