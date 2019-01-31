package io.hacksy.aoc.v2016.day11;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.hacksy.aoc.v2016.day11.Component.Type.GENERATOR;

public class BuildingValidator {
    private static Predicate<List<Component>> genericPred = (c) -> {
        List<Component> chips = c.stream().filter(component -> component.getType() == Component.Type.MICROCHIP).collect(Collectors.toList());
        List<Component> generators = c.stream().filter(component -> component.getType() == Component.Type.GENERATOR).collect(Collectors.toList());

        return chips.stream().map(chip -> !generators.contains(new Component(chip.getElement(), GENERATOR)) && generators.size() > 0).filter(b -> b).findFirst().isEmpty();
    };

    public static boolean validate(List<List<Component>> buildingState) {
        return buildingState.stream()
                .map(f -> !genericPred.test(f))
                .filter(b -> b)
                .findFirst().isEmpty();
    }
}
