package io.hacksy.aoc.v2016.day11;

import lombok.Data;

import java.util.List;

@Data
public class Iteration {
    private final List<List<Component>> floors;
    private final int elevatorIndex;
    private final long iterationNumber;
}
