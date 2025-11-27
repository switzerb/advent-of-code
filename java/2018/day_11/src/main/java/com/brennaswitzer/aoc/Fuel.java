package com.brennaswitzer.aoc;

public class Fuel {

    int[][] grid = new int[300][300];
    int SERIAL;

    Fuel(int serial) {
        this.SERIAL = serial;
        buildCells();
    }

    void buildCells() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                grid[x][y] = getPowerLevel(x, y);
            }
        }
    }

    String largest3x3() {
        int max = Integer.MIN_VALUE;
        int top = 0;
        int left = 0;
        for (int x = 0; x < grid.length - 3; x++) {
            for (int y = 0; y < grid.length - 3; y++) {
                int fuelMax = get3x3Total(x, y);
                if (fuelMax > max) {
                    max = fuelMax;
                    top = x;
                    left = y;
                }
            }
        }
        System.out.println(max);
        return top + "," + left;
    }

    String largestAll() {
        int max = Integer.MIN_VALUE;
        int top = 0;
        int left = 0;
        int s = 0;
        for (int size = 1; size < 300; size++) {
            for (int x = 0; x < grid.length - size; x++) {
                for (int y = 0; y < grid.length - size; y++) {
                    int fuelMax = getAllTotals(x, y, size);
                    if (fuelMax > max) {
                        max = fuelMax;
                        top = x;
                        left = y;
                        s = size;
                    }
                }
            }
        }
        return top + "," + left + "," + s;
    }

    int getAllTotals(int top, int left, int size) {
        int sum = 0;
        for (int x = top; x < top + size; x++) {
            for (int y = left; y < left + size; y++) {
                sum += grid[x][y];
            }
        }
        return sum;
    }

    int get3x3Total(int top, int left) {
        int sum = 0;
        for (int x = top; x < top + 3; x++) {
            for (int y = left; y < left + 3; y++) {
                sum += grid[x][y];
            }
        }
        return sum;
    }

    int getPowerLevel(int x, int y) {
        int rackID = getRackID(x);
        return hundreds(rackID * ((rackID * y) + SERIAL)) - 5;
    }

    int getRackID(int x) {
        return x + 10;
    }

    int hundreds(int value) {
        String v = String.valueOf(value);
        String[] split = v.split("");
        if (split.length >= 3) {
            return Integer.parseInt(split[split.length - 3]);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < grid.length; x++) {
            sb.append("\n");
            for (int y = 0; y < grid.length; y++) {
                sb.append(grid[x][y]).append(" ");
            }
        }
        return sb.toString();
    }
}
