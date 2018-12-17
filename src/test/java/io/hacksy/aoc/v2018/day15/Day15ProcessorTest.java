package io.hacksy.aoc.v2018.day15;

import io.hacksy.aoc.v2018.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day15ProcessorTest {
    private Day15Processor processor = new Day15Processor();

    @Test
    public void partOne01() {
        File file = FileUtil.getResourceFile("input/day15/input3.txt");
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("27730"));
    }

    @Test
    public void partOne02() {
        File file = FileUtil.getResourceFile("input/day15/inputA.txt");
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("36334"));
    }

    @Test
    public void partOne03() {
        File file = FileUtil.getResourceFile("input/day15/inputB.txt");
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("39514"));
    }

    @Test
    public void partOne04() {
        File file = FileUtil.getResourceFile("input/day15/inputC.txt");
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("27755"));
    }

    @Test
    public void partOne05() {
        File file = FileUtil.getResourceFile("input/day15/inputD.txt");
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("28944"));
    }

    @Test
    public void partOne06() {
        File file = FileUtil.getResourceFile("input/day15/inputE.txt");
        assertThat(processor.partOne(FileUtil.fileToStringList(file)), is("18740"));
    }

    @Test
    public void queueTest() {
        Queue<String> q = new ArrayDeque<>();
        q.add("A");
        q.add("B");
        assertThat(q.remove(), is("A"));
    }

    @Test
    public void partTwo01() {
        File file = FileUtil.getResourceFile("input/day15/input2-1.txt");
        assertThat(processor.partTwo(FileUtil.fileToStringList(file)), is("4988"));
    }

    @Test
    public void partTwo02() {
        List<String> input = new ArrayList<>();
        input.add("#######");
        input.add("#E..EG#");
        input.add("#.#G.E#");
        input.add("#E.##E#");
        input.add("#G..#.#");
        input.add("#..E#.#");
        input.add("#######");

        assertThat(processor.partTwo(input), is("31284"));
    }

    @Test
    public void partTwo03() {
        List<String> input = new ArrayList<>();
        input.add("#######");
        input.add("#E.G#.#");
        input.add("#.#G..#");
        input.add("#G.#.G#");
        input.add("#G..#.#");
        input.add("#...E.#");
        input.add("#######");

        assertThat(processor.partTwo(input), is("3478"));
    }

    @Test
    public void partTwo04() {
        List<String> input = new ArrayList<>();
        input.add("#######");
        input.add("#.E...#");
        input.add("#.#..G#");
        input.add("#.###.#");
        input.add("#E#G#G#");
        input.add("#...#G#");
        input.add("#######");

        assertThat(processor.partTwo(input), is("6474"));
    }

    @Test
    public void partTwo05() {
        List<String> input = new ArrayList<>();
        input.add("#########");
        input.add("#G......#");
        input.add("#.E.#...#");
        input.add("#..##..G#");
        input.add("#...##..#");
        input.add("#...#...#");
        input.add("#.G...G.#");
        input.add("#.....G.#");
        input.add("#########");

        assertThat(processor.partTwo(input), is("1140"));
    }
}