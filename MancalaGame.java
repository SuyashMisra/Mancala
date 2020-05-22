import javax.swing.JOptionPane;

public class MancalaGame 
{
	private MancalaModel model;
	private GameBoard board;
	
	public static void main(String [] args)
	{
		MancalaGame m = new MancalaGame();
	}
	public MancalaGame()
	{
		initGame();
	}
	public void initGame()
	{
		if (board != null)
		{
			board.dispose();
		}
		String initPits = "4";
		model = new MancalaModel(Integer.parseInt(initPits));
		board = new GameBoard(this, model, new MancalaDesign());
	}
	public void endGame(char winner)
	{       
            if(winner == 'a' || winner == 'b'){
		int option = JOptionPane.showConfirmDialog(board, "Player " + Character.toUpperCase(winner) + " wins!\nDo you wish to play again?", "Congratulations!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.NO_OPTION)
		{
			board.dispose();
			System.exit(0);
		}
		else
		{
			initGame();
		}
            }
            else{
                int option = JOptionPane.showConfirmDialog(board, "It's a draw!\nDo you wish to play again?", "Congratulations!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.NO_OPTION)
		{
			board.dispose();
			System.exit(0);
		}
		else
		{
			initGame();
		}
            }
	}
}