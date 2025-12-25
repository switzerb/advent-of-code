import {describe, expect, it} from "vitest";
import {partOne, partTwo} from "./d03-lobby";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d03.txt");

const example = `
987654321111111
811111111111119
234234234234278
818181911112111`;

describe('solutions', () => {
    it('runs part one example', () => {
        expect(partOne(example)).toStrictEqual(357);
    });

    it('runs part one input', () => {
        expect(partOne(input)).toStrictEqual(17427);
    });

    it('runs part two example', () => {
        expect(partTwo(example)).toStrictEqual(3121910778619);
    });

    it('runs part one input', () => {
        expect(partTwo(input)).toStrictEqual(173161749617495);
    });
})