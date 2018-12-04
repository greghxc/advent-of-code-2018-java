package io.hacksy.aoc.v2018.day03;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03Processor {
    private FabricClaimFactory claimFactory = new FabricClaimFactory();

    public Long partOne(List<String> input) {
        Map<Coordinate, Long> coordinates = input.stream()
                .map(claimFactory::fabricClaimFrom)
                .map(this::coordinatesInClaim)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return coordinates.values().stream()
                .filter(l -> l > 1L)
                .count();
    }

    public String partTwo(List<String> input) {
        List<FabricClaim> claims = input.stream()
                .map(claimFactory::fabricClaimFrom)
                .collect(Collectors.toList());

        Map<Coordinate, Long> coordinates = claims.stream()
                .map(this::coordinatesInClaim)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (FabricClaim claim : claims) {
            Long sharedCoords = coordinatesInClaim(claim).stream()
                    .map(coordinates::get)
                    .filter(seenCount -> seenCount > 1)
                    .count();

            if (sharedCoords == 0) { return claim.toString(); }
        }

        throw new RuntimeException("No unshared claims found.");
    }

    private List<Coordinate> coordinatesInClaim(FabricClaim claim) {
        Coordinate topLeft =
                new Coordinate(claim.getLeftMargin(), claim.getTopMargin());
        Coordinate bottomRight =
                new Coordinate(claim.getWidth() + claim.getLeftMargin(), claim.getHeight() + claim.getTopMargin());
        return coordinatesInRange(topLeft, bottomRight);
    }

    private List<Coordinate> coordinatesInRange(Coordinate topLeft, Coordinate bottomRight) {
        List<Coordinate> coords = new ArrayList<>();
        for (int i = topLeft.getX(); i < bottomRight.getX(); i++) {
            for (int j = topLeft.getY(); j < bottomRight.getY(); j++) {
                coords.add(new Coordinate(i, j));
            }
        }
        return coords;
    }

    private class FabricClaimFactory {
        private final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

        FabricClaim fabricClaimFrom(String line) {
            Matcher m = PATTERN.matcher(line);

            if (m.find()) {
                return new FabricClaim(
                        Integer.valueOf(m.group(1)),
                        Integer.valueOf(m.group(2)),
                        Integer.valueOf(m.group(3)),
                        Integer.valueOf(m.group(4)),
                        Integer.valueOf(m.group(5))
                );
            } else {
                throw new IllegalArgumentException(String.format("Cannot create FabricClaim: %s", line));
            }
        }
    }

    @Data
    private class FabricClaim {
        private final int id;
        private final int leftMargin;
        private final int topMargin;
        private final int width;
        private final int height;
    }
}
