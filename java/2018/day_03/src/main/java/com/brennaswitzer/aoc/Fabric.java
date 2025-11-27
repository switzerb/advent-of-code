package com.brennaswitzer.aoc;

import java.util.List;

public class Fabric {

  private int[] grid;
  private int width;
  List<Claim> claims;

  Fabric(int rows, int cols, List<Claim> claims) {
    this.grid = new int[rows * cols];
    this.width = cols;
    this.claims = claims;
  }

  void claimRunner() {
    for(Claim claim : claims) {
      setClaim(claim);
    }
  }

  void setClaim(Claim claim) {
    for (int i = claim.getCol(); i < (claim.getAcross() + claim.getCol()); i++) {
      setIfUnclaimed(getIndex(i, claim.getRow()), claim.getClaimID());
      for (int j = claim.getRow() + 1; j < (claim.getDown() + claim.getRow()); j++) {
        setIfUnclaimed(getIndex(i, j), claim.getClaimID());
      }
    }
  }

  boolean hasOverlap(Claim claim) {
    for (int i = claim.getCol(); i < (claim.getAcross() + claim.getCol()); i++) {
      if(isOverlapped(getIndex(i, claim.getRow()))) {
        return true;
      }
      for (int j = claim.getRow() + 1; j < (claim.getDown() + claim.getRow()); j++) {
        if(isOverlapped(getIndex(i, j))) {
          return true;
        }
      }
    }
    return false;
  }

  int getNoOverlap() {
    for(Claim c : claims) {
      if(!hasOverlap(c)) {
        return c.getClaimID();
      }
    }
    return 0;
  }

  void setIfUnclaimed(int index, int id) {
    if (isUnclaimed(index)) {
      grid[index] = id;
    } else {
      grid[index] = -1;
    }
  }

  boolean isOverlapped(int index) {
    return grid[index] == -1;
  }

  boolean isUnclaimed(int index) {
    return grid[index] == 0;
  }

  int getIndex(int col, int row) {
    return (this.width * row) + col;
  }

  int getOverlappingClaims() {
    int overlap = 0;
    for(int square : grid) {
      if( square == -1) {
        overlap++;
      }
    }
    return overlap;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= grid.length - 1; i++) {
      if (i % this.width == 0) {
        sb.append("\n");
      }
      if (grid[i] == 0) {
        sb.append(".");
      } else if (grid[i] == -1) {
        sb.append("X");
      } else {
        sb.append(grid[i]);
      }
    }
    return sb.toString();
  }
}
