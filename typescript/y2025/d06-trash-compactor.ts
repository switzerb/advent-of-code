
function parse(input: string) {
    const parsed = input
        .trim()
        .split("\n")
        .map(it => it.trim().split(/\s+/));
    return {
        operations: parsed.pop(),
        nums: parsed.map(it => it.map(Number))
    };
}

function multiply(nums: number[]) {
    return nums.reduce((a, b) => a * b, 1);
}

function add(nums: number[]) {
    return nums.reduce((a, b) => a + b, 0);
}

export function partOne(input: string) {
    const {nums, operations} = parse(input);
    const collected = [];

    for(let i = 0; i < operations.length; i++) {
        const temp = {
            ops: operations[i],
            ints: []
        };
        for(let j = 0; j < nums.length; j++) {
            temp.ints.push(nums[j][i]);
        }
        collected.push(temp);
    }

    return collected
        .map(it => it.ops === "*" ? multiply(it.ints) : add(it.ints))
        .reduce((a, b) => a + b, 0);
}

export function partTwo(input: string) {
    const parsed = input.split("\n");
    const ops = parsed.pop();
    const results = [];
    let digits = [];
    let current = -1;

    while(Math.abs(current) < parsed[0].length) {
        while(true) {
            digits.push(Number(parsed.map(it => it.at(current)).join('')));
            const op = ops.at(current);
            if (op === '+' || op === '*') {
                results.push(op === '*' ? multiply(digits) : add(digits));
                digits = [];
                break;
            }
            current--;
        }
        current = current - 2;
    }

    return results.reduce((a, b) => a + b, 0);
}