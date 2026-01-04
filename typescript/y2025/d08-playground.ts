import {PQueue} from "../lib/pq";

type Point3D = [x: number, y: number, z: number];
type Key = { distance: number, idx1: number, idx2: number}

function parse(input: string): Point3D[] {
    return input.trim().split("\n").map(it => {
        return it.trim().split(",").map(Number) as Point3D;
    });
}

function calcDistance([x1,y1,z1]: Point3D, [x2,y2,z2]: Point3D) : number {
    return Math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2 + (z1 - z2) ** 2);
}

export function partOne(input: string, pairs = 1000) {
    const boxes : Point3D[] = parse(input);
    const queue = new PQueue<Key>(
        (i: Key,j: Key) => (i.distance > j.distance),
        pairs
    );

    for (let i = 0; i < boxes.length; i++) {
        for(let j = i + 1; j < boxes.length; j++) {
            const distance = calcDistance(boxes[i], boxes[j]);
            queue.insert({distance, idx1: i, idx2: j});
        }
    }

    console.log(queue.popMax());

    return 0;
}