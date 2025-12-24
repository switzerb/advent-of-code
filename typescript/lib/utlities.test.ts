import { describe, it, expect } from 'vitest';
import {toDigits} from "./utils";

describe('toDigits function', () => {
    it('should convert single digit numbers correctly', () => {
        expect(toDigits(0)).toEqual([0]);
        expect(toDigits(5)).toEqual([5]);
        expect(toDigits(9)).toEqual([9]);
    });

    it('should convert multi-digit positive numbers correctly', () => {
        expect(toDigits(12)).toEqual([1, 2]);
        expect(toDigits(355)).toEqual([3, 5, 5]);
        expect(toDigits(1000)).toEqual([1, 0, 0, 0]);
        expect(toDigits(9876)).toEqual([9, 8, 7, 6]);
    });

    it('should handle negative numbers by using their absolute value', () => {
        expect(toDigits(-7)).toEqual([7]);
        expect(toDigits(-42)).toEqual([4, 2]);
        expect(toDigits(-123)).toEqual([1, 2, 3]);
    });

    it('should handle large numbers correctly', () => {
        expect(toDigits(123456789)).toEqual([1, 2, 3, 4, 5, 6, 7, 8, 9]);
        expect(toDigits(1000000000)).toEqual([1, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
    });

    it('should handle edge cases', () => {
        expect(toDigits(0)).toEqual([0]);
        expect(toDigits(-0)).toEqual([0]);

        // Floating point numbers (should only process the integer part)
        expect(toDigits(Math.floor(12.34))).toEqual([1, 2]);
        expect(toDigits(Math.trunc(-56.78))).toEqual([5, 6]);
        expect(toDigits(Math.floor(-56.78))).toEqual([5, 7]);
    });
});