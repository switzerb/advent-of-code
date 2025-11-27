package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws IOException {
    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));
    String line = r.readLine().trim();
    int max_presents = Integer.parseInt(line);

    System.out.println("Part One: " + Main.partOneSolver(max_presents));
    System.out.println("Part Two: " + Main.partTwoSolver(max_presents));
  }

  public static int partOneSolver(int max) {
    int houseCount = 0;

    while (true) {
      houseCount += 1;
      int p = getTotalPresents(houseCount);
      if (p >= max) {
        return houseCount;
      }
    }
  }

  public static int partTwoSolver(int max) {
    int houseCount = 0;
    while (true) {
      houseCount += 1;
      int p = getLazyPresents(houseCount);
      if (p >= max) {
        return houseCount;
      }
    }
  }

  public static int getTotalPresents(int house) {
    int count = 0;

    Set<Integer> divisors = getDivisors(house);
    for (int i : divisors) {
      count += i * 10;
    }
    return count;
  }

  public static int getLazyPresents(int house) {
    int count = 0;

    Set<Integer> divisors = getDivisors(house);
    for (int i : divisors) {
      if( house < (i*50) ) {
        count += i * 11;
      }
    }
    return count;
  }

  public static Set<Integer> getDivisors(int house) {
    Set<Integer> d = new HashSet<>();
    d.add(1); // one is always a divisor
    d.add(house); // a number can always be divided by itself

    for (int elf = 2; elf <= Math.sqrt(house); elf++) {
      if (house % elf == 0) {
        d.add(elf);
        if (elf != house / elf) {
          d.add(house / elf);
        }
      }
    }
    return d;
  }

}
