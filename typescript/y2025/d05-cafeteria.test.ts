
import {describe, expect, it} from "vitest";
import {partOne, partTwo} from "./d05-cafeteria";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d05.txt");

const example = `
3-5
10-14
16-20
12-18

1
5
8
11
17
32
`;

describe('solutions', () => {
    it('runs part one', () => {
        expect(partOne(example)).toStrictEqual(3);
        expect(partOne(input)).toStrictEqual(607);
    });

    it('runs part two', () => {
        expect(partTwo(example)).toStrictEqual(14);
        expect(partTwo(input)).toStrictEqual(342433357244012); // 311541667160941 (low)
    })
});