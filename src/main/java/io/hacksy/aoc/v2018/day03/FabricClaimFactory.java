package io.hacksy.aoc.v2018.day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FabricClaimFactory {
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
