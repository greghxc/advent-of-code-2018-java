package io.hacksy.aoc.v2018.day01;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class Day01Processor {
    public Integer partOne(List<String> input) {
        return input.stream().mapToInt(Integer::valueOf).sum();
    }

    public Integer partTwo(List<String> input) {
        Set<Integer> intSet = Sets.newHashSet(0);
        int agg = 0;
        int i = 0;

        while (true) {
            agg += Integer.valueOf(input.get(i % input.size()));
            if (intSet.contains(agg)) { return agg; }
            intSet.add(agg);
            i++;
        }
    }
}
