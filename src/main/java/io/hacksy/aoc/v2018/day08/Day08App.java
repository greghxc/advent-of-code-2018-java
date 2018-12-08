package io.hacksy.aoc.v2018.day08;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day08App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day08/input.txt");

        Day08Processor processor = new Day08Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 8 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 8 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
