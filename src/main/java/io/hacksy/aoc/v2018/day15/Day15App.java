package io.hacksy.aoc.v2018.day15;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day15App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day15/input.txt");

        Day15Processor processor = new Day15Processor();

//        Performance.timeAndPrint(() ->
//                String.format("Day 15 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        Performance.timeAndPrint(() ->
                String.format("Day 15 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
