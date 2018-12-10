package io.hacksy.aoc.v2018.day07;

import lombok.Data;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
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

        // while there are still instructions in progress or not started...
        while (instructions.size() > 0 || workerPool.stream().anyMatch(w -> w.getAssignedInstruction() != null)) {

            // get all workers not doing anything
            List<Worker> availableWorkers = workerPool.stream()
                    .filter(w -> w.assignedInstruction == null)
                    .collect(Collectors.toList());

            // get all instructions that are currently ready to be worked on
            List<Instruction> availableInstuctions = instructions.stream()
                    .filter(i -> i.isReady(completedInstruction))
                    .sorted(Comparator.comparing(i -> i.name))
                    .collect(Collectors.toList());

            // assign available work to available workers until we run out of on or the the other
            availableWorkers.forEach(w -> {
                if (availableInstuctions.size() > 0) {
                    w.setAssignedInstruction(availableInstuctions.get(0));
                    instructions.remove(w.getAssignedInstruction());
                    availableInstuctions.remove(w.getAssignedInstruction());
                }
            });

            // for each worker that has work, remove one unit of work from the instruction. If the work is complete,
            // move the task to completed and free up the worker.
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

    // parse each line of raw input into an instruction
    Collection<Instruction> parseInstructions(List<String> input, int countDownModifier) {
        Pattern pattern = Pattern.compile("Step ([A-Z]+) must be finished before step ([A-Z]+) can begin.");
        Map<String, Instruction> instructions = new HashMap<>();
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            // match group 1 is the instruction that must be finished before match group 2
            if (matcher.find()) {
                // if the instruction already exists, add an additional prerequisite
                if (instructions.containsKey(matcher.group(2))) {
                    instructions.get(matcher.group(2)).addRequirement(matcher.group(1));
                // if the instruction does not already exist, create it
                } else {
                    Instruction instruction = new Instruction(buildLatch(matcher.group(2), countDownModifier), matcher.group(2));
                    instruction.addRequirement(matcher.group(1));
                    instructions.put(matcher.group(2), instruction);
                }
                // if the prerequisite instruction does not already exist, create that, too, with NO prerequisite.
                if (!instructions.containsKey(matcher.group(1))) {
                    Instruction instruction = new Instruction(buildLatch(matcher.group(1), countDownModifier), matcher.group(1));
                    instructions.put(matcher.group(1), instruction);
                }
            }
        }
        return instructions.values();
    }

    // determine the amount of work to be done (seconds to complete) based on letter of alphabet and provided modifier.
    CountDownLatch buildLatch(String name, int modifier) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return new CountDownLatch(alphabet.indexOf(name) + 1 + modifier);
    }

    @Data
    class Instruction {
        // amount of work remaining to be done (seconds)
        final CountDownLatch countDownLatch;
        final String name;
        // a collection of predicates (functions) each with take a collection of (completed) instructions for their
        // argument. these are the "rules" that must all return true before this instruction can be started.
        Collection<Predicate<Collection<Instruction>>> preconditions = new ArrayList<>();

        // give a list of already completed instructions, execute each predicate. If all are true, we're ready!
        Boolean isReady(Collection<Instruction> competedInstructions) {
             return preconditions.stream()
                     .map(f -> f.test(competedInstructions))
                     .noneMatch(b -> b.equals(false));
        }

        // give a named an instruction that must be completed before this instruction starts, build a function that
        // takes in a list of completed instructions, and returns true if the provided instruction is complete.
        void addRequirement(String instructionName) {
            Predicate<Collection<Instruction>> precondition = (completedInstructions) ->
                    completedInstructions.stream()
                            .map(Instruction::getName)
                            .anyMatch(s -> s.equals(instructionName));
            preconditions.add(precondition);
        }

        // decrement the amount of work remaining to be done.
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
