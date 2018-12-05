package io.hacksy.aoc.v2018.day01;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

// Day one was fast! I'd mostly set up my project ahead of time, so part one was really just reading the story
// and pasting in the input. Part two took minimally longer.
public class Day01Processor {
    public Integer partOne(List<String> input) {
        return input.stream().mapToInt(Integer::valueOf).sum();
    }

    public Integer partTwo(List<String> input) {
        Set<Integer> intSet = Sets.newHashSet(0);
        int agg = 0;

        for (int i = 0; true; i = (i + 1) % input.size()){
            agg += Integer.valueOf(input.get(i));
            if (intSet.contains(agg)) {
                return agg;
            }
            intSet.add(agg);
        }
    }
}
