package com.brennaswitzer.aoc;

public class RecipeBuilder {
    
    int target;
    String scoreboard;
    final int iterations = 100_000_000;
    
    RecipeBuilder(int target) {
        this.target = target;
    }
    
    public String makeRecipes(int iter) {
        StringBuilder scores = new StringBuilder();
        int first = 3;
        int second = 7;
        
        int e1_current = 0;
        int e2_current = 1;
        
        scores.append(first);
        scores.append(second);
        
        while (scores.length() < iter + 10) {
            int sum = first + second;
            if (sum > 9) {
                scores.append(sum / 10); // first digit
                scores.append(sum % 10); // second digit
            } else {
                scores.append(sum); // otherwise just append it
            }
            e1_current = (e1_current + (1 + first)) % scores.length();
            e2_current = (e2_current + (1 + second)) % scores.length();
            first = scores.charAt(e1_current) - '0';
            second = scores.charAt(e2_current) - '0';
        }
        return scores.toString();
    }
    
    public String getLastTen() {
        String strScores = makeRecipes(target);
        return strScores.substring(target);
    }
    
    public int previousRecipes() {
        String strScores = makeRecipes(100000000);
        return strScores.indexOf(String.valueOf(target));
    }
    
    
}
