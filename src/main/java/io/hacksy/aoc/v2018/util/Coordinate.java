package io.hacksy.aoc.v2018.util;

import lombok.Data;

@Data
public class Coordinate {
    private final Integer x;
    private final Integer y;

    public static int manhattanDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
    }
}