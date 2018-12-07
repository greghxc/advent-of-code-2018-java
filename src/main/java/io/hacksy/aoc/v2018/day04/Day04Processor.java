package io.hacksy.aoc.v2018.day04;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// SO. MUCH. TYPING.
public class Day04Processor {
    final Pattern pattern = Pattern.compile("\\[\\d+-\\d+-\\d+ (\\d+):(\\d+)\\] ?(Guard #(\\d+))? ([a-z ]+)");

    public String partOne(List<String> input) {
        Map<String, List<Integer>> sleepingMinutes = getSleepingMinutes(input);

        // get the sleepiest guard
        List<Guard> sorted = sleepingMinutes.keySet().stream()
                .map(k -> new Guard(k, sleepingMinutes.get(k)))
                .sorted(Comparator.comparingInt(g -> g.getSleepingMinutes().size()))
                .collect(Collectors.toList());

        Guard sleepiest = sorted.get(sorted.size() - 1);

        // Fine the minute he slept the most
        Map<Integer, Long> mins = sleepiest.getSleepingMinutes().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long maxValue = 0L;
        Integer maxInteger = null;

        for (Integer min : mins.keySet()) {
            if (mins.get(min) > maxValue) {
                maxValue = mins.get(min);
                maxInteger = min;
            }
        }

        return String.valueOf(Integer.valueOf(sleepiest.getId()) * (maxInteger - 60));
    }

    public String partTwo(List<String> input) {
        Map<String, List<Integer>> sleepingMinutes = getSleepingMinutes(input);

        // get the counts for each minute of who slept and how many times
        Map<Integer, Map<String, Long>> countMap = new HashMap<>();
        for (String id : sleepingMinutes.keySet()) {
            Map<Integer, Long> counts = sleepingMinutes.get(id).stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (Integer minute : counts.keySet()) {
                Map<String, Long> existingMap = countMap.getOrDefault(minute, new HashMap<>());
                existingMap.put(id, counts.get(minute));
                countMap.put(minute, existingMap);
            }
        }

        // figure out the sleepiest person who slept most for each minute
        Map<Integer, String> sleepiest = new HashMap<>();
        Map<Integer, Long> mostTimesAsleep = new HashMap<>();
        for (Integer minute : countMap.keySet()) {
            for (String id : countMap.get(minute).keySet()) {
                if (countMap.get(minute).get(id) > mostTimesAsleep.getOrDefault(minute, 0L)) {
                    mostTimesAsleep.put(minute, countMap.get(minute).get(id));
                    sleepiest.put(minute, id);
                }
            }
        }

        // go through each minute to figure out which minute had the most repeated sleeps
        Integer sleepiestInteger = null;
        String sleepiestString = null;
        Long mostTimesAsleepLong = 0L;

        for (Integer min : mostTimesAsleep.keySet()) {
            if (mostTimesAsleep.get(min) > mostTimesAsleepLong) {
                mostTimesAsleepLong = mostTimesAsleep.get(min);
                sleepiestString = sleepiest.get(min);
                sleepiestInteger = min;
            }
        }

        // thank god
        return String.valueOf((sleepiestInteger - 60) * Integer.valueOf(sleepiestString));
    }

    private Map<String, List<Integer>> getSleepingMinutes(List<String> input) {
        Map<String, List<Integer>> sleepingMinutes = new HashMap<>();
        List<Event> events = input.stream()
                .sorted()
                .map(this::eventFrom)
                .collect(Collectors.toList());

        // Build a list of all minutes asleep
        String lastID = null;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getType().equals("begins shift")) {
                lastID = event.getGuardId();
            }
            if (event.getType().equals("wakes up")) {
                List<Integer> originalList = sleepingMinutes.getOrDefault(lastID, new ArrayList<>());
                originalList.addAll(IntStream.range(events.get(i - 1).getMinute(), event.getMinute())
                        .boxed()
                        .collect(Collectors.toList()));
                sleepingMinutes.put(lastID, originalList);
            }
        }

        return sleepingMinutes;
    }

    Event eventFrom(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            int hourBuffer = 0;
            if (matcher.group(1).equals("00")) { hourBuffer = 60; }
            return new Event(
                    matcher.group(4),
                    Integer.valueOf(matcher.group(2)) + hourBuffer,
                    matcher.group(5)
            );
        }
        throw new RuntimeException(String.format("Can't build event from: %s", line));
    }
}
