package vermeer.luc.tictactoe;

import android.service.quicksettings.Tile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void resetClicked() {
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void tileClicked(View view) {
        TextView gameText = (TextView) findViewById(R.id.gameText);
        if (game.getGameState() != GameState.IN_PROGRESS) {
            gameText.setText("Press reset to start a new game!");
            return;
        }
        Button tile = (Button) view;
        int id = tile.getId();
        int[] coordinates = getCoordinates(id);
        int row = coordinates[0];
        int column = coordinates[1];
        TileState state = game.choose(row, column);
        Log.d("New tilestate", state.toString());

        switch(state) {
            case CROSS:
                tile.setText("X");
                gameText.setText("O's turn");
                break;
            case CIRCLE:
                gameText.setText("X's turn");
                tile.setText("O");
                break;
            case INVALID:
                break;
        }
        checkGameState();
    }

    private void checkGameState(){
        GameState gameState = game.getGameState();
        TextView gameText = (TextView) findViewById(R.id.gameText);
        if (gameState == GameState.PLAYER_ONE) {
            gameText.setText("Player X won!");
        } else if (gameState == GameState.PLAYER_TWO) {
            gameText.setText("Player O won!");
        } else if (gameState == GameState.DRAW) {
            gameText.setText("Tie!");
        }
    }

    private int[] getCoordinates(int id){
        int[] x;
        x = new int[2];
        int row, column;
        switch(id) {
            case R.id.b00:
                row = 0;
                column = 0;
                break;
            case R.id.b01:
                row = 0;
                column = 1;
                break;
            case R.id.b02:
                row = 0;
                column = 2;
                break;
            case R.id.b10:
                row = 1;
                column = 0;
                break;
            case R.id.b11:
                row = 1;
                column = 1;
                break;
            case R.id.b12:
                row = 1;
                column = 2;
                break;
            case R.id.b20:
                row = 2;
                column = 0;
                break;
            case R.id.b21:
                row = 2;
                column = 1;
                break;
            default:
                row = 2;
                column = 2;
                break;
        }
        x[0] = row;
        x[1] = column;
        return ( x );
    }
}
