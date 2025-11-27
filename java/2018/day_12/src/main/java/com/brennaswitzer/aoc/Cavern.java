package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;

public class Cavern {
    
    String initial;
    List<Rule> rules = new ArrayList<>();
    
    Cavern(String initial) {
        this.initial = initial;
    }
    
    void addRule(String rule) {
        String[] split = rule.split("=>");
        Rule r = new Rule(split[0].trim(), split[1].trim());
        rules.add(r);
    }
    
    String willGrow(String pattern) {
        for (Rule r : rules) {
            if (pattern.equals(r.in)) {
                return r.out;
            }
        }
        return ".";
    }
    
    int getSumOfPots(int gens) {
        int sum = 0;
        int center = 0;
        
        String prev = initial;
        String last = "";
    
        for (int e = 0; e < gens; e++) {
            prev = "....." + prev + "...";
            String next = "";
            center += 3;
        
            for (int i = 2; i < prev.length() - 2; i++) {
                // need to send the pot, plus two previous and two after to willGrow function
                String pattern = prev.substring(i - 2, i + 3);
                next += willGrow(pattern);
            }
            prev = next;
            if(e == gens -1){
                last = next;
            }
        }
    
        for (int i = 0; i < last.length(); i++) {
            if(last.charAt(i) == '#') {
                sum += i - center;
            }
        }
        return sum;
    }
    
    long getHugeEvolution() {
        long sum = 0L;
        // need to put in here dynamic calculation of the "63" difference
        sum += getSumOfPots(400);

        sum += (50_000_000_000L - 400) * 63;
        return sum;
    }
    
    public static class Rule {
        String in;
        String out;
        
        Rule(String in, String out) {
            this.in = in;
            this.out = out;
        }
    }
}
