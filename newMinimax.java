
import java.util.ArrayList;

/**
 *
 * @author Suyash Misra
 */
public class newMinimax {
    
    int bestPitFinder(State currentState){
        
        ArrayList<Integer> allPossibleMoves = currentState.availableMoves();
        
        int bestScore = Integer.MIN_VALUE;
        
        int bestMove = 0;
        
        for (Integer move : allPossibleMoves) {
            
            State movedState = new State(currentState);
            movedState.Turn(currentState.getPlayer(), move);        //make turn in the copy of the current state
            int depth = 2;      //set the depth of minimax search
            
            movedState.setPlayer('A');      //after making turn, set the player of the moved state to the opponent
            int score = minimax(movedState, depth, false);      //call minimax on this state and get the best score associated with this move
            
            if(score > bestScore){      //check if the score is bigger than the best previous score
                bestScore = Math.max(score, bestScore);
                bestMove = move;
            }
        }
        return bestMove;
    }
    
    
    int minimax(State currentState, int depth, boolean  isMaximizing){
        
        if(isTerminal(currentState) || depth == 0){
           
            return Evaluate(currentState);
        
        }
        
        if(isMaximizing){

           currentState.setPlayer('B');
           int bestScore = Integer.MIN_VALUE;
           ArrayList<State> children = currentState.getChildren();
            
           for (State child : children) {
                int score = minimax(child, depth-1, !isMaximizing);

                bestScore = Math.max(score, bestScore);
            }
           
            return bestScore;
        }
        else{

           currentState.setPlayer('A');
           int bestScore = Integer.MAX_VALUE;
           ArrayList<State> children = currentState.getChildren();
            
           for (State child : children) {
                int score = minimax(child, depth-1, isMaximizing);

                bestScore = Math.min(score, bestScore);
            }
            
           return bestScore;
        }
    }
    
    boolean isTerminal(State currState){
        
        if(currState.availableMoves().isEmpty()){
            
            currState.getEmptied();
            return true;
        
        }
        
        else
            return false;
    }
    
    int Evaluate(State currState){
        
        return currState.mancalaB.getAmount() - currState.mancalaA.getAmount();
    
    }
}
