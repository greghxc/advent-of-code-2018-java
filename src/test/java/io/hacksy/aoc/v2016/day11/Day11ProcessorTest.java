package io.hacksy.aoc.v2016.day11;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day11ProcessorTest {
    @Test
    public void test01() {
        List<String> input = List.of(
                "The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.",
                "The second floor contains a hydrogen generator.",
                "The third floor contains a lithium generator.",
                "The fourth floor contains nothing relevant."
        );

        Day11Processor processor = new Day11Processor();

        assertThat(processor.partOne(input), is(11));
    }
}
