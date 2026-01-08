import type {Pos} from "../lib/grid";

function parse(input: string) : Pos[] {
    return input
        .trim()
        .split("\n")
        .map(it => it.split(",").map(Number)) as Pos[]
}

export const calcArea = (p1: Pos, p2: Pos) => (Math.abs(p1[0] - p2[0]) + 1) * (Math.abs(p1[1] - p2[1]) + 1)

export const zip = (p1: Pos[], p2:Pos[]) => {
    const zipped = [];
    for(let i = 0; i < p1.length; i++) {
        for(let j = 0; j < p2.length; j++) {
            zipped.push([p1[i], p2[j]]);
        }
    }
    return zipped;
}

export const consecutivePairs = (coords: Pos[]): [Pos, Pos][] => {
    return coords.slice(0, -1).map((coord, i) => [coord, coords[i + 1]]);
}

export const maxDistance = ([p1, p2]: [Pos, Pos]) => {
    return {point: [p1, p2], distance: Math.max(
        Math.abs(p1[0] - p2[0]),
        Math.abs(p1[1] - p2[1])
    )};
}

export function partOne(input: string) {
    const squares = parse(input);
    const pairs = zip(squares, squares.slice(1));
    const areas = pairs.map(it => calcArea(it[0], it[1]));
    areas.sort((a,b) => b - a);
    return areas[0];
}

export function partTwo(input: string) {
    const squares = parse(input);
    const pairs = consecutivePairs(squares);
    const something = pairs.map(pair => maxDistance(pair)).reduce(
        (a,b) =>
            a.distance > b.distance ? a : b);

    console.log(something);

    return 0;
}