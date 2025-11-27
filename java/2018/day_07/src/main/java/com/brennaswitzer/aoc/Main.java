package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ClassLoader cl = Main.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("input.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(in));

        List<String> input = new ArrayList<>();
        Graph instructions = new Graph();

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            input.add(line);
        }

        for(String line : input) {
            String[] split = line.split(" ");
            String key = split[1];
            String value = split[7];
            if(instructions.containsKey(key)){
                instructions.get(key).add(value);
            } else {
                List<String> a = new ArrayList<>();
                a.add(value);
                instructions.put(key, a);
            }
        }

        Instructions i = new Instructions(instructions, 5);
        System.out.println("Part One Answer: " + i.printSteps());
    }

}
