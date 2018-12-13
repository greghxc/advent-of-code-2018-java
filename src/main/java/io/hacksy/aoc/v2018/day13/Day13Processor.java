package io.hacksy.aoc.v2018.day13;

import io.hacksy.aoc.v2018.util.Coordinate;

import java.util.*;

public class Day13Processor {
    String partOne(List<String> input) {
        RailMap railMap = new RailMap(input);
        Coordinate crashSite = null;

        while (crashSite == null) {
            Optional<Coordinate> foundCrash = railMap.getCartMap().keySet().stream()
                    .sorted(Comparator.comparingInt(c -> c.getY() * 1000 + c.getX()))
                    .map(c -> {
                        Cart cart = railMap.getCartMap().get(c);
                        Coordinate newCoordinate = cart.move(c);
                        railMap.getCartMap().remove(c);

                        if (railMap.getCartMap().containsKey(newCoordinate)) {
                            return newCoordinate;
                        } else {
                            railMap.getCartMap().put(newCoordinate, cart);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .findFirst();
            crashSite = foundCrash.orElse(null);
        }


        return String.format("%s,%s", crashSite.getX(), crashSite.getY());
    }

    String partTwo(List<String> input) {
        return null;
    }
}
