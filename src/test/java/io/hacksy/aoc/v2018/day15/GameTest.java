package io.hacksy.aoc.v2018.day15;

import io.hacksy.aoc.v2018.util.Coordinate;
import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class GameTest {
    @Test
    public void constructionAssignsWalls() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        Coordinate[] expectedWalls = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(2, 0),
                new Coordinate(3, 0),
                new Coordinate(4, 0),
                new Coordinate(5, 0),
                new Coordinate(6, 0),
                new Coordinate(0, 1),
                new Coordinate(6, 1),
                new Coordinate(0, 2),
                new Coordinate(6, 2),
                new Coordinate(0, 3),
                new Coordinate(6, 3),
                new Coordinate(0, 4),
                new Coordinate(1, 4),
                new Coordinate(2, 4),
                new Coordinate(3, 4),
                new Coordinate(4, 4),
                new Coordinate(5, 4),
                new Coordinate(6, 4)
        };
        assertThat(game.getWalls(), containsInAnyOrder(expectedWalls));
    }

    @Test
    public void constructionAssignsElves() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        List<Coordinate> actualElves = game.getCombatantMap().entrySet().stream()
                .filter(e -> e.getValue().getType() == Combatant.Type.Elf)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        assertThat(actualElves.contains(new Coordinate(4, 1)), is(true));
        assertThat(actualElves.contains(new Coordinate(1, 2)), is(true));
        assertThat(actualElves.contains(new Coordinate(5, 2)), is(true));
        assertThat(actualElves.contains(new Coordinate(4, 3)), is(true));
    }

    @Test
    public void constructionAssignsGoblins() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        List<Coordinate> actualGoblins = game.getCombatantMap().entrySet().stream()
                .filter(e -> e.getValue().getType() == Combatant.Type.Goblin)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        assertThat(actualGoblins.contains(new Coordinate(2, 1)), is(true));
        assertThat(actualGoblins.contains(new Coordinate(3, 2)), is(true));
        assertThat(actualGoblins.contains(new Coordinate(2, 3)), is(true));
    }

    @Test
    public void constructionAssignsPoints() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        Set<Integer> actualHitPoints = game.getCombatantMap().entrySet().stream()
                .map(e -> e.getValue().getHitPoints())
                .collect(Collectors.toSet());
        Set<Integer> actualAttackPoints = game.getCombatantMap().entrySet().stream()
                .map(e -> e.getValue().getAttackPoints())
                .collect(Collectors.toSet());
        assertThat(actualHitPoints, contains(200));
        assertThat(actualAttackPoints, contains(3));
    }

    @Test
    public void getCombatant01() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        assertThat(game.targetSquare(new Coordinate(1, 2), Combatant.Type.Goblin), is(new Coordinate(1, 1)));
    }

    @Test
    public void getInRange01() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        assertThat(game.combatantsInAttackRange(new Coordinate(3, 1), Combatant.Type.Elf), is(new Coordinate(4,1)));
    }

    @Test
    public void getInRange02() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        assertThat(game.combatantsInAttackRange(new Coordinate(2, 1), Combatant.Type.Elf), nullValue());
    }

    @Test
    public void getInRange03() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input.txt")));
        assertThat(game.combatantsInAttackRange(new Coordinate(2, 2), Combatant.Type.Goblin), is(new Coordinate(2, 1)));
    }

    @Test
    public void route01() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/input3.txt")));
        assertThat(game.fastestNextStep(new Coordinate(5, 4), new Coordinate(3,4)), is(new Coordinate(3, 5)));
    }

    @Test
    public void route02() {
        Game game = new Game(FileUtil.fileToStringList(FileUtil.getResourceFile("input/day15/inputE.txt")));
        assertThat(game.fastestNextStep(new Coordinate(2, 2), new Coordinate(6, 6)), is(new Coordinate(5, 6)));
    }

    @Test
    public void shortestRoute01() {
        List<String> input = new ArrayList<>();
        input.add("######");
        input.add("#....#");
        input.add("#E##G#");
        input.add("#....#");
        input.add("######");

        Game game = new Game(input);

        assertThat(game.shortestRoute(new Coordinate(4, 2), Combatant.Type.Elf).get(1), is(new Coordinate(4, 1)));
    }

    @Test
    public void shortestRoute02() {
        List<String> input = new ArrayList<>();
        input.add("######");
        input.add("#.#..#");
        input.add("#E##G#");
        input.add("#....#");
        input.add("######");

        Game game = new Game(input);

        assertThat(game.shortestRoute(new Coordinate(4, 2), Combatant.Type.Elf).get(1), is(new Coordinate(4, 3)));
    }

    @Test
    public void shortestRoute03() {
        List<String> input = new ArrayList<>();
        input.add("#########");
        input.add("#.G..G..#");
        input.add("#.E.#...#");
        input.add("#.G##...#");
        input.add("#...##..#");
        input.add("#...#...#");
        input.add("#.G.....#");
        input.add("#.G.....#");
        input.add("#########");

        Game game = new Game(input);

        assertThat(game.shortestRoute(new Coordinate(2, 6), Combatant.Type.Elf).get(1), is(new Coordinate(2, 5)));
    }

    @Test
    public void shortestRoute04() {
        List<String> input = new ArrayList<>();
        input.add("#######");
        input.add("#.EG#.#");
        input.add("#G#G..#");
        input.add("#..#..#");
        input.add("#.G.#G#");
        input.add("#...E.#");
        input.add("#######");

        Game game = new Game(input);

        assertThat(game.shortestRoute(new Coordinate(4, 5), Combatant.Type.Goblin).get(1), is(new Coordinate(5, 5)));
    }
}