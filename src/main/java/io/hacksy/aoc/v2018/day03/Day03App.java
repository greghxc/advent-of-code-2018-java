package io.hacksy.aoc.v2018.day03;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day03App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day03/input.txt");

        Day03Processor processor = new Day03Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 3 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 3 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
