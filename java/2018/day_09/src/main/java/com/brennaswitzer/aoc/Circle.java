package com.brennaswitzer.aoc;

import java.util.LinkedList;

public class Circle extends LinkedList<Integer> {
  void turn(int num) {
    if (num == 0) return;
    if (num > 0) {
      for (int i = 0; i < num; i++) {
        Integer marble = removeLast();
        addFirst(marble);
      }
    } else {
      for (int i = 0; i < Math.abs(num) - 1; i++) {
        Integer marble = remove();
        addLast(marble);
      }
    }
  }
}
