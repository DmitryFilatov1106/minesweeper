package ru.fildv.minesweeper.service;

import ru.fildv.minesweeper.util.Ranges;

class Matrix {
    private Box[][] matrix;

    Matrix(final Box defaultBox) {
        matrix = new Box[Ranges.getSize().x()][Ranges.getSize().y()];
        for (Coord coord : Ranges.getAllCoords()) {
            matrix[coord.x()][coord.y()] = defaultBox;
        }
    }

    Box get(final Coord coord) {
        if (Ranges.inRange(coord)) {
            return matrix[coord.x()][coord.y()];
        }
        return null;
    }

    void set(final Coord coord, final Box box) {
        if (Ranges.inRange(coord)) {
            matrix[coord.x()][coord.y()] = box;
        }
    }
}
