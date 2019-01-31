package io.hacksy.aoc.v2016.day11;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day11App {
    public static void main( String[] args ) {
        Day11Processor processor = new Day11Processor();

        File filePartOne = FileUtil.getResourceFile("input/2016/day11/input.txt");
        File filePartTwo = FileUtil.getResourceFile("input/2016/day11/inputPartTwo.txt");

        Performance.timeAndPrint(() ->
                String.format("Day 11 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(filePartOne))));
//        Performance.timeAndPrint(() ->
//                String.format("Day 11 - Part 2: %s", processor.partOne(FileUtil.fileToStringList(filePartTwo))));
    }
}
