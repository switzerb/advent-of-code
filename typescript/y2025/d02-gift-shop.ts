import {chunk, collect, range} from "@certes/lazy";
import {toDigits} from "../lib/utils";
import {every} from "@certes/list";
import {isArrayEqual, assert} from "radashi";

type Range = [number, number];

const parse = (input: string) => input
        .split(",")
        .map(it => it
            .split("-")
            .map(it => Number(it.trim().replace('\n','')))
        );

export function everyItemEqual(digits: number[]) {
    assert(digits.length >= 2, "Cannot compare less than two numbers");
    return every(it => it === digits[0])(digits);
}

export function isInvalid(n: number) {
    const digits = toDigits(n);
    if(digits.length % 2 !== 0) return false;
    if(everyItemEqual(digits)) return true;
    const [start,end] = collect(chunk(digits.length / 2)(digits));
    return isArrayEqual(start, end);
}

export function ranger([start, end]: Range) {
    return [...range(start, end)];
}

export function partOne(input: string) {
    const ranges = parse(input);
    return ranges
        .map(ranger)
        .map(range => range
            .filter(isInvalid)
            .reduce((a,b) => a+b,0))
        .reduce((a,b) => a+b,0);
}