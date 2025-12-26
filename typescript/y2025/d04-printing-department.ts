import {getNeighborsFull, Grid} from "../lib/grid";
import type {Pos} from "../lib/grid";
/*
 * --- Day 4: Printing Department ---
 * https://adventofcode.com/2025/day/4
 */

export function partOne(input: string) {
    const rolls = Grid.to2D(input);
    const width = Grid.width(rolls);
    const height = Grid.height(rolls);
    let count = 0;

    const hasPaper = (spot: Pos) => Grid.at(rolls, spot) === '@';
    const paperRollCount = (neighbors: Pos[]) => neighbors.filter(hasPaper).length;

    for (const [row, line] of rolls.entries()) {
        for (const [col,spot] of line.entries()) {
            if (spot === '@') {
                const neighbors = getNeighborsFull([row,col], width, height);
                const paperCount = paperRollCount(neighbors);
                if (paperCount < 4) {
                    count++;
                }
            }
        }
    }

    return count;
}

export function partTwo() {
    return 0;
}