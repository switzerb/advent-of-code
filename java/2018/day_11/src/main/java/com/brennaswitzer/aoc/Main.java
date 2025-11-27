package com.brennaswitzer.aoc;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {

    final int SERIAL = 9995;

    Fuel fuel = new Fuel(SERIAL);
    System.out.println("Solution Part One " + fuel.largest3x3());
    System.out.println("Solution Part Two " + fuel.largestAll());
  }
}
