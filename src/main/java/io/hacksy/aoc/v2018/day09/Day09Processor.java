package io.hacksy.aoc.v2018.day09;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day09Processor {
    String partOneOld(int playerCount, int finalScore) {
        LinkedList<Integer> circle = new LinkedList<>();
        Supplier<Integer> idSupplier = new Supplier<Integer>() {
            Integer lastId = 0;

            @Override
            public Integer get() {
                lastId++;
                return lastId;
            }
        };

        List<List<Integer>> playerScores = IntStream.range(0, playerCount).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());

        Integer currentPlayerIndex = 0;
        Integer currentMarbleIndex = 0;

        circle.add(0);

        Integer lastScore = null;
        Integer lastMarble = 0;

        while (lastScore == null || lastScore != finalScore) {
            Integer newMarble = idSupplier.get();
            lastMarble = newMarble;

            if (newMarble % 23 == 0) {
                if (currentMarbleIndex > 7) {
                    currentMarbleIndex = currentMarbleIndex - 7;
                } else {
                    currentMarbleIndex = circle.size() - Math.abs(currentMarbleIndex - 7) % circle.size();
                }

                Integer bonusMarble = circle.remove(currentMarbleIndex.intValue());

                playerScores.get(currentPlayerIndex).add(bonusMarble);
                playerScores.get(currentPlayerIndex).add(newMarble);
            } else {
                currentMarbleIndex = (currentMarbleIndex + 2) % circle.size();
                circle.add(currentMarbleIndex, newMarble);
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % playerScores.size();
            lastScore = newMarble;
        }

        return Integer.toString(playerScores.stream()
                .mapToInt(i -> i.stream()
                        .mapToInt(Integer::intValue).sum())
                .max().getAsInt());
    }

    String partOne(int playerCount, int finalScore) {
        Circle circle = new Circle();

        Supplier<Integer> idSupplier = new Supplier<Integer>() {
            Integer lastId = 0;

            @Override
            public Integer get() {
                lastId++;
                return lastId;
            }
        };

        List<Long> playerScores = IntStream.range(0, playerCount).mapToObj(i -> 0L).collect(Collectors.toList());

        Integer currentPlayerIndex = 0;

        Integer lastScore = null;
        Integer lastMarble = 0;

        while ((lastScore == null || lastScore != finalScore)) {
            Integer newMarble = idSupplier.get();
            lastMarble = newMarble;

            if (newMarble % 23 == 0) {
                circle.advanceCounterClockwise(7);
                int bonusMarble = circle.removeItem().getId();

                playerScores.set(currentPlayerIndex, playerScores.get(currentPlayerIndex) + bonusMarble + newMarble);
            } else {
                circle.advanceClockwise(2);
                circle.addItem(newMarble);
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % playerScores.size();
            lastScore = newMarble;
        }


        return playerScores.stream().max(Comparator.comparingLong(i -> i)).get().toString();
    }
}
