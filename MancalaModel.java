 import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaModel
{
   protected char currentPlayer;
   private int hand;   
   private Pit[] a;   
   private Pit[] b;   
   private Stones mancalaA;   
   private Stones mancalaB;    
   private ArrayList<ChangeListener> listeners;
  
   public MancalaModel(int stoneAmount)
   {
	      hand = 0;
	      currentPlayer = 'a';
	      a = new Pit[6];
	      for (int i = 0; i < 6; i++)
	         a[i] = new Pit(stoneAmount);
	      b = new Pit[6];
	      for (int i = 0; i < 6; i++)
	         b[i] = new Pit(stoneAmount);
	      
	      mancalaA = new Stones();
	      mancalaB = new Stones(); 

	      listeners = new ArrayList<ChangeListener>();
   }
   public int getPitValue(char side, int pitNumber)
   {
      if (side == 'a')
         return a[pitNumber].getAmount();
      else if (side == 'b')
         return b[pitNumber].getAmount();

      return -1;
   }
   public int getMancalaValue(char player)
   {
      if (player == 'a')
         return mancalaA.getAmount();
      else if (player == 'b')
         return mancalaB.getAmount();
      
      System.out.println("Error: getMancalaValue()");
      return -1;
   }
   public void attach(ChangeListener c)
   {
      listeners.add(c);
   }
   
   public char getCurrPlayer()
   {
	   return currentPlayer;
   }
   
   
   public void doTurn(char player, int pitNumber)
   {
                int stones;
                if(player=='a'){     //player A will pick from anyone of a[] pits
                    stones=a[pitNumber].takeAll();   //pick up all stones in the specified pit
                    int i=pitNumber+1;   //setting i to the next pit for depositing stones
                    boolean flag=false;  //flag used to check whether to change sides while depositing
                    while(stones-->0){
                        if(i==6 && !flag){    //checking if i is indicating the mancala A, assuming 0 based pitNumber
                            mancalaA.addStones(1);
                            i=0;     //setting i to 0 to start depositing in pit b[]
                            flag=true;
                        }
                        else if(flag){   //depositing on opponent pits
                            if(i==6){    //if i reaches the opponent mancala
                                i=0;     //i is reset to 0 to point to starting of a pit
	                       
                                if(stones==0 && a[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                    mancalaA.addStones(b[5-i].takeAll()+1);
                                    //take the stone and opponent pit's stones and deposit in player mancala
                                }
                                else{
                               
                                    a[i].addStone();
                                    flag=false;  //indicating player depositing on own side
                                    i++;
                                }
                            }
                            else{
                                b[i].addStone();
                                i++;
                            }
                        }
                        else{    //if i indicates player's pits
                            if(stones==0 && a[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                mancalaA.addStones(b[5-i].takeAll()+1);//take the stone and opponent pit's stones and deposit in player mancala
	                      
                            }
                            else{
	                        a[i].addStone();
	                        i++;
                            }
                        }
	             
                    }
                    //after doing A's turn change current player to B
                    currentPlayer='b';
                    
                    State currentState = new State(a, b, mancalaA, mancalaB,'B');
                    newMinimax obj = new newMinimax();
                    int BpitNumber = obj.bestPitFinder(currentState);
                    doTurn(currentPlayer, BpitNumber);
                }
                else{      //player B will pick from any one of b[] pits
                    stones=b[pitNumber].takeAll();   //pick up all stones in the specified pit
                    int i=pitNumber+1;   //setting i to the next pit for depositing stones
                    boolean flag=false;  //flag used to check whether to change sides while depositing
                    while(stones-->0){
                        if(i==6 && !flag){    //checking if i is indicating the mancala B, assuming 0 based pitNumber
                            mancalaB.addStones(1);
                            i=0;     //setting i to 0 to start depositing in pit a[]
                            flag=true;
                        }
                        else if(flag){   //depositing on opponent pits
                            
                            if(i==6){    //if i reaches the opponent mancala
                                
                                i=0;     //mancala is skipped and i is reset to 0 to point to starting of b pit
                                
                                if(stones==0 && b[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                
                                    mancalaB.addStones(a[5-i].takeAll()+1);
                                    //take the stone and opponent pit's stones and deposit in player mancala
                                }
                                
                                else{
                                
                                b[i].addStone();
                                i++;
                                flag=false;  //indicating player depositing on own side
                            
                                }
                            }
                            else{
                                a[i].addStone();
                                i++;
                            }
                        }
                        else{    //if i indicates player's pits
                            if(stones==0 && b[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                mancalaB.addStones(a[5-i].takeAll()+1);
                                //take the stone and opponent pit's stones and deposit in player mancala
                            }
                            else{
                                b[i].addStone();
	                        i++;
                            }
                        }
                    }
                    //after doing B's turn change current player to A
                    currentPlayer='a';
                }

                changeEvent(); 
 
   }
   
  
   public char checkWinner()
   {    
       //checking if A side is empty
        boolean AsideEmpty=true;
                
        for (int i = 0; i < 6; i++) {
            if(a[i].getAmount()!=0){
                AsideEmpty=false;
                break;
            }
        }
        //if A side is empty, pick all stones from B side and place in mancala B
        if(AsideEmpty){
            for (int i = 0; i < 6; i++) {
                mancalaB.addStones(b[i].takeAll());
            }
        }


        boolean BsideEmpty=true;
        //if A side is not empty check for B side
        if(!AsideEmpty){

            for (int i = 0; i < 6; i++) {
                if(b[i].getAmount()!=0){
                    BsideEmpty=false;
                    break;
                }
            }
            //if B side is empty, pick all stones from A side and place in mancala A
            if(BsideEmpty){
                for (int i = 0; i < 6; i++) {
                    mancalaA.addStones(a[i].takeAll());
                }
            }
        }
       
        changeEvent();
        if(AsideEmpty || BsideEmpty){
            
            if(mancalaA.getAmount()> mancalaB.getAmount()){
               return 'a';
            }
            else if(mancalaA.getAmount()< mancalaB.getAmount()){
               return 'b';
            }
            else
                return 'd';     //in case of draw
        }
       
	   return 'c';
   }
   
   
  public void changeEvent()
   {      
      for (ChangeListener l : listeners)
      {
         l.stateChanged(new ChangeEvent(this));
      }
   }
   
   
  
   
}