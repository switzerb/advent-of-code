function calcHeight(item: string[]) {
	const heights = [];
	for (const it of item) {
		const pins = it[0].match(/#{5}/) ? it.slice(1) : it.slice(0, it.length - 1);
		const height = [];
		for (let c = 0; c < pins[0].length; c++) {
			const col = Array.from(pins).map((line) => line[c]);
			height.push(col.filter((c) => c === "#").length);
		}
		heights.push(height);
	}
	return heights;
}

export function partOne(input: string) {
	const [locks, keys] = parse(input);
	const lockHeights = calcHeight(locks);
	const keyHeights = calcHeight(keys);
	const width = 5;
	const height = 7;
	let count = 0;

	for (const lock of lockHeights) {
		for (const key of keyHeights) {
			let fits = true;
			for (let i = 0; i < width; i++) {
				if (lock[i] + key[i] > height - 2) {
					fits = false;
				}
			}
			if (fits) count += 1;
		}
	}

	return count;
}

function parse(input: string) {
	const schematic = input
		.trim()
		.split("\n\n")
		.map((block) => block.trim().split("\n"));
	return schematic.reduce(
		(acc, it) =>
			it[0].match(/#{5}/)
				? [acc[0].concat([it]), acc[1]]
				: [acc[0], acc[1].concat([it])],
		[[], []],
	);
}
