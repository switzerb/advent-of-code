import {asKey, Grid, isEqual, type Pos} from "../lib/grid";


/** [row,col] **/
function drop(pos) {
    return [pos[0] + 1, pos[1]];
}

function splitter(pos) {
    return [ [pos[0], pos[1] - 1], [pos[0], pos[1] + 1]]
}

function buildManifold(grid: string[][]) {
    const manifold = new Set<string>();
    let start = [0,0];
    for(const [row, line] of grid.entries()) {
        for(const [col, cell] of line.entries()) {
            if(cell === 'S') start = [row, col];
            if(cell === '^') manifold.add(asKey([row, col]));
        }
    }
    return {manifold, start};
}

function uniqueBeams(beams: Pos[]): Pos[] {
    return Array.from(
        new Map(beams.map(b => [b.join(','), b])).values()
    );
}

/**
 * Day 7 part two is: build a graph and do a DFS!
 *
 */
export function partOne(input: string) {
    const grid = Grid.to2D(input);
    const splits = new Set<string>();
    const {manifold, start} = buildManifold(grid);
    const height = Grid.height(grid);
    let row = 0;
    let beams  = [start];

    while(row < height) {
        beams = beams.map(drop);
        for(const beam of beams) {
            if(manifold.has(asKey(beam as Pos))) {
                beams = beams.concat(splitter(beam));
                beams = beams.filter(pos => !isEqual(pos, beam));
                beams = uniqueBeams(beams);
                splits.add(asKey(beam as Pos));
            }
        }
        row++;
    }

    return splits.size;
}
