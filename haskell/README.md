# Advent of Code - Haskell

These are [Advent of Code](https://adventofcode.com) solvers written in Haskell. Most of the solvers will be from 2025, but I might wander into other years in my attempt to learn.

## Why Haskell?

It was first suggested to me in 2016 as one way to practice understanding the principles of functional programming. Despite the delightfully named and written [Learn You a Haskell For Great Good](https://learnyouahaskell.github.io/) I was far too intimidated by the language and rejected the advice. Nearly 10 years and many more programming hours later, I decided to give it a try.

## Running the solvers

This project uses [Cabal](https://www.haskell.org/cabal/) for building and dependency management. You'll need to have Haskell and Cabal installed - the easiest way is through [GHCup](https://www.haskell.org/ghcup/).

### Building and Running

1. **Install dependencies** (Cabal will do this automatically on first build):

   ```bash
   cabal build
   ```

2. **Run the executable**:

   ```bash
   cabal run
   ```

   This will build the project (if needed) and execute the `main` function in `app/Main.hs`.

3. **Build only** (without running):

   ```bash
   cabal build
   ```

4. **Run individual solver files** (if you want to test a specific solver file):

   Using `cabal exec` ensures the project's dependencies are available:

   ```bash
   # Run a file directly
   cabal exec runghc app/y2019-d03--crossed-wires.hs
   ```

   Or load it in GHCi with dependencies:

   ```bash
   cabal exec ghci app/y2019-d03--crossed-wires.hs
   ```

   Then in the GHCi prompt, just run:

   ```haskell
   main
   ```

### Running Tests

This project uses [HUnit](https://hackage.haskell.org/package/HUnit) for unit testing. Tests are located in the `test/` directory.

1. **Run all tests**:

   ```bash
   cabal test
   ```

2. **Build tests only** (without running):

   ```bash
   cabal build --enable-tests
   ```

3. **Run tests with verbose output**:

   ```bash
   cabal test --test-show-details=direct
   ```

4. **Run a specific test suite**:

   You can run a specific test suite using `cabal repl` to load the test environment:

   ```bash
   cabal repl tests
   ```

   Then in GHCi, you can run a specific test:

   ```haskell
   :l test/Tests.hs
   runTestTT y2019D03Tests  -- Run only the y2019-d03 tests
   ```

   Alternatively, you can temporarily modify `test/Tests.hs` to only include the test suite you want to run.

To add new tests, edit `test/Tests.hs` and add your test cases using HUnit's `TestCase` or `TestList` functions. The test suite has access to all library modules (`Grid`, `Utils`, etc.).

### Project Structure

- `app/` - Contains the solver files and `Main.hs`
- `src/` - Contains shared library modules (`Grid`, `Utils`)
- `test/` - Contains unit tests using HUnit (`Tests.hs`)
- `app/*.txt` - Input data files for the puzzles
