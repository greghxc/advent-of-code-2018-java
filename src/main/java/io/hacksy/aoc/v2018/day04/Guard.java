package io.hacksy.aoc.v2018.day04;

import lombok.Data;

import java.util.List;

@Data
class Guard {
    private final String id;
    private final List<Integer> sleepingMinutes;
}