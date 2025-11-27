import { getTarget, to2DGrid } from "./grid";

const run = (grid, movements) => {
	let [bot_r, bot_c] = getTarget(grid, "@");

	movements.map(([dr, dc]) => {
		const canMove = (row: number, col: number) => {
			const block: string = grid[row][col];

			if (".#".includes(block)) {
				return block === ".";
			}

			if (canMove(row + dr, col + dc)) {
				grid[row + dr][col + dc] = block;
				return true;
			}
			return false;
		};

		if (canMove(bot_r, bot_c)) {
			grid[bot_r][bot_c] = ".";
			bot_r = bot_r + dr;
			bot_c = bot_c + dc;
		}
	});

	return grid;
};

export function partOne(input: string) {
	const [grid, commands] = parse(input);
	const result = run(grid, commands);

	return result
		.flatMap((row, y) => row.map((c, x) => ("O".includes(c) ? 100 * y + x : 0)))
		.reduce((sum, v) => sum + v);
}

const scaleX = (grid) => {
	return grid.map((row) =>
		row.flatMap((c) => ({ O: ["[", "]"], "@": ["@", "."] })[c] ?? [c, c]),
	);
};

const run_again = (grid, commands) => {
	let py = grid.findIndex((row) => row.includes("@"));
	let px = grid[py].indexOf("@");

	commands.map(([dy, dx]) => {
		const testMove = (y, x) => {
			const c = grid[y][x];
			if (".#".includes(c)) return c === ".";
			if (dx || c === "@") return testMove(y + dy, x + dx);
			return testMove(y + dy, x + (c === "[" ? 1 : -1)) && testMove(y + dy, x);
		};

		const move = (r, c) => {
			const block = grid[r][c];
			if ("." === block) return;
			if (dx || block === "@") {
				move(r + dy, c + dx);
				grid[r + dy][c + dx] = block;
			} else {
				const x2 = c + (block === "[" ? 1 : -1);
				move(r + dy, x2);
				move(r + dy, c);
				grid[r + dy][c] = block;
				grid[r + dy][x2] = block === "[" ? "]" : "[";
				grid[r][x2] = ".";
			}
		};

		if (testMove(py, px)) {
			move(py, px);
			grid[py][px] = ".";
			py = py + dy;
			px = px + dx;
		}
	});

	return grid;
};

export function partTwo(input) {
	const [grid, commands] = parse(input);
	const scaled = scaleX(grid);
	const result = run_again(scaled, commands);
	return result
		.flatMap((row, y) =>
			row.map((c, x) => ("[O".includes(c) ? 100 * y + x : 0)),
		)
		.reduce((sum, v) => sum + v);
}

const CMD = { "^": [-1, 0], ">": [0, 1], v: [1, 0], "<": [0, -1] };

const parse = (input: string) => {
	const [map, cmds] = input.split("\n\n");
	return [to2DGrid(map), [...cmds.split("\n").join("")].map((c) => CMD[c])];
};
