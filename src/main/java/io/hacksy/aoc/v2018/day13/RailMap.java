package io.hacksy.aoc.v2018.day13;

import io.hacksy.aoc.v2018.util.Coordinate;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class RailMap {
    @Getter private Map<Coordinate, Cart> cartMap = new HashMap<>();
    @Getter private Map<Coordinate, DirectionModifier> modifierMap = new HashMap<>();

    RailMap(List<String> input) {
        Pattern pattern = Pattern.compile("[\\^>v<\\\\/\\+]");
        IntStream.range(0, input.size())
                .forEach(i -> {
                    Matcher m = pattern.matcher(input.get(i));
                    while (m.find()) {
                        switch(m.group(0)) {
                            case "+":
                                modifierMap.put(new Coordinate(i, m.start()), DirectionModifier.INTERSECTION);
                                break;
                            case "/":
                                modifierMap.put(new Coordinate(i, m.start()), DirectionModifier.FORWARD_SLASH);
                                break;
                            case "\\":
                                modifierMap.put(new Coordinate(i, m.start()), DirectionModifier.BACK_SLASH);
                                break;
                            case "^":
                                cartMap.put(new Coordinate(i, m.start()), new Cart('^'));
                                break;
                            case ">":
                                cartMap.put(new Coordinate(i, m.start()), new Cart('>'));
                                break;
                            case "v":
                                cartMap.put(new Coordinate(i, m.start()), new Cart('v'));
                                break;
                            case "<":
                                cartMap.put(new Coordinate(i, m.start()), new Cart('<'));
                                break;
                        }
                    }
                });
    }

    public enum DirectionModifier {
        FORWARD_SLASH, BACK_SLASH, INTERSECTION;

        static DirectionModifier getEnum(String string) {
            switch (string) {
                case "/":
                    return FORWARD_SLASH;
                case "\\":
                    return BACK_SLASH;
                case "+":
                    return INTERSECTION;
                default:
                    throw new IllegalArgumentException("Invalid modifier.");
            }
        }
    }

    public static class CrashException extends Exception {
        @Getter Coordinate crashSite;

        public CrashException(Coordinate crashSite) {
            this.crashSite = crashSite;
        }
    }
}
