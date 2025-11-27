package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ClassLoader cl = Main.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("input.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(in));

        List<String> coordinates = new ArrayList<>();

        String[] names = {
                "Tanna",
                "Joeann",
                "Inell",
                "Kyra",
                "Shirleen",
                "Rosalind",
                "Isabella",
                "Adelina",
                "In",
                "Hue",
                "Steve",
                "Emory",
                "Anibal",
                "Delpha",
                "Galina",
                "Camila",
                "Breann",
                "Kimi",
                "Nerissa",
                "Marget",
                "Vania",
                "Christal",
                "Taneka",
                "Aurea",
                "Roland",
                "Leeann",
                "Rosalee",
                "Obdulia",
                "Esperanza",
                "Alan",
                "Nguyet",
                "Nicolle",
                "Leta",
                "Leonarda",
                "Josephine",
                "Denisha",
                "Corey",
                "Fallon",
                "Raquel",
                "Melony",
                "Mittie",
                "Gretchen",
                "Qiana",
                "Clara",
                "Percy",
                "Gertrude",
                "Lezlie",
                "Haywood",
                "Danica",
                "August",
                "Barney",
                "Brenna"
        };

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            coordinates.add(line.trim());
        }

        Grid grid = new Grid(350);

        int index = 0;
        for(String line : coordinates) {
            String[] split = line.split(",");
            int col = parseCoordinate(split[0]);
            int row = parseCoordinate(split[1]);
            grid.addCoordinate(new Coordinate(col, row, names[index]));
            index++;
        }

        int TARGET = 10000;
        grid.mapClosestCoordinate();
        System.out.println("Part One Solution: " + grid.getMaxArea());
        System.out.println("Part Two Solution: " + grid.getDesiredRegionArea(TARGET));

    }

    public static int parseCoordinate(String s){
        return Integer.parseInt(s.trim());
    }

}
