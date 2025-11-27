package com.brennaswitzer.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Reservoir {

    final char CLAY = '#';
    final char SAND = '.';
    final char SPRING = '+';
    final char WATER = '|';

    int width;
    int height;
    char[][] visualizer;

    Map<Point, Character> ground = new HashMap<>();

    // Map data structure to hold water and clay positions
    // Key = Position and Value = WATER / CLAY

    Reservoir(int width, int height) {
        visualizer = new char[height][];
        this.width = width;
        this.height = height;

        // Start with all sand
        for (int y = 0; y < height; y++) {
            visualizer[y] = new char[width];
            Arrays.fill(visualizer[y], SAND);
        }
        ground.put(new Point(0, 10), SPRING);

        for (int i = 2; i < 7; i++) {
            ground.put(new Point(i, 1), CLAY);
        }

        for (int i = 3; i < 7; i++) {
            ground.put(new Point(i, 12), CLAY);
        }

        for (int i = 1; i < 13; i++) {
            ground.put(new Point(7, i), CLAY);
        }

    }

    /**
     * Rules for water flow:
     * it always runs straight down until it hits clay (#)
     * water flows to the left and right until it reaches the top of the "container"
     * water can flow to either side
     */

    void flow(Point start) {
        Character meter = ground.get(start);
        Point current = start.go(Direction.South);
        int i = 0;
        while (i < 5) {
            ground.put(current, WATER);
            current = current.go(Direction.South);
            System.out.println(current);
            i++;
        }
    }

    private boolean isClay(Point p) {
        return ground.containsKey(p) && ground.get(p) == CLAY;
    }

    private boolean isWater(Point p) {
        return ground.containsKey(p) && ground.get(p) == WATER;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < height; r++) {
            if (r > 0) sb.append('\n');
            for (int c = 0; c < width; c++) {
                Point p = new Point(r, c);
                if (ground.get(p) != null) {
                    sb.append(ground.get(p));
                } else {
                    sb.append('.');
                }
            }
        }
        return sb.toString();
    }
}