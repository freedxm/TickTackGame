package com.ticktack.project.model;

import com.ticktack.project.handler.ConsoleHandler;

public class GameField {
    public static final int SIDE = 3;
    private String[][] gameField = new String[SIDE][SIDE];
    private ConsoleHandler handler;
    public GameField(ConsoleHandler handler){
        this.handler = handler;
    }

    public String[][] getGameField() {
        return gameField;
    }
}
