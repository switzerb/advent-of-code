package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Battlefield {

    // map of the battlefield grid
    // govern the behavior and position of points
    // make sure we can get reading order for anything, like open squares and combatants

    List<Unit> units;
    char[][] battlefield;
    int elfCount;
    int width;
    int height;
    int rounds;
    boolean done;

    Battlefield(List<String> input) {
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
        initUnits(3);
    }

    Battlefield(String init) {
        String[] split = init.split("\n");
        List<String> input = Arrays.asList(split);
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
        initUnits(3);
    }

    Battlefield(List<String> input, int attackUp) {
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
        initUnits(attackUp);
    }

    Battlefield(String init, int attackUp) {
        String[] split = init.split("\n");
        List<String> input = Arrays.asList(split);
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
        initUnits(attackUp);
    }

    private void initUnits(int elfPower) {
        units = new ArrayList<>();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (battlefield[r][c] == 'E') {
                    Unit elf = new Unit('E', new Position(r, c), elfPower);
                    elfCount++;
                    units.add(elf);
                }
                if (battlefield[r][c] == 'G') {
                    Unit goblin = new Unit('G', new Position(r, c), 3);
                    units.add(goblin);
                }
            }
        }
        units.sort(UNITS_IN_READING_ORDER);
    }

    /**
     * Return a unit by current position on the battlefield. If there is no unit at that position, return null
     *
     * @param position
     * @return Unit
     */
    Unit getUnitByPosition(Position position) {
        for (Unit u : units) {
            if (u.getCurrent().equals(position) && u.isAlive()) {
                return u;
            }
        }
        return null;
    }

    List<Unit> findEnemies(char species) {
        List<Unit> e = new ArrayList<>();
        for (Unit u : units) {
            if (u.self == species && u.isAlive()) {
                e.add(u);
            }
        }
        e.sort(UNITS_IN_READING_ORDER);
        return e.size() > 0 ? e : null;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    char getPosition(Position p) {
        return battlefield[p.getRow()][p.getCol()];
    }

    /**
     * Runs a single round of play.
     * Combat proceeds in rounds; in each round, each unit that is still alive takes a turn, resolving all of its actions before the next unit's turn begins.
     * For instance, the order in which units take their turns within a round is the reading order of their starting positions in that round
     */
    void runRound() {
        units = getSurvivors();
        for (Unit unit : units) {
            done = unit.turn(this);
            if (done) break;
        }
        if (done) return;
        rounds++;
    }

    private List<Unit> getSurvivors() {
        List<Unit> nextRound = new ArrayList<>();
        for (Unit u : units) {
            if (u.isAlive()) {
                nextRound.add(u);
            }
        }
        nextRound.sort(UNITS_IN_READING_ORDER);
        return nextRound;
    }

    boolean isOver() {
        return done;
    }

    /**
     * Remove a unit from the battlefield when it's dead
     *
     * @param target unit that is being removed
     */
    public void removeUnit(Unit target) {
        Position current = target.getCurrent();
        battlefield[current.getRow()][current.getCol()] = '.';
    }

    /**
     * Move a unit on the field to somewhere new and replace that position with a '.' instead
     *
     * @param unit
     * @return new position of unit
     */
    void moveUnit(Unit unit, Position moveTo) {
        battlefield[unit.getCurrent().getRow()][unit.getCurrent().getCol()] = '.';
        battlefield[moveTo.getRow()][moveTo.getCol()] = unit.getSelf();
    }

    /**
     * The outcome of the battle: the number of full rounds that were completed (not counting the round in which combat ends)
     * multiplied by the sum of the hit points of all remaining units at the moment combat ends.
     * (Combat only ends when a unit finds no targets during its turn.)
     *
     * @return
     */
    int outcome() {
        int sum = 0;
        for (Unit u : units) {
            if (u.isAlive()) {
                sum += u.getHitpoints();
            }
        }
        System.out.println("hp: " + sum);
        System.out.println("r: " + rounds);
        return sum * rounds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < height; r++) {
            if (r > 0) sb.append('\n');
            for (int c = 0; c < width; c++) {
                sb.append(battlefield[r][c]);
            }
        }
        return sb.toString();
    }

    public static Comparator<Unit> UNITS_IN_READING_ORDER = new Comparator<Unit>() {
        @Override
        public int compare(Unit u1, Unit u2) {

            if (u1.getCurrent().getRow() < u2.getCurrent().getRow()) return -1;
            if (u1.getCurrent().getRow() > u2.getCurrent().getRow()) return 1;
            return Integer.compare(u1.getCurrent().getCol(), u2.getCurrent().getCol());
        }
    };

    char getWinner() {
        return units.get(0).self;
    }

    int countElves() {
        int count = 0;
        for (Unit u : units) {
            if (u.getSelf() == 'E') {
                count++;
            }
        }
        return count;
    }

    public boolean elvesWin() {
        return getWinner() == 'E' && countElves() == elfCount;
    }
}
