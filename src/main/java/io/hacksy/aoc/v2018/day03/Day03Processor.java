package io.hacksy.aoc.v2018.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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




}
