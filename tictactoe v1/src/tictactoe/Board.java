package tictactoe;

/**
 * Created by pwilkin on 08-Nov-18.
 */
public class Board {

    protected Player[][] board;
    protected PlayerAgent playerCross; 
    protected PlayerAgent playerCircle;
    protected Player currentPlayer = Player.CROSS;
    protected int movesMade = 0;
    protected int movesPossible; // moves possible before the tie;

    public Board() {
        this.board = new Player[3][3];
        this.movesPossible = 3*3;
        playerCross = new HumanPlayer("human", Player.CROSS);
        playerCircle = new RandomPlayer("computer", Player.CIRCLE);
    }
    
    public Board(Opponent playerCrss, Opponent playerCrcl) {
    	switch (playerCrss) {
    	case HUMAN: this.playerCross = new HumanPlayer("human", Player.CROSS);
    		break;
    	case COMPUTER: this.playerCross = new RandomPlayer("computer", Player.CIRCLE);
    		break;
    	}		
    	switch (playerCrcl) {
    	case HUMAN: this.playerCircle = new HumanPlayer("human2", Player.CROSS);
    		break;
    	case COMPUTER: this.playerCircle = new RandomPlayer("computer", Player.CIRCLE);
    		break;
    	}
    	this.board = new Player[3][3];
    	this.movesPossible = 3*3;
    }
    

    public Player getPlayerForField(int row, int col) {
        return board[row][col];
    }

    public void makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            this.movesMade++;
            
        } else {
            throw new IllegalArgumentException("Row " + row + ", column " + col + " is already occupied!");
        }
        currentPlayer = currentPlayer == Player.CROSS ? Player.CIRCLE : Player.CROSS;
    }
    
    public void makeMove(int row, int col, Player player) {
        if (board[row][col] == null) {
            board[row][col] = player;
        } else {
            throw new IllegalArgumentException("Row " + row + ", column " + col + " is already occupied!");
        }
        currentPlayer = currentPlayer == Player.CROSS ? Player.CIRCLE : Player.CROSS;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private Player checkRows() {
        for (int i = 0; i < 3; i++) {
            Player first = board[0][i];
            if (first != null) {
                boolean wins = true;
                for (int j = 1; j < 3; j++) {
                    if (first != board[j][i]) {
                        wins = false;
                        break;
                    }
                }
                if (wins) {
                    return first;
                }
            }
        }
        return null;
    }

    private Player checkColumns() {
        for (int i = 0; i < 3; i++) {
            Player first = board[i][0];
            if (first != null) {
                boolean wins = true;
                for (int j = 1; j < 3; j++) {
                    if (first != board[i][j]) {
                        wins = false;
                        break;
                    }
                }
                if (wins) {
                    return first;
                }
            }
        }
        return null;
    }

    private Player checkTopLeftDiagonal() {
        Player first = board[0][0];
        if (first != null) {
            boolean wins = true;
            for (int i = 1; i < 3; i++) {
                if (first != board[i][i]) {
                    wins = false;
                    break;
                }
            }
            if (wins) {
                return first;
            }
        }
        return null;
    }

    private Player checkTopRightDiagonal() {
        Player first = board[0][2];
        if (first != null) {
            boolean wins = true;
            for (int i = 1; i < 3; i++) {
                if (first != board[i][2-i]) {
                    wins = false;
                    break;
                }
            }
            if (wins) {
                return first;
            }
        }
        return null;
    }

    public Player checkVictory() {
        // check rows
        Player winner = checkRows();
        // check columns
        if (winner == null) {
            winner = checkColumns();
        }
        // check first diagonal
        if (winner == null) {
            winner = checkTopLeftDiagonal();
        }
        // check second diagonal
        if (winner == null) {
            winner = checkTopRightDiagonal();
        }
        return winner;
    }
    
    public boolean checkTie() { //checking for a tie
    	return ((this.movesMade == this.movesPossible) ? true : false);
    }
    
    public Player[][] getBoard() {
    	return this.board;
    }
    
    public int[] giveMove(Player player, Player[][] board) {
    	
    	if (player.equals(playerCross.getSymbol()))
    		return playerCross.giveMove(board);
    	else
    		return playerCircle.giveMove(board);
    }
    
    public int[] giveMove(Player[][] board, int[] click) {
    	Player current = this.currentPlayer;
    	return ( (current.equals(playerCross.getSymbol())) ? playerCross.giveMove(board, click) : playerCircle.giveMove(board, click) );
    	
    	//if (current.equals(playerCross.getSymbol()))
    	//	return playerCross.giveMove(board);
    	//else
    	//	return playerCircle.giveMove(board);
    }
    
    public int[] giveCrossMove() {
    	return this.playerCircle.giveMove();
    }
    
    public int[] giveCircleMove() {
    	return this.playerCircle.giveMove();
    }
    
    public int[] giveCircleMove(Player[][] brd) {
    	return this.playerCircle.giveMove(brd);
    }
    
}
