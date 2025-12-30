import {describe, expect, it} from "vitest";
import {partOne, partTwo} from "./d06-trash-compactor";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d06.txt");

const example = `123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +  `;

describe('solutions', () => {
    it('runs part one', () => {
        expect(partOne(example)).toStrictEqual(4277556);
        expect(partOne(input)).toStrictEqual(4805473544166);
    });

    it('runs part two', () => {
        expect(partTwo(example)).toStrictEqual(3263827);
        expect(partTwo(input)).toStrictEqual(8907730960817); //3902496504
    })
});