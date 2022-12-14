package com.ticktack.project.service;

import com.ticktack.project.handler.ConsoleHandler;
import com.ticktack.project.model.GameField;
import com.ticktack.project.model.Player;
import com.ticktack.project.model.Type;
import com.ticktack.project.util.Messages;

import static com.ticktack.project.model.Type.CROSS;
import static com.ticktack.project.model.Type.ZERO;

public class Processor {
    private Player crossPlayer;
    private Player zeroPlayer;
    private GameField gameField;
    private GameService service;
    private ConsoleHandler handler;
    private DrawService drawService;

    public void run() {
        init();
        drawService.fillGameField(gameField.getGameField());
        playersMove();
        Type winnerType = service.returnWinner();
        checkWinner(winnerType);
    }

    private void checkWinner(Type type) {
        if (type == CROSS) {
            handler.writeFormatted(Messages.PLAYER_WON, crossPlayer.getName());
        } else if (type == ZERO) {
            handler.writeFormatted(Messages.PLAYER_WON, zeroPlayer.getName());
        } else {
            handler.write(Messages.DRAW);
        }
    }

    private void init() {
        handler = new ConsoleHandler();
        handler.write(Messages.WELCOME);
        gameField = new GameField();
        drawService = new DrawService(gameField, handler);
        service = new GameService(gameField, handler);
        crossPlayer = new Player(CROSS, service.getName(CROSS));
        zeroPlayer = new Player(ZERO, service.getName(ZERO));
    }

    private void playersMove() {
        handler.write(Messages.START);
        drawService.printGameField(gameField.getGameField());
        Player movingPlayer = crossPlayer;
        while (!drawService.boardIsFull(gameField.getGameField()) && service.returnWinner() == null) {
            handler.write(movingPlayer.getName() + Messages.PLAYER_MOVES);
            drawService.printGameField(toMove(movingPlayer.getType()));
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
            if (x <= 2 && x >= 0 && y >= 0 && y <= 2 && gameField.getGameField()[x][y].equals(Messages.EMPTY_CELL)) {
                if (type == Type.CROSS) {
                    gameField.getGameField()[x][y] = Messages.CROSS;
                } else {
                    gameField.getGameField()[x][y] = Messages.ZERO;
                }
                return gameField.getGameField();
            } else if (!(x <= 2 && x >= 0 && y >= 0 && y <= 2)) {
                handler.write(Messages.WRONG_CRDS);
            } else if (!(gameField.getGameField()[x][y].equals(Messages.EMPTY_CELL))) {
                handler.write(Messages.CELL_IS_ALREADY_FILLED);
            }
        }
    }
}


