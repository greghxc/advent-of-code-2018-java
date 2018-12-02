package io.hacksy.aoc.v2018.day02;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day02Processor {
    public Long partOne(List<String> input) {
        return input.stream()
                .map(str -> Lists.charactersOf(str).stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                .map(m -> m.values().stream().distinct())
                .flatMap(Function.identity())
                .filter(l -> l == 2L || l == 3L)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .reduce(1L, (a, b) -> a * b);
    }

    public String partTwo(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size() - 1; j++) {
                String sameLetters = sameLetters(input.get(i), input.get(j));
                if (sameLetters.length() == input.get(i).length() - 1) { return sameLetters; }
            }
        }
        throw new RuntimeException("No match found");
    }

    private String sameLetters(String str1, String str2) {
        if (str1.length() != str1.length()) { throw new IllegalArgumentException("String must be same length."); }
        return IntStream.range(0, str1.length())
                .filter(i -> str1.charAt(i) == str2.charAt(i))
                .mapToObj(str1::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}