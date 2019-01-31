package io.hacksy.aoc.v2016.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class FloorsCombinator {
    static List<Iteration> getPossibleNextFloors(Iteration iteration) {
        List<List<ComposedComponent>> possibleCombinations = new ArrayList<>();
        List<ComposedComponent> currentFloor = iteration.getFloors().get(iteration.getElevatorIndex());

        possibleCombinations.addAll(currentFloor.stream().map(Collections::singletonList).collect(Collectors.toList()));

        for (int x = 0; x < currentFloor.size(); x++) {
            for (int y = x + 1; y < currentFloor.size(); y++) {
                possibleCombinations.add(asList(currentFloor.get(x), currentFloor.get(y)));
            }
        }

        List<Iteration> iterations = new ArrayList<>();

        for (List<ComposedComponent> combination : possibleCombinations) {
            if (iteration.getElevatorIndex() < iteration.getFloors().size() - 1) {
                iterations.add(
                        new Iteration(
                                moveItems(iteration.getElevatorIndex(), 1, combination, iteration.getFloors()),
                                iteration.getElevatorIndex() + 1,
                                iteration.getIterationNumber() + 1
                        )
                );
            }

            if (iteration.getElevatorIndex() > 0) {
                iterations.add(
                        new Iteration(
                                moveItems(iteration.getElevatorIndex(), -1, combination, iteration.getFloors()),
                                iteration.getElevatorIndex() - 1,
                                iteration.getIterationNumber() + 1
                        )
                );
            }
        }

        return iterations;
    }

    private static List<List<ComposedComponent>> moveItems(int position, int movement, List<ComposedComponent> components, List<List<ComposedComponent>> origList) {
        return IntStream.range(0, origList.size())
                .mapToObj(fIndex -> {
                    if (fIndex == position + movement) {
                        return Stream.concat(origList.get(fIndex).stream(), components.stream()).collect(Collectors.toList());
                    }
                    if (fIndex == position) {
                        List<ComposedComponent> newList = new ArrayList<>(origList.get(fIndex));
                        newList.removeAll(components);
                        return newList;
                    }
                    return origList.get(fIndex);
                })
                .collect(Collectors.toList());
    }
}
