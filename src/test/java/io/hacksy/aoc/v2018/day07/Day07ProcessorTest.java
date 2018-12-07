package io.hacksy.aoc.v2018.day07;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day07ProcessorTest {
    File file = FileUtil.getResourceFile("input/day07/input.txt");

    Day07Processor processor = new Day07Processor();

    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("CABDFE"));
    }

    @Test
    public void partTwo() {
        assertThat(processor.partTwo(FileUtil.fileToStringList(file), 2,0), is("15"));
    }
}