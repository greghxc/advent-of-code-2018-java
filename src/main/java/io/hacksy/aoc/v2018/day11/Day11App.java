package io.hacksy.aoc.v2018.day11;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day11App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day10/input.txt");

        Day11Processor processor = new Day11Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 10 - Part 1: %s", processor.partOne(300, 300, 8141)));
        Performance.timeAndPrint(() ->
                String.format("Day 10 - Part 2: %s", processor.partTwo(300, 300, 8141)));
    }
}
