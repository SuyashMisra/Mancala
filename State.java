
import java.util.ArrayList;


/**
 *
 * @author Suyash Misra
 */
public class State {
    Pit[] pitA;
    Pit[] pitB;
    Stones mancalaA;
    Stones mancalaB;

    char Player;

    
    void setPlayer(char player){
        Player = player;
    }
    
    char getPlayer(){
        return  Player;
    }
    
    void getEmptied(){
        if(Player == 'A'){      //empty B pits
            for(int i = 0; i<6; i++){
                if(pitB[i].getAmount()!=0){
                    mancalaB.addStones(pitB[i].takeAll());
                }
            }
        }
        else{      //empty A pits
            for(int i = 0; i<6; i++){
                if(pitA[i].getAmount()!=0){
                    mancalaA.addStones(pitA[i].takeAll());
                }
            }
        }
    }

    public State(Pit[] pA, Pit[] pB, Stones manA, Stones manB, char plyr) {

        pitA = new Pit[6];
        pitB = new Pit[6];
        
        for (int i = 0; i < 6; i++)
	    pitA[i] = new Pit(pA[i].getAmount());
	    
	for (int i = 0; i < 6; i++)
            pitB[i] = new Pit(pB[i].getAmount());
        
        mancalaA = new Stones();
        mancalaB = new Stones();
        mancalaA.setStones(manA.getAmount());
        mancalaB.setStones(manB.getAmount());
        Player = plyr;
    }

    public State(State obj) {

        pitA = new Pit[6];
        pitB = new Pit[6];
        
        for (int i = 0; i < 6; i++)
	    pitA[i] = new Pit(obj.pitA[i].getAmount());
	    
	for (int i = 0; i < 6; i++)
            pitB[i] = new Pit(obj.pitB[i].getAmount());
        
        
        mancalaA = new Stones();
        mancalaB = new Stones();
        mancalaA.setStones(obj.mancalaA.getAmount());
        mancalaB.setStones(obj.mancalaB.getAmount());
        

    }

    
    ArrayList<Integer> availableMoves(){
        ArrayList<Integer> moves = new ArrayList<>();
        if(Player == 'A'){
            for(int i = 0; i < 6; i++){
                if(pitA[i].getAmount()!=0){
                    moves.add(i);
                }
            }
        }
        else{
            for(int i = 0; i < 6; i++){
                if(pitB[i].getAmount()!=0){
                    moves.add(i);
                }
            }
        }
        return moves;
    }
    
    ArrayList<State> getChildren(){
        ArrayList<State> children = new ArrayList<>();
        for (int move : this.availableMoves()) {
            State child = new State(this);
            child.Turn(Player, move);
            child.setPlayer(child.getPlayer() == 'A'?'B':'A');
            children.add(child);
        }
        return children;
    }
    
    void Turn(char Player, int pitNumber){
        
                    int stones;

                    if(Player=='A'){     //player A will pick from anyone of a[] pits

                        stones=pitA[pitNumber].takeAll();   //pick up all stones in the specified pit
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
                                    i=0;     //i is reset to 0 to point to starting of A's pit
                                    flag = false;
                                    if(stones==0 && pitA[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                        mancalaA.addStones(pitB[5-i].takeAll()+1);//take the stone and opponent pit's stones and deposit in player mancala   
                                    }
                                    else{
                                        pitA[i].addStone();
//                                        flag=false;  //indicating player depositing on own side
                                        i++;
                                    }
                                }
                                else{
                                    pitB[i].addStone();
                                    i++;
                                }
                            }
                            else{    //if i indicates player's pits
                                if(stones==0 && pitA[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                    mancalaA.addStones(pitB[5-i].takeAll()+1);//take the stone and opponent pit's stones and deposit in player mancala   
                                }
                                else{
                                    pitA[i].addStone();
                                    i++;
                                }
                            }
                        }
                    }
                    else{      //player B will pick from any one of b[] pits

                        stones=pitB[pitNumber].takeAll();   //pick up all stones in the specified pit
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
                                    flag = false;
                                    if(stones==0 && pitB[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                        mancalaB.addStones(pitA[5-i].takeAll()+1);
                                        //take the stone and opponent pit's stones and deposit in player mancala
                                    }
                                    else{

                                        pitB[i].addStone();
                                        i++;
//                                        flag=false;  //indicating player depositing on own side	            
                                    }
                                }
                                else{
                                    pitA[i].addStone();
                                    i++;
                                }
                            }
                            else{    //if i indicates player's pits
                                if(stones==0 && pitB[i].getAmount()==0){    //if last stone in hand and i points an empty pit    
                                    mancalaB.addStones(pitA[5-i].takeAll()+1);
                                    //take the stone and opponent pit's stones and deposit in player mancala
                                }
                                else{
                                    pitB[i].addStone();
                                    i++;
                                }
                            }
                        }
                    }

    }
        
        
}
