import {chunk, collect, range} from "@certes/lazy";
import {isArrayEqual, assert} from "radashi";

type Range = [number, number];

const parse = (input: string) : Range[] => input
        .split(",")
        .map(it => it
            .split("-")
            .map(it => Number(it.trim().replace('\n','')))
        ).map(it => it as Range);

export function everyItemEqual(digits: string[]) {
    if(digits.length < 2) return false;
    return digits.every(it => it === digits[0]);
}

export function isInvalid(n: number) {
    const digits = n.toString().split("");
    if(digits.length % 2 !== 0) return false;
    if(everyItemEqual(digits)) return true;
    const [start,end] = collect(chunk(digits.length / 2)(digits));

    return isArrayEqual(start, end);
}



export function isMoreInvalid(n: number) {
    const digits = n.toString().split("");

    // if they are all the same number, then it's invalid
    if(everyItemEqual(digits)) return true;

    const options = [...range(2, Math.floor(digits.length / 2))];

    for(const option of options) {
        const arrays = collect(chunk(option)(digits));
        const isEqual = allArraysEqual(...arrays);
        if(isEqual) return true;
    }

    return false;
}

export function ranger([start, end]: Range) {
    return range(start, end);
}

export function partOne(input: string) {
    const ranges = parse(input);

    return ranges
        .map(ranger)
        .map(itemsInRange => {
            let sums = 0;
            for (const item of itemsInRange) {
                if(isInvalid(item)) sums += item;
            }
            return sums;
        })
        .reduce((a,b) => a+b,0);
}

export function allArraysEqual(...arrays: string[][]): boolean {
  if (arrays.length < 2) {
    return true; // If there are 0 or 1 arrays, they're considered equal
  }

  const firstArray = arrays[0];
  
  // Check if all arrays are equal to the first array
  return arrays.every(array => isArrayEqual(firstArray, array));
}

export function partTwo(input: string) {
    const ranges = parse(input);

    return ranges
        .map(ranger)
        .map(itemsInRange => {
            let sums = 0;
            for (const item of itemsInRange) {
                if(isMoreInvalid(item)) sums += item;
            }
            return sums;
        })
        .reduce((a,b) => a+b,0);
}