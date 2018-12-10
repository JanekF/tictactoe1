package tictactoe;

public class OpponentChoiceEvent implements TicTacToeEvent {
	
	protected Opponent opnt;
	
	public OpponentChoiceEvent(Opponent choice) {
		this.opnt = choice;
	}
	
	public OpponentChoiceEvent() {
		this.opnt = Opponent.COMPUTER;
	}
	
	public Opponent getOpponent() {
		return this.opnt;
	}

}
