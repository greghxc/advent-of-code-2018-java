package io.hacksy.aoc.v2016.day11;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Component {
    @Getter private final String element;
    @Getter private final Type type;

    public String toString() {
        return String.format("%s-%s", element, type.toString());
    }

    public enum Type {
        MICROCHIP, GENERATOR
    }
}
