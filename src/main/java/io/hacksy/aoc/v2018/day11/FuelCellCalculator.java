package io.hacksy.aoc.v2018.day11;

import io.hacksy.aoc.v2018.util.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class FuelCellCalculator {
    static int getPowerForCell(Coordinate coordinate, int serial) {
        Long rackId = coordinate.getX() + 10L;
        Long powerLevel = rackId * coordinate.getY();
        powerLevel += serial;
        powerLevel *= rackId;
        String dividedString = Long.toString(powerLevel / 100L);
        powerLevel = Long.parseLong(String.valueOf(dividedString.charAt(dividedString.length() - 1)));
        powerLevel -= 5;
        return Math.toIntExact(powerLevel);
    }

    static Map<Coordinate, Integer> generatePowerGrid(int x, int y, int serial) {
        Map<Coordinate, Integer> map = new HashMap<>();
        for (int i = 0; i < y; i ++) {
            for (int j = 0; j < x; j++) {
                Coordinate coordinate = new Coordinate(j, i);
                map.put(coordinate, getPowerForCell(coordinate, serial));
            }
        }
        return map;
    }

    static int getPowerForCellCluster(Map<Coordinate, Integer> grid, Coordinate coordinate) {
        return getPowerForCellCluster(grid, coordinate, 3);
    }

    static int getPowerForCellCluster(Map<Coordinate, Integer> grid, Coordinate coordinate, int size) {
        int total = 0;

        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j++) {
                Coordinate lookup = new Coordinate(j + coordinate.getX(), i + coordinate.getY());
                total += grid.get(lookup);
            }
        }

        return total;
    }

    static int getPowerForCellCluster(Map<Coordinate, Integer> grid, Coordinate coordinate, int prevValue, int size) {
        int total = 0;
        for (int i = size - 1; i < size; i ++) {
            for (int j = 0; j < size; j++) {
                Coordinate lookup = new Coordinate(j + coordinate.getX(), i + coordinate.getY());
                total += grid.get(lookup);
            }
        }

        for (int i = 0; i < size - 1; i ++) {
            for (int j = size - 1; j < size; j++) {
                Coordinate lookup = new Coordinate(j + coordinate.getX(), i + coordinate.getY());
                total += grid.get(lookup);
            }
        }

        return total + prevValue;
    }
}
