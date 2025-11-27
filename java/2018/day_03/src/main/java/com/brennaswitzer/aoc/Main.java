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

    List<String> c = new ArrayList<>();

    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      c.add(line.trim());
    }

    List<Claim> claims = parseClaims(c);
    Fabric fabric = new Fabric(1000, 1000, claims);
    fabric.claimRunner();

    System.out.println("Solution Part One: " + fabric.getOverlappingClaims());
    System.out.println("Solution Part Two: " + fabric.getNoOverlap());

  }

  public static List<Claim> parseClaims(List<String> claims) {
    List<Claim> c = new ArrayList<>();
    for(String in : claims) {
      String[] split = in.split(" ");
      int claimID = Integer.parseInt(split[0].substring(1));
      int col = parseCol(split[2]);
      int row = parseRow(split[2]);
      int across = parseAcross(split[3]);
      int down = parseDown(split[3]);
      c.add(new Claim(claimID, col, row, across, down));
    }
    return c;
  }

  public static int parseCol(String str) {
    String trim = str.substring(0, str.length() - 1);
    String[] split = trim.split(",");
    return Integer.parseInt(split[0]);
  }

  public static int parseRow(String str) {
    String trim = str.substring(0, str.length() - 1);
    String[] split = trim.split(",");
    return Integer.parseInt(split[1]);
  }

  public static int parseAcross(String str) {
    String[] split = str.split("x");
    return Integer.parseInt(split[0]);
  }

  public static int parseDown(String str) {
    String[] split = str.split("x");
    return Integer.parseInt(split[1]);
  }
}
