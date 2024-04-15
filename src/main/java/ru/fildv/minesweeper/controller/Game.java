package ru.fildv.minesweeper.controller;

import ru.fildv.minesweeper.service.Bomb;
import ru.fildv.minesweeper.service.Box;
import ru.fildv.minesweeper.service.Coord;
import ru.fildv.minesweeper.service.Flag;
import ru.fildv.minesweeper.service.GameState;
import ru.fildv.minesweeper.util.Ranges;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public Game(final int cols, final int rows, final int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();

    }

    public void start() {
        bomb.start();
        flag.start();
        gameState = GameState.PLAY;
    }

    public Box getBox(final Coord coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        }
        return flag.get(coord);
    }

    public void pressLeft(final Coord coord) {
        if (gameOver()) {
            return;
        }
        openBox(coord);
        checkWin();
    }

    private void checkWin() {
        if (gameState == GameState.PLAY) {
            if (flag.getClosedBoxes() == bomb.getTotalBombs()) {
                gameState = GameState.WIN;
            }
        }
    }

    private void openBox(final Coord coord) {
        switch (flag.get(coord)) {
            case OPENED -> setOpenedAroundNumber(coord);
            case FLAGED -> {
                return;
            }
            case CLOSED -> {
                switch (bomb.get(coord)) {
                    case BOMB -> openBombs(coord);
                    case ZERO -> openBoxesAround(coord);
                    default -> flag.setOpened(coord);
                }
            }
            default -> {
                return;
            }
        }
    }

    private void setOpenedAroundNumber(final Coord coord) {
        if (bomb.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlaggedAround(coord)
                    == bomb.get(coord).getNumber()) {
                for (Coord c : Ranges.getCoordsAround(coord)) {
                    if (flag.get(c) == Box.CLOSED) {
                        openBox(c);
                    }
                }
            }
        }
    }

    private void openBombs(final Coord coord) {
        gameState = GameState.LOSE;
        flag.setBombed(coord);
        for (Coord c : Ranges.getAllCoords()) {
            if (bomb.get(c) == Box.BOMB) {
                flag.setOpenOnBomb(c);
            } else {
                flag.setNoBombOnFlag(c);
            }
        }
    }

    private void openBoxesAround(final Coord coord) {
        flag.setOpened(coord);
        for (Coord c : Ranges.getCoordsAround(coord)) {
            openBox(c);
        }
    }

    public void pressRight(final Coord coord) {
        if (gameOver()) {
            return;
        }
        flag.setFlaged(coord);
    }

    public GameState getGameState() {
        return gameState;
    }

    private boolean gameOver() {
        if (gameState == GameState.PLAY) {
            return false;
        }
        start();
        return true;
    }
}
