package com.ticktack.project.service;

import com.ticktack.project.handler.ConsoleHandler;
import com.ticktack.project.model.GameField;
import com.ticktack.project.util.Messages;

import static com.ticktack.project.model.GameField.SIDE;

public class DrawService {
    private final GameField gameField;
    private final ConsoleHandler handler;

    public DrawService(GameField gameField, ConsoleHandler handler) {
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
                gameField[x][y] = Messages.EMPTY_CELL;
            }
        }
    }

    public boolean boardIsFull(String[][] gameField) {
        int counterOfFilledCells = 0;
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (!gameField[x][y].equals(Messages.EMPTY_CELL)) {
                    counterOfFilledCells++;
                }
            }
        }
        return counterOfFilledCells == 9;
    }
}
