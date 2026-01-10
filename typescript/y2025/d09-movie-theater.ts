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

export const findPoint = (
    boxes: Pos[], 
    predicate: (point: Pos) => boolean,
    startIndex: number | undefined,
    fromEnd = false
): { point: Pos; index: number } | null => {
    const defaultStart = fromEnd ? boxes.length - 1 : 0;
    const start = startIndex ?? defaultStart;
    if (fromEnd) {
        for (let i = start; i >= 0; i--) {
            if (predicate(boxes[i])) {
                return { point: boxes[i], index: i };
            }
        }
    } else {
        for (let i = start; i < boxes.length; i++) {
            if (predicate(boxes[i])) {
                return { point: boxes[i], index: i };
            }
        }
    }
    return null;
}

export function partOne(input: string) {
    const squares = parse(input);
    const pairs = zip(squares, squares.slice(1));
    const areas = pairs.map(it => calcArea(it[0], it[1]));
    areas.sort((a,b) => b - a);
    return areas[0];
}

export function partTwo(input: string) {
    const boxes = parse(input);
    const magicTop =  [94901, 48488] as Pos;
    const magicBottom = [94901, 50265] as Pos;
    let topRect = 0;
    let bottomRect = 0;

    const topBox = findPoint(boxes, (point) => point[0] <= magicTop[0], boxes.length - 1, true);

    if(topBox) {
        const yResult = findPoint(boxes, (point) => point[1] >= topBox.point[1], topBox.index - 1, true);
        topRect = calcArea(magicTop, yResult.point); // 1441310195
    }

    /**
     * In order from the beginning, find the first box that has an x coordinate
     * that is smaller or equal to the magic bottom x coordinate.
     */
    const bottomBox = findPoint(boxes, (point) => point[0] <= magicBottom[0], 0);

    if(bottomBox) {
        const yResult = findPoint(boxes, (point) => point[1] <= bottomBox.point[1], bottomBox.index + 1);
        bottomRect = calcArea(magicBottom, yResult.point);
    } // 1708880105

    console.log(bottomRect, topRect);
    console.log(Math.max(topRect, bottomRect));
    return 0;
}