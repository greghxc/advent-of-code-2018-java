package io.hacksy.aoc.v2016.day11;

import java.util.*;
import java.util.stream.Collectors;

public class Day11Processor {
    public Integer partOne(List<String> input) {
        List<List<Component>> floors = input.stream()
                .map(ComponentParser::parse)
                .collect(Collectors.toList());

        ArrayDeque<Iteration> queue = new ArrayDeque<>();
        queue.push(new Iteration(floors, 0, 0));

        Set<Iteration> seen = new HashSet<>();

        while (!queue.isEmpty()) {
            Iteration currentIt = queue.pop();
            List<Iteration> iterations = FloorsCombinator.getPossibleNextFloors(currentIt).stream()
                    .filter(i -> BuildingValidator.validate(i.getFloors()))
                    .collect(Collectors.toList());

            for (Iteration iteration : iterations) {
                if (isDone(iteration)) { return Math.toIntExact(iteration.getIterationNumber()); }
                if (!seen.contains(cleanIteration(iteration))) {
                    queue.add(iteration);
                    seen.add(cleanIteration(iteration));
                }
            }
        }

        return -1;
    }

    private Iteration cleanIteration(Iteration iteration) {
        return new Iteration(
                iteration.getFloors().stream().map(f -> f.stream().sorted(Comparator.comparingInt(Component::hashCode)).collect(Collectors.toList())).collect(Collectors.toList()),
                iteration.getElevatorIndex(),
                0
        );
    }

    private boolean isDone(Iteration iteration) {
        int componentCount = iteration.getFloors().stream().mapToInt(List::size).sum();
        return iteration.getFloors().get(iteration.getFloors().size() - 1).size() == componentCount;
    }
}
