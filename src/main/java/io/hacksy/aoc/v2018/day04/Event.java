package io.hacksy.aoc.v2018.day04;

import lombok.Data;

@Data
class Event {
    private final String guardId;
    private final Integer minute;
    private final String type;
}
