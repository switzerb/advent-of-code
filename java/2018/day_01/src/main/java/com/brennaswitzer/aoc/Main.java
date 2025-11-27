package com.brennaswitzer.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> lines = Loader.getInput("input.txt");
        List<Integer> frequencies = new ArrayList<>();

        for (String line : lines) {
            frequencies.add(Integer.parseInt(line.trim()));
        }

        System.out.println("Solution Part One: " + Main.partOneSolver(frequencies));
        System.out.println("Solution Part Two: " + Main.partTwoSolver(frequencies));
    }

    public static int partOneSolver(List<Integer> frequencies) {
        int sum = 0;
        for (Integer i : frequencies) {
            sum += i;
        }
        return sum;
    }

    public static int partTwoSolver(List<Integer> frequencies) {
        int sum = 0;
        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        while (true) {
            for (Integer frequency : frequencies) {
                sum += frequency;
                if (sums.contains(sum)) {
                    return sum;
                }
                sums.add(sum);
            }
        }
    }

}
