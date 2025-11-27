package com.brennaswitzer.aoc;

public enum Turn {
    LEFT, STRAIGHT, RIGHT;

    Turn turn() {
        Turn[] turns = Turn.values();
        return turns[(ordinal() + 1) % values().length];
    }
}