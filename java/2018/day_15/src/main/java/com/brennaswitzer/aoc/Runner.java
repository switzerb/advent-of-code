package com.brennaswitzer.aoc;

import java.util.List;

public class Runner {

    int attackUp = 3;

    int run(List<String> input) {
        boolean elvesWin = false;
        Battlefield battle = null;

        while (!elvesWin) {
            attackUp++;
            battle = new Battlefield(input, attackUp);
            while (!battle.isOver()) {
                battle.runRound();
            }
            elvesWin = battle.elvesWin();
        }
        return battle.outcome();
    }


    int run(String input) {
        boolean elvesWin = false;
        Battlefield battle = null;

        while (!elvesWin) {
            attackUp++;
            battle = new Battlefield(input, attackUp);
            while (!battle.isOver()) {
                battle.runRound();
            }
            elvesWin = battle.elvesWin();
        }
        return battle.outcome();
    }

    int getAttackUp() {
        return attackUp;
    }
}
