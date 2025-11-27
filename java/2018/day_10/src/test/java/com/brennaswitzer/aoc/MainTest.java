package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

  Sky one = new Sky();
  Sky two = new Sky();
  Sky example = new Sky();

  @Before
  public void setup() throws IOException {
    one.setStar(3, 9, 1, -2);

    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("test_input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));

    List<String> input = new ArrayList<>();

    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      input.add(line);
    }

    for(String l : input) {
      String[] split = l.split("<|>|,");
      int x = Integer.parseInt(split[1].trim());
      int y = Integer.parseInt(split[2].trim());
      int vx = Integer.parseInt(split[4].trim());
      int vy = Integer.parseInt(split[5].trim());
      example.setStar(x,y,vx,vy);
    }

  }

  @Test
  public void testPointMovement() {
    Sky.Point test = new Sky.Point(3, 9, 1, -2);
    test.move();
    assertEquals(4, test.getX());
    assertEquals(7, test.getY());
  }

  @Test
  public void testSeeSky() {
      example.animate();
  }


}
