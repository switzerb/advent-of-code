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

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            input.add(line);
        }

    }
}
