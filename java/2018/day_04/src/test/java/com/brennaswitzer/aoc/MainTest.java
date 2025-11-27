package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MainTest {

  private List<Record> records = new ArrayList<>();
  private Record one = new Record(10, 11, 1);
  private Record two = new Record(99, 11, 2);
  private Record three = new Record(10, 11, 3);
  private Record four = new Record(99, 11, 4);
  private Record five = new Record(99, 11, 5);

  private Register register = new Register(records);

  @Before
  public void setup() throws IOException {
    records.add(one);
    records.add(two);
    records.add(three);
    records.add(four);
    records.add(five);
  }

  @Test
  public void testParse() {
  }

  @Test
  public void testsetNap() {
    one.setNap(5, 25);
    assertEquals(20, one.getNapTotal());
  }

  @Test
  public void testsetNap2() {
    one.setNap(30, 55);
    assertEquals(25, one.getNapTotal());
  }

  @Test
  public void testsetNap3() {
    one.setNap(24, 29);
    assertEquals(5, one.getNapTotal());
  }

  @Test
  public void testgetHoursSlept() {
    one.setNap(5, 25);
    one.setNap(30, 55);
    three.setNap(24, 29);
    assertEquals(50, register.getTotalforGuard(10));
  }

  @Test
  public void testGetSleepiestGuard() {
    one.setNap(5, 25);
    one.setNap(30, 55);
    two.setNap(40, 50);
    three.setNap(24, 29);
    four.setNap(36, 46);
    five.setNap(45, 55);
    assertEquals(10, register.getSleepiestGuard());
  }

  @Test
  public void testGetSleepiestMinute() {
    one.setNap(5, 25);
    one.setNap(30, 55);
    two.setNap(40, 50);
    three.setNap(24, 29);
    four.setNap(36, 46);
    five.setNap(45, 55);
    assertEquals(24, register.getSleepiestMinutePerGuard(10));
  }

  @Test
  public void testExampleAnswer() {
    one.setNap(5, 25);
    one.setNap(30, 55);
    five.setNap(45, 55);
    two.setNap(40, 50);
    three.setNap(24, 29);
    four.setNap(36, 46);
    assertEquals(240, register.getSleepiestGuard() * register.getSleepiestMinutePerGuard(10));
  }

  @Test
  public void testGetMostMinutesPerGuard() {
    one.setNap(5, 25);
    one.setNap(30, 55);
    five.setNap(45, 55);
    two.setNap(40, 50);
    three.setNap(24, 29);
    four.setNap(36, 46);
    assertEquals(3, register.getMostMinutesPerGuard(99));
  }

  @Test
  public void testgetGuardwithMostMinutes() {
    one.setNap(5, 25);
    one.setNap(30, 55);
    five.setNap(45, 55);
    two.setNap(40, 50);
    three.setNap(24, 29);
    four.setNap(36, 46);
    assertEquals(99, register.getGuardwithMostMinutes());
  }

}
