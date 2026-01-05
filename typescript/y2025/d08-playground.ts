import {PQueue} from "../lib/priority-queue";
import {UnionFind} from "../lib/union-find";

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

function getTop3(arr: number[]) {
    return arr.toSorted((a,b) => b - a).splice(0,3);
}

function distanceQueue(boxes: Point3D[], size?: number) {
    const pq = new PQueue<Key>(
        (i: Key,j: Key) => (i.distance < j.distance),
        1000
    );

    for (let i = 0; i < boxes.length; i++) {
        for(let j = i + 1; j < boxes.length; j++) {
            const distance = calcDistance(boxes[i], boxes[j]);
            pq.insert({distance, idx1: i, idx2: j});
            if(size && pq.size() > size) {
                pq.pop();
            }
        }
    }
    return pq;
}

export function partOne(input: string, pairs = 1000) {
    const boxes : Point3D[] = parse(input);
    const queue = distanceQueue(boxes, pairs);
    let count = 0;

    const components = new UnionFind(boxes.length);

    while(count < pairs) {
        const boxesToConnect = queue.pop();
        components.union(boxesToConnect.idx1, boxesToConnect.idx2);
        count++;
    }

    return getTop3(components.sizes())
        .reduce((acc, it) => acc * it, 1);
}

export function partTwo(input: string) {
    const boxes : Point3D[] = parse(input);
    const components = new UnionFind(boxes.length);
    let count = boxes.length;
    const distances : Key[] = [];

    for (let i = 0; i < boxes.length; i++) {
        for(let j = i + 1; j < boxes.length; j++) {
            const distance = calcDistance(boxes[i], boxes[j]);
            distances.push({distance, idx1: i, idx2: j});
        }
    }

    distances.sort((a,b) => b.distance - a.distance);

    while(count >= 1) {
        const boxesToConnect = distances.pop();
        components.union(boxesToConnect.idx1, boxesToConnect.idx2);
        count = components.count();
        if(count === 1) {
            const [p1,p2] = [boxes[boxesToConnect.idx1], boxes[boxesToConnect.idx2]];
            return p1[0] * p2[0];
        }
    }

    throw new Error("No solution found");
}