package com.brennaswitzer.aoc;

public class Collector {
    Position p;
    int steps;

    Collector(Position p, int steps) {
        this.p = p;
        this.steps = steps;
    }

    Position getPosition() {
        return this.p;
    }

    int getSteps() {
        return steps;
    }
}
