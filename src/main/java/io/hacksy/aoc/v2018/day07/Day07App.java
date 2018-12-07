package io.hacksy.aoc.v2018.day07;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day07App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day07/input.txt");

        Day07Processor processor = new Day07Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 7 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 7 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file), 5,60)));
    }
}
