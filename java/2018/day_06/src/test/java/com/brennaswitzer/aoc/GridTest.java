package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GridTest {

    private Grid grid = new Grid(10);

    @Before
    public void setup() {
        grid.addCoordinate(new Coordinate(1, 1, "A"));
        grid.addCoordinate(new Coordinate(1, 6, "B"));
        grid.addCoordinate(new Coordinate(8, 3, "C"));
        grid.addCoordinate(new Coordinate(3, 4, "D"));
        grid.addCoordinate(new Coordinate(5, 5, "E"));
        grid.addCoordinate(new Coordinate(8, 9, "F"));
    }

    @Test
    public void testManhattanDistance() {
        int answer = grid.getManhattanDistance(0, 0, 1, 1);
        assertEquals(2, answer);
    }

    @Test
    public void testGetClosestPoint() {
        grid.mapClosestCoordinate();
        System.out.println(grid);
    }

    @Test
    public void testGetMaxArea() {
        grid.mapClosestCoordinate();
        assertEquals(17, grid.getMaxArea());
    }

    @Test
    public void testGetSumOfDifferences(){
        assertEquals(30, grid.getSumOfDistances(4,3));
    }

    @Test
    public void testGetDesiredRegionArea(){
        int TARGET = 32;
        assertEquals(16, grid.getDesiredRegionArea(TARGET));
    }

}
