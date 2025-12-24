import fs from "node:fs";
import {join} from "node:path";

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

export function readInput(path: string) {
    return fs.readFileSync(join(__dirname, `../${path}`), "utf8");
}
