package io.hacksy.aoc.v2016.day11;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

public class Day11App {
    public static void main( String[] args ) {
        var processor = new Day11Processor();

        var filePartOne = FileUtil.getResourceFile("input/2016/day11/input.txt");
        var filePartTwo = FileUtil.getResourceFile("input/2016/day11/inputPartTwo.txt");

        Performance.timeAndPrint(() ->
                String.format("Day 11 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(filePartOne))));
        Performance.timeAndPrint(() ->
                String.format("Day 11 - Part 2: %s", processor.partOne(FileUtil.fileToStringList(filePartTwo))));
    }
}
