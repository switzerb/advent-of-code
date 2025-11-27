package com.brennaswitzer.aoc;

import java.util.Arrays;

public class MarbleGame {

  int players;
  int marbles;

  MarbleGame(int players, int marbles) {
    this.players = players;
    this.marbles = marbles;
  }

  long playGame() {

    Circle circle = new Circle();
    circle.addFirst(0);
    long[] p = new long[players];

    for (int i = 1; i <= marbles; i++) {
      if (i % 23 == 0) {
        circle.turn(-7);
        p[i % players] += i + circle.pop();

      } else {
        circle.turn(2);
        circle.addLast(i);
      }
    }
    return Arrays.stream(p).max().getAsLong();
  }
}
