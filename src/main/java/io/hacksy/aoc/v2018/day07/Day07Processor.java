package io.hacksy.aoc.v2018.day07;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day07Processor {
    public String partOne(List<String> input) {
        Collection<Instruction> instructions = parseInstructions(input, 0);
        Collection<Instruction> completedInstruction = new ArrayList<>();

        while (instructions.size() > 0) {
            completedInstruction = doNext(instructions, completedInstruction);
            instructions.removeAll(completedInstruction);
        }

        return completedInstruction.stream().map(Instruction::getName).collect(Collectors.joining());
    }
    public String partTwo(List<String> input, int workerCount, int countDownModifier) {
        List<Worker> workerPool = IntStream.range(0, workerCount).mapToObj(i -> new Worker()).collect(Collectors.toList());
        Collection<Instruction> instructions = parseInstructions(input, countDownModifier);
        Collection<Instruction> completedInstruction = new ArrayList<>();

        int seconds = 0;
        while (instructions.size() > 0 || workerPool.stream().anyMatch(w -> w.getAssignedInstruction() != null)) {
            List<Worker> availableWorkers = workerPool.stream()
                    .filter(w -> w.assignedInstruction == null)
                    .collect(Collectors.toList());

            List<Instruction> availableInstuctions = instructions.stream()
                    .filter(i -> i.isReady(completedInstruction))
                    .sorted(Comparator.comparing(i -> i.name))
                    .collect(Collectors.toList());

            availableWorkers.forEach(w -> {
                if (availableInstuctions.size() > 0) {
                    w.setAssignedInstruction(availableInstuctions.get(0));
                    instructions.remove(w.getAssignedInstruction());
                    availableInstuctions.remove(w.getAssignedInstruction());
                }
            });

            workerPool.forEach(w -> {
                if (w.getAssignedInstruction() != null) {
                    w.getAssignedInstruction().doWork();
                    if (w.getAssignedInstruction().getCountDownLatch().getCount() == 0) {
                        completedInstruction.add(w.getAssignedInstruction());
                        w.setAssignedInstruction(null);
                    }
                }
            });

            seconds++;
        }

        return Integer.toString(seconds);
    }

    Collection<Instruction> doNext(Collection<Instruction> instructions, Collection<Instruction> completedInstructions) {
        completedInstructions.add(instructions.stream()
                .filter(i -> i.isReady(completedInstructions))
                .sorted(Comparator.comparing(Instruction::getName))
                .findFirst().orElse(null));
        return completedInstructions;
    }

    Collection<Instruction> parseInstructions(List<String> input, int countDownModifier) {
        Pattern pattern = Pattern.compile("Step ([A-Z]+) must be finished before step ([A-Z]+) can begin.");
        Map<String, Instruction> instructions = new HashMap<>();
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                if (instructions.containsKey(matcher.group(2))) {
                    instructions.get(matcher.group(2)).addRequirement(matcher.group(1));
                } else {
                    Instruction instruction = new Instruction(buildLatch(matcher.group(2), countDownModifier), matcher.group(2));
                    instruction.addRequirement(matcher.group(1));
                    instructions.put(matcher.group(2), instruction);
                }
                if (!instructions.containsKey(matcher.group(1))) {
                    Instruction instruction = new Instruction(buildLatch(matcher.group(1), countDownModifier), matcher.group(1));
                    instructions.put(matcher.group(1), instruction);
                }
            }
        }
        return instructions.values();
    }

    CountDownLatch buildLatch(String name, int modifier) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return new CountDownLatch(alphabet.indexOf(name) + 1 + modifier);
    }

    @Data
    class Instruction {
        final CountDownLatch countDownLatch;
        final String name;
        Set<Function<Collection<Instruction>, Boolean>> preconditions = Sets.newHashSet(i -> true);

        Boolean isReady(Collection<Instruction> competedInstructions) {
             return preconditions.stream()
                     .map(f -> f.apply(competedInstructions))
                     .allMatch(b -> b.equals(true));
        }

        void addRequirement(String instructionName) {
            Function<Collection<Instruction>, Boolean> precondition = (completedInstructions) ->
                    completedInstructions.stream()
                            .map(Instruction::getName)
                            .anyMatch(s -> s.equals(instructionName));
            preconditions.add(precondition);
        }

        int doWork() {
            countDownLatch.countDown();
            return Math.toIntExact(countDownLatch.getCount());
        }
    }

    @Data
    class Worker {
        Instruction assignedInstruction;
    }
}
