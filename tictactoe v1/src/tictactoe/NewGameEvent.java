package tictactoe;

public class NewGameEvent implements TicTacToeEvent {
	
	protected Opponent opponent;
	
	public NewGameEvent() {
		this.opponent = Opponent.COMPUTER;
	}
	
	public NewGameEvent(Opponent opn) {
		this.opponent = opn;
	}
	
	public Opponent getOpponent() {
		return opponent;
	}

}
