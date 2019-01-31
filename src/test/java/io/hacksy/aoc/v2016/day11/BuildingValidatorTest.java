package io.hacksy.aoc.v2016.day11;

import org.junit.Test;

import static io.hacksy.aoc.v2016.day11.ComposedComponent.Element.*;
import static io.hacksy.aoc.v2016.day11.ComposedComponent.Type.*;
import static java.util.Arrays.asList;

import java.util.List;

import static org.junit.Assert.*;

public class BuildingValidatorTest {
    @Test
    public void validate05() {
        List<List<ComposedComponent>> floors = asList(
                asList(new ComposedComponent(H, M)),
                asList(new ComposedComponent(H, M), new ComposedComponent(L, G), new ComposedComponent(H, G)),
                asList(new ComposedComponent(H, M), new ComposedComponent(H, G))
        );
        assertTrue(BuildingValidator.validate2(floors));
    }
}