import fs from "node:fs";
import path from "node:path";

export function partOne(input: string) {
	const { values, gates } = parse(input);

	while (gates.length > 0) {
		gates.forEach((gate, i) => {
			const [first, op, second, _, result] = gate
				.split(" ")
				.map((l) => l.trim());
			if (values.get(first) !== undefined && values.get(second) !== undefined) {
				if (op === "AND") {
					values.set(result, values.get(first) && values.get(second));
				} else if (op === "OR") {
					values.set(result, values.get(first) || values.get(second));
				} else {
					values.set(result, values.get(first) ^ values.get(second));
				}
				gates.splice(i, 1);
			}
		});
	}

	const outputs = Array.from(values.keys())
		.filter((key) => key.startsWith("z"))
		.sort((a, b) => b.localeCompare(a));

	let binary = "";
	for (const key of outputs) {
		binary += values.get(key);
	}
	return Number.parseInt(binary, 2);
}

export function partTwo(input: string) {
	const { values, gates } = parse(input);
	const nodes = new Map();
	const edges = [];
	const output = [];

	// build all nodes
	for (const [key, _] of values) {
		nodes.set(key, "");
	}
	for (const gate of gates) {
		const [first, op, second, _, result] = gate.split(" ").map((l) => l.trim());
		if (!nodes.has(first)) nodes.set(first, "");
		if (!nodes.has(second)) nodes.set(second, "");
		nodes.set(result, op);
	}

	// build edges
	for (const gate of gates) {
		const [first, op, second, _, result] = gate.split(" ").map((l) => l.trim());
		edges.push(`${first} -> ${result} [ ]`);
		edges.push(`${second} -> ${result} [ ]`);
	}

	// draw output
	for (const [k, v] of nodes) {
		output.push(`${k} [ label = "${v} ${k}" ]`);
	}

	const out = `digraph {\n ${output.join("\n")} \n\n ${edges.join("\n")}}`;

	fs.writeFile(path.resolve(__dirname, "./d24.txt"), out, (_) => {});
	console.log(output.join("\n"));
	console.log(edges.join("\n"));
	return 0;
}

const parse = (input: string) => {
	const values = new Map();
	const [inputs, gates] = input
		.trim()
		.split("\n\n")
		.map((each) => each.split("\n"));

	for (const v of inputs) {
		const [key, val] = v.split(":").map((it) => it.trim());
		values.set(key, Number(val));
	}

	return {
		values,
		gates,
	};
};
