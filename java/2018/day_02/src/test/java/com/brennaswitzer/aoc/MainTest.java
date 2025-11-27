package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

  private List<String> t1_boxIds = new ArrayList<>();
  private List<String> t2_boxIds = new ArrayList<>();
  private List<String> boxIds = new ArrayList<>();
  private Warehouse t1_wh;
  private Warehouse t2_wh;
  private Warehouse wh;

  @Before
  public void setup() throws IOException {
    ClassLoader cl = MainTest.class.getClassLoader();
    InputStream in_1 = cl.getResourceAsStream("test_input_1.txt");
    InputStream in_2 = cl.getResourceAsStream("test_input_2.txt");
    BufferedReader r1 = new BufferedReader(new InputStreamReader(in_1));
    BufferedReader r2 = new BufferedReader(new InputStreamReader(in_2));

    while (true) {
      String line = r1.readLine();
      if (line == null) {
        break;
      }
      t1_boxIds.add(line.trim());
    }

    while (true) {
      String line = r2.readLine();
      if (line == null) {
        break;
      }
      t2_boxIds.add(line.trim());
    }

    ClassLoader loader = Main.class.getClassLoader();
    InputStream input = loader.getResourceAsStream("input.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

    while (true) {
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      boxIds.add(line.trim());
    }

    t1_wh = new Warehouse(t1_boxIds);
    t2_wh = new Warehouse(t2_boxIds);
    wh = new Warehouse(boxIds);
  }

  @Test
  public void testPartOne() {
    assertEquals(5478, wh.getChecksum() );
  }

  @Test
  public void testPartTwo() {
    assertEquals("qyzphxoiseldjrntfygvdmanu", wh.getCommonLetters() );
  }

  @Test
  public void testFileRead() {
    assertEquals(7, t1_boxIds.size());
  }

  @Test
  public void test_p1_ex1() {
    assertEquals(12, (t1_wh.getChecksum()));
  }

  @Test
  public void test_counts_ex1() {
    char[] test = {'b', 'a', 'b', 'a', 'b', 'c'};
    Histogram<Character> h = t1_wh.buildHistogram(test);
    int value = h.get('b');
    assertEquals(3, value);
  }

  @Test
  public void test_counts_ex2() {
    char[] test = {'b', 'a', 'b', 'a', 'b', 'c'};
    Histogram<Character> h = t1_wh.buildHistogram(test);
    int value = h.get('a');
    assertEquals(2, value);
  }

  @Test
  public void test_p2_ex1() {
    assertEquals("fgij", t2_wh.getCommonLetters());
  }

  @Test
  public void test_compareDiff1() {
    char[] first = {'a', 'b', 'c', 'd', 'e'};
    char[] second = {'a', 'x', 'c', 'y', 'e'};
    assertEquals(2, t2_wh.countDiffs(first, second));
  }

  @Test
  public void test_compareDiff2() {
    char[] first = {'f', 'g', 'h', 'i', 'j'};
    char[] second = {'f', 'g', 'u', 'i', 'j'};
    assertEquals(1, t2_wh.countDiffs(first, second));
  }
}
