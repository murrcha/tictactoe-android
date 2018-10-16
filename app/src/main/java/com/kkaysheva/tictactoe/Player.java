package com.kkaysheva.tictactoe;

import java.io.Serializable;

/**
 * Player for tic tac toe
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 16/10/2018
 */
public class Player implements Serializable {

    private int points;

    public int getPoints() {
        return points;
    }

    public void incrementPoints() {
        points++;
    }

    public void resetPoints() {
        points = 0;
    }
}
