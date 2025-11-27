package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    
    int TARGET = 894501;
    
    RecipeBuilder recipes = new RecipeBuilder(TARGET);
    System.out.println("Solution Part One: " + recipes.getLastTen());
    System.out.println("Solution Part Two: " + recipes.previousRecipes());
  
  }
  
}
