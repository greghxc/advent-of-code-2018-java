package io.hacksy.aoc.v2016.day11;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ComponentParser {
    static Pattern pattern = Pattern.compile("([A-Za-z]+)(-compatible)? (generator|microchip)");
    public static Set<Component> parse(String line) {
        var matcher = pattern.matcher(line);
        var components = new HashSet<Component>();
        while (matcher.find()) {
            components.add(new Component(matcher.group(1), Component.Type.valueOf(matcher.group(3).toUpperCase())));
        }
        return components;
    }
}
