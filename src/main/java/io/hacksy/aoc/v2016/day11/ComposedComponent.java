package io.hacksy.aoc.v2016.day11;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class ComposedComponent {
    @Getter
    private final Element element;
    @Getter
    private final Type type;

    public String toString() {
        return String.format("%s%s", element.toString(), type.toString());
    }

    public enum Type {
        M, G
    }
    public enum Element {
        H, L, T, P, S, R, PR, E, D
    }
}
