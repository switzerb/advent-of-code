package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws IOException {
    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));

    String input = r.readLine();

    String[] split = input.split(" ");
    int[] numbers = new int[split.length];
    for(int i = 0; i < split.length; i++){
      numbers[i] = Integer.parseInt(split[i]);
    }

    Node root = new Node();
    root = root.buildNode(numbers);

    System.out.println("Answer Part One: " + root.sumMeta());
    System.out.println("Answer Part Two: " + root.getValue());

  }


}
