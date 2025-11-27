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
import static junit.framework.TestCase.assertTrue;

public class InstructionTest {

    private Instructions i;
    private Instructions all;

    @Before
    public void setup() throws IOException {

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

        all = new Instructions(instructions, 5);

        Graph inst = new Graph();

        List<String> a = new ArrayList<>();
        a.add("A");
        a.add("F");

        List<String> b = new ArrayList<>();
        b.add("B");
        b.add("D");

        List<String> c = new ArrayList<>();
        c.add("E");

        inst.put("C", a);
        inst.put("A", b);
        inst.put("B", c);
        inst.put("D", c);
        inst.put("F", c);

        i = new Instructions(inst, 2);
    }
    
    @Test
    public void testDoIt(){
        assertEquals("CABDFE", i.printSteps());
    }

    @Test
    public void testGetNext(){
        assertEquals("B", i.getNext("A"));
    }

    @Test
    public void testKeyInDone() {
        assertTrue(i.keyInDone("C"));
    }

    @Test
    public void testGetLetterValue() {
        assertEquals(61, i.timeToFinish("A"));
        assertEquals(63, i.timeToFinish("C"));
        assertEquals(66, i.timeToFinish("F"));
    }

    @Test
    public void testGetMaxValue() {
        assertEquals(1828, i.getMaxTimeValue());
    }

//    @Test
//    public void testFindTime() {
//        assertEquals(0, i.timeToComplete());
//    }

    @Test
    public void testPartOne() {
        assertEquals("FMOXCDGJRAUIHKNYZTESWLPBQV", all.printSteps());
    }

}
