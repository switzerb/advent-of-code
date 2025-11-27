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
        String line = r.readLine();

        Reactor re = new Reactor(line.trim());
        String complete = re.reactor(line.trim());

        System.out.println("Part One Solution: " + re.countUnits(complete));
        System.out.println("Part Two Solution: " + re.getOptimalHack());

    }
}
