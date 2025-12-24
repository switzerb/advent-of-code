# Advent of Code TypeScript

This repository contains solutions to [Advent of Code](https://adventofcode.com/) puzzles implemented in TypeScript.

## Project Structure

The `typescript` folder is organized as follows:

- Each puzzle solution is contained in its own directory, typically named by day (e.g., `day1`, `day2`)
- Within each day's directory:
  - Solution files (`.ts`) contain the logic to solve that day's challenges
  - Test files (`.test.ts`) verify the solutions with example and actual inputs
  - Input files contain puzzle inputs provided by Advent of Code

## Running the Solutions

All solutions can be verified by running the test suite. The tests validate both the examples provided in the puzzle descriptions and the actual puzzle inputs.

### Install dependencies (first time only)

`pnpm install`

### To run all the tests

`pnpm run test`

### To run specific solutions

`pnpm run test -- y2025`
`pnpm run test -- y2025/day01`
