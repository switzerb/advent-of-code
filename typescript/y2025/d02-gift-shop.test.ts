import { describe, expect, it } from "vitest";
import {isInvalid, partOne} from "./d02-gift-shop";
import {readInput} from "../lib/utils";

const input = readInput("y2025/inputs/d02.txt");

const example = `
11-22,
95-115,
998-1012,
1188511880-1188511890,
222220-222224,
1698522-1698528,
446443-446449,
38593856-38593862,
565653-565659,
824824821-824824827,
2121212118-2121212124
`;

describe('puzzle solutions', () => {
    it('checks for invalid ids', () => {
      expect(isInvalid(55)).toStrictEqual(true);
      expect(isInvalid(99)).toStrictEqual(true);
      expect(isInvalid(1010)).toStrictEqual(true);
      expect(isInvalid(101)).toStrictEqual(false);
      expect(isInvalid(1188511885)).toStrictEqual(true);
      expect(isInvalid(1188511886)).toStrictEqual(false);
    });

    it('returns an answer for part one', () => {
        expect(partOne(example)).toStrictEqual(1227775554);
        expect(partOne(input)).toStrictEqual(64215794229);
    })
})