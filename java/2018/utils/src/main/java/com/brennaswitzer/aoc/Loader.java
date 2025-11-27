package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static List<String> getInput(String path) throws IOException {
        ClassLoader cl = Loader.class.getClassLoader();
        InputStream in = cl.getResourceAsStream(path);
        BufferedReader r = new BufferedReader(new InputStreamReader(in));

        List<String> result = new ArrayList<>();

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            result.add(line);
        }
        return result;
    }
}
