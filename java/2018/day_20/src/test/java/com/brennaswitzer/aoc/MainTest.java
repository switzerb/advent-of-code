package com.brennaswitzer.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    
    NorthPole pole = new NorthPole();
    
    @Test
    public void test_p1_ex1() {
        String test = "#####\n" +
                              "#.|.#\n" +
                              "#|###\n" +
                              "#.|X#\n" +
                              "#####";
        String input = "WNE";
        pole.traverseMap(input.toCharArray(), new Point(0, 0));
        assertEquals(test, pole.toString());
    }
    
    @Test
    public void test_p1_ex2() {
        String example = "#########\n" +
                                 "#.|.|.|.#\n" +
                                 "#|#######\n" +
                                 "#.|.|.|.#\n" +
                                 "#|#####|#\n" +
                                 "#.#.#X|.#\n" +
                                 "#|#|#####\n" +
                                 "#.|.|.|.#\n" +
                                 "#########";
        
        String directions = "ENWWW(NEEE|SSE(EE|N))";
        pole.traverseMap(directions.toCharArray(), new Point(0, 0));
        System.out.println(pole);
        assertEquals(example, pole.toString());
    }
    
    @Test
    public void test_p1_ex3() {
        String example = "###########\n" +
                                 "#.|.#.|.#.#\n" +
                                 "#|###|#|#|#\n" +
                                 "#.|.|.#.#.#\n" +
                                 "#|#####|#|#\n" +
                                 "#.#.#X|.#.#\n" +
                                 "#|#|#####|#\n" +
                                 "#.#.|.|.|.#\n" +
                                 "#|###|###|#\n" +
                                 "#.|.|.#.|.#\n" +
                                 "###########";
        String directions = "ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN";
        pole.traverseMap(directions.toCharArray(), new Point(0, 0));
        assertEquals(example, pole.toString());
    }
    
    @Test
    public void test_p1_ex4() {
        String directions = "WNE";
        pole.traverseMap(directions.toCharArray(), new Point(0,0));
        assertEquals(3, pole.getFurthestRoom());
    }
    
    @Test
    public void test_p1_ex5() {
        String directions = "ENWWW(NEEE|SSE(EE|N))";
        pole.traverseMap(directions.toCharArray(), new Point(0,0));
        assertEquals(10, pole.getFurthestRoom());
    }
    
    @Test
    public void test_p1_ex7() {
        String directions = "ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN";
        pole.traverseMap(directions.toCharArray(), new Point(0,0));
        assertEquals(18, pole.getFurthestRoom());
    }
    
    @Test
    public void test_p1_ex8() {
        String example = "#############\n" +
                                 "#.|.|.|.|.|.#\n" +
                                 "#|#####|###|#\n" +
                                 "#.#.|.#.#.#.#\n" +
                                 "#|#|###|#|#|#\n" +
                                 "#.#.#.|.#.|.#\n" +
                                 "#|#|#|#####|#\n" +
                                 "#.#.#.#X|.#.#\n" +
                                 "#|#|#|###|#|#\n" +
                                 "#.|.#.|.#.#.#\n" +
                                 "###|#|###|#|#\n" +
                                 "#.|.#.|.|.#.#\n" +
                                 "#############";
        String directions = "ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))";
        pole.traverseMap(directions.toCharArray(), new Point(0,0));
        assertEquals(example, pole.toString());
    }
    
    @Test
    public void test_p1_ex9() {
        String directions = "ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))";
        pole.traverseMap(directions.toCharArray(), new Point(0,0));
        assertEquals(23, pole.getFurthestRoom());
    }
    
    @Test
    public void test_p1_ex10() {
        String test = "#######\n" +
                              "#.|.###\n" +
                              "###|###\n" +
                              "#.|.|.#\n" +
                              "#|#####\n" +
                              "#.|X###\n" +
                              "#######";
        String input = "WNE(E|NW)";
        pole.traverseMap(input.toCharArray(), new Point(0, 0));
        System.out.println(pole);
        assertEquals(test, pole.toString());
    }
    
    @Test
    public void test_p1_ex11() {
        String directions = "WNE(E|NW)";
        pole.traverseMap(directions.toCharArray(), new Point(0, 0));
        assertEquals(5, pole.getFurthestRoom());
    }
    
    @Test
    public void test_p1_ex12() {
        String directions = "WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))";
        pole.traverseMap(directions.toCharArray(), new Point(0, 0));
        assertEquals(31, pole.getFurthestRoom());
    }
}
