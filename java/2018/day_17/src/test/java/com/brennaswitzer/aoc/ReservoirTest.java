package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ReservoirTest {

    Reservoir reservoir;

    @Before
    public void setup() throws IOException {
        List<String> input = Loader.getInput("test_input.txt");
        System.out.println(input);
        reservoir = new Reservoir(20, 40);
    }

    @Test
    public void test_printGround() {
        System.out.println(reservoir);
    }

    @Test
    public void test_flow() {
        Point spring = new Point(0, 10);
        reservoir.flow(spring);
        System.out.println(reservoir);
    }


}
