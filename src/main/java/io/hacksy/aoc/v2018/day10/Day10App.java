package io.hacksy.aoc.v2018.day10;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day10App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day10/input.txt");

        Day10Processor processor = new Day10Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 10 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
//        Performance.timeAndPrint(() ->
//                String.format("Day 9 - Part 2: %s", processor.partOne(FileUtil.fileToStringList(file))));
    }
}
