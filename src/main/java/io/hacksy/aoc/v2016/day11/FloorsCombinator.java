package io.hacksy.aoc.v2016.day11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class FloorsCombinator {
    // get all "physically" possible next moves by moving every possible combination up and down a floor if there is one.
    static List<Iteration> getPossibleNextFloors(Iteration iteration) {
        List<List<Component>> possibleCombinations = new ArrayList<>();
        Set<Component> currentFloorSet = iteration.getFloors().get(iteration.getElevatorIndex());
        List<Component> currentFloor = new ArrayList<>(currentFloorSet);

        possibleCombinations.addAll(currentFloor.stream().map(Collections::singletonList).collect(Collectors.toList()));

        for (int x = 0; x < currentFloor.size(); x++) {
            for (int y = x + 1; y < currentFloor.size(); y++) {
                possibleCombinations.add(asList(currentFloor.get(x), currentFloor.get(y)));
            }
        }

        List<Iteration> iterations = new ArrayList<>();

        for (List<Component> combination : possibleCombinations) {
            if (iteration.getElevatorIndex() < iteration.getFloors().size() - 1) {
                iterations.add(moveItems(1, new HashSet<>(combination), iteration));
            }

            if (iteration.getElevatorIndex() > 0) {
                iterations.add(moveItems(-1, new HashSet<>(combination), iteration));
            }
        }

        return iterations;
    }

    private static Iteration moveItems(int movement, Set<Component> componentsToMove, Iteration iteration) {
        var origList = iteration.getFloors();
        var position = iteration.getElevatorIndex();
        var floors = IntStream.range(0, origList.size())
                .mapToObj(fIndex -> {
                    if (fIndex == position + movement) {
                        return Stream.concat(origList.get(fIndex).stream(), componentsToMove.stream()).collect(Collectors.toSet());
                    }
                    if (fIndex == position) {
                        return origList.get(fIndex).stream().filter(c -> !componentsToMove.contains(c)).collect(Collectors.toSet());
                    }
                    return origList.get(fIndex);
                })
                .collect(Collectors.toList());

        return new Iteration(
                floors,
                iteration.getElevatorIndex() + movement,
                iteration.getIterationNumber() + 1
        );
    }
}
