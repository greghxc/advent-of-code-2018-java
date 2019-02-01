package io.hacksy.aoc.v2016.day11;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BuildingValidatorTest {
    @Test
    public void validate05() {
        List<String> input = List.of(
                "The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.",
                "The second floor contains a hydrogen generator.",
                "The third floor contains a lithium generator.",
                "The fourth floor contains nothing relevant."
        );

        List<Set<Component>> floors = input.stream()
                .map(ComponentParser::parse)
                .collect(Collectors.toList());

        assertTrue(BuildingValidator.validate(floors));
    }
}