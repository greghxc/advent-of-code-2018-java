package io.hacksy.aoc.v2018.day13;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day13App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day13/input.txt");

        Day13Processor processor = new Day13Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 13 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 13 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
