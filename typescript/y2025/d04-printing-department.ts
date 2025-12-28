import {getNeighborsFull, Grid, asKey} from "../lib/grid";
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
        for (const [col, spot] of line.entries()) {
            if (spot === '@') {
                const neighbors = getNeighborsFull([row, col], width, height);
                const paperCount = paperRollCount(neighbors);
                if (paperCount < 4) {
                    count++;
                }
            }
        }
    }

    return count;
}

export function partTwo(input: string) {
    const rolls = Grid.to2D(input);
    const width = Grid.width(rolls);
    const height = Grid.height(rolls);
    let removed = 0;

    // storage of graphing structure
    const floor = new Map<Pos, Pos[]>();

    const isOnFloor = (roll: Pos) => floor.has(roll);

    // build graph
    for (const [row, line] of rolls.entries()) {
        for (const [col, spot] of line.entries()) {
            if (spot === '@') {
                const neighbors = getNeighborsFull([row, col], width, height)
                    .filter(cell => Grid.at(rolls, cell) === '@').map(asKey);
                floor.set(asKey([row, col]), neighbors);
            }
        }
    }

    const queue: Pos[] = [...floor.keys()];

    while (queue.length > 0) {
        const current = queue.shift();
        const neighbors = (floor.get(current) ?? []).filter(isOnFloor);

        if (isOnFloor(current) && neighbors.length < 4) {
            floor.delete(current);
            for (const neighbor of neighbors) {
                queue.push(neighbor);
            }
            removed++;
        }
    }

    return removed;
}