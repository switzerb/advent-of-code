package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {

    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));

    List<String> boxIDs = new ArrayList<>();

    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      boxIDs.add(line.trim());
    }

    Warehouse warehouse = new Warehouse(boxIDs);
    System.out.println("Solution Part One: " + warehouse.getChecksum());
    System.out.println("Solution Part Two: " + warehouse.getCommonLetters());
  }

}
