const numericKeypad = keypadMap(["789", "456", "123", "#0A"]);
const directionalKeypad = keypadMap(["#^A", "<v>"]);

function getSequences(
	keypad: string[],
	x1: number,
	y1: number,
	x2: number,
	y2: number,
	currentPath: string[],
	sequences: string[],
) {
	if (x1 === x2 && y1 === y2) {
		sequences.push(currentPath.join(""));
		return;
	}

	const dx = x2 === x1 ? 0 : x2 > x1 ? 1 : -1;
	const dy = y2 === y1 ? 0 : y2 > y1 ? 1 : -1;

	if (dx !== 0 && keypad[x1 + dx][y1] !== "#") {
		const move = dx > 0 ? "v" : "^";
		currentPath.push(move);
		getSequences(keypad, x1 + dx, y1, x2, y2, currentPath, sequences);
		currentPath.pop();
	}
	if (dy !== 0 && keypad[x1][y1 + dy] !== "#") {
		const move = dy > 0 ? ">" : "<";
		currentPath.push(move);
		getSequences(keypad, x1, y1 + dy, x2, y2, currentPath, sequences);
		currentPath.pop();
	}
}

function keypadMap(keypad: string[]): Map<string, string[]> {
	const seqMap: Map<string, string[]> = new Map();

	for (let x1 = 0; x1 < keypad.length; x1++)
		for (let y1 = 0; y1 < keypad[0].length; y1++)
			for (let x2 = 0; x2 < keypad.length; x2++)
				for (let y2 = 0; y2 < keypad[0].length; y2++)
					if (keypad[x1][y1] !== "#" && keypad[x2][y2] !== "#") {
						const path: string[] = [];
						const sequences: string[] = [];
						getSequences(keypad, x1, y1, x2, y2, path, sequences);
						seqMap.set(`${keypad[x1][y1]}${keypad[x2][y2]}`, sequences);
					}
	return seqMap;
}

function moveNumericPad(
	code: string,
	cur: string,
	next: number,
	sequences: string[],
	path = [],
) {
	if (next >= code.length) {
		sequences.push(path.join(""));
		return;
	}

	const potentials = numericKeypad.get(cur + code[next]);
	for (const step of potentials) {
		path.push(`${step}A`);
		moveNumericPad(code, code[next], next + 1, sequences, path);
		path.pop();
	}
}

function codeValue(code: string): number {
	return Number(code.slice(0, code.length - 1));
}

function run(code: string, depth: number): number {
	const potentials: string[] = [];
	const cache: Map<string, number> = new Map();
	moveNumericPad(code, "A", 0, potentials);

	let minPath = Number.MAX_SAFE_INTEGER;
	for (const moves of potentials) {
		const shortest = search(moves, 1, depth, cache);
		if (shortest < minPath) minPath = shortest;
	}
	return minPath;
}

function search(
	seq: string,
	curDepth: number,
	targetDepth: number,
	cache: Map<string, number>,
): number {
	const key = `${seq}+${curDepth.toString()}`;
	if (cache.get(key)) return cache.get(key);

	if (curDepth === targetDepth) return seq.length;

	let p = "A";
	let solution = 0;

	for (const dir of seq) {
		const potentials = directionalKeypad.get(`${p}${dir}`);
		let min = Number.MAX_SAFE_INTEGER;
		for (const next of potentials) {
			const shortest = search(`${next}A`, curDepth + 1, targetDepth, cache);
			if (shortest < min) min = shortest;
		}
		solution += min;
		p = dir;
	}
	cache.set(key, solution);
	return solution;
}

export function partOne(input: string, robots: number) {
	const codes = parse(input);

	return codes.reduce((acc, code) => {
		return acc + run(code, robots) * codeValue(code);
	}, 0);
}

export function partTwo(input: string, robots: number) {
	return partOne(input, robots);
}

export function parse(input: string) {
	return input.trim().split("\n");
}
