package io.hacksy.aoc.v2016.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComponentParser {
    static Pattern pattern = Pattern.compile("([A-Za-z]+)(-compatible)? (generator|microchip)");
    public static List<Component> parse(String line) {
        Matcher matcher = pattern.matcher(line);
        List<Component> components = new ArrayList<>();
        while (matcher.find()) {
            components.add(new Component(matcher.group(1), Component.Type.valueOf(matcher.group(3).toUpperCase())));
        }
        return components;
    }
}
