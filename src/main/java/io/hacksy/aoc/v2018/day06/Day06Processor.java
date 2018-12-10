package io.hacksy.aoc.v2018.day06;

import com.google.common.collect.Lists;
import io.hacksy.aoc.v2018.util.Coordinate;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.hacksy.aoc.v2018.util.Coordinate.manhattanDistance;

public class Day06Processor {
    private Pattern inputPattern = Pattern.compile("(\\d+), (\\d+)");

    String partOne(List<String> input) {
        List<Coordinate> inputCoordinates = parseCoordinates(input);
        Set<Coordinate> gridCoordinates = gridCoordinatesContaining(inputCoordinates);

        Map<Coordinate, List<Coordinate>> gridCoordsByInputCoord = gridCoordinates.stream()
                .map(gridCoordinate -> inputCoordinates.stream()
                            .collect(Collectors.groupingBy(i -> manhattanDistance(i, gridCoordinate))))
                .map(m -> m.entrySet().stream()
                        .min(Comparator.comparingInt(Map.Entry::getKey))
                        .filter(e -> e.getValue().size() == 1)
                        .map(e -> e.getValue().get(0))
                        .orElse(null)
                ).filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity()));

        return Integer.toString(gridCoordsByInputCoord.entrySet().stream()
                .max(Comparator.comparingInt(e -> Math.toIntExact(e.getValue().size()))).get().getValue().size());
    }

    String partOneOriginal(List<String> input) {
        List<Coordinate> inputCoordinates = parseCoordinates(input);
        Set<Coordinate> gridCoordinates = gridCoordinatesContaining(inputCoordinates);

        Map<Coordinate, List<Coordinate>> nearestInputCoordByGridCoord = new HashMap<>();
        gridCoordinates.forEach(g -> {
            nearestInputCoordByGridCoord.put(g, new ArrayList<>());
            inputCoordinates.forEach(c -> {
                // if no one has claimed it, claim it
                if (nearestInputCoordByGridCoord.get(g).size() == 0) {
                    nearestInputCoordByGridCoord.put(g, Lists.newArrayList(c));//
                    // if the claimed one is further away than me, claim it for me!
                } else if (manhattanDistance(g, nearestInputCoordByGridCoord.get(g).get(0)) > manhattanDistance(g, c)) {
                    nearestInputCoordByGridCoord.put(g, Lists.newArrayList(c));
                    // if we're the SAME distance, add me to the list!
                } else if (manhattanDistance(g, nearestInputCoordByGridCoord.get(g).get(0)) == (manhattanDistance(g, c))) {
                    nearestInputCoordByGridCoord.get(g).add(c);
                }
            });
        });

        Map<Coordinate, List<Coordinate>> gridCoordsByInputCoord = nearestInputCoordByGridCoord.entrySet().stream()
                .filter(e -> e.getValue().size() == 1)
                .map(e -> e.getValue().get(0))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.toList()));

        return Integer.toString(gridCoordsByInputCoord.entrySet().stream()
                .max(Comparator.comparingInt(e -> Math.toIntExact(e.getValue().size()))).get().getValue().size());
    }

    String partTwo(List<String> input, int maxDistance) {
        List<Coordinate> coordinates = parseCoordinates(input);
        Set<Coordinate> grid = gridCoordinatesContaining(coordinates);

        long count = grid.stream()
                .mapToInt(g -> coordinates.stream().mapToInt(c -> manhattanDistance(g, c)).sum())
                .filter(s -> s < maxDistance)
                .count();

        return Long.toString(count);
    }

    private Coordinate coordinateFrom(String line) {
        Matcher matcher = inputPattern.matcher(line);
        if(matcher.find()) {
            return new Coordinate(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
        }
        return null;
    }

    private List<Coordinate> parseCoordinates(List<String> input) {
        return input.stream()
                .map(this::coordinateFrom)
                .collect(Collectors.toList());
    }

    private Set<Coordinate> gridCoordinatesContaining(List<Coordinate> coordinates) {
        Integer minX = coordinates.stream().mapToInt(Coordinate::getX).min().getAsInt();
        Integer minY = coordinates.stream().mapToInt(Coordinate::getY).min().getAsInt();
        Integer maxX = coordinates.stream().mapToInt(Coordinate::getX).max().getAsInt();
        Integer maxY = coordinates.stream().mapToInt(Coordinate::getY).max().getAsInt();

        Set<Coordinate> grid = new HashSet<>();
        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                grid.add(new Coordinate(i, j));
            }
        }

        return grid;
    }
}
