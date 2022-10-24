package com.ticktack.project.model;

public class GameField {
    public static final int SIDE = 3;
    private final String[][] gameField = new String[SIDE][SIDE];

    public String[][] getGameField() {
        return gameField;
    }
}
