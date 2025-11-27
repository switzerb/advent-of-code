package com.brennaswitzer.aoc;

import java.util.*;

public class Unit {

    char self;
    char enemy;
    Position current;
    int hitpoints = 200;
    int power;

    Unit(char self, Position current, int power) {
        this.power = power;
        this.self = self;
        this.enemy = this.self == 'E' ? 'G' : 'E';
        this.current = current;
    }

    Position getCurrent() {
        return current;
    }

    char getSelf() {
        return self;
    }

    int getHitpoints() {
        return hitpoints;
    }

    void setHitpoints(int hp) {
        this.hitpoints = hp;
    }

    /**
     * Each unit begins its turn by identifying all possible targets (enemy units)
     * if no enemy targets, combat ends
     * else unit takes a turn,
     * If the unit is not already in range of a target, and there are no open squares which are in range of a target, the unit ends its turn.
     * If the unit is already in range of a target, it does not moveTo, but continues its turn with an attack. Otherwise, since it is not in range of a target, it moves.
     * if in range, attack
     *
     * @return true if combat is ended, false if turn is over
     */
    boolean turn(Battlefield field) {

        if (!isAlive()) return false;

        List<Unit> enemies = field.findEnemies(enemy);
        if (enemies == null) return true;

        Adjacent enemiesNear = inAttackRange(field);
        if (enemiesNear == null) {

            // if there are no enemies near, then try to move
            Position targetPosition = getTargetPosition(field, enemies);

            // if no available target positions, turn is over; otherwise, try moving
            if (targetPosition == null) return false;

            Position moveTo = moveTo(field, targetPosition);
            if (moveTo == null) return false;

            field.moveUnit(this, moveTo);
            current = new Position(moveTo);

            // now that we've moved, try and attack
            Adjacent e = inAttackRange(field);
            if (e != null) {
                Unit target = attack(e);
                if (!target.isAlive()) {
                    field.removeUnit(target);
                }
            }
            return false;
        } else {
            Unit target = attack(enemiesNear);
            if (!target.isAlive()) {
                field.removeUnit(target);
            }
            return false;
        }
    }

    boolean isAlive() {
        return hitpoints > 0;
    }

    /**
     * To attack, the unit first determines all of the targets that are in range of it by being immediately adjacent to it.
     */
    Adjacent inAttackRange(Battlefield field) {
        Adjacent enemiesInRange = new Adjacent();

        for (Direction dir : Direction.values()) {
            Unit u = field.getUnitByPosition(current.go(dir));
            if (u != null && u.self == enemy) {
                enemiesInRange.put(current.go(dir), u);
            }
        }
        return enemiesInRange.size() > 0 ? enemiesInRange : null;
    }

    /**
     * For all adjacent enemies, attack the one with the lowest hitpoints
     * In a tie, the adjacent target with the fewest hit points which is first in reading order is selected.
     *
     * @param enemies Map of all enemies in attack range
     */
    Unit attack(Adjacent enemies) {
        int min = Integer.MAX_VALUE;
        Unit target = null;
        for (Unit u : enemies.values()) {
            if (u.getHitpoints() < min) {
                min = u.getHitpoints();
                target = u;
            }
        }
        assert target != null;
        target.setHitpoints(target.getHitpoints() - this.power);
        return target;
    }

    /**
     * To moveTo, the unit first considers the squares that are in range near enemy targets and determines
     * If the unit cannot reach (find an open path to) any of the squares that are in range, it ends its turn.
     * If multiple squares are in range and tied for being reachable in the fewest steps,
     * the square which is first in reading order is chosen. For example:
     * <p>
     * Enemies:      In range:     Reachable:    Nearest:      Chosen:
     * #######       #######       #######       #######       #######
     * #E..G.#       #E.?G?#       #E.@G.#       #E.!G.#       #E.+G.#
     * #...#.#  -->  #.?.#?#  -->  #.@.#.#  -->  #.!.#.#  -->  #...#.#
     * #.G.#G#       #?G?#G#       #@G@#G#       #!G.#G#       #.G.#G#
     * #######       #######       #######       #######       #######
     */
    Position getTargetPosition(Battlefield field, List<Unit> enemies) {

        TreeSet<Position> openPositions = inRange(enemies, field);
        if (openPositions == null) return null;

        // In range, reachable and nearest me, in reading order
        return nearest(field, openPositions, getCurrent());
    }

