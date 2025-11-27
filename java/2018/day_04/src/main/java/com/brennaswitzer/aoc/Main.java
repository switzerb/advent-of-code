package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

  // read in input
  // sort in chronological order
  // count minutes asleep for each guard
  // Find the guard that has the most minutes asleep.
  // What minute does that guard spend asleep the most?

  public static void main(String[] args) throws IOException {

    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));

    List<String> input = new ArrayList<>();

    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      input.add(line.trim());
    }

    List<Record> records = parseRecords(input);
    Register register = new Register(records);
    int one = register.getSleepiestGuard();
    int minute = register.getSleepiestMinutePerGuard(one);

    int two = register.getGuardwithMostMinutes();
    int m = register.getSleepiestMinutePerGuard(two);


      System.out.println("Part One Solution: " + one * minute);
      System.out.println("Part Two Solution: " + two * m);

  }

  public static List<Record> parseRecords(List<String> input) {
    List<Record> records = new ArrayList<>();

    input.sort(new c());
    int id = 0;
    int start = 0;
    int end = 0;

    for (String record : input) {
      String[] split = record.split(" ");
      int month = parseMonth(split[0]);
      int day = parseDay(split[0]);

      if (split[2].equals("Guard")) {
        id = parseGuard(split[3]);
      } else if (split[2].equals("falls")) {
        start = parseTime(split[1]);
      } else if (split[2].equals("wakes")) {
        end = parseTime(split[1]);
//        System.out.println("Write Record: " + id + " " + month + " " + day + " " + start + " " + end);
        records.add(new Record(id, month, day, start, end));
      }
    }
    return records;
  }

  public static int parseMonth(String str) {
    return Integer.parseInt(str.substring(6, 8));
  }

  public static int parseDay(String str) {
    return Integer.parseInt(str.substring(9, 11));
  }

  public static int parseGuard(String str) {
    return Integer.parseInt(str.substring(1));
  }

  public static int parseTime(String str) {
    return Integer.parseInt(str.substring(3, 5));
  }

  public static class c implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
      return o1.compareTo(o2);
    }
  }

}
