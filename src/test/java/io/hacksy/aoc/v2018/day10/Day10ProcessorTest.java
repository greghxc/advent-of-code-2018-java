package io.hacksy.aoc.v2018.day10;

import io.hacksy.aoc.v2018.day08.Day08Processor;
import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class Day10ProcessorTest {
    private File file = FileUtil.getResourceFile("input/day10/input.txt");

    private Day10Processor processor = new Day10Processor();

    @Test
    public void partOne() {
        processor.partOne(FileUtil.fileToStringList(file));
    }
}