package io.hacksy.aoc.v2018.day05;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day05Processor {
    Pattern pattern = Pattern.compile(allCombos().stream().collect(Collectors.joining("|")));

    String partOne(List<String> input) {
        return String.valueOf(react(input.get(0)).length());
    }

    String partTwo(List<String> input) {
        String inputString = input.get(0);
        List<String> letters = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        OptionalInt answer = letters.stream()
                .map(letter -> {
                    return Lists.charactersOf(inputString).stream()
                            .filter(c -> {
                                return !String.valueOf(c).toLowerCase().equals(letter);
                            })
                            .map(String::valueOf)
                            .collect(Collectors.joining());
                })
                .map(this::react)
                .mapToInt(String::length).min();
        return String.valueOf(answer.getAsInt());
    }

    private String react(String input) {
        String remaining = "";
        String newestResult = input;

        while (remaining.length() != newestResult.length()) {
            remaining = newestResult;
            newestResult = pattern.matcher(input).replaceAll("");
        }

        return newestResult;
    }

    private Set<String> allCombos() {
        Set<String> pairs = new HashSet<>();
        List<String> letters = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        for (String letter : letters) {
            pairs.add(letter + letter.toUpperCase());
            pairs.add(letter.toUpperCase() + letter);
        }
        return pairs;
    }
}
