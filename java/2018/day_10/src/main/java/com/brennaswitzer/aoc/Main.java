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
        Sky north = new Sky();

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            input.add(line);
        }

        for (String l : input) {
            String[] split = l.split("<|>|,");
            int x = Integer.parseInt(split[1].trim());
            int y = Integer.parseInt(split[2].trim());
            int vx = Integer.parseInt(split[4].trim());
            int vy = Integer.parseInt(split[5].trim());
            north.setStar(x, y, vx, vy);
        }

        north.animate();
        System.out.println("Part Two Answer: " + north.getSeconds());


    }

}
