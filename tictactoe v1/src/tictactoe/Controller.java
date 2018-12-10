package tictactoe;

import java.util.Optional;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    protected GridPane grid;

    protected boolean gameEnded;
    protected Board board;
    //protected TicTacToeEventHandler newGameEventHandler = new TicTacToeEventHandler();

    public void initialize() {
    	
        Alert alert = new Alert(AlertType.WARNING, "Do you want to play against the computer?", ButtonType.YES, ButtonType.NO);
        
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.isPresent() && result.get() == ButtonType.YES) {
    		//this.board = new Board(Opponent.HUMAN, Opponent.COMPUTER);
    		handleOpponentChoiceEvent(new OpponentChoiceEvent(Opponent.COMPUTER));
    	}
    	if (result.isPresent() && result.get() == ButtonType.NO) {
    		//this.board = new Board(Opponent.HUMAN, Opponent.HUMAN);
    		handleOpponentChoiceEvent(new OpponentChoiceEvent(Opponent.HUMAN));
    	}

    		
    		
    	
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            child.setOnMouseClicked(event -> handleMove(row, column));
        }
        gameEnded = false;
        //sboard = new Board();
    }

    private void handleMove(Integer row, Integer column) {
        if (!gameEnded) {
        	
        	int[] click = new int[] {row, column};
        	
        	int[] move = board.giveMove(board.getBoard(), click);
        	
        	if (board.getPlayerForField(move[0], move[1]) == null) {
        		board.makeMove(move[0], move[1]);
        		drawAndCheckEnd();
        	}
            else {
            	Alert fieldOccupied = new Alert(AlertType.WARNING, "The field is occupied. Choose another one.", ButtonType.OK);
            	fieldOccupied.showAndWait();
            }
        	
        	/*
            if (board.getPlayerForField(row, column) == null) {
                board.makeMove(row, column);
                
                drawAndCheckEnd();

                //RANDOM MOVE
                int[] randomMove = board.giveMove(board.getCurrentPlayer(), board.getBoard());
                board.makeMove(randomMove[0], randomMove[1]);
      
                
                //int[] circleMove = this.board.giveCircleMove(this.board.getBoard());
                
                                
                drawAndCheckEnd();

            }
            else {
            	Alert fieldOccupied = new Alert(AlertType.WARNING, "The field is occupied. Choose another one.", ButtonType.OK);
            	fieldOccupied.showAndWait();
            }
			*/
        }
    }

    private void drawBoard() {
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            Pane pane = (Pane) child;
            pane.getChildren().clear();
            Player inCell = board.getPlayerForField(row, column);
            if (inCell != null) {
                Label label = new Label(inCell.getSign());
                label.setPrefWidth(30.0);
                label.setPrefHeight(30.0);
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
            }
        }
    }

    private boolean showVictoryMessage(Player wins) {  //returns if the player wants to play again
        Alert alert = new Alert(AlertType.WARNING, "Player " + wins + " wins! Play again?", ButtonType.OK);
        
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.isPresent() && result.get() == ButtonType.OK) {
    		//initialize();
    		return true;
    	}
    	return false;
    }
    
    private boolean showTieMessage() {  //same here
        Alert alert = new Alert(AlertType.WARNING, "A tie! Play again?", ButtonType.OK);
        
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.isPresent() && result.get() == ButtonType.OK) {
    		//initialize();
    		return true;
    	}
    	return false;
    }
    
    private void drawAndCheckEnd() {
        drawBoard();                
        Player wins = board.checkVictory();
        if (wins != null) {
            gameEnded = true;
            if(showVictoryMessage(wins))
            	handleEvent(new NewGameEvent(Opponent.COMPUTER));
            
        }
        //checking tie
        if (this.board.checkTie()) {
            gameEnded = true;
            if (showTieMessage())
            	handleEvent(new NewGameEvent(Opponent.COMPUTER));
        }
    }
    
    public <T extends TicTacToeEvent> void handleEvent(T event) {
    	if (event.getClass().equals(new NewGameEvent().getClass())) {
    		initialize();
    	}	
    }
    
    public void handleOpponentChoiceEvent(OpponentChoiceEvent evt) {
    	switch (evt.getOpponent()) {
    	case HUMAN: this.board = new Board(Opponent.HUMAN, Opponent.HUMAN); break;
    	case COMPUTER: this.board = new Board(Opponent.HUMAN, Opponent.COMPUTER); break;
    	}
    	
    }
    
    
}
