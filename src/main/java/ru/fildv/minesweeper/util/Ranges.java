package ru.fildv.minesweeper.util;

import ru.fildv.minesweeper.service.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Ranges {
    private static Coord size;
    private static List<Coord> allCoords;
    private static final Random RANDOM = new Random();

    public static Coord getSize() {
        return size;
    }

    public static void setSize(final Coord sizeParameter) {
        size = sizeParameter;
        allCoords = new ArrayList<>(size.y() * size.x());
        for (int y = 0; y < size.y(); y++) {
            for (int x = 0; x < size.x(); x++) {
                allCoords.add(new Coord(x, y));
            }
        }
    }

    public static List<Coord> getAllCoords() {
        return allCoords;
    }

    public static boolean inRange(final Coord coord) {
        return coord.x() >= 0 && coord.x() < size.x()
                && coord.y() >= 0 && coord.y() < size.y();
    }

    public static Coord getRandomCoord() {
        return new Coord(RANDOM.nextInt(size.x()), RANDOM.nextInt(size.y()));
    }

    public static List<Coord> getCoordsAround(final Coord coord) {
        Coord around;
        List<Coord> list = new ArrayList<>();
        for (int x = coord.x() - 1; x <= coord.x() + 1; x++) {
            for (int y = coord.y() - 1; y <= coord.y() + 1; y++) {
                around = new Coord(x, y);
                if (inRange(around) && !around.equals(coord)) {
                    list.add(around);
                }
            }
        }
        return list;
    }

    private Ranges() {
    }
}
