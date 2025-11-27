package com.brennaswitzer.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecipeBuilderTest {
    
    int TARGET = 894501;
    RecipeBuilder recipes = new RecipeBuilder(TARGET);
    
    @Test
    public void testPartOne() {
        assertEquals("2157138126", recipes.getLastTen());
    }
    
    @Test
    public void testPartTwo() {
        assertEquals(20365081, recipes.previousRecipes());
    }
    
}
