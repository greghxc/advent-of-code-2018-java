package io.hacksy.aoc.v2016.day11;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Iteration {
    private final List<Set<Component>> floors;
    private final int elevatorIndex;
    private final long iterationNumber;
}
