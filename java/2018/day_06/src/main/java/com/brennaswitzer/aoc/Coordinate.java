package com.brennaswitzer.aoc;

public class Coordinate {
    int col;
    int row;
    String name;

    Coordinate(int col, int row, String name) {
        this.col = col;
        this.row = row;
        this.name = name;
    }

    int getRow() {
        return this.row;
    }

    int getCol(){
        return this.col;
    }

    String getName(){
        return this.name;
    }

}
