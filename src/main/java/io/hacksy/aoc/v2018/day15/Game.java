package io.hacksy.aoc.v2018.day15;

import com.google.common.collect.Lists;
import io.hacksy.aoc.v2018.util.Coordinate;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
    @Getter private Set<Coordinate> walls;
    @Getter private Map<Coordinate, Combatant> combatantMap;

    private Coordinate gridSize;

    public Game(List<String> input) {
        this(input, 3);
    }

    public Game(List<String> input, int elfAttack) {
        walls = new HashSet<>();
        combatantMap = new HashMap<>();

        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                switch (input.get(y).charAt(x)) {
                    case '#':
                        walls.add(new Coordinate(x, y));
                        break;
                    case 'E':
                        combatantMap.put(new Coordinate(x, y), new Combatant(200, elfAttack, Combatant.Type.Elf));
                        break;
                    case 'G':
                        combatantMap.put(new Coordinate(x, y), new Combatant(200, 3, Combatant.Type.Goblin));
                }
            }
        }

        gridSize = new Coordinate(input.get(0).length(), input.size());
    }

    void printMap() {
        String map = IntStream.range(0, gridSize.getY())
                .mapToObj(y -> IntStream.range(0, gridSize.getX())
                        .mapToObj(x -> {
                            Coordinate coordinate = new Coordinate(x, y);
                            if (combatantMap.containsKey(coordinate)) {
                                if (combatantMap.get(coordinate).getType() == Combatant.Type.Goblin) {
                                    return "G";
                                } else {
                                    return "E";
                                }
                            } else if (walls.contains(coordinate)) {
                                return "#";
                            } else {
                                return ".";
                            }
                        }).collect(Collectors.joining(""))).collect(Collectors.joining("\n"));
        System.out.println(map);
    }

    Coordinate targetSquare(Coordinate coordinate, Combatant.Type type) {
        List<Coordinate> potentialTargets = combatantMap.entrySet().stream()
                .filter(e -> e.getValue().getType() == type)
                .flatMap(e -> vulnerableSquares(e.getKey()).stream())
                .collect(Collectors.toList());

        Comparator<Coordinate> coordinateComparator = Comparator.comparingInt(Coordinate::getY)
                .thenComparing(Coordinate::getX);

        int minDistance = Integer.MAX_VALUE;
        List<Coordinate> nearestCoordinates = new ArrayList<>();

        for (Coordinate potentialTarget : potentialTargets) {
            int distance = Coordinate.manhattanDistance(coordinate, potentialTarget);
            if (distance < minDistance) {
                nearestCoordinates = Lists.newArrayList(potentialTarget);
                minDistance = distance;
            } else if (distance == minDistance) {
                nearestCoordinates.add(potentialTarget);
            }
        }

        return nearestCoordinates.stream()
                .min(coordinateComparator)
                .orElse(null);
    }

    List<Coordinate> vulnerableSquares(Coordinate coordinate) {
        return adjacentSquares(coordinate).stream()
                .filter(c -> !combatantMap.containsKey(c) && !walls.contains(c))
                .sorted(Comparator.comparingInt(this::getIndex))
                .collect(Collectors.toList());
    }

    Coordinate combatantsInAttackRange(Coordinate coordinate, Combatant.Type type) {
        Comparator<Coordinate> coordinateComparator = Comparator.comparingInt(Coordinate::getY)
                .thenComparing(Coordinate::getX);

        List<Coordinate> coords = combatantMap.entrySet().stream()
                .filter(e -> e.getValue().getType() == type)
                .filter(e -> Coordinate.manhattanDistance(coordinate, e.getKey()) == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Coordinate enemy = coords.stream()
                .min(Comparator.comparingInt(c -> combatantMap.get(c).getHitPoints()))
                .orElse(null);

        if (enemy == null) { return null; }

        int maxHitPoints = combatantMap.get(enemy).getHitPoints();

        return coords.stream()
                .filter(c -> combatantMap.get(c).getHitPoints() == maxHitPoints)
                .min(coordinateComparator).orElse(null);
    }

    Coordinate fastestNextStep(Coordinate origin, Coordinate destination) {
        Coordinate completeRoute = null;

        Set<Coordinate> seen = new HashSet<>();
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(origin);

        while (completeRoute == null && queue.size() > 0) {
            Coordinate coordinate = queue.remove();
            seen.add(coordinate);
            if (vulnerableSquares(destination).contains(coordinate)) {
                completeRoute = coordinate;
            }
            for (Coordinate c : vulnerableSquares(coordinate)) {
                if (!seen.contains(c)) {
                    queue.add(c);
                }
            }
        }

        return completeRoute;
    }

    private List<Coordinate> adjacentSquares(Coordinate coordinate) {
        return Lists.newArrayList(
                new Coordinate(coordinate.getX() - 1, coordinate.getY()),
                new Coordinate(coordinate.getX(), coordinate.getY() - 1),
                new Coordinate(coordinate.getX() + 1, coordinate.getY()),
                new Coordinate(coordinate.getX(), coordinate.getY() + 1)
        );
    }

    private Collection<Coordinate> allVulnerableCoordinates(Combatant.Type type) {
        return combatantMap.entrySet().stream()
                .filter(e -> e.getValue().getType() == type)
                .flatMap(e -> vulnerableSquares(e.getKey()).stream())
                .collect(Collectors.toSet());
    }

    List<Coordinate> shortestRoute(Coordinate origin, Combatant.Type type) {
        Collection<Coordinate> vulnerableSquares = allVulnerableCoordinates(type);

        List<List<Coordinate>> completeRoute = new ArrayList<>();

        Set<Coordinate> seen = new HashSet<>();
        Queue<List<Coordinate>> queue = new ArrayDeque<>();
        queue.add(Lists.newArrayList(origin));

        while (queue.size() > 0) {
            List<Coordinate> coordinate = queue.remove();
            if (vulnerableSquares.contains(coordinate.get(coordinate.size() - 1))) {
                completeRoute.add(coordinate);
            }
            for (Coordinate c : vulnerableSquares(coordinate.get(coordinate.size() - 1))) {
                if (completeRoute.size() == 0 && !seen.contains(c)) {
                    List<Coordinate> newList = new ArrayList<>(coordinate);
                    newList.add(c);
                    seen.add(c);
                    queue.add(newList);
                }
            }
        }

        if (completeRoute.size() == 0) { return null; }

        int minRoute = completeRoute.stream().mapToInt(List::size).min().getAsInt();

        Comparator<List<Coordinate>> coordinateComparator = Comparator.comparingInt(l -> getIndex(l.get(1)));

        return completeRoute.stream()
                .filter(l -> l.size() == minRoute)
                .min(coordinateComparator)
                .orElse(null);
    }

    int getIndex(Coordinate coordinate) {
        return coordinate.getY() * gridSize.getX() + coordinate.getX();
    }
}
