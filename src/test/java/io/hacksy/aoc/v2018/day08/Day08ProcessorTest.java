package io.hacksy.aoc.v2018.day08;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day08ProcessorTest {
    private File file = FileUtil.getResourceFile("input/day08/input2.txt");

    private Day08Processor processor = new Day08Processor();


    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("138"));
    }

    @Test
    public void partTwo() {
        assertThat(processor.partTwo(FileUtil.fileToStringList(file)), is("66"));
    }
}