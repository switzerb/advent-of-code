package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuelTest {


    @Before
    public void setup() {

    }

    @Test
    public void testGetHundreds() {
        Fuel fuel = new Fuel(8);
        assertEquals(2, fuel.hundreds(1234));
    }

    @Test
    public void testGetHundreds2() {
        Fuel fuel = new Fuel(8);
        assertEquals(0, fuel.hundreds(12));
    }

    @Test
    public void testRackID() {
        Fuel fuel = new Fuel(8);
        assertEquals(13, fuel.getRackID(3));
    }

    @Test
    public void testGetPowerLevel() {
        Fuel fuel = new Fuel(8);
        assertEquals(4, fuel.getPowerLevel(3, 5));
    }

    @Test
    public void testGetPowerLevel2() {
        Fuel fuel = new Fuel(57);
        assertEquals(-5, fuel.getPowerLevel(122, 79));
    }

    @Test
    public void testGetPowerLevel3() {
        Fuel fuel = new Fuel(39);
        assertEquals(0, fuel.getPowerLevel(217, 196));
    }

    @Test
    public void testGetPowerLevel4() {
        Fuel fuel = new Fuel(71);
        assertEquals(4, fuel.getPowerLevel(101, 153));
    }

    @Test
    public void testLookAtGrid() {
        Fuel fuel = new Fuel(18);
        fuel.buildCells();
//        System.out.println(fuel);
    }

    @Test
    public void testGet3x3Total() {
        Fuel fuel = new Fuel(18);
        fuel.buildCells();
        assertEquals(29, fuel.get3x3Total(33,45));
    }

    @Test
    public void testGet3x3Total_ex2() {
        Fuel fuel = new Fuel(42);
        fuel.buildCells();
        assertEquals(30, fuel.get3x3Total(21,61));
    }

    @Test
    public void testGetMostPower() {
        Fuel fuel = new Fuel(18);
        assertEquals("33,45", fuel.largest3x3());
    }

    @Test
    public void testGetMostPower_ex2() {
        Fuel fuel = new Fuel(42);
        assertEquals("21,61", fuel.largest3x3());
    }

    @Test
    public void testGetAllCells() {
        Fuel fuel = new Fuel(18);
        assertEquals(113, fuel.getAllTotals(90,269,16));
    }

    @Test
    public void testGetAllCells_ex2() {
        Fuel fuel = new Fuel(42);
        assertEquals(119, fuel.getAllTotals(232,251,12));
    }

    @Test
    public void testGetMostPower_p2() {
        Fuel fuel = new Fuel(18);
        assertEquals("90,269,16", fuel.largestAll());
    }

    @Test
    public void testGetMostPower_p2_ex2() {
        Fuel fuel = new Fuel(42);
        assertEquals("232,251,12", fuel.largestAll());
    }

}
