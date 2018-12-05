package io.hacksy.aoc.v2018.day05;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class Day05Processor {
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
                                return c != Character.valueOf(letter.charAt(0)) && c != Character.valueOf(letter.toUpperCase().charAt(0));
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
            newestResult = cleanUpString(remaining);
        }

        return newestResult;
    }

    private String cleanUpString(String input) {
        StringBuilder remainingString = new StringBuilder();
        Set<String> pairs = allCombos();
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i == charArray.length - 1) {
                remainingString.append(charArray[i]);
            } else {
                String pair = new String(new char[] {charArray[i], charArray[i+1]});
                if (pairs.contains(pair)) {
                    i++;
                } else {
                    remainingString.append(charArray[i]);
                }
            }
        }
        return remainingString.toString();
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
