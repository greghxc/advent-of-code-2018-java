package io.hacksy.aoc.v2018.day11;

import io.hacksy.aoc.v2018.util.Coordinate;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FuelCellCalculatorTest {
    @Test
    public void getPowerForCell01() {
        assertThat(FuelCellCalculator.getPowerForCell(new Coordinate(3, 5), 8), is(4));
    }

    @Test
    public void getPowerForCell02() {
        assertThat(FuelCellCalculator.getPowerForCell(new Coordinate(122, 79), 57), is(-5));
    }

    @Test
    public void getPowerForCell03() {
        assertThat(FuelCellCalculator.getPowerForCell(new Coordinate(217, 196), 39), is(0));
    }

    @Test
    public void getPowerForCell04() {
        assertThat(FuelCellCalculator.getPowerForCell(new Coordinate(101, 153), 71), is(4));
    }

    @Test
    public void getPowerCluster01() {
        assertThat(
                FuelCellCalculator.getPowerForCellCluster(
                        FuelCellCalculator.generatePowerGrid(300, 300, 18),
                        new Coordinate(33, 45)
                ), is(29)
        );
    }

    @Test
    public void getPowerCluster02() {
        assertThat(
                FuelCellCalculator.getPowerForCellCluster(
                        FuelCellCalculator.generatePowerGrid(300, 300, 42),
                        new Coordinate(21, 61)
                ), is(30)
        );
    }

    @Test
    public void getPowerCluster03() {
        assertThat(
                FuelCellCalculator.getPowerForCellCluster(
                        FuelCellCalculator.generatePowerGrid(300, 300, 18),
                        new Coordinate(90, 269),
                        16
                ), is(113)
        );
    }

    @Test
    public void getPowerCluster04() {
        assertThat(
                FuelCellCalculator.getPowerForCellCluster(
                        FuelCellCalculator.generatePowerGrid(300, 300, 42),
                        new Coordinate(232, 251),
                        12
                ), is(119)
        );
    }

    @Test
    public void getPowerCluster0N() {
        Map<Coordinate, Integer> grid = FuelCellCalculator.generatePowerGrid(300, 300, 42);
        Coordinate coordinate = new Coordinate(21, 61);
        Integer r1 = FuelCellCalculator.getPowerForCellCluster(grid, coordinate, 1);
        Integer r2 = FuelCellCalculator.getPowerForCellCluster(grid, coordinate, r1, 2);
        Integer r3 = FuelCellCalculator.getPowerForCellCluster(grid, coordinate, r2, 3);

        assertThat(r3, is(30));
    }
}
