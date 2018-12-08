package io.hacksy.aoc.v2018.day08;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Iterables.getLast;

public class Day08Processor {
    String partOne(List<String> input) {
        List<Integer> entries = Arrays.asList(input.get(0).split(" ")).stream()
                .map(Integer::valueOf).collect(Collectors.toList());
        List<Node> unresolvedNodes = new ArrayList<>();
        List<Node> resolvedNodes = new ArrayList<>();

        for (int i = 0; i < entries.size() - 2; i++) {
            if (unresolvedNodes.size() == 0 || getLast(unresolvedNodes).getChildren() > 0) {
                unresolvedNodes.add(new Node(entries.get(i), entries.get(i + 1)));
                i++;
            } else {
                Node nodeToResolve = getLast(unresolvedNodes);
                nodeToResolve.getMetadataEntries().addAll(
                        entries.subList(i, i + nodeToResolve.getMetadata())
                );
                unresolvedNodes.remove(unresolvedNodes.size() - 1);
                resolvedNodes.add(nodeToResolve);
                if (unresolvedNodes.size() > 0) {
                    unresolvedNodes.set(
                            unresolvedNodes.size() - 1,
                            new Node(
                                    getLast(unresolvedNodes).getChildren() - 1,
                                    getLast(unresolvedNodes).getMetadata()
                            )
                    );
                }
                i += nodeToResolve.metadata - 1;
            }
        }

        return Integer.toString(resolvedNodes.stream().mapToInt(n -> n.getMetadataEntries().stream().mapToInt(i -> i).sum()).sum());
    }

    String partTwo(List<String> input) {
        return null;
    }

    @Data
    class Node {
        final int children;
        final int metadata;
        Collection<Integer> metadataEntries = new ArrayList<>();
    }
}
