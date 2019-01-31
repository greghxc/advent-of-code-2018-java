package io.hacksy.aoc.v2016.day11;

import org.junit.Test;

import java.util.List;

import static io.hacksy.aoc.v2016.day11.ComposedComponent.Element.*;
import static io.hacksy.aoc.v2016.day11.ComposedComponent.Type.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FloorsCombinatorTest {

    @Test
    public void getPossibleNextFloors() {
        List<List<ComposedComponent>> floors = List.of(
                List.of(new ComposedComponent(H, M), new ComposedComponent(L, M)),
                List.of(new ComposedComponent(H, G)),
                List.of(new ComposedComponent(L, G)),
                List.of()
        );

        Iteration iteration = new Iteration(floors, 0, 0);

        var result = FloorsCombinator.getPossibleNextFloors(iteration);

        assertThat(result.size(), is(3));
    }
}