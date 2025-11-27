package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {


  @Test
  public void test_p1_ex1() {
    MarbleGame game = new MarbleGame(9, 25);
    assertEquals( 32, game.playGame() );
  }

  @Test
  public void test_p1_ex2() {
    MarbleGame game = new MarbleGame(10, 1618);
    assertEquals( 8317, game.playGame() );
  }

  @Test
  public void test_p1_ex3() {
    MarbleGame game = new MarbleGame(13, 7999);
    assertEquals( 146373, game.playGame() );
  }

  @Test
  public void test_p1_ex4() {
    MarbleGame game = new MarbleGame(17, 1104);
    assertEquals( 2764, game.playGame() );
  }

  @Test
  public void test_p1_ex5() {
    MarbleGame game = new MarbleGame(21, 6111);
    assertEquals( 54718, game.playGame() );
  }

  @Test
  public void test_p1_ex6() {
    MarbleGame game = new MarbleGame(30, 5807);
    assertEquals( 37305, game.playGame() );
  }

}
