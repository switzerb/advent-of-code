package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {

    private Integer[] a = {1, -2, 3, 1};
    private Integer[] b = {1, 1, 1};
    private Integer[] c = {1, 1, -2};
    private Integer[] d = {-1, -2, -3};
    private Integer[] e = {+1, -1};
    private Integer[] f = {+3, +3, +4, -2, -4};
    private Integer[] g = {-6, +3, +8, +5, -6};
    private Integer[] h = {+7, +7, -2, -7, -4};

    private List<Integer> frequencies = new ArrayList<>();
    private List<Integer> example1 = Arrays.asList(a);
    private List<Integer> example2 = Arrays.asList(b);
    private List<Integer> example3 = Arrays.asList(c);
    private List<Integer> example4 = Arrays.asList(d);
    private List<Integer> example5 = Arrays.asList(e);
    private List<Integer> example6 = Arrays.asList(f);
    private List<Integer> example7 = Arrays.asList(g);
    private List<Integer> example8 = Arrays.asList(h);

    @Before
    public void testPartOne() throws IOException {
        List<String> lines = Loader.getInput("input.txt");
        for (String line : lines) {
            frequencies.add(Integer.parseInt(line.trim()));
        }
    }

    @Test
    public void test_partOne() {
        assertEquals(479, Main.partOneSolver(frequencies));
    }

    @Test
    public void test_partTwo() {
        assertEquals(66105, Main.partTwoSolver(frequencies));
    }

    @Test
    public void test_p1_ex1() {
        assertEquals(3, Main.partOneSolver(example1));
    }

    @Test
    public void test_p1_ex2() {
        assertEquals(3, Main.partOneSolver(example2));
    }

    @Test
    public void test_p1_ex3() {
        assertEquals(0, Main.partOneSolver(example3));
    }

    @Test
    public void test_p1_ex4() {
        assertEquals(-6, Main.partOneSolver(example4));
    }

    @Test
    public void test_p2_ex1() {
        assertEquals(2, Main.partTwoSolver(example1));
    }

    @Test
    public void test_p2_ex2() {
        assertEquals(0, Main.partTwoSolver(example5));
    }

    @Test
    public void test_p2_ex3() {
        assertEquals(10, Main.partTwoSolver(example6));
    }

    @Test
    public void test_p2_ex4() {
        assertEquals(5, Main.partTwoSolver(example7));
    }

    @Test
    public void test_p2_ex5() {
        assertEquals(14, Main.partTwoSolver(example8));
    }

}
