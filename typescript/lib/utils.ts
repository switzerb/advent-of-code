import fs from "node:fs";
import {join,dirname} from "node:path";
import { fileURLToPath } from 'node:url';
import {isArrayEqual} from "radashi";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

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
    if (arrays.length < 2) return true;
    return arrays.every(array => isArrayEqual(arrays[0], array));
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
