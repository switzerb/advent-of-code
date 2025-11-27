package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    
    Register r = new Register();
    
    @Before
    public void setUp() {
        int[] before = {3,2,1,1};
        int[] after = {3,2,2,1};
        r.addOpcode(9, 2, 1, 2, before, after);
    }
    
    @Test
    public void testAddi() {
        Register.Opcode o = r.getInstruction(0);
        int[] expected = {3, 2, 2, 1};
        assertTrue(Arrays.equals(r.addi(o), expected));
    }
    
    @Test
    public void testAddr() {
        Register.Opcode o = r.getInstruction(0);
        int[] expected = {3, 2, 3, 1};
        // 1 + 2
        assertTrue(Arrays.equals(r.addr(o), expected));
    }

    @Test
    public void testMuli() {
        Register.Opcode o = r.getInstruction(0);
        int[] expected = {3,2,1,1};
        assertTrue(Arrays.equals(r.muli(o), expected));
    }
    
    @Test
    public void testMulr() {
        Register.Opcode o = r.getInstruction(0);
        int[] expected = {3, 2, 2, 1};
        assertTrue(Arrays.equals(r.mulr(o), expected));
    }
    
    @Test
    public void testBanr() {
        Register.Opcode o = r.getInstruction(0);
        int[] expected = {3,2,0,1};
        assertTrue(Arrays.equals(r.banr(o), expected));
    }
    
    @Test
    public void testBani() {
        Register.Opcode o = r.getInstruction(0);
        int[] expected = {3, 2, 1, 1};
        assertTrue(Arrays.equals(r.bani(o), expected));
    }

    @Test
    public void testCountInstructions() {
        r.countInstructions();
        assertEquals(1, r.countInstructions());
    }
    
}
