package io.hacksy.aoc.v2018.day04;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day04App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day04/input.txt");

        Day04Processor processor = new Day04Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 4 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 4 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
