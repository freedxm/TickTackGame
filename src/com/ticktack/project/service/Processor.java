package com.ticktack.project.service;

import com.ticktack.project.consolehandler.Handler;
import com.ticktack.project.model.GameField;
import com.ticktack.project.model.Player;
import com.ticktack.project.model.Type;
import com.ticktack.project.util.Massages;

import java.util.Scanner;

import static com.ticktack.project.model.Type.CROSS;
import static com.ticktack.project.model.Type.ZERO;

public class Processor {
    private Player crossPlayer;
    private Player zeroPlayer;
    private GameField gameField;
    private GameService service;
    private Handler handler = new Handler(new Scanner(System.in));

    public void run() {
        handler.write(Massages.WELCOME);
        init();
        gameField.fillGameField(gameField.getGameField());
        playersMove();
        Type winnerType = service.returnWinner();
        checkWinner(winnerType);
    }
    private void checkWinner(Type type){
        if(type == CROSS){
            handler.writeFormatted(Massages.PLAYER_WON, crossPlayer.getName());
        }
        else if(type == ZERO){
            handler.writeFormatted(Massages.PLAYER_WON, zeroPlayer.getName());
        }
        else{
            handler.write(Massages.DRAW);
        }
    }
    private void init(){
        gameField = new GameField(handler);
        service = new GameService(gameField, handler);
        crossPlayer =  new Player(CROSS, service.getName(CROSS));
        zeroPlayer = new Player(ZERO, service.getName(ZERO));
    }
    private void playersMove() {
        handler.write(Massages.START);
        gameField.printGameField(gameField.getGameField());
        Player movingPlayer = crossPlayer;
        while (!gameField.boardIsFull() && service.returnWinner() == null) {
            handler.write(movingPlayer.getName() + Massages.PLAYER_MOVES);
            gameField.printGameField(toMove(movingPlayer.getType()));
            if (movingPlayer.getType() == CROSS) {
                movingPlayer = zeroPlayer;
            } else if (movingPlayer.getType() == ZERO) {
                movingPlayer = crossPlayer;
            }
        }
    }

    private String[][] toMove(Type type) {
        while (true) {
            String xVal = handler.read();
            int x = service.stringIsNumeric(xVal) - 1;
            String yVal = handler.read();
            int y = service.stringIsNumeric(yVal) - 1;
            if (x <= 2 && x >= 0 && y >= 0 && y <= 2 && gameField.getGameField()[x][y].equals(Massages.EMPTY_CELL)) {
                if (type == Type.CROSS) {
                    gameField.getGameField()[x][y] = Massages.CROSS;
                } else {
                    gameField.getGameField()[x][y] = Massages.ZERO;
                }
                return gameField.getGameField();
            } else if (!(x <= 2 && x >= 0 && y >= 0 && y <= 2)) {
                handler.write(Massages.WRONG_CRDS);
            } else if (!(gameField.getGameField()[x][y].equals(Massages.EMPTY_CELL))) {
                handler.write(Massages.CELL_IS_ALREADY_FILLED);
            }
        }
    }
}


