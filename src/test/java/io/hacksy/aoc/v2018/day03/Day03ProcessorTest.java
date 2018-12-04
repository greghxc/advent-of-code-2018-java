package io.hacksy.aoc.v2018.day03;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day03ProcessorTest {
    private Day03Processor processor = new Day03Processor();
    private File testFile = FileUtil.getResourceFile("input/day03/input.txt");

    @Test
    public void partOne() {
        assertThat(processor.partOne(FileUtil.fileToStringList(testFile)), is(4L));
    }

    @Test
    public void partTwo() {
        assertThat(
                processor.partTwo(FileUtil.fileToStringList(testFile)),
                is("Day03Processor.FabricClaim(id=3, leftMargin=5, topMargin=5, width=2, height=2)")
        );
    }
}