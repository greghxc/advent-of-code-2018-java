package io.hacksy.aoc.v2018.day05;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day05ProcessorTest {
    private Day05Processor processor = new Day05Processor();
    private File file = FileUtil.getResourceFile("input/day05/input.txt");

    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("blah"));
    }

    @Test
    public void partTwo() {
        assertThat(processor.partTwo(FileUtil.fileToStringList(file)), is("blah"));
    }
}