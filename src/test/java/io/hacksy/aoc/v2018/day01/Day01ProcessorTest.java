package io.hacksy.aoc.v2018.day01;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class Day01ProcessorTest {
    @Test
    public void partOne() {
        Day01Processor processor = new Day01Processor();

        List<List<String>> inputs = Lists.newArrayList(
                Lists.newArrayList("+1", "-2", "+3", "+1"),
                Lists.newArrayList("+1", "+1", "+1"),
                Lists.newArrayList("+1", "+1", "-2"),
                Lists.newArrayList("-1", "-2", "-3")
        );

        List<Integer> expectedResults = Lists.newArrayList(3, 3, 0, -6);

        List<Integer> actualResults = inputs.stream()
                .map(processor::partOne)
                .collect(Collectors.toList());

        assertThat(actualResults, contains(expectedResults.toArray()));
    }

    @Test
    public void partTwo() {
        Day01Processor processor = new Day01Processor();

        List<List<String>> inputs = Lists.newArrayList(
                Lists.newArrayList("+1", "-2", "+3", "+1"),
                Lists.newArrayList("+1", "-1"),
                Lists.newArrayList("+3", "+3", "+4", "-2", "-4"),
                Lists.newArrayList("-6", "+3", "+8", "+5", "-6"),
                Lists.newArrayList("+7", "+7", "-2", "-7", "-4")
        );

        List<Integer> expectedResults = Lists.newArrayList(2, 0, 10, 5, 14);

        List<Integer> actualResults = inputs.stream()
                .map(processor::partTwo)
                .collect(Collectors.toList());

        assertThat(actualResults, contains(expectedResults.toArray()));
    }
}