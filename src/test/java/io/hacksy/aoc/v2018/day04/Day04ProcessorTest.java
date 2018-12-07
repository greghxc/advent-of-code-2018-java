package io.hacksy.aoc.v2018.day04;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day04ProcessorTest {
    private Day04Processor processor = new Day04Processor();
    private File file = FileUtil.getResourceFile("input/day04/input.txt");

    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("240"));
    }

    @Test
    public void partTwo() {
        assertThat(processor.partTwo(FileUtil.fileToStringList(file)), is("4455"));
    }
}