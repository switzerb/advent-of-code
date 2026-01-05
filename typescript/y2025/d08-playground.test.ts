import {describe, expect, it} from "vitest";
import {partOne, partTwo} from "./d08-playground";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d08.txt");

const example = `162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689`;

describe('solutions', () => {
    it('runs part one', () => {
        expect(partOne(example, 10)).toStrictEqual(40);
        expect(partOne(input, 1000)).toStrictEqual(112230);
    });

    it('runs part two', () => {
        expect(partTwo(example)).toStrictEqual(25272);
        expect(partTwo(input)).toStrictEqual(2573952864);
    })

});