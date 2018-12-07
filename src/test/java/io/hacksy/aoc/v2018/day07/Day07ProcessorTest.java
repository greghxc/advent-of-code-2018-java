package io.hacksy.aoc.v2018.day07;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

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

    @Test
    public void test() {
        Set<Function<Integer, Integer>> set = new HashSet<>();
        set.add(i -> i + 1);
        set.add(i -> i + 1);
        set.add(i -> i + 1);
        assertThat(set.size(), is(1));
    }
}