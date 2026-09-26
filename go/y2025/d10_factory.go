package main

import (
	_ "embed"
	"fmt"
	"regexp"
	"strconv"
	"strings"
)

//go:embed inputs/d10.txt
var puzzleInput string

/*

The manual describes one machine per line.
Each line contains
- a single indicator light diagram in [square brackets],
- one or more button wiring schematics in (parentheses),
- and joltage requirements in {curly braces}.

   [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
   [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
   [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}

indicator lights are all initially off
*/

type Button []int

func (b Button) mask() uint {
	var mask uint
	for _, n := range b {
		mask |= 1 << n
	}
	return mask
}

type Indicator string

func (i Indicator) mask() uint {
	var mask uint
	for idx, r := range i {
		if r == '#' {
			mask |= 1 << idx
		}
	}
	return mask
}

type State struct {
	indicator uint
	pressed   uint
	steps     int
}

type Machine struct {
	target  string
	buttons []Button
	joltage []int
}

func newMachine(target string, buttons []Button, joltage []int) Machine {
	machine := Machine{target, buttons, joltage}
	return machine
}

var targetPattern = regexp.MustCompile(`\[([^\]]+)\]`)
var buttonPattern = regexp.MustCompile(`\(([^)]+)\)`)
var joltagePattern = regexp.MustCompile(`\{([^}]+)\}`)

func parseNumbers(s string) []int {
	parts := strings.Split(s, ",")
	numbers := make([]int, len(parts))
	for i, p := range parts {
		n, err := strconv.Atoi(strings.TrimSpace(p))
		if err != nil {
			panic(err)
		}
		numbers[i] = n
	}
	return numbers
}

func parseMachine(line string) Machine {
	target := ""
	if match := targetPattern.FindStringSubmatch(line); match != nil {
		target = match[1]
	}

	buttonMatches := buttonPattern.FindAllStringSubmatch(line, -1)
	buttons := make([]Button, len(buttonMatches))
	for i, match := range buttonMatches {
		buttons[i] = parseNumbers(match[1])
	}

	joltage := []int{}
	if match := joltagePattern.FindStringSubmatch(line); match != nil {
		joltage = parseNumbers(match[1])
	}

	return newMachine(target, buttons, joltage)
}

func parse(input string) []Machine {
	lines := strings.Split(strings.TrimSpace(input), "\n")
	machines := make([]Machine, len(lines))
	for i, line := range lines {
		machines[i] = parseMachine(line)
	}
	return machines
}

func minButtonPresses(m Machine) int {
	target := Indicator(m.target).mask()
	var initial State

	if initial.indicator == target {
		return 0
	}
	visited := map[State]bool{}
	visited[initial] = true

	queue := []State{initial}

	for len(queue) > 0 {
		current := queue[0]
		queue = queue[1:]

		for idx, button := range m.buttons {
			buttonBit := uint(1) << idx
			if current.pressed&buttonBit != 0 {
				continue
			}

			next := State{
				indicator: current.indicator ^ button.mask(),
				pressed:   current.pressed | buttonBit,
				steps:     current.steps + 1,
			}

			if next.indicator == target {
				return next.steps
			}

			if !visited[next] {
				visited[next] = true
				queue = append(queue, next)
			}
		}
	}

	panic("target unreachable")
}

func partOne(input string) int {
	total := 0
	for _, m := range parse(input) {
		total += minButtonPresses(m)
	}
	return total
}

func main() {
	fmt.Println(partOne(puzzleInput))
}
