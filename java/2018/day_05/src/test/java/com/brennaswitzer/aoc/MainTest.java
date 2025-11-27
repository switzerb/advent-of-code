package com.brennaswitzer.aoc;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MainTest {

    private Reactor r = new Reactor("dabAcCaCBAcCcaDA");

    @Test
    public void testisReactive1() {
        Reactor r = new Reactor("aA");
        assertEquals(true, r.isReactive("aA"));
    }

    @Test
    public void testisReactive2() {
        Reactor r = new Reactor("aa");
        assertEquals(false, r.isReactive("aa"));
    }

    @Test
    public void testisReactive3() {
        Reactor r = new Reactor("aA");
        assertEquals(false, r.isReactive("AA"));
    }

    @Test
    public void testisReactive4() {
        Reactor r = new Reactor("aA");
        assertEquals(true, r.isReactive("Yy"));
    }

    @Test
    public void testReactor() {
        Reactor r = new Reactor("dabAcCaCBAcCcaDA");
        assertEquals("dabCBAcaDA", r.reactor("dabAcCaCBAcCcaDA"));
    }

    @Test
    public void testP2_replace(){
        assertEquals("dbcCCBcCcD", r.removeUnit("A"));
    }

    @Test
    public void testP2_ex1(){
        String collapsed = r.removeUnit("A");
        String complete = r.reactor(collapsed);
        assertEquals(6, r.countUnits(complete));
    }

    @Test
    public void testP2_ex2() {
        assertEquals(4, r.getOptimalHack());
    }

    @Test
    public void testCountUnits() {
        Reactor r = new Reactor("a");
        assertEquals(10, r.countUnits("dabCBAcaDA"));
    }

}
