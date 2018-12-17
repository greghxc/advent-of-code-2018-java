package io.hacksy.aoc.v2018.day15;

import io.hacksy.aoc.v2018.util.Coordinate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day15Processor {
    String partOne(List<String> input) {
        Game game = new Game(input, 3);
        Comparator<Coordinate> coordinateComparator = Comparator.comparingInt(Coordinate::getY)
                .thenComparing(Coordinate::getX);

        int round = 0;

        game.printMap();

        while (notDone(game)) {
            boolean sawComplete = false;
            List<Coordinate> turns = game.getCombatantMap().keySet().stream()
                    .sorted(coordinateComparator)
                    .collect(Collectors.toList());

            for (Coordinate c : turns) {
                if (!notDone(game)) { sawComplete = true; }
                if (game.getCombatantMap().containsKey(c)) {
                    Combatant.Type enemyType = Combatant.Type.Goblin;
                    if (game.getCombatantMap().get(c).getType() == Combatant.Type.Goblin) {
                        enemyType = Combatant.Type.Elf;
                    }

                    Coordinate attack = game.combatantsInAttackRange(c, enemyType);

                    if (attack == null) {
                        List<Coordinate> destinationRoute = game.shortestRoute(c, enemyType);
                        if (destinationRoute != null) {
                            Coordinate destination = destinationRoute.get(1);
                            if (!game.getCombatantMap().containsKey(destination)) {
                                Combatant combatant = game.getCombatantMap().get(c);
                                game.getCombatantMap().remove(c);
                                game.getCombatantMap().put(destination, combatant);
                                attack = game.combatantsInAttackRange(destination, enemyType);
                            }
                        }
                    }

                    if (attack != null) {
                        Combatant attackee = game.getCombatantMap().get(attack);
                        System.out.println(String.format("attacking %s %s (HP %s)", attackee.getType(), attack, attackee.getHitPoints()));
                        attackee.setHitPoints(attackee.getHitPoints() - 3);
                        if (attackee.getHitPoints() <= 0) {
//                            System.out.println(String.format("Killed %s %s", attackee.getType(), attack));
                            game.getCombatantMap().remove(attack);
                        }
                    }
                }
            }
            if (!sawComplete) { round++; }
            System.out.println(String.format("After %s round:", round));
            System.out.println(game.getCombatantMap().entrySet().stream().map(e -> String.format("%s %sHP %s", e.getValue().type, e.getValue().getHitPoints(), e.getKey())).collect(Collectors.joining("\n")));
            game.printMap();
        }

        game.printMap();
        int sum = game.getCombatantMap().values().stream().mapToInt(Combatant::getHitPoints).sum();
        System.out.println(game.getCombatantMap().entrySet().stream().map(e -> String.format("%s %sHP %s", e.getValue().type, e.getValue().getHitPoints(), e.getKey())).collect(Collectors.joining("\n")));
        return Integer.toString(sum * round );
    }

    String partTwo(List<String> input) {
        return null;
    }

    private Game runGame(List<String> input, int elfAttackValue) {
        Game game = new Game(input, elfAttackValue);
        Comparator<Coordinate> coordinateComparator = Comparator.comparingInt(Coordinate::getY)
                .thenComparing(Coordinate::getX);

        int round = 0;

        game.printMap();

        while (notDone(game)) {
            boolean sawComplete = false;
            List<Coordinate> turns = game.getCombatantMap().keySet().stream()
                    .sorted(coordinateComparator)
                    .collect(Collectors.toList());

            for (Coordinate c : turns) {
                if (!notDone(game)) { sawComplete = true; }
                if (game.getCombatantMap().containsKey(c)) {
                    Combatant.Type enemyType = Combatant.Type.Goblin;
                    if (game.getCombatantMap().get(c).getType() == Combatant.Type.Goblin) {
                        enemyType = Combatant.Type.Elf;
                    }

                    Coordinate attack = game.combatantsInAttackRange(c, enemyType);

                    if (attack == null) {
                        List<Coordinate> destinationRoute = game.shortestRoute(c, enemyType);
                        if (destinationRoute != null) {
                            Coordinate destination = destinationRoute.get(1);
                            if (!game.getCombatantMap().containsKey(destination)) {
                                Combatant combatant = game.getCombatantMap().get(c);
                                game.getCombatantMap().remove(c);
                                game.getCombatantMap().put(destination, combatant);
                                attack = game.combatantsInAttackRange(destination, enemyType);
                            }
                        }
                    }

                    if (attack != null) {
                        Combatant attackee = game.getCombatantMap().get(attack);
                        System.out.println(String.format("attacking %s %s (HP %s)", attackee.getType(), attack, attackee.getHitPoints()));
                        attackee.setHitPoints(attackee.getHitPoints() - 3);
                        if (attackee.getHitPoints() <= 0) {
//                            System.out.println(String.format("Killed %s %s", attackee.getType(), attack));
                            game.getCombatantMap().remove(attack);
                        }
                    }
                }
            }
            if (!sawComplete) { round++; }
            System.out.println(String.format("After %s round:", round));
            System.out.println(game.getCombatantMap().entrySet().stream().map(e -> String.format("%s %sHP %s", e.getValue().type, e.getValue().getHitPoints(), e.getKey())).collect(Collectors.joining("\n")));
            game.printMap();
        }

        game.printMap();
        int sum = game.getCombatantMap().values().stream().mapToInt(Combatant::getHitPoints).sum();
        System.out.println(game.getCombatantMap().entrySet().stream().map(e -> String.format("%s %sHP %s", e.getValue().type, e.getValue().getHitPoints(), e.getKey())).collect(Collectors.joining("\n")));

        //        return Integer.toString(sum * round );
    }

    private boolean notDone(Game game) {
        Map<Combatant.Type, Long> countsByType = game.getCombatantMap().entrySet().stream()
                .map(e -> e.getValue().getType())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countsByType.size() > 1;
    }
}
