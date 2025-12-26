import fs from "node:fs";
import {join} from "node:path";
import {isArrayEqual} from "radashi";

/**
 * Converts a number to an array of its individual digits
 * using mathematical operations only (no string conversion)
 * @param num - The number to convert
 * @returns Array of individual digits
 *
 * Examples:
 * - 12 becomes [1, 2]
 * - 355 becomes [3, 5, 5]
 */
export function toDigits(num: number): number[] {
    if (num === 0) return [0];
    const absNum = Math.abs(num);
    const digits: number[] = [];

    let n = absNum;

    while (n > 0) {
        digits.unshift(n % 10);
        n = Math.floor(n / 10);
    }

    return digits;
}

export function allArraysEqual<T>(...arrays: T[][]): boolean {
    if (arrays.length < 2) {
        return true; // If there are 0 or 1 arrays, they're considered equal
    }

    const firstArray = arrays[0];

    // Check if all arrays are equal to the first array
    return arrays.every(array => isArrayEqual(firstArray, array));
}

/** Shamelessly stolen from
 *. https://github.com/ArrayKnight/advent-of-code/blob/main/src/utils.ts#L163
 *  because it is a very handy utility
 */
export const TimeUtils = {
    log: <T>(callback: () => T, label = "runtime") => {
        console.time(label);

        const result = callback();

        console.timeEnd(label);

        return result;
    },
};

export function readInput(path: string) {
    return fs.readFileSync(join(__dirname, `../${path}`), "utf8");
}
