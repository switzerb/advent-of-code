import {asKey, Grid, isEqual, type Pos} from "../lib/grid";

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

export function partOne(input: string) {
    const grid = Grid.to2D(input);
    const splits = new Set<string>();
    const {manifold, start} = buildManifold(grid);
    const height = Grid.height(grid);
    let row = 0;
    const beams  = new Set<number>([start[1]]);

    while(row < height) {
        for(const col of beams) {
            if(manifold.has(asKey([row, col] as Pos))) {
                beams
                    .add(col - 1)
                    .add(col + 1)
                    .delete(col);
                splits.add(asKey([row, col] as Pos));
            }
        }
        row = row + 2;
    }

    return splits.size;
}

export function partTwo(input: string) {
    const grid = Grid.to2D(input);
    const {manifold, start} = buildManifold(grid);
    const height = Grid.height(grid);
    let row = 0;
    /** key = column, value = count of beams in that column **/
    const beams  = new Map<number, number>([[start[1],1]]);

    while(row < height) {
        for(const [col, count] of beams.entries()) {
            if(manifold.has(asKey([row, col] as Pos))) {
                beams.set(col - 1, (beams.get(col - 1) ?? 0) + count);
                beams.set(col + 1, (beams.get(col + 1) ?? 0) + count);
                beams.delete(col);
            }
        }
        row = row + 2;
    }

    return Array.from(beams.values()).reduce((a,b) => a + b, 0);
}
