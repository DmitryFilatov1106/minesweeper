package ru.fildv.minesweeper.service;

public record Coord(int x, int y) {
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coord coord = (Coord) o;

        if (x != coord.x) {
            return false;
        }
        return y == coord.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
