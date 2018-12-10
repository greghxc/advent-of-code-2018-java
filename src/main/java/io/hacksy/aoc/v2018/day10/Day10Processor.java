package io.hacksy.aoc.v2018.day10;

import com.google.common.collect.Lists;
import io.hacksy.aoc.v2018.util.Coordinate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day10Processor {
    public String partOne(List<String> input) {
        List<MovingCoordinate> coordinates = buildGrid(input);

        Set<Coordinate> newGrid = null;
        Set<Coordinate> oldGrid = null;

        int steps = 0;

        while (oldGrid == null || gridSize(newGrid) < gridSize(oldGrid)) {
            int localSteps = steps;
            Set<Coordinate> result = coordinates.stream()
                    .map(c -> {
                        return c.stepForward(localSteps);
                    }).collect(Collectors.toSet());
            oldGrid = newGrid;
            newGrid = result;
            steps++;
        }

        Set<Coordinate> grid = gridCoordinatesContaining(Lists.newArrayList(oldGrid));

        Integer minX = grid.stream().mapToInt(Coordinate::getX).min().getAsInt();
        Integer minY = grid.stream().mapToInt(Coordinate::getY).min().getAsInt();
        Integer maxX = grid.stream().mapToInt(Coordinate::getX).max().getAsInt();
        Integer maxY = grid.stream().mapToInt(Coordinate::getY).max().getAsInt();

        for (int y = minY; y <= maxY; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = minX; x <= maxX; x++) {
                if (oldGrid.contains(new Coordinate(x, y))) {
                    line.append("#");
                } else {
                    line.append(".");
                }
            }
            System.out.println(line.toString());
        }
        return null;
    }

    public void PartTwo(List<String> input) {

    }

    List<MovingCoordinate> buildGrid(List<String> input) {
        Pattern pattern = Pattern.compile("position=< ?(-?[0-9]+),  ?(-?[0-9]+)> velocity=< ?(-?[0-9]+),  ?(-?[0-9]+)>");
        return input.stream()
                .map(l -> {
                    Matcher m = pattern.matcher(l);
                    if (m.find()) {
                        return new MovingCoordinate(
                                new Coordinate(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))),
                                new Coordinate(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)))
                        );
                    }
                    return null;
                })
//                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    long gridSize(Collection<Coordinate> coordinates) {
        Integer minX = coordinates.stream().mapToInt(Coordinate::getX).min().getAsInt();
        Integer minY = coordinates.stream().mapToInt(Coordinate::getY).min().getAsInt();
        Integer maxX = coordinates.stream().mapToInt(Coordinate::getX).max().getAsInt();
        Integer maxY = coordinates.stream().mapToInt(Coordinate::getY).max().getAsInt();

        return new Long(Math.abs(maxY - minY)) * new Long(Math.abs(maxX - minX));
    }

    private Set<Coordinate> gridCoordinatesContaining(List<Coordinate> coordinates) {
        Integer minX = coordinates.stream().mapToInt(Coordinate::getX).min().getAsInt();
        Integer minY = coordinates.stream().mapToInt(Coordinate::getY).min().getAsInt();
        Integer maxX = coordinates.stream().mapToInt(Coordinate::getX).max().getAsInt();
        Integer maxY = coordinates.stream().mapToInt(Coordinate::getY).max().getAsInt();

        Set<Coordinate> grid = new HashSet<>();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                grid.add(new Coordinate(i, j));
            }
        }

        return grid;
    }

    class MovingCoordinate {
        Coordinate startingCoordinate;
        Coordinate velocity;

        MovingCoordinate(Coordinate startingCoordinate, Coordinate velocity) {
            this.startingCoordinate = startingCoordinate;
            this.velocity = velocity;
        }

        Coordinate stepForward(int steps) {
            return new Coordinate(
                    startingCoordinate.getX() + velocity.getX() * steps,
                    startingCoordinate.getY() + velocity.getY() * steps
            );
        }
    }
}
