package io.hacksy.aoc.v2018.day11;

import io.hacksy.aoc.v2018.util.Coordinate;
import lombok.Data;

import java.util.*;
import java.util.stream.IntStream;

public class Day11Processor {
    String partOne(int x, int y, int serial) {
        Map<Coordinate, Integer> powerGrid = FuelCellCalculator.generatePowerGrid(x, y, serial);
        Integer highestVal = null;
        Coordinate highestCoordinate = null;

        for (int i = 0; i < y - 2; i++ ) {
            for (int j = 0; j < x -2; j++) {
                Coordinate lookup =  new Coordinate(j, i);
                int cluseterPower = FuelCellCalculator.getPowerForCellCluster(powerGrid, lookup);
                if (highestVal == null || cluseterPower > highestVal) {
                    highestVal = cluseterPower;
                    highestCoordinate = lookup;
                }
            }
        }
        return String.format("%s,%s", highestCoordinate.getX(), highestCoordinate.getY());
    }

    String partTwo(int x, int y, int serial) {
        Map<Coordinate, Integer> powerGrid = FuelCellCalculator.generatePowerGrid(x, y, serial);
        Integer highestVal = null;
        Coordinate highestCoordinate = null;
        Integer highestN = null;

        Map<Coordinate, Integer> coordinateValues = new HashMap<>();
        for (int n = 0; n < 300; n++) {
            System.out.println(String.format("%s",n));
            for (int i = 0; i < y - n; i++ ) {
                for (int j = 0; j < x - n; j++) {
                    Coordinate lookup =  new Coordinate(j, i);
                    int clusterPower = FuelCellCalculator.getPowerForCellCluster(powerGrid, lookup, coordinateValues.getOrDefault(lookup, 0), n + 1);
                    coordinateValues.put(lookup, clusterPower);
                    if (highestVal == null || clusterPower > highestVal) {
                        highestVal = clusterPower;
                        highestCoordinate = lookup;
                        highestN = n;
                    }
                }
            }
        }

        return String.format("%s,%s,%s", highestCoordinate.getX(), highestCoordinate.getY(), highestN + 1);
    }

    @Data
    class CoordinateValue {
        private final Coordinate coordinate;
        private final Integer value;
        private final IntStream size;
    }


}
