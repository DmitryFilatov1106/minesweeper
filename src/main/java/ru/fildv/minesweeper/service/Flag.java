package ru.fildv.minesweeper.service;

import ru.fildv.minesweeper.util.Ranges;

public class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x() * Ranges.getSize().y();
    }

    public Box get(final Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpened(final Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    public void setFlaged(final Coord coord) {
        if (Box.FLAGED == flagMap.get(coord)) {
            flagMap.set(coord, Box.CLOSED);
        } else {
            if (Box.CLOSED == flagMap.get(coord)) {
                flagMap.set(coord, Box.FLAGED);
            }
        }
    }

    public int getClosedBoxes() {
        return countOfClosedBoxes;
    }

    public void setBombed(final Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    public void setOpenOnBomb(final Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    public void setNoBombOnFlag(final Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }

    public int getCountOfFlaggedAround(final Coord coord) {
        int count = 0;
        for (Coord c : Ranges.getCoordsAround(coord)) {
            if (flagMap.get(c) == Box.FLAGED) {
                count++;
            }
        }
        return count;
    }
}
