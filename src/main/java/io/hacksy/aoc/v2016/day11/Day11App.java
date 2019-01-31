package io.hacksy.aoc.v2016.day11;

import io.hacksy.aoc.v2018.util.Performance;

import java.util.List;

import static io.hacksy.aoc.v2016.day11.ComposedComponent.Element.*;
import static io.hacksy.aoc.v2016.day11.ComposedComponent.Type.*;

public class Day11App {
    public static void main( String[] args ) {
        Day11Processor processor = new Day11Processor();

        List<List<ComposedComponent>> partOneFloors = List.of(
                List.of(new ComposedComponent(T, G), new ComposedComponent(T, M), new ComposedComponent(P, G), new ComposedComponent(S, G)),
                List.of(new ComposedComponent(P, M), new ComposedComponent(S, M)),
                List.of(new ComposedComponent(PR, G), new ComposedComponent(PR, M), new ComposedComponent(R, G),new ComposedComponent(R, M)),
                List.of()
        );

        List<List<ComposedComponent>> partTwoFloors = List.of(
                List.of(new ComposedComponent(T, G), new ComposedComponent(T, M), new ComposedComponent(P, G), new ComposedComponent(S, G),
                        new ComposedComponent(E, G), new ComposedComponent(E, M),new ComposedComponent(D, G),new ComposedComponent(D, M)),
                List.of(new ComposedComponent(P, M), new ComposedComponent(S, M)),
                List.of(new ComposedComponent(PR, G), new ComposedComponent(PR, M), new ComposedComponent(R, G),new ComposedComponent(R, M)),
                List.of()
        );

//        The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
//        The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
//                The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
//                The fourth floor contains nothing relevant.

        List<List<ComposedComponent>> paulsFloors = List.of(
                List.of(new ComposedComponent(T, G), new ComposedComponent(T, M), new ComposedComponent(P, G), new ComposedComponent(S, G)),
                List.of(new ComposedComponent(P, M), new ComposedComponent(S, M)),
                List.of(new ComposedComponent(PR, G), new ComposedComponent(PR, M), new ComposedComponent(R, G),new ComposedComponent(R, M)),
                List.of()
        );

        Performance.timeAndPrint(() ->
                String.format("Day 11 - Part 1: %s", processor.part01(partOneFloors)));
        Performance.timeAndPrint(() ->
                String.format("Day 11 - Part 2: %s", processor.part01(partTwoFloors)));
    }
}
