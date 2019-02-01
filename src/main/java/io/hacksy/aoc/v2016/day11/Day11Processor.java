package io.hacksy.aoc.v2016.day11;

import java.util.*;
import java.util.stream.Collectors;

public class Day11Processor {
    public Integer partOne(List<String> input) {
        List<Set<Component>> floors = input.stream()
                .map(ComponentParser::parse)
                .collect(Collectors.toList());

        ArrayDeque<Iteration> queue = new ArrayDeque<>();
        queue.push(new Iteration(floors, 0, 0));

        Set<Iteration> seen = new HashSet<>();

        // no need to do this every time I wanna isdone, lazily putting this here
        int componentCount = floors.stream().mapToInt(Set::size).sum();

        while (!queue.isEmpty()) {
            Iteration currentIt = queue.pop();
            // get all "physically" possible next moves and filter out the ones the ones that fry the chips.
            List<Iteration> iterations = FloorsCombinator.getPossibleNextFloors(currentIt).stream()
                    .filter(i -> BuildingValidator.validate(i.getFloors()))
                    .collect(Collectors.toList());

            // if any iteration is done, lets stop. the best anything else in the queue could do is match the same
            // number of steps. if i've already seen an iteration where the floor layout is the same AND the elevator
            // is on the same floor, no need to do it again; I would already have queued up one as short or shorter.
            for (Iteration iteration : iterations) {
                if (isDone(iteration, componentCount)) { return Math.toIntExact(iteration.getIterationNumber()); }
                if (!seen.contains(cleanIteration(iteration))) {
                    queue.add(iteration);
                    seen.add(cleanIteration(iteration));
                }
            }
        }

        // return a known bad value just so I don't have to write some handler
        return -1;
    }

    // "clear" iteration for seen set. this is just so I can reuse the iteration class.
    private Iteration cleanIteration(Iteration iteration) {
        return new Iteration(
                iteration.getFloors(),
                iteration.getElevatorIndex(),
                0
        );
    }

    // are all the elements on the top floor?
    private boolean isDone(Iteration iteration, int totalElements) {
        return iteration.getFloors().get(iteration.getFloors().size() - 1).size() == totalElements;
    }
}
