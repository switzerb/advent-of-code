package com.brennaswitzer.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

  private final static int  MAX_PRESENTS = 36000000;

  @Test
  public void testPartOne() {
    assertEquals(831600, Main.partOneSolver(MAX_PRESENTS));
  }

  @Test
  public void testPartTwo() {
    assertEquals(884520, Main.partTwoSolver(MAX_PRESENTS));
  }

  @Test
  public void testExample() {
    assertEquals(8, Main.partOneSolver(150));
  }

  @Test
  public void  test_p1_ex1() {
    assertEquals(10, Main.getTotalPresents(1));
  }

  @Test
  public void  test_p1_ex2() {
    assertEquals(30, Main.getTotalPresents(2));
  }

  @Test
  public void  test_p1_ex3() {
    assertEquals(40, Main.getTotalPresents(3));
  }

  @Test
  public void  test_p1_ex4() {
    assertEquals(70, Main.getTotalPresents(4));
  }

  @Test
  public void  test_p1_ex5() {
    assertEquals(60, Main.getTotalPresents(5));
  }

  @Test
  public void  test_p1_ex6() {
    assertEquals(120, Main.getTotalPresents(6));
  }

  @Test
  public void  test_p1_ex7() {
    assertEquals(80, Main.getTotalPresents(7));
  }

  @Test
  public void  test_p1_ex8() {
    assertEquals(150, Main.getTotalPresents(8));
  }

  @Test
  public void  test_p1_ex9() {
    assertEquals(130, Main.getTotalPresents(9));
  }

  @Test
  public void  test_p1_ex10() {
    assertEquals(180, Main.getTotalPresents(10));
  }

  @Test
  public void  test_p1_ex11() {
    assertEquals(36902400, Main.getTotalPresents(831600));
  }

  @Test
  public void test_p2_ex1() {
    assertEquals(11, Main.getLazyPresents(1));
  }

  @Test
  public void test_p2_ex2() {
    assertEquals(33, Main.getLazyPresents(2));
  }

}
