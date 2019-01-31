package io.hacksy.aoc.v2016.day11;

import org.junit.Test;

import java.util.List;

import static io.hacksy.aoc.v2016.day11.ComposedComponent.Element.*;
import static io.hacksy.aoc.v2016.day11.ComposedComponent.Type.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day11ProcessorTest {
    @Test
    public void test01() {
        List<List<ComposedComponent>> floors = List.of(
                List.of(new ComposedComponent(H, M), new ComposedComponent(L, M)),
                List.of(new ComposedComponent(H, G)),
                List.of(new ComposedComponent(L, G)),
                List.of()
        );

        Day11Processor processor = new Day11Processor();

        assertThat(processor.part01(floors), is(11));
    }
}
