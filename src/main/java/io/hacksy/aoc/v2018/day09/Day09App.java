package io.hacksy.aoc.v2018.day09;

import io.hacksy.aoc.v2018.util.FileUtil;
import io.hacksy.aoc.v2018.util.Performance;

import java.io.File;

public class Day09App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day09/input.txt");

        Day09Processor processor = new Day09Processor();

        Performance.timeAndPrint(() ->
                String.format("Day 9 - Part 1: %s", processor.partOne(400,71864)));
        Performance.timeAndPrint(() ->
                String.format("Day 9 - Part 2: %s", processor.partOne(400, 7186400)));
    }
}
