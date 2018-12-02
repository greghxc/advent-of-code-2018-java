package io.hacksy.aoc.v2018.day02;

import io.hacksy.aoc.v2018.util.FileUtil;

import java.io.File;

public class Day02App {
    public static void main( String[] args ) {
        File file = FileUtil.getResourceFile("input/day02/input.txt");

        Day02Processor processor = new Day02Processor();
        System.out.println(String.format("Day 2 - Part 1: %s", processor.partOne(FileUtil.fileToStringList(file))));
        System.out.println(String.format("Day 2 - Part 2: %s", processor.partTwo(FileUtil.fileToStringList(file))));
    }
}
