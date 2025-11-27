package com.brennaswitzer.aoc;

public class Crash {

    Cart A;
    Cart B;

    Crash(Cart A, Cart B) {
        this.A = A;
        this.B = B;
    }

    Cart getA() {
        return A;
    }

    Cart getB() {
        return B;
    }
}
