package io.hacksy.aoc.v2016.day11;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.hacksy.aoc.v2016.day11.Component.Type.GENERATOR;

public class BuildingValidator {
    private static Predicate<Set<Component>> genericPred = (c) -> {
        // this is probably unneeded work, but it made the next part easier to reason on when writing it
        var chips = c.stream().filter(component -> component.getType() == Component.Type.MICROCHIP).collect(Collectors.toList());
        var generators = c.stream().filter(component -> component.getType() == Component.Type.GENERATOR).collect(Collectors.toList());

        // return true if there are any chips that do not have a matching generator IF there are non-matching generators on the same floor
        return chips.stream().map(chip -> !generators.contains(new Component(chip.getElement(), GENERATOR)) && generators.size() > 0).filter(b -> b).findFirst().isEmpty();
    };

    public static boolean validate(List<Set<Component>> buildingState) {
        // test each floor, filter for the ones that failed, and break as soon as one is found. valid if none are found.
        return buildingState.stream()
                .map(f -> genericPred.test(f))
                .filter(b -> !b)
                .findFirst().isEmpty();
    }
}
