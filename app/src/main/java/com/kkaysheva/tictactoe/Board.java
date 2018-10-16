package com.kkaysheva.tictactoe;

import android.widget.Button;

import java.io.Serializable;

/**
 * Board for tic tac toe
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 16/10/2018
 */
public class Board implements Serializable {

    /* count of rounds */
    private int roundCount;

    /* fist player start */
    private boolean player1Turn = true;

    /**
     * Method checkForWin check current situation on board
     * @param cells of board
     * @return result
     */
    public boolean checkForWin(Button[][] cells) {
        boolean result = false;
        int size = cells.length;
        String[][] field = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = cells[i][j].getText().toString();
            }
        }
        for (int i = 0; i < size; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                result = true;
                break;
            }
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                result = true;
                break;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            result = true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            result = true;
        }
        return result;
    }

    /**
     * Method resetBoard - reset board
     * @param cells of board
     */
    public void resetBoard(Button[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    /**
     * Method isPlayer1Turn - check is first player starting
     * @return result
     */
    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    /**
     * Method setPlayer1Turn set first player starting or not
     * @param player1Turn value
     */
    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    /**
     * Method - getRoundCount
     * @return round count
     */
    public int getRoundCount() {
        return roundCount;
    }

    /**
     * Method incrementRoundCount
     */
    public void incrementRoundCount() {
        roundCount++;
    }
}
