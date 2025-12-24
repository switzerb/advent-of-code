import { reduce } from "@certes/list";

const DIAL = 100;
const START = 50;

const mod = (a: number, n: number) => ((a % n) + n) % n;

function rotate(direction: "L" | "R", steps: number, position: number) {
	const turn = direction === "L" ? position - steps : position + steps;
	return mod(turn, DIAL);
}

const getInstruction = (instruction: string): ["L" | "R", number] => [
	instruction.at(0) as "L" | "R",
	Number.parseInt(instruction.substring(1), 10),
];

function countStopsOnZero([position, count]: number[], instruction: string) {
	const [dir, steps] = getInstruction(instruction);
	const current = rotate(dir, steps, position);
	return current === 0 ? [current, count + 1] : [current, count];
}

function countPassesOverZero([position, count]: number[], instruction: string) {
	const [dir, steps] = getInstruction(instruction);
	const rotations = Math.floor(steps / DIAL);
	count += rotations;
	const current = rotate(dir, steps, position);
	if (position !== 0) {
		if (
			current === 0 ||
			(dir === "R" && current < position) ||
			(dir === "L" && current > position)
		) {
			count++;
		}
	}
	return [current, count];
}

export function partOne(input: string[], start: number = START) {
	const [, count] = reduce(countStopsOnZero)([start, 0])(input);
	return count;
}

export function partTwo(input: string[], start: number = START) {
	const [, count] = reduce(countPassesOverZero)([start, 0])(input);
	return count;
}
