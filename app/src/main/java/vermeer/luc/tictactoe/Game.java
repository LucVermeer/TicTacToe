package vermeer.luc.tictactoe;

import android.util.Log;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;
    private GameState gameState;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;
        playerOneTurn = true;
        gameOver = false;
        gameState = GameState.IN_PROGRESS;
    }

    public TileState[][] getBoard() {
        return board;
    }

    public void setBoard(TileState[][] newBoard){
        this.board = newBoard;
    }

    public GameState getGameState() {
        GameState returnState = gameState;
        return returnState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public TileState choose(int row, int column) {
        TileState clickedTile = board[row][column];
        Log.d("Old tilestate", clickedTile.toString());
        if (clickedTile != TileState.BLANK){
            return TileState.INVALID;
        }

        if (playerOneTurn) {
            movesPlayed++;
            board[row][column] = TileState.CROSS;
            checkGame(row, column);
            playerOneTurn = false;
            return TileState.CROSS;
        } else {
            movesPlayed++;
            board[row][column] = TileState.CIRCLE;
            checkGame(row, column);
            playerOneTurn = true;
            return TileState.CIRCLE;
        }
    }

    private void checkGame(int row, int column) {
        if (board[row][0] == board[row][1] &&
                board[row][1] == board[row][2] &&
                board[row][0] != TileState.BLANK) {
            // won over horizontal
            setWinnerChickenDinner();
        }

        if (board[0][column] == board[1][column] &&
                board[1][column] == board[2][column] &&
                board[0][column] != TileState.BLANK) {
            // won over vertical
            setWinnerChickenDinner();
        }

        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] &&
                board[1][1] != TileState.BLANK) {
            // won diagonal left to right
            setWinnerChickenDinner();
        }

        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0] &&
                board[1][1] != TileState.BLANK){
            // won diagonal right to left
            setWinnerChickenDinner();
        }

        if (movesPlayed == 9 && gameState == GameState.IN_PROGRESS) {
            // draw
            gameOver = true;
            gameState = GameState.DRAW;
        }
    }

    private GameState setWinnerChickenDinner(){
        gameOver = true;
        if (playerOneTurn) {
            gameState = GameState.PLAYER_ONE;
            return gameState;
        } else {
            gameState = GameState.PLAYER_TWO;
            return gameState;
        }
    }
}
