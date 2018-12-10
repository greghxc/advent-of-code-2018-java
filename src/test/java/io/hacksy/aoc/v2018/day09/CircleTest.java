package io.hacksy.aoc.v2018.day09;

import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CircleTest {

    @Test
    public void advanceClockwise() {
        Circle circle = new Circle();
        circle.addItem(1);
        circle.addItem(2);
        circle.addItem(3);
        circle.addItem(4);
        // 4*, 3, 2, 1, 0
        circle.advanceClockwise(12);
        assertThat(circle.currentItem().getId(), is(2));
    }

    @Test
    public void advanceCounterClockwise() {
        Circle circle = new Circle();
        circle.addItem(1);
        circle.addItem(2);
        circle.addItem(3);
        circle.addItem(4);
        // 4*, 3, 2, 1, 0
        circle.advanceCounterClockwise(12);
        assertThat(circle.currentItem().getId(), is(1));
    }

    @Test
    public void addItem() {
        Circle circle = new Circle();
        circle.addItem(1);
        circle.addItem(2);
        circle.addItem(3);
        circle.addItem(4);

        Collection<Circle.Item> items = circle.items.values().stream().sorted(Comparator.comparing(i -> i.getId())).collect(Collectors.toList());
        assertThat(circle.currentItem().getId(), is(4));
        assertThat(circle.currentItem().getNext(), is(3));
        assertThat(circle.currentItem().getPrevious(), is(0));
    }

    @Test
    public void removeItem() {
        Circle circle = new Circle();
        circle.addItem(1);
        circle.addItem(2);
        circle.addItem(3);
        circle.addItem(4);
        // 4*, 3, 2, 1, 0
        circle.advanceClockwise(2);
        // 4, 3, 2*, 1, 0
        circle.removeItem();
        // 4, 3, 1*, 0
        assertThat(circle.currentItem().getId(), is(1));
        circle.advanceClockwise(7);
        // 4, 3*, 1, 0
        assertThat(circle.currentItem().getId(), is(3));

    }
}