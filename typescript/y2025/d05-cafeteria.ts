
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
    const visited = new Set<number>();
    for (const ingredient of ingredients) {
        for (const [start, end] of ranges) {
            if (
                ingredient >= start
                && ingredient <= end
                && !visited.has(ingredient)
            ) {
                visited.add(ingredient);
                count++;
            };
        }
    }
    return count;
}

export function partTwo(input: string) {
    return 0
}