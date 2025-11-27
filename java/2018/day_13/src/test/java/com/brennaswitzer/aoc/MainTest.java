package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    Track track;
    Track loop;
    Track collision;
    Track track2;

    @Before
    public void setUp() throws IOException {
        List<String> lines = Loader.getInput("test_input.txt");
        track = new Track(lines);

        List<String> looper = Loader.getInput("test_loop.txt");
        loop = new Track(looper);

        List<String> coll = Loader.getInput("test_collision.txt");
        collision = new Track(coll);

        List<String> lines2 = Loader.getInput("test_input2.txt");
        track2 = new Track(lines2);

    }

    @Test
    public void test_p1_ex1() {
        String snapshot = "/->-\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | v  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/   ";
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex2() {
        String snapshot = "/-->\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\->--/\n" +
                "  \\------/   ";
        for (int i = 0; i < 1; i++) {
            track.step();
        }
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex3() {
        String snapshot = "/---v        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+>-/\n" +
                "  \\------/   ";
        for (int i = 0; i < 2; i++) {
            track.step();
        }
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex4() {
        String snapshot = "/---\\        \n" +
                "|   v  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+->/\n" +
                "  \\------/   ";
        for (int i = 0; i < 3; i++) {
            track.step();
        }
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex5() {
        String snapshot = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /->--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+--^\n" +
                "  \\------/   ";
        for (int i = 0; i < 4; i++) {
            track.step();
        }
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex6() {
        String snapshot = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--v-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  ^-+--/\n" +
                "  \\------/   ";
        for (int i = 0; i < 13; i++) {
            track.step();
        }
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex7() {
        String snapshot = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  X |  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/   ";
        for (int i = 0; i < 14; i++) {
            track.step();
        }
        assertEquals(snapshot, track.withCollisions());
    }

    @Test
    public void test_p1_ex8() {
        while (track.collisionCount() == 0) {
            track.step();
        }
        assertEquals("7,3", track.firstCollision());
    }


    @Test
    public void test_loop() {
        String snapshot = "/->-\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickOne() {
        String snapshot = "/-->\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        for (int i = 0; i < 1; i++) {
            loop.step();
        }
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickTwo() {
        String snapshot = "/---v\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        for (int i = 0; i < 2; i++) {
            loop.step();
        }
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickThree() {
        String snapshot = ">---\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        for (int i = 0; i < 14; i++) {
            loop.step();
        }
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickCollisionStart() {
        String snapshot = "/---\\\n" +
                "^   |\n" +
                "|   |\n" +
                "v   |\n" +
                "\\---/";
        assertEquals(snapshot, collision.toString());
    }

    @Test
    public void test_tickCollisionEnd() {
        String snapshot = "/---\\\n" +
                "|   |\n" +
                "|   X\n" +
                "|   |\n" +
                "\\---/";
        for (int i = 0; i < 7; i++) {
            collision.step();
        }
        assertEquals(snapshot, collision.withCollisions());
    }

    @Test
    public void test_getCollisionPosition() {
        while (collision.collisionCount() == 0) {
            collision.step();
        }
        assertEquals("4,2", collision.firstCollision());
    }

    @Test
    public void test_p2_ex1() {
        String snapshot = "/---\\  \n" +
                "|   |  \n" +
                "| v-+-\\\n" +
                "| | | |\n" +
                "\\-+-/ |\n" +
                "  |   |\n" +
                "  ^---^";
        for (int i = 0; i < 1; i++) {
            track2.step();
        }
        assertEquals(snapshot, track2.toString());
    }

    @Test
    public void test_p2_ex2() {
        String snapshot = "/---\\  \n" +
                "|   |  \n" +
                "| /-+-\\\n" +
                "| | | |\n" +
                "\\-+-/ ^\n" +
                "  |   |\n" +
                "  \\---/";
        for (int i = 0; i < 3; i++) {
            track2.step();
        }
        assertEquals(snapshot, track2.toString());
    }

    @Test
    public void test_lastCart() {
        while (track2.getCarts() > 1) {
            track2.step();
        }
        assertEquals("6,4", track2.getLastCart());
    }


}
