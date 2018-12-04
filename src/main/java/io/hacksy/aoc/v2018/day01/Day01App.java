package io.hacksy.aoc.v2018.day01;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day01App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day01/input.txt");

        Day01Processor processor = new Day01Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 1 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 1 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
