package com.brennaswitzer.aoc;

class Position implements Comparable<Position> {

    private final java.awt.Point p;

    Position() {
        p = new Point();
    }

    public Position(Position n) {
        p = new java.awt.Point(n.getCol(), n.getRow());
    }

    public Position(int row, int col) {
        p = new java.awt.Point(col, row);
    }

    public final int getRow() {
        return p.y;
    }

    public final int getCol() {
        return p.x;
    }

    public Position go(Direction dir) {
        return dir.move(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return p.equals(position.p);
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }

    @Override
    public int compareTo(Position o) {
        int c = p.y - o.p.y;
        if (c != 0) return c;
        return p.x - o.p.x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + p.y + "," + p.x + "]");
        return sb.toString();
    }
}
