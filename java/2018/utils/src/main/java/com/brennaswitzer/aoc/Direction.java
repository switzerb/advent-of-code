package com.brennaswitzer.aoc;

public enum Direction {
    North(-1, 0), South(1, 0), East(0, 1), West(0, -1);

    int row_delta, col_delta;

    Direction(int row, int col) {
        this.row_delta = row;
        this.col_delta = col;
    }

    Point move(Point p) {
        return new Point(p.getRow() + row_delta, p.getCol() + col_delta);
    }

}