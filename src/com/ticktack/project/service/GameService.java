package com.ticktack.project.service;

import com.ticktack.project.handler.ConsoleHandler;
import com.ticktack.project.model.GameField;
import com.ticktack.project.model.Type;
import com.ticktack.project.util.Massages;

import static com.ticktack.project.model.GameField.SIDE;
import static com.ticktack.project.model.Type.CROSS;
import static com.ticktack.project.model.Type.ZERO;
import static com.ticktack.project.util.Massages.WRONG_CP_NAME;

public class GameService {
    private GameField gameField;
    private ConsoleHandler handler;

    public GameService(GameField gameField, ConsoleHandler handler){
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
            while (arrayName.length < 2 || Character.isDigit(arrayName[0])) {
                handler.write(WRONG_CP_NAME);
                name = handler.read();
                arrayName = name.toCharArray();
            }
        return name;
    }

    public Type returnWinner() {
        Type type = checkDiagonal(gameField.getGameField());
        if(type == null){
            type = checkX();
        }
        if(type == null){
            checkY();
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
                break;
            } else if (oCounter == 3) {
                break;
            }
            xCounter = 0;
            oCounter = 0;
        }
        return checkTypeCounters(xCounter, oCounter);
    }

    private Type checkTypeCounters(int xCounter, int oCounter){
        if (xCounter == 3) {
            return CROSS;
        } else if (oCounter == 3) {
            return ZERO;
        }else{
            return null;
        }
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
        int xCounter = 0;
        int oCounter = 0;
        for (int i = 0; i < SIDE; i++) {
            if(gameField[i][i].equals(Massages.CROSS)){
                xCounter++;
            }else if(gameField[i][i].equals(Massages.ZERO)){
                oCounter++;
            }
        }
        if(checkTypeCounters(xCounter, oCounter) != null){
            return checkTypeCounters(xCounter, oCounter);
        }
        xCounter = 0;
        oCounter = 0;
        for (int x = 0, y = 2; x < SIDE && y >= 0; x++, y--) {
            if(gameField[x][y].equals(Massages.CROSS)){
                xCounter++;
            }else if(gameField[x][y].equals(Massages.ZERO)){
                oCounter++;
            }
        }
        if(checkTypeCounters(xCounter, oCounter) != null){
            return checkTypeCounters(xCounter, oCounter);
        }else{
            return null;
        }
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
