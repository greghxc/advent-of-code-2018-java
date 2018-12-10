package io.hacksy.aoc.v2018.day09;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day09ProcessorTest {
    private Day09Processor processor = new Day09Processor();

    @Test
    public void partOne01() {
        assertThat(processor.partOne(9, 32), is("32"));
    }

    @Test
    public void partOne02Old() {
        assertThat(processor.partOneOld(10, 1618), is("8317"));
    }

    @Test
    public void partOne02() {
        assertThat(processor.partOne(10, 1618), is("8317"));
    }

    @Test
    public void partOne03() {
        assertThat(processor.partOne(13, 7999), is("146373"));
    }

    @Test
    public void partOne04() {
        assertThat(processor.partOne(17, 1104), is("2764"));
    }

    @Test
    public void partOne05() {
        assertThat(processor.partOne(21, 6111), is("54718"));
    }

    @Test
    public void partOne06() {
        assertThat(processor.partOne(30, 5807), is("37305"));
    }

    @Test
    public void partTwo() {

    }
}