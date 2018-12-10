package tictactoe;

public interface TicTacToeEventHandler<T extends TicTacToeEvent> {
	
	public void handleEvent(T event);

}
