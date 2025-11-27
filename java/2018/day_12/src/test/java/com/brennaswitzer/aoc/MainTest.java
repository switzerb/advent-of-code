package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MainTest {
    
    Cavern cave;
    
    @Before
    public void setup() throws IOException {
        ClassLoader cl = Main.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("test_input.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        
        List<String> input = new ArrayList<>();
        
        String initial = r.readLine().substring(15);
        cave = new Cavern(initial);
        
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            input.add(line);
        }
        
        for (String l : input) {
            if (l.length() > 0) {
                cave.addRule(l);
            }
        }
        
    }

    @Test
    public void testSumOfPots() {
        assertEquals(325, cave.getSumOfPots(20));
    }
    
}
