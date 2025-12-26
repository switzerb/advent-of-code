import {chunk, collect, filter, range} from "@certes/lazy";
import {allArraysEqual} from "../lib/utils";
import {pipe} from "@certes/composition";

type Range = [number, number];

const parse = (input: string) : Range[] => input
        .split(",")
        .map(it => it
            .split("-")
            .map(it => Number(it.trim().replace('\n','')))
        ).map(it => it as Range);

export function everyItemEqual(digits: string) {
    if(digits.length < 2) return false;
    return digits.split("").every(it => it === digits[0]);
}

export function isInvalid(n: number) {
    const digits = n.toString();
    if (digits.length % 2 !== 0) return false;
    if (everyItemEqual(digits)) return true;
    const mid = digits.length / 2;
    const firstHalf = digits.substring(0, mid);
    const secondHalf = digits.substring(mid);

    return firstHalf === secondHalf;
}

export function isMoreInvalid(n: number) {
    const digits = n.toString();
    if(everyItemEqual(digits)) return true;
    const options = collect(range(2, Math.floor(digits.length / 2)));
    for(const option of options) {
        const arrays = collect(chunk(option)(digits));
        const isEqual = allArraysEqual(...arrays);
        if(isEqual) return true;
    }
    return false;
}

export function sumOfInvalidIds(items: number[], isInvalid: (n: number) => boolean){
    const invalidIds =  pipe(
        filter((n: number) => isInvalid(n)),
        collect,
    )(items);
    return invalidIds.reduce((a: number,b: number) => a+b, 0)
}

const buildRanges = ([start, end]: Range) => range(start, end);

export function partOne(input: string) {
    return parse(input)
        .map(buildRanges)
        .map((items: number[]) => sumOfInvalidIds(items, isInvalid))
        .reduce((a:number,b:number) => a+b,0);
}


export function partTwo(input: string) {
    return parse(input)
        .map(buildRanges)
        .map((items: number[]) => sumOfInvalidIds(items, isMoreInvalid))
        .reduce((a:number,b:number) => a+b,0);
}