package io.hacksy.aoc.v2018.day06;

import io.hacksy.aoc.v2018.day05.Day05Processor;
import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day06App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day06/input.txt");

        Day06Processor processor = new Day06Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 6 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 6 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file), 10000)));
    }
}
