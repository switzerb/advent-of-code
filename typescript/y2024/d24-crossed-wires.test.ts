import fs from "node:fs";
import path from "node:path";
import { describe, expect, it } from "vitest";
import { partOne, partTwo } from "./d24-crossed-wires";
import func from "./d24_ray";
import { TimeUtils } from "../lib/utils";

const smallEx = `
x00: 1
x01: 1
x02: 1
y00: 0
y01: 1
y02: 0

x00 AND y00 -> z00
x01 XOR y01 -> z01
x02 OR y02 -> z02
`;

const bigEx = `
x00: 1
x01: 0
x02: 1
x03: 1
x04: 0
y00: 1
y01: 1
y02: 1
y03: 1
y04: 1

ntg XOR fgs -> mjb
y02 OR x01 -> tnw
kwq OR kpj -> z05
x00 OR x03 -> fst
tgd XOR rvg -> z01
vdt OR tnw -> bfw
bfw AND frj -> z10
ffh OR nrd -> bqk
y00 AND y03 -> djm
y03 OR y00 -> psh
bqk OR frj -> z08
tnw OR fst -> frj
gnj AND tgd -> z11
bfw XOR mjb -> z00
x03 OR x00 -> vdt
gnj AND wpb -> z02
x04 AND y00 -> kjc
djm OR pbm -> qhw
nrd AND vdt -> hwm
kjc AND fst -> rvg
y04 OR y02 -> fgs
y01 AND x02 -> pbm
ntg OR kjc -> kwq
psh XOR fgs -> tgd
qhw XOR tgd -> z09
pbm OR djm -> kpj
x03 XOR y03 -> ffh
x00 XOR y04 -> ntg
bfw OR bqk -> z06
nrd XOR fgs -> wpb
frj XOR qhw -> z04
bqk OR frj -> z07
y03 OR x01 -> nrd
hwm AND bqk -> z03
tgd XOR rvg -> z12
tnw OR pbm -> gnj
`;

const file = fs.readFileSync(
	path.resolve(__dirname, "./inputs/d24.txt"),
	"utf8",
);

function process(input: string) {
	const [inputs, outputs] = input.split("\n\n");

	return {
		inputs: Object.fromEntries(
			inputs.split("\n").map((line) => {
				const [key, value] = line.split(": ");

				return [key, Number(value)] as [string, number];
			}),
		),
		outputs: outputs.split("\n").map((line) => {
			const [condition, output] = line.split(" -> ");
			const [aId, operand, bId] = condition.split(" ");

			return {
				condition: [aId, operand, bId] as [string, string, string],
				output,
			};
		}),
	};
}

const alt = process(file);

describe("part one", () => {
	it("runs", () => {
		expect(partOne(smallEx)).toStrictEqual(4);
		expect(partOne(bigEx)).toStrictEqual(2024);
		expect(partOne(file)).toStrictEqual(51745744348272); // 51745700828256 low - 51745744348272
	});
});

describe("part two", () => {
	it("runs", () => {
		expect(partTwo(file)).toStrictEqual(0);
	});

	it("should pass with me stealing ray's hard work", () => {
		expect(TimeUtils.log(() => func(alt))).toBe(
			"bfq,bng,fjp,hkh,hmt,z18,z27,z31",
		);
	});
});
