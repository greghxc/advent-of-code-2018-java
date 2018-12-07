package io.hacksy.aoc.v2018.day05;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day05Processor {
    private static final List<String> alphabet = Lists.charactersOf("abcdefghijklmnopqrstuvwxyz").stream()
            .map(c -> c.toString()).collect(Collectors.toList());

    String partOne(List<String> input) {
        return String.valueOf(react(input.get(0)).length());
    }

    String partTwo(List<String> input) {
        String inputString = react(input.get(0));
        OptionalInt answer = alphabet.stream()
                .map(l -> inputString.replaceAll(String.format("%s|%s", l, l.toUpperCase()), ""))
                .map(this::react)
                .mapToInt(String::length).min();
        return String.valueOf(answer.getAsInt());
    }

    private String react(String input) {
        String oldLine = null;
        while (oldLine == null || !oldLine.equals(input)) {
            oldLine = input;
            for (String letter : allCombos()) {
                input = StringUtils.replace(input, letter, "");
            }
        }
        return input;
    }

    private Set<String> allCombos() {
        Set<String> pairs = new HashSet<>();
        for (String letter : alphabet) {
            pairs.add(letter + letter.toUpperCase());
            pairs.add(letter.toUpperCase() + letter);
        }
        return pairs;
    }
}
