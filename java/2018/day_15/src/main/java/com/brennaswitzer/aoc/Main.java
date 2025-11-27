package com.brennaswitzer.aoc;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        /**
         * Combat proceeds in rounds;
         * the order in which units take their turns within a round is the reading order of their starting positions in that round
         * in each round,
         *      for each unit that is still alive
         * no diagonal movement or attack
         * ties are broken in reading order -- left to right, top to bottom
         */

        List<String> input = Loader.getInput("input.txt");
        Battlefield battle = new Battlefield(input);
        while (!battle.isOver()) {
            battle.runRound();
        }

        Runner simulation = new Runner();

        /**
         * 239330 answer is too high
         * 227290 - is the right answer
         * (2674 Hitpoints * 85 rounds)
         * 224616 is too low
         */
        System.out.println("Solution Part One: " + battle.outcome());
        System.out.println("Solution Part Two: " + simulation.run(input));


    }
}
