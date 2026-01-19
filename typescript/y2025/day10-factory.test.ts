import {describe, expect, it} from "vitest";
import {minButtonPresses, minJoltagePresses, parse, partOne, partTwo, incrementJoltage} from "./d10-factory";
import {readInput} from "../lib/utils";

const example = `[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}`;

const input = readInput("y2025/inputs/d10.txt");

const machine = {
    target: '.##.',
    buttons: [[3], [1,3], [2], [2,3], [0,2], [0,1]],
    joltage: [3, 5, 4, 7]
};

const machine2 ={
    target: '...#.',
    buttons: [ [ 0, 2, 3, 4 ], [ 2, 3 ], [ 0, 4 ], [ 0, 1, 2 ], [ 1, 2, 3, 4 ] ],
    joltage: [ 7, 5, 12, 7, 2 ]
}
const machine3 = {
    target: '.###.#',
    buttons: [ [ 0, 1, 2, 3, 4 ], [ 0, 3, 4 ], [ 0, 1, 2, 4, 5 ], [ 1, 2 ] ],
    joltage: [ 10, 11, 11, 5, 10, 5 ]
}


describe('parsing', () => {
    it('parses machine input correctly', () => {
        const machines = parse(example);

        expect(machines.length).toBe(3);

        expect(machines[0]).toEqual({
            target: '.##.',
            buttons: [[3], [1,3], [2], [2,3], [0,2], [0,1]],
            joltage: [3, 5, 4, 7]
        });

        expect(machines[1]).toEqual({
            target: '...#.',
            buttons: [[0,2,3,4], [2,3], [0,4], [0,1,2], [1,2,3,4]],
            joltage: [7, 5, 12, 7, 2]
        });

        expect(machines[2]).toEqual({
            target: '.###.#',
            buttons: [[0,1,2,3,4], [0,3,4], [0,1,2,4,5], [1,2]],
            joltage: [10, 11, 11, 5, 10, 5]
        });
    });
});

describe('minButtonPresses', () => {
    it('finds minimum button presses using BFS', () => {
        // Optimal path might be pressing [1,3] then [2,3] = 2 steps
        expect(minButtonPresses(machine)).toBe(2);
        expect(minButtonPresses(machine2)).toBe(3);
        expect(minButtonPresses(machine3)).toBe(2);
    });

    it('returns 0 if already at target', () => {
        const machine = {
            target: '....',
            buttons: [[0], [1], [2]],
            joltage: []
        };

        expect(minButtonPresses(machine)).toBe(0);
    });

    it('returns 1 for single button press', () => {
        const machine = {
            target: '##',
            buttons: [[0, 1], [0], [1]],
            joltage: []
        };

        // Pressing button [0,1] reaches "##" in one step
        expect(minButtonPresses(machine)).toBe(1);
    });

    it('finds minimum across multiple paths', () => {
        const machine = {
            target: '#.#',
            buttons: [[0], [1], [2], [0, 2]],
            joltage: []
        };

        // Could press [0] then [2] = 2 steps
        // OR press [0, 2] = 1 step
        expect(minButtonPresses(machine)).toBe(1);
    });
});

describe('incrementJoltage', () => {
    it('increments joltage at specified positions', () => {
        const joltage = [0, 1, 2, 3];
        const button = [1, 3];
        const result = incrementJoltage(joltage, button);

        expect(result).toEqual([0, 2, 2, 4]);
        expect(joltage).toEqual([0, 1, 2, 3]);
    });

    it('handles empty button positions', () => {
        const joltage = [1, 2, 3];
        const button: number[] = [];
        const result = incrementJoltage(joltage, button);

        expect(result).toEqual([1, 2, 3]);
    });

    it('handles single position', () => {
        const joltage = [0, 0, 0];
        const button = [1];
        const result = incrementJoltage(joltage, button);

        expect(result).toEqual([0, 1, 0]);
    });

    it('handles multiple increments on same array', () => {
        let joltage = [0, 0, 0, 0];
        joltage = incrementJoltage(joltage, [1, 3]); // [0, 1, 0, 1]
        joltage = incrementJoltage(joltage, [0, 1]); // [1, 2, 0, 1]
        joltage = incrementJoltage(joltage, [2]);    // [1, 2, 1, 1]

        expect(joltage).toEqual([1, 2, 1, 1]);
    });

});

describe('minJoltagePresses', () => {
    it('returns 0 if already at target', () => {
        expect(minJoltagePresses({
            target: '....',
            buttons: [[0], [1]],
            joltage: [0, 0, 0]
        })).toBe(0);
    });

    it('handles multiple presses of same button', () => {
        expect(minJoltagePresses({
            target: '##',
            buttons: [[0], [1]],
            joltage: [3, 2]
        })).toBe(5);
    });

    // Could press [0] twice and [1] twice = 4 presses OR press [0,1] twice = 2 presses
    it('finds even with multiple paths possible', () => {
        expect(minJoltagePresses({
            target: '##',
            buttons: [[0], [1], [0, 1]],
            joltage: [2, 2]
        })).toBe(2);
    });

    it('finds minimum presses to reach joltage target', () => {
        expect(minJoltagePresses(machine)).toStrictEqual(10);
        expect(minJoltagePresses(machine2)).toStrictEqual(12);
        expect(minJoltagePresses(machine3)).toStrictEqual(11);
    });
});

describe('solutions', () => {
    it('runs part one', () => {
        expect(partOne(example)).toStrictEqual(7);
        expect(partOne(input)).toStrictEqual(571);
    });

    it('runs part two', () => {
        expect(partTwo(example)).toStrictEqual(33);
        // expect(partTwo(input)).toStrictEqual(0);
    });
});