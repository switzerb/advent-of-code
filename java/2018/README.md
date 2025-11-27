# aoc-yahtzee

This repo contains code to solve puzzles for the [Advent of Code 2018](https://adventofcode.com/2018) challenge. The solutions
are written in Java (see details of requirements below).

### Requirements to run

* Java 1.8.0
* Java JDK 1.8.0
* Apache Maven 3.5.4

### General Notes
The solvers are segmented into one module per puzzle day. To run your input, you will need to replace the puzzle input that is currently in the files with the puzzle input to be tested and then run the solver. Please note that not all days are solved (yet) and so not all puzzles are available to be run. There is a manifest for days and where the input files must be stored below in the "Run Solvers" section.

In general, the input is located in day_XX/src/main/resources/input.txt. To get specific instructions for each day, look in the README.md for each individual day.

Note that Day 13 and Day 25 can be ignored and do not contain solvers.

### Build project
To compile the project and install solvers locally, check the repo out of github and then compile

    $ git clone https://github.com/switzerb/aoc-yahtzee.git
    $ cd aoc-yahtzee
    $ mvn install


### Run Solvers
For each day that you want to run, you need to replace the current input.txt (or in some cases, add input directly into the Main class file) and then run the solver. Run solver by pasting input into **<project-root>/<aoc_day>/src/main/resources/input.txt** and then execute command:

    $ ./<day_num>/run.sh

If you have cut and paste the input into the approriate place for each day, you can run all the solvers at the same time using from the root of the project:

    $ ./run_all.sh

Below is a manifest of the days that are available for solving and where to paste the input

| Day          |  Input Location                     |
| ------------ | ----------------------------------- |
| Day One | day_01/src/main/resources/input.txt |
| Day Two | day_02/src/main/resources/input.txt |
| Day Three | day03/src/main/resources/input.txt |
| Day Four | day_04/src/main/resources/input.txt |
| Day Five | day_05/src/main/resources/input.txt |
| Day Six | day_06/src/main/resources/input.txt |
| Day Seven (part one only) | day_07/src/main/resources/input.txt |
| Day Eight | day_08/src/main/resources/input.txt |
| Day Nine | day_09/src/main/Main, Lines 7 & 8 (MARBLES, PLAYERS)|
| Day Ten | day_10/src/main/resources/input.txt |
| Day Eleven | day_11/src/main/Main Line 8 (SERIAL) |
| Day Twelve | day_12/src/main/resources/input.txt |
| Day Fourteen | day_14/src/main/Main Line 13 (TARGET) |
| Day Sixteen | day_16/src/main/resources/input.txt |
| Day Twenty | day_20/src/main/resources/input.txt |
