import {describe, expect, it} from "vitest";
import {partOne} from "./d04-printing-department";

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
        expect(partOne()).toStrictEqual(0);
    });
});