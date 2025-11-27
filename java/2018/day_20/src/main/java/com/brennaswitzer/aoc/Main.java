package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader cl = Main.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("input.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        
        String input = r.readLine().trim();
        input = input.substring(1, input.length() - 1);
        
        NorthPole facility = new NorthPole();
        Point start = new Point(0, 0);
        facility.traverseMap(input.toCharArray(), start);

        System.out.println("Solution Part One: " + facility.getFurthestRoom());
        System.out.println("Solution Part Two: " + facility.countThreshold(1000));
    }
}
