package com.kkaysheva.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * GameActivity for tic tac toe
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 16/10/2018
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SIZE = 3;
    private final Button[][] buttons = new Button[SIZE][SIZE];
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Board board = new Board();
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private void player1Wins() {
        player1.incrementPoints();
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointText();
        board.resetBoard(buttons);
    }

    private void player2Wins() {
        player2.incrementPoints();
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointText();
        board.resetBoard(buttons);
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        board.resetBoard(buttons);
    }

    private void updatePointText() {
        textViewPlayer1.setText(String.format("Player 1: %s", player1.getPoints()));
        textViewPlayer2.setText(String.format("Player 2: %s", player2.getPoints()));
    }

    private void resetGame() {
        player1.resetPoints();
        player2.resetPoints();
        updatePointText();
        board.resetBoard(buttons);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String buttonId = String.format("button_%s%s", i, j);
                int resId = getResources()
                        .getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().equals("")) {
            return;
        }
        if (board.isPlayer1Turn()) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        board.incrementRoundCount();
        if (board.checkForWin(buttons)) {
            if (board.isPlayer1Turn()) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (board.getRoundCount() == 9) {
            draw();
        } else {
            board.setPlayer1Turn(!board.isPlayer1Turn());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("board", board);
        outState.putSerializable("player1", player1);
        outState.putSerializable("player2", player2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        board = (Board) savedInstanceState.getSerializable("board");
        player1 = (Player) savedInstanceState.getSerializable("player1");
        player2 = (Player) savedInstanceState.getSerializable("player2");
    }
}
