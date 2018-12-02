package io.hacksy.aoc.v2018.day02;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day02ProcessorTest {

    @Test
    public void partOne() {
        List<String> input = FileUtil.fileToStringList(FileUtil.getResourceFile("input/day02/part01.txt"));
        Day02Processor processor = new Day02Processor();
        assertThat(processor.partOne(input), is(12L));
    }

    @Test
    public void partTwo() {
        List<String> input = FileUtil.fileToStringList(FileUtil.getResourceFile("input/day02/part02.txt"));
        Day02Processor processor = new Day02Processor();
        assertThat(processor.partTwo(input), is("fgij"));
    }
}