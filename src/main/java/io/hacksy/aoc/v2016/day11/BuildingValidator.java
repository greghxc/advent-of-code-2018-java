package io.hacksy.aoc.v2016.day11;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.hacksy.aoc.v2016.day11.ComposedComponent.Type.G;

public class BuildingValidator {
    private static Predicate<List<ComposedComponent>> genericPred = (c) -> {
        List<ComposedComponent> chips = c.stream().filter(component -> component.getType() == ComposedComponent.Type.M).collect(Collectors.toList());
        List<ComposedComponent> generators = c.stream().filter(component -> component.getType() == ComposedComponent.Type.G).collect(Collectors.toList());

        return chips.stream().map(chip -> !generators.contains(new ComposedComponent(chip.getElement(), G)) && generators.size() > 0).filter(b -> b).findFirst().isEmpty();
    };

    public static boolean validate2(List<List<ComposedComponent>> buildingState) {
        return buildingState.stream()
                .map(f -> !genericPred.test(f))
                .filter(b -> b)
                .findFirst().isEmpty();
    }
}
