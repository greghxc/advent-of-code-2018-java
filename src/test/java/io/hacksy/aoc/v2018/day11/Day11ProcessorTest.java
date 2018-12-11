package io.hacksy.aoc.v2018.day11;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Day11ProcessorTest {
    File file = FileUtil.getResourceFile("input/day11/input.txt");

    Day11Processor processor = new Day11Processor();

    @Test
    public void partOne01() {
        assertThat(processor.partOne(300, 300, 18), is("33,45"));
    }

    @Test
    public void partOne02() {
        assertThat(processor.partOne(300, 300, 42), is("21,61"));
    }

    @Test
    public void partTwo01() {
        assertThat(processor.partTwo(300, 300, 18), is("90,269,16"));
    }
    @Test
    public void partTwo02() {
        assertThat(processor.partTwo(300, 300, 42), is("232,251,12"));
    }
}