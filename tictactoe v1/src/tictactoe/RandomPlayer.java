package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends PlayerAgent {

	public RandomPlayer(String name, Player player) {
		super(name, player);
	}

	@Override
	public int[] giveMove() {
		return null;
	}
	
	public int[] giveMove(Player[][] board) {
		//return null;
		
		/*
		//checking free fields
		ArrayList freeFields = new ArrayList();
		for (int i=0; i<board.length; i++) { //i assume the board is square
			for (int j=0; j<board.length; j++) {
				if (board[i][j].equals(null))
					freeFields.add(new int[] {i, j});
			}
		}
		**/
		
		int size = board.length; //i assume the board is square
		
        Random rand = new Random();
        int int1 = rand.nextInt(size);
        int int2 = rand.nextInt(size);                   
        while (board[int1][int2] != null) {		
            int1 = rand.nextInt(size);
            int2 = rand.nextInt(size); 
            }
        return new int[] {int1, int2};
	}

	@Override
	public int[] giveMove(Player[][] board, int[] click) {
		return giveMove(board);
	}

}