    /**
     * Identify the open squares (.) that are in range of each target;
     * these are the squares which are adjacent (immediately up, down, left, or right)
     * to any target and which aren't already occupied by a wall or another unit.*
     *
     * @param enemies
     * @param field
     * @return set of open positions around enemy targets
     */
    TreeSet<Position> inRange(List<Unit> enemies, Battlefield field) {
        TreeSet<Position> openPositions = new TreeSet<>();
        for (Unit u : enemies) {

            for (Direction dir : Direction.values()) {
                char c = field.getPosition(u.getCurrent().go(dir));
                if (c == '.') {
                    openPositions.add(u.getCurrent().go(dir));
                }
            }
        }
        return openPositions.size() > 0 ? openPositions : null;
    }

    /**
     * The unit then takes a single step toward the chosen square along the shortest path to that square.
     * If multiple steps would put the unit equally closer to its destination,
     * the unit chooses the step which is first in reading order.
     * <p>
     * The Elf sees three squares in range of a target (?), two of which are nearest (!),
     * and so the first in reading order is chosen (+).
     * Under "Distance", each open square is marked with its distance from the destination square;
     * the two squares to which the Elf could moveTo on this turn (down and to the right) are both equally good moves
     * and would leave the Elf 2 steps from being in range of the Goblin.
     * <p>
     * Because the step which is first in reading order is chosen, the Elf moves right one square.
     * <p>
     * Chosen:       Distance:     Step:
     * #######       #######       #######
     * #.E...#       #4E212#       #..E..#
     * #...+.#  -->  #32101#  -->  #.....#
     * #...G.#       #432G2#       #...G.#
     * #######       #######       #######
     */
    Position moveTo(Battlefield field, Position chosen) {
        Set<Position> self = new TreeSet<>();
        for (Direction dir : Direction.values()) {
            if (getCurrent().go(dir).equals(chosen)) {
                return chosen;
            }
            char c = field.getPosition(getCurrent().go(dir));
            if (c == '.') {
                self.add(getCurrent().go(dir));
            }
        }
        return nearest(field, self, chosen);
    }


    /**
     * Set of positions that are in range, reachable and nearest
     *
     * @param field   Current battlefield
     * @param inRange Open positions near enemies
     * @param start   Our current position
     * @return TreeSet<Position> Ordered position set nearest to our current position and reachable
     */
    Position nearest(Battlefield field, Set<Position> inRange, Position start) {
        int min_steps = Integer.MAX_VALUE;
        TreeSet<Position> nearest = new TreeSet<>();
        LinkedList<Collector> queue = new LinkedList<>();
        Map<Position, Integer> visited = new HashMap<>();
        Collector current = new Collector(start, 0);
        visited.put(start, 0);
        queue.add(current);

        while (queue.size() != 0) {
            current = queue.poll();

            for (Direction dir : Direction.values()) {
                Collector next = new Collector(current.getPosition().go(dir), current.getSteps() + 1);

                if (field.getPosition(next.getPosition()) == '.' && !visited.containsKey(next.getPosition())) {

                    if (inRange.contains(next.getPosition()) && next.getSteps() <= min_steps) {
                        nearest.add(next.getPosition());
                        min_steps = next.getSteps();
                    }

                    visited.put(next.getPosition(), next.getSteps());
                    queue.add(next);
                }
            }
        }
        return nearest.size() > 0 ? nearest.first() : null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(self).append(" ");
        sb.append(current);
        sb.append("(").append(hitpoints).append(")");
        return sb.toString();
    }
}
