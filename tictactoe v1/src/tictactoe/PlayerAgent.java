package tictactoe;

public abstract class PlayerAgent {
	
	protected String name;
	protected final Player symbol;
	
	public PlayerAgent(String name, Player symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	public abstract int[] giveMove();
	
	public Player getSymbol() {
		return this.symbol;
	}
	
	public abstract int[] giveMove(Player[][] board);
	
	public abstract int[] giveMove(Player[][] board, int[] click);
}
