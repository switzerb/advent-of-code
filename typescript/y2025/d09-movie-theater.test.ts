import {describe, expect, it} from "vitest";
import {calcArea, partOne, partTwo} from "./d09-movie-theater";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d09.txt");

const example = `7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3`;

describe('solutions', () => {
    it('runs part one', () => {
        expect(partOne(example)).toStrictEqual(50);
        expect(partOne(input)).toStrictEqual(4748985168);
    });

    it('calculates area', () => {
        expect(calcArea([2,5], [9,7])).toStrictEqual(24);
        expect(calcArea([7,1], [11,7])).toStrictEqual(35);
        expect(calcArea([2,5], [11,1])).toStrictEqual(50);
    })

    it('runs part two', () => {
        expect(partTwo(example)).toStrictEqual(0);
    })

});