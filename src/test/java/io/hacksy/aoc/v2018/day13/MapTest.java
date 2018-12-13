package io.hacksy.aoc.v2018.day13;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MapTest {
    @Test
    public void enumTest() {
        assertThat(RailMap.DirectionModifier.valueOf("/"), is(RailMap.DirectionModifier.FORWARD_SLASH));
    }
}