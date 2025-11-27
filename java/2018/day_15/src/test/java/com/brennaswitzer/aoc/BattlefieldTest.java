package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BattlefieldTest {

    @Before
    public void setup() throws IOException {
    }

    @Test
    public void test_width() {
        String s = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        assertEquals(7, battle.getWidth());
    }

    @Test
    public void test_height() {
        String s = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        assertEquals(5, battle.getHeight());
    }

    @Test
    public void test_readField() {
        String init = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(init);
        assertEquals(init, battle.toString());
    }

    @Test
    public void test_runRound_step1() {
        String start = "#########\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..E..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#########";

        String finish = "#########\n" +
                "#.G...G.#\n" +
                "#...G...#\n" +
                "#...E..G#\n" +
                "#.G.....#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#########";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 1; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_runRound_step2() {
        String n = "#########\n" +
                "#..G.G..#\n" +
                "#...G...#\n" +
                "#.G.E.G.#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########";

        String s = "#########\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..E..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#########";
        Battlefield battle = new Battlefield(s);
        for (int i = 0; i < 2; i++) {
            battle.runRound();
        }
        assertEquals(n, battle.toString());
    }

    @Test
    public void test_runRound_step3() {
        String n = "#########\n" +
                "#.......#\n" +
                "#..GGG..#\n" +
                "#..GEG..#\n" +
                "#G..G...#\n" +
                "#......G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########";

        String s = "#########\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..E..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#########";
        Battlefield battle = new Battlefield(s);
        for (int i = 0; i < 3; i++) {
            battle.runRound();
        }
        assertEquals(n, battle.toString());
    }

    @Test
    public void test_ex1_r1() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "" +
                "#######\n" +
                "#..G..#\n" +
                "#...EG#\n" +
                "#.#G#G#\n" +
                "#...#E#\n" +
                "#.....#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 1; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r2() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "" +
                "#######\n" +
                "#...G.#\n" +
                "#..GEG#\n" +
                "#.#.#G#\n" +
                "#...#E#\n" +
                "#.....#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 2; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r23() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#...G.#\n" +
                "#..G.G#\n" +
                "#.#.#G#\n" +
                "#...#E#\n" +
                "#.....#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 23; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r24() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#..G..#\n" +
                "#...G.#\n" +
                "#.#G#G#\n" +
                "#...#E#\n" +
                "#.....#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 24; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r25() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#.G...#\n" +
                "#..G..#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 25; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r26() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#G....#\n" +
                "#.G...#\n" +
                "#.#.#G#\n" +
                "#...#E#\n" +
                "#..G..#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 26; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r27() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#G....#\n" +
                "#.G...#\n" +
                "#.#.#G#\n" +
                "#...#E#\n" +
                "#...G.#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 27; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r28() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#G....#\n" +
                "#.G...#\n" +
                "#.#.#G#\n" +
                "#...#E#\n" +
                "#....G#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 28; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_ex1_r47() {
        String start = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String finish = "#######\n" +
                "#G....#\n" +
                "#.G...#\n" +
                "#.#.#G#\n" +
                "#...#.#\n" +
                "#....G#\n" +
                "#######";

        Battlefield battle = new Battlefield(start);
        for (int i = 0; i < 47; i++) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_outcome_ex1() {
        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(27730, battle.outcome());
    }

    @Test
    public void test_outcome_ex2_p1() {
        String start = "#######\n" +
                "#G..#E#\n" +
                "#E#E.E#\n" +
                "#G.##.#\n" +
                "#...#E#\n" +
                "#...E.#\n" +
                "#######";

        String finish = "#######\n" +
                "#...#E#\n" +
                "#E#...#\n" +
                "#.E##.#\n" +
                "#E..#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_outcome_ex2_p2() {
        String start = "#######\n" +
                "#G..#E#\n" +
                "#E#E.E#\n" +
                "#G.##.#\n" +
                "#...#E#\n" +
                "#...E.#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(36334, battle.outcome());
    }

    @Test
    public void test_outcome_ex3_p1() {
        String start = "#######\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######";

        String finish = "#######\n" +
                "#.E.E.#\n" +
                "#.#E..#\n" +
                "#E.##.#\n" +
                "#.E.#.#\n" +
                "#...#.#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_outcome_ex3_p2() {
        String start = "#######\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(39514, battle.outcome());
    }

    @Test
    public void test_outcome_ex4_p1() {
        String start = "#######\n" +
                "#E.G#.#\n" +
                "#.#G..#\n" +
                "#G.#.G#\n" +
                "#G..#.#\n" +
                "#...E.#\n" +
                "#######";

        String finish = "#######\n" +
                "#G.G#.#\n" +
                "#.#G..#\n" +
                "#..#..#\n" +
                "#...#G#\n" +
                "#...G.#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_outcome_ex4_p2() {
        String start = "#######\n" +
                "#E.G#.#\n" +
                "#.#G..#\n" +
                "#G.#.G#\n" +
                "#G..#.#\n" +
                "#...E.#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(27755, battle.outcome());
    }

    @Test
    public void test_outcome_ex5_p1() {
        String start = "#######\n" +
                "#.E...#\n" +
                "#.#..G#\n" +
                "#.###.#\n" +
                "#E#G#G#\n" +
                "#...#G#\n" +
                "#######";

        String finish = "#######\n" +
                "#.....#\n" +
                "#.#G..#\n" +
                "#.###.#\n" +
                "#.#.#.#\n" +
                "#G.G#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_outcome_ex5_p2() {
        String start = "#######\n" +
                "#.E...#\n" +
                "#.#..G#\n" +
                "#.###.#\n" +
                "#E#G#G#\n" +
                "#...#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(28944, battle.outcome());
    }

    @Test
    public void test_outcome_ex6_p1() {
        String start = "#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########";

        String finish = "#########\n" +
                "#.G.....#\n" +
                "#G.G#...#\n" +
                "#.G##...#\n" +
                "#...##..#\n" +
                "#.G.#...#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(finish, battle.toString());
    }

    @Test
    public void test_outcome_ex6_p2() {
        String start = "#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########";
        Battlefield battle = new Battlefield(start);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(18740, battle.outcome());
    }

    @Test
    public void test_p2_ex1_1() {
        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Runner simulator = new Runner();
        simulator.run(s);
        assertEquals(15, simulator.getAttackUp());
    }

    @Test
    public void test_p2_ex1_2() {
        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        Runner simulator = new Runner();
        int outcome = simulator.run(s);
        assertEquals(4988, outcome);
    }

    @Test
    public void test_p2_ex2_1() {
        String start = "########\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######";
        Runner simulator = new Runner();
        simulator.run(start);
        assertEquals(4, simulator.getAttackUp());
    }

    @Test
    public void test_p2_ex2_2() {
        String start = "#######\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######";
        Runner simulator = new Runner();
        int outcome = simulator.run(start);
        assertEquals(31284, outcome);
    }

}
