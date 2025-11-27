package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

    String[][] grid;
    List<Coordinate> points = new ArrayList<>();

    Grid(int n) {
        grid = new String[n][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = ".";
            }
        }
    }

    void addCoordinate(Coordinate c) {
        points.add(c);
    }

    int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    void mapClosestCoordinate() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int min = Integer.MAX_VALUE;
                for (Coordinate point : points) {
                    int distance = getManhattanDistance(i, j, point.getCol(), point.getRow());
                    if (distance == 0) {
                        grid[j][i] = point.getName();
                        break;
                    }
                    if (distance == min) {
                        grid[j][i] = ".";
                    } else if (distance < min) {
                        grid[j][i] = point.getName().toLowerCase();
                        min = distance;
                    }
                }
            }
        }
    }

    int getMaxArea() {
        int max = 0;
        Map<String, Integer> frequency = buildFrequency();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if(entry.getValue() > max && !isInfinite(entry.getKey())) {
                max = entry.getValue();
            }
        }
        return max;
    }

    Map<String, Integer> buildFrequency() {
        Map<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                String k = grid[i][j];
                int v = 1;
                if (frequency.containsKey(k)) {
                    v = frequency.get(k);
                }
                frequency.put(k, v + 1);
            }
        }
        return frequency;
    }

    boolean isInfinite(String key) {
        // for a key, look at all four edges of the grid. if the key appears, then it is infinite
        // check the edges of the grid? if they appear in there anywhere, it's infinite
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0].equals(key)) {
                return true;
            }
            if (grid[0][i].equals(key)) {
                return true;
            }
            if (grid[i][grid.length-1].equals(key)) {
                return true;
            }
            if (grid[grid.length-1][i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    int getSumOfDistances(int x, int y){
        int sum = 0;
        for(Coordinate p : points) {
            sum += getManhattanDistance(p.getCol(), p.getRow(),x, y);
        }
        return sum;
    }

    int getDesiredRegionArea(int target) {
        int area = 0;
        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid.length; y++){
                int sum = getSumOfDistances(x, y);
                if( sum < target){
                    area += 1;
                }
            }
        }
        return area;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            sb.append("\n");
            for (int j = 0; j < grid.length; j++) {
                sb.append(grid[i][j]);
            }
        }
        return sb.toString();
    }

}
