package com.brennaswitzer.aoc;

public class Main {

  public static void main(String[] args) {

    final int MARBLES = 71617;
    final int PLAYERS = 416;

    MarbleGame game = new MarbleGame(PLAYERS, MARBLES);
    MarbleGame gameTwo = new MarbleGame(PLAYERS, MARBLES * 100);

    System.out.println("Solution Part One: " + game.playGame());
    System.out.println("Solution Part Two: " + gameTwo.playGame());
  }

}
