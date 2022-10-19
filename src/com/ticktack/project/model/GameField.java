package com.ticktack.project.model;

import com.ticktack.project.consolehandler.Handler;
import com.ticktack.project.util.Massages;

public class GameField {
    public static final int SIDE = 3;
    public String[][] gameField = new String[SIDE][SIDE];
    private Handler handler;
    public GameField(Handler handler){
        this.handler = handler;
    }

    public String[][] getGameField() {
        return gameField;
    }
    public void printGameField(String[][] gameField) {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                handler.writeWithoutLine(gameField[x][y]);
            }
            handler.write("");
        }
    }
    public void fillGameField(String[][] gameField) {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                gameField[x][y] = Massages.EMPTY_CELL;
            }
        }
    }
    public boolean boardIsFull() {
        int counterOfFilledCells = 0;
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (!getGameField()[x][y].equals(Massages.EMPTY_CELL)) {
                    counterOfFilledCells++;
                }
            }
        }
        return counterOfFilledCells == 9;
    }
}
