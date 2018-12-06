package io.hacksy.aoc.v2018.day06;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day06ProcessorTest {
    private Day06Processor processor = new Day06Processor();
    private File file = FileUtil.getResourceFile("input/day06/input.txt");

    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("17"));
    }

    @Test
    public void partTwo() {
        assertThat(processor.partTwo(FileUtil.fileToStringList(file), 32), is("16"));
    }
}