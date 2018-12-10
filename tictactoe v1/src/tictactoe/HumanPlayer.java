package tictactoe;

public class HumanPlayer extends PlayerAgent
{

	public HumanPlayer(String name, Player player) {
		super(name, player);
	}

	@Override
	public int[] giveMove() {
		return null;
	}
	
	public int[] giveMove(Player[][] board) {
		return null;
	}

	@Override
	public int[] giveMove(Player[][] board, int[] click) {
		return click;
	}

}
