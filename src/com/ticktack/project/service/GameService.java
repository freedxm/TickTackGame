package com.ticktack.project.service;

import com.ticktack.project.consolehandler.Handler;
import com.ticktack.project.model.GameField;
import com.ticktack.project.model.Type;
import com.ticktack.project.util.Massages;

import static com.ticktack.project.model.GameField.SIDE;
import static com.ticktack.project.model.Type.CROSS;
import static com.ticktack.project.model.Type.ZERO;
import static com.ticktack.project.util.Massages.WRONG_CP_NAME;

public class GameService {
    private GameField gameField;
    private Handler handler;

    public GameService(GameField gameField, Handler handler){
        this.gameField = gameField;
        this.handler = handler;
    }

    public String getName(Type type) {
        String name = "";
        if(type == CROSS) {
            handler.write(Massages.INPUT_CROSS_PLAYER_NAME);
        }else{
            handler.write(Massages.INPUT_ZERO_PLAYER_NAME);
        }
        name = handler.read();
        char[] arrayName = name.toCharArray();
        if (arrayName.length < 2 || Character.isDigit(arrayName[0])) {
            while (arrayName.length < 2 || Character.isDigit(arrayName[0])) {
                System.out.println(WRONG_CP_NAME);
                name = handler.read();
                arrayName = name.toCharArray();
            }
        }
        return name;
    }

    public Type returnWinner() {
        Type type = null;
        if (checkDiagonal(gameField.getGameField()) == CROSS || checkX() == CROSS || checkY() == CROSS) {
            type = CROSS;
        } else if (checkDiagonal(gameField.getGameField()) == ZERO || checkX() == ZERO || checkY() == ZERO) {
            type = ZERO;
        }
        return type;
    }

    public Type checkX() {
        int xCounter = 0;
        int oCounter = 0;
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (gameField.getGameField()[x][y].equals(Massages.CROSS)) {
                    xCounter++;
                } else if (gameField.getGameField()[x][y].equals(Massages.ZERO)) {
                    oCounter++;
                }
            }
            if (xCounter == 3) {
                return CROSS;
            } else if (oCounter == 3) {
                return ZERO;
            }
            xCounter = 0;
            oCounter = 0;
        }
        return null;
    }

    public Type checkY() {
        int xCounter = 0;
        int oCounter = 0;
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (gameField.getGameField()[y][x].equals(Massages.CROSS)) {
                    xCounter++;
                } else if (gameField.getGameField()[y][x].equals(Massages.ZERO)) {
                    oCounter++;
                }
            }
            if (xCounter == 3) {
                return CROSS;
            } else if (oCounter == 3) {
                return ZERO;
            }
            xCounter = 0;
            oCounter = 0;
        }
        return null;
    }

    public Type checkDiagonal(String[][] gameField) {
        if (gameField[0][0].equals(gameField[1][1]) && gameField[1][1].equals(gameField[2][2]) && gameField[2][2].equals(Massages.CROSS) || gameField[0][2].equals(gameField[1][1]) && gameField[1][1].equals(gameField[2][0]) && gameField[2][0].equals(Massages.CROSS)) {
            return Type.CROSS;
        } else if (gameField[0][0].equals(gameField[1][1]) && gameField[1][1].equals(gameField[2][2]) && gameField[2][2].equals(Massages.ZERO) || gameField[0][2].equals(gameField[1][1]) && gameField[1][1].equals(gameField[2][0]) && gameField[2][0].equals(Massages.ZERO)) {
            return Type.ZERO;
        }
        return null;
    }

    public int stringIsNumeric(String string){
        String regex = "[1-3]";
        while(true){
            if(string.matches(regex)){
                return Integer.parseInt(string);
            }
            else{
                handler.write(Massages.WRONG_CRDS);
                string = handler.read();
            }
        }
    }
}
