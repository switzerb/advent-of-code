import fs from "node:fs";
import path from "node:path";
import { describe, expect, it } from "vitest";
import { partOne } from "./d25-code-chronicle";

const example = `
#####
.####
.####
.####
.#.#.
.#...
.....

#####
##.##
.#.##
...##
...#.
...#.
.....

.....
#....
#....
#...#
#.#.#
#.###
#####

.....
.....
#.#..
###..
###.#
###.#
#####

.....
.....
.....
#....
#.#..
#.#.#
#####
`;

const file = fs.readFileSync(
	path.resolve(__dirname, "./inputs/d25.txt"),
	"utf8",
);

describe("part one", () => {
	it("runs", () => {
		expect(partOne(example)).toStrictEqual(3);
		expect(partOne(file)).toStrictEqual(0);
	});
});
