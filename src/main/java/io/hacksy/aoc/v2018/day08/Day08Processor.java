package io.hacksy.aoc.v2018.day08;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Iterables.getLast;

public class Day08Processor {
    String partOne(List<String> input) {
        List<Node> resolvedNodes = getNodeTree(input.get(0));

        return Integer.toString(resolvedNodes.stream().mapToInt(n -> n.getMetadataEntries().stream().mapToInt(i -> i).sum()).sum());
    }

    String partTwo(List<String> input) {
        List<Node> resolvedNodes = getNodeTree(input.get(0));

        return Integer.toString(getLast(resolvedNodes).getValue());
    }

    List<Node> getNodeTree(String input) {
        List<Integer> entries = Arrays.asList(input.split(" ")).stream()
                .map(Integer::valueOf).collect(Collectors.toList());
        List<Node> unresolvedNodes = new ArrayList<>();
        List<Node> resolvedNodes = new ArrayList<>();

        for (int i = 0; i < entries.size() - 2; i++) {
            if (unresolvedNodes.size() == 0 || getLast(unresolvedNodes).getChildren() > 0) {
                unresolvedNodes.add(new Node(getNextName(), entries.get(i), entries.get(i + 1)));
                i++;
            } else {
                Node nodeToResolve = getLast(unresolvedNodes);
                nodeToResolve.getMetadataEntries().addAll(
                        entries.subList(i, i + nodeToResolve.getMetadata())
                );
                unresolvedNodes.remove(unresolvedNodes.size() - 1);
                resolvedNodes.add(nodeToResolve);
                if (unresolvedNodes.size() > 0) {
                    Node originalNode = getLast(unresolvedNodes);
                    Node decrementedNode = new Node(
                            originalNode.getName(),
                            originalNode.getChildren() - 1,
                            originalNode.metadata
                    );
                    decrementedNode.getChildNodes().addAll(originalNode.getChildNodes());
                    decrementedNode.getChildNodes().add(nodeToResolve);
                    unresolvedNodes.set(unresolvedNodes.size() - 1, decrementedNode);
                }
                i += nodeToResolve.metadata - 1;
            }
        }
        return resolvedNodes;
    }

    String getNextName() {
        return UUID.randomUUID().toString();
    }

    @Data
    class Node {
        final String name;
        final int children;
        final int metadata;
        final Collection<Integer> metadataEntries = new ArrayList<>();
        final List<Node> childNodes = new ArrayList<>();

        Node(String name, int children, int metadata) {
            this.name = name;
            this.children = children;
            this.metadata = metadata;
        }

        int getValue() {
            if (childNodes.size() == 0) {
                return metadataEntries.stream()
                        .mapToInt(i -> i)
                        .sum();
            } else {
                int result = metadataEntries.stream()
                        .mapToInt(i -> {
                            if (i < 1 || i > childNodes.size()) {
                                return 0;
                            }
                            return childNodes.get(i - 1).getValue();
                        }).sum();
                return result;
            }
        }
    }

    void p(String m) {
        System.out.println(m);
    }
}
