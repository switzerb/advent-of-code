package com.brennaswitzer.aoc;

public class Claim {

  int claimID;
  int col;
  int row;
  int across;
  int down;

  Claim(int id, int col, int row, int across, int down) {
    this.claimID = id;
    this.col = col;
    this.row = row;
    this.across = across;
    this.down = down;
  }

  int getClaimID() {
    return this.claimID;
  }

  int getCol() {
    return this.col;
  }

  int getRow() {
    return this.row;
  }

  int getAcross() {
    return this.across;
  }

  int getDown() {
    return this.down;
  }
}
