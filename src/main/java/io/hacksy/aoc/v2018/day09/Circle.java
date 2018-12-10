package io.hacksy.aoc.v2018.day09;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Circle {
    Map<Integer, Item> items;
    Integer pointer;

    Circle() {
        items = new HashMap<>();
        items.put(0, new Item(0, 0, 0));
        pointer = 0;
    }

    void advanceClockwise(int steps) {
        for (int i = 0; i < steps; i ++) {
            pointer = items.get(pointer).getNext();
        }
    }

    void advanceCounterClockwise(int steps) {
        for (int i = 0; i < steps; i ++) {
            pointer = items.get(pointer).getPrevious();
        }
    }

    void addItem(int id) {
        try {
            Item currentItem = items.get(pointer);
            Item previousItem = items.get(currentItem.getPrevious());

            Item newItem = new Item(id, currentItem.getPrevious(), currentItem.getId());
            items.put(id, newItem);

            if (currentItem.getPrevious().equals(currentItem.getId())) {
                Item newPreviousAndNextItem = new Item(previousItem.getId(), id, id);
                items.put(newPreviousAndNextItem.getId(), newPreviousAndNextItem);
            } else {
                Item newPreviousItem = new Item(previousItem.getId(), previousItem.getPrevious(), id);
                Item newNextItem = new Item(currentItem.getId(), id, currentItem.getNext());
                items.put(newNextItem.getId(), newNextItem);
                items.put(newPreviousItem.getId(), newPreviousItem);
            }
        } catch (OutOfMemoryError e) {
            System.out.println(e);
        }

        pointer = id;
    }

    Item removeItem() {
        Item currentItem = items.remove(pointer);
        Item previousItem = items.get(currentItem.getPrevious());
        Item nextItem = items.get(currentItem.getNext());

        Item newPreviousItem = new Item(previousItem.getId(), previousItem.getPrevious(), currentItem.getNext());
        Item newNextItem = new Item(nextItem.getId(), currentItem.getPrevious(), nextItem.getNext());

        items.put(newPreviousItem.getId(), newPreviousItem);
        items.put(newNextItem.getId(), newNextItem);

        pointer = currentItem.next;
        return currentItem;
    }

    Item currentItem() {
        return items.get(pointer);
    }

    public String toString() {
        List<Integer> print = new ArrayList<>();
        Item currentItem = items.get(pointer);
        while (!print.contains(currentItem.getId())) {
            print.add(currentItem.getId());
            currentItem = items.get(currentItem.getNext());
        }
        return print.stream().map(i -> i.toString()).collect(Collectors.toList()).stream().collect(Collectors.joining(" "));
    }

    @Data
    class Item {
        private final Integer id;
        private final Integer previous;
        private final Integer next;
    }
}


