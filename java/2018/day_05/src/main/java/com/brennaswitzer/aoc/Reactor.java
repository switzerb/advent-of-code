package com.brennaswitzer.aoc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Reactor {

    private String polymer;
    private Set<String> alphabet = new HashSet<>();

    Reactor() {
    }

    Reactor(String polymer) {
        this.polymer = polymer;
        String[] letters = polymer.split("");
        alphabet.addAll(Arrays.asList(letters));
        System.out.println(alphabet);
    }

    boolean isReactive(String unit) {
        String first = unit.substring(0, 1);
        String second = unit.substring(1);

        if (first.compareToIgnoreCase(second) == 0) {
            return first.compareTo(second) != 0;
        }
        return false;
    }

    int getOptimalHack() {
        int min = Integer.MAX_VALUE;
        for( String letter : alphabet){
            String p = polymer;
            p = removeUnit(letter);
            String collapsed = reactor(p);
            int l = countUnits(collapsed);
            if( l < min ) {
                min = l;
            }
        }
        return min;
    }

    String removeUnit(String unit) {
        String edit = polymer;
        String upper = unit.toUpperCase();
        String lower = unit.toLowerCase();
        edit = edit.replaceAll(upper, "");
        edit = edit.replaceAll(lower, "");
        return edit;
    }

    String reactor(String p) {
        for (int i = 0; i < p.length() - 1; i++) {
            String unit = p.substring(i, i + 2);
            if (isReactive(unit)) {
                String s = p.replaceAll(unit, "");
                return reactor(s);
            }
        }
        return p;
    }

    int countUnits(String polymer) {
        return polymer.length();
    }
}
