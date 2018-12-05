package io.hacksy.aoc.v2018.day05;

import io.hacksy.aoc.v2018.day04.Day04Processor;
import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day05App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day04/input.txt");

        Day04Processor processor = new Day04Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 5 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 5 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
