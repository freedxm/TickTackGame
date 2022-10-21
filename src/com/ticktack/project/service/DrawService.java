package com.ticktack.project.service;

import com.ticktack.project.handler.ConsoleHandler;
import com.ticktack.project.model.GameField;
import com.ticktack.project.util.Massages;

import static com.ticktack.project.model.GameField.SIDE;

public class DrawService {
    private GameField gameField;
    private ConsoleHandler handler;

    public DrawService(GameField gameField, ConsoleHandler handler){
        this.gameField = gameField;
        this.handler = handler;
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
                if (!gameField.getGameField()[x][y].equals(Massages.EMPTY_CELL)) {
                    counterOfFilledCells++;
                }
            }
        }
        return counterOfFilledCells == 9;
    }
}
