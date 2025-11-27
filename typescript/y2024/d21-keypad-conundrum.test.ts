import { describe, expect, it } from "vitest";
import { partOne, partTwo } from "./d21-keypad-conundrum";

const example = `
029A
980A
179A
456A
379A
`;

const input = `
671A
826A
670A
085A
283A
`;

describe("part one", () => {
	it("runs", () => {
		expect(partOne("029A", 3)).toStrictEqual(1972);
		expect(partOne(example, 3)).toStrictEqual(126384);
		expect(partOne(input, 3)).toStrictEqual(182844); // 184316 - 177480
	});
});

describe("part two", () => {
	it("runs", () => {
		expect(partTwo(input, 26)).toStrictEqual(226179529377982);
	});
});
