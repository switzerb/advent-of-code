
function parse(input: string) {
    const split = input
        .trim()
        .split("\n\n")
        .map(it => it.split("\n"));

    return {
        ranges: split[0].map(range => range.split("-").map(Number)),
        ingredients: split[1].map(Number)
    };
}

const range = (start:number, end:number) => Array.from({ length: end - start + 1 }, (_, i) => start + i);

export function partOne(input: string) {
    let count = 0;
    const {ranges, ingredients} = parse(input);
    for (const ingredient of ingredients) {
        for (const [start, end] of ranges) {
            if (ingredient >= start && ingredient <= end) {
                count++;
                break;
            }
        }
    }
    return count;
}

export function partTwo(input: string) {
    const {ranges} = parse(input);
    ranges.sort((a, b) => a[0] - b[0]);

    const treeMap = new Map<number, number[]>();
    treeMap.set(ranges[0][0], ranges[0]);

    // build sorted map
    for(const range of ranges) {
        const start = range[0];
        const priorStart = Array.from(treeMap.keys()).at(-1);
        const [,priorEnd] = treeMap.get(priorStart);
        start <= priorEnd
            ? treeMap.set(priorStart, [priorStart ,Math.max(range[1], priorEnd)])
            :treeMap.set(start, range);
    }

    return Array.from(treeMap
        .values())
        .reduce(
            (acc, range) =>
                acc + range[1] - range[0] + 1,
            0);
}