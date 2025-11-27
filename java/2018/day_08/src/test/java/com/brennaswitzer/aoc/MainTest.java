package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class MainTest {

  int[] numbers;

  @Before
  public void setup() throws IOException {
    String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
    String[] split = input.split(" ");
    numbers = new int[split.length];
    for(int i = 0; i < split.length; i++){
      numbers[i] = Integer.parseInt(split[i]);
    }
  }

  @Test
  public void testBuildNode() {
    Node root = new Node();
    root = root.buildNode(numbers);
    assertEquals(138, root.sumMeta());
  }

  @Test
  public void testGetValue() {
    Node root = new Node();
    root = root.buildNode(numbers);
    assertEquals(66, root.getValue());
  }

}
