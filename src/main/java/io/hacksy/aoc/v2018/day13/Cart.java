package io.hacksy.aoc.v2018.day13;

import com.google.common.collect.Lists;
import io.hacksy.aoc.v2018.util.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Cart {
    private Character heading;
    private int decisionNum = 0;

    private List<Consumer<Cart>> decisionList = Lists.newArrayList(
            Cart::turnLeft,
            (c) -> {},
            Cart::turnRight
    );

    public Cart(Character heading) {
        this.heading = heading;
    }

    void modifyDirection(RailMap.DirectionModifier modifier) {
        switch (modifier) {
            case FORWARD_SLASH:
                switch (heading) {
                    case '<':
                        heading = 'v';
                        break;
                    case '^':
                        heading = '>';
                        break;
                    default:
                        throw new RuntimeException("Unexpect director modifier.");
                }
                break;
            case BACK_SLASH:
                switch (heading) {
                    case '>':
                        heading = 'v';
                        break;
                    case '^':
                        heading = '<';
                        break;
                    default:
                        throw new RuntimeException("Unexpect director modifier.");
                }
                break;
            case INTERSECTION:
                decisionList.get(decisionNum).accept(this);
                decisionNum++;
                break;
        }
    }

    void turnLeft() {
        switch (heading) {
            case '^':
                heading = '<';
                break;
            case '<':
                heading = 'v';
                break;
            case 'v':
                heading = '>';
                break;
            case '>':
                heading = '^';
                break;
        }
    }

    void turnRight() {
        switch (heading) {
            case '^':
                heading = '>';
                break;
            case '>':
                heading = 'v';
                break;
            case 'v':
                heading = '<';
                break;
            case '<':
                heading = '^';
                break;
        }
    }

    Coordinate move(Coordinate startingCoordinate) {
        return movementFunctionForHeading(heading).apply(startingCoordinate);
    }

    private static Function<Coordinate, Coordinate> movementFunctionForHeading(Character heading) {
        Map<Character, Function<Coordinate, Coordinate>> headingFunctions = new HashMap<>();
        headingFunctions.put('^', (c) -> new Coordinate(c.getX(), c.getY() - 1));
        headingFunctions.put('>', (c) -> new Coordinate(c.getX() + 1, c.getY()));
        headingFunctions.put('v', (c) -> new Coordinate(c.getX(), c.getY() + 1));
        headingFunctions.put('<', (c) -> new Coordinate(c.getX() - 1, c.getY() - 1));
        if (headingFunctions.containsKey(heading)) {
            return headingFunctions.get(heading);
        } else {
            throw new RuntimeException("Invalid heading: " + heading);
        }
    }
}
