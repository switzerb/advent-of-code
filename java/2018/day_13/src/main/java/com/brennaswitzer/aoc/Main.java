package com.brennaswitzer.aoc;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> lines = Loader.getInput("input.txt");
        Track track = new Track(lines);

        while (track.getCarts() > 1) {
            track.step();
        }

        System.out.println("Solution Part One: " + track.firstCollision());
        System.out.println("Solution Part Two: " + track.getLastCart());

    }
}
