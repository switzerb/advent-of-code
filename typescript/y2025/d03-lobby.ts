import { assert} from 'radashi';

function parse(input: string) {
    const whatever = input.trim();

    return input.trim().split("\n");
}

function getMax(bank: string, cellsRemaining: number) {
    assert(bank.length > 0, "Bank must not be empty");
    let max = Number(bank[0]);
    let idx = 0;

    const last = bank.length - cellsRemaining;

    for(let i = 1; i < last; i++) {
        if(Number(bank[i]) > max) {
            max = Number(bank[i]);
            idx = i;
        }
    }

    return [max, idx];
}

function recurse(bank: string, remainingCells: number) {
    const [max, idx] = getMax(bank, remainingCells);
    if(remainingCells === 0) return max;

    return ((10 ** remainingCells) * max) + recurse(bank.substring(idx + 1), remainingCells - 1);
}

export function partOne(input: string) {
    const banks = parse(input);
    return banks
        .map(bank => recurse(bank, 1))
        .reduce((a,b) => a + b, 0);
}

export function partTwo(input: string) {
    const banks = parse(input);
    return banks
        .map(bank => recurse(bank, 11))
        .reduce((a,b) => a + b, 0);
}