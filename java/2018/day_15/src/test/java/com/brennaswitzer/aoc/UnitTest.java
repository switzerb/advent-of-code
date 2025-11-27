package com.brennaswitzer.aoc;

import org.junit.Test;

import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class UnitTest {

    @Test
    public void test_enemiesInRangeNeg() {
        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        Unit goblin = battle.units.get(0);
        assertEquals(null, goblin.inAttackRange(battle));
    }

    @Test
    public void test_enemiesInRangePos() {
        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);

        Adjacent near = new Adjacent();
        Unit goblin = battle.units.get(2);
        near.put(goblin.getCurrent(), goblin);
        Unit elf = battle.units.get(1);
        assertEquals(near, elf.inAttackRange(battle));
    }

    @Test
    public void test_attack_ex1() {
        String s = "#######\n" +
                "#.G.G.#\n" +
                "#..GEG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        Unit elf = battle.units.get(3);

        Adjacent near = new Adjacent();
        Unit goblin1 = battle.units.get(1);
        Unit goblin2 = battle.units.get(2);
        Unit goblin3 = battle.units.get(3);

        near.put(goblin3.getCurrent(), goblin3);
        near.put(goblin1.getCurrent(), goblin1);
        near.put(goblin2.getCurrent(), goblin2);
        Unit target = elf.attack(near);
        assertEquals(197, target.getHitpoints());
    }

    @Test
    public void test_attack_ex2() {
        String s = "#######\n" +
                "#.G.G.#\n" +
                "#..GEG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        Unit elf = battle.units.get(3);

        Adjacent near = new Adjacent();
        Unit goblin1 = battle.units.get(1);
        Unit goblin2 = battle.units.get(2);
        goblin2.setHitpoints(10);

        Unit goblin3 = battle.units.get(3);
        goblin3.setHitpoints(100);

        near.put(goblin3.getCurrent(), goblin3);
        near.put(goblin1.getCurrent(), goblin1);
        near.put(goblin2.getCurrent(), goblin2);
        Unit target = elf.attack(near);

        assertEquals(7, target.getHitpoints());
    }

    @Test
    public void test_attack_ex3() {
        String s = "#######\n" +
                "#.G.G.#\n" +
                "#..GEG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        String outcome = "#######\n" +
                "#.G.G.#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";

        Battlefield battle = new Battlefield(s);
        Unit elf = battle.units.get(3);

        Unit goblin2 = battle.units.get(2);
        goblin2.setHitpoints(2);
        elf.turn(battle);

        assertEquals(outcome, battle.toString());
    }

    @Test
    public void test_inRangePositions_ex1() {
        String s = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";

        Battlefield battle = new Battlefield(s);
        Unit elf = battle.units.get(0);
        List<Unit> enemies = battle.findEnemies(elf.enemy);
        TreeSet<Position> inRange = new TreeSet<>();
        inRange.add(new Position(1, 3));
        inRange.add(new Position(1, 5));
        inRange.add(new Position(2, 2));
        inRange.add(new Position(2, 5));
        inRange.add(new Position(3, 1));
        inRange.add(new Position(3, 3));
        assertEquals(inRange, elf.inRange(enemies, battle));
    }

    @Test
    public void test_findTargetPosition_ex1() {
        String s = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";

        Battlefield battle = new Battlefield(s);
        Unit elf = battle.units.get(0);
        List<Unit> enemies = battle.findEnemies(elf.enemy);
        assertEquals(new Position(1, 3), elf.getTargetPosition(battle, enemies));
    }

    @Test
    public void test_move() {
        String s = "#######\n" +
                "#.E...#\n" +
                "#.....#\n" +
                "#...G.#\n" +
                "#######";

        Battlefield battle = new Battlefield(s);
        Unit elf = battle.units.get(0);
        List<Unit> enemies = battle.findEnemies(elf.enemy);
        Position chosen = elf.getTargetPosition(battle, enemies);
        elf.moveTo(battle, chosen);
    }

}
