package io.hacksy.aoc.v2018.day13;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Day13ProcessorTest {
    File file = FileUtil.getResourceFile("input/day13/input.txt");

    Day13Processor processor = new Day13Processor();

    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("7,3"));
    }
}