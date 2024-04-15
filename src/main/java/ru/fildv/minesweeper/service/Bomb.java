package ru.fildv.minesweeper.service;

import ru.fildv.minesweeper.util.Ranges;

public class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    public Bomb(final int totalBombs) {
        this.totalBombs = totalBombs;
        correctTotalBombs();
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBombs();
        }
    }

    public Box get(final Coord coord) {
        return bombMap.get(coord);
    }

    private void correctTotalBombs() {
        int maxBombs = Ranges.getSize().x() * Ranges.getSize().y() / 4;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBombs() {
        Coord coord;
        do {
            coord = Ranges.getRandomCoord();
        } while (bombMap.get(coord) == Box.BOMB);
        bombMap.set(coord, Box.BOMB);
        incNumbersAroundBombs(coord);
    }

    private void incNumbersAroundBombs(final Coord coord) {
        for (Coord c : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(c)) {
                bombMap.set(c, bombMap.get(c).nextBox());
            }
        }
    }

    public int getTotalBombs() {
        return totalBombs;
    }
}
