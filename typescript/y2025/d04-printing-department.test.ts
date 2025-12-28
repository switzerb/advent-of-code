import {describe, expect, it} from "vitest";
import {partOne, partTwo} from "./d04-printing-department";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d04.txt");

const example = `
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.
`;

describe('solutions', () => {
    it('runs part one', () => {
        expect(partOne(example)).toStrictEqual(13);
        expect(partOne(input)).toStrictEqual(1424);
    });

    it('runs part two', () => {
        expect(partTwo(example)).toStrictEqual(43);
        expect(partTwo(input)).toStrictEqual(8727);
    })
});