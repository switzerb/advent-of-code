import { describe, expect, it } from "vitest";
import { partOne, partTwo } from "./d01-secret-entrance";
import { readInput } from "../lib/utils";

const data = readInput("y2025/inputs/d01.txt");

const example_input = [
	"L68",
	"L30",
	"R48",
	"L5",
	"R60",
	"L55",
	"L1",
	"L99",
	"R14",
	"L82",
];

const ex2 = ["L6", "R6"];

const input = data.split("\n");

describe("solutions", () => {
	it("part one runs", () => {
		expect(partOne(example_input)).toStrictEqual(3);
	});

	it("returns an answer for part one", () => {
		expect(partOne(input)).toStrictEqual(1139);
	});

	it("part two runs", () => {
		expect(partTwo(example_input)).toStrictEqual(6);
	});

	it("part two example 2", () => {
		expect(partTwo(ex2, 6)).toStrictEqual(1);
	});

	it("part two example 3", () => {
		expect(partTwo(["L200"], 0)).toStrictEqual(2);
	});

	it("returns an answer for part two", () => {
		expect(partTwo(input)).toStrictEqual(6684);
	});

});
