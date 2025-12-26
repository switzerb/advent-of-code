import {describe, expect, it} from "vitest";
import {partOne} from "./d04-printing-department";
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
    it('runs part one example', () => {
        expect(partOne(example)).toStrictEqual(13);
        expect(partOne(input)).toStrictEqual(1424);
    });
});