import type { Pos } from "../lib/grid";

type Game = {
	a: Pos;
	b: Pos;
	prize: Pos;
};

const COST = {
	a: 3,
	b: 1,
};

export function partOne(games: Game[]) {
	let total = 0;

	for (const { a, b, prize } of games) {
		const [aX, aY] = a;
		const [bX, bY] = b;
		const [pX, pY] = prize;
		/**
		 * Find intersection of two slopes
		 *
		 * Start with defining slopes:
		 * aX * aCount + bX * bCount = pX
		 * aY * aCount + bY * bCount = pY
		 *
		 * Solve for aCount:
		 * bCount = (pX - aX * aCount) / bX
		 * bCount = (pY - aY * aCount) / bY
		 *
		 * (pX - aX * aCount) / bX = (pY - aY * aCount) / bY
		 * bY * (pX - aX * aCount) = bX * (pY - aY * aCount)
		 * bY * pX - bY * aX * aCount = bX * pY - bX * aY * aCount
		 * bY * pX - bX * pY = bY * aX * aCount - bX * aY * aCount
		 * (bY * pX - bX * pY) / (bY * aX - bX * aY) = aCount
		 */
		const aCount = (bY * pX - bX * pY) / (bY * aX - bX * aY);

		const bCount = (pX - aX * aCount) / bX;

		// Solution can't involve partial button presses
		if (!Number.isInteger(aCount) || !Number.isInteger(bCount)) {
			continue;
		}

		total += COST.a * aCount + COST.b * bCount;
	}

	return total;
}

export function partTwo() {
	return 0;
}

export function parse(input: string) {
	function each(input: string): Pos {
		const [, x, y] = /X[=+](\d+),\sY[=+](\d+)/.exec(input) ?? [];

		return [Number(x), Number(y)];
	}

	return input.split("\n\n").map((text) => {
		const [A, B, P] = text.split("\n");

		return {
			a: each(A),
			b: each(B),
			prize: each(P),
		};
	});
}
