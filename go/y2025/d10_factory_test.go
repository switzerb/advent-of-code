package main

import (
	"os"
	"reflect"
	"strings"
	"testing"
)

const example = `
[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
`

func readInput(t *testing.T, path string) string {
	t.Helper()
	data, err := os.ReadFile(path)
	if err != nil {
		t.Fatalf("failed to read input: %v", err)
	}
	return strings.TrimSpace(string(data))
}

func TestParse(t *testing.T) {
	machines := parse(example)

	want := []Machine{
		{
			target: ".##.",
			buttons: []Button{
				{3},
				{1, 3},
				{2},
				{2, 3},
				{0, 2},
				{0, 1},
			},
			joltage: []int{3, 5, 4, 7},
		},
		{
			target: "...#.",
			buttons: []Button{
				{0, 2, 3, 4},
				{2, 3},
				{0, 4},
				{0, 1, 2},
				{1, 2, 3, 4},
			},
			joltage: []int{7, 5, 12, 7, 2},
		},
		{
			target: ".###.#",
			buttons: []Button{
				{0, 1, 2, 3, 4},
				{0, 3, 4},
				{0, 1, 2, 4, 5},
				{1, 2},
			},
			joltage: []int{10, 11, 11, 5, 10, 5},
		},
	}

	if !reflect.DeepEqual(machines, want) {
		t.Errorf("parse(example) = %+v, want %+v", machines, want)
	}
}

func TestPartOne(t *testing.T) {
	t.Run("example", func(t *testing.T) {
		got := partOne(example)
		want := 7
		if got != want {
			t.Errorf("partOne(example) = %d, want %d", got, want)
		}
	})

	t.Run("input", func(t *testing.T) {
		input := readInput(t, "inputs/d10.txt")
		got := partOne(input)
		want := 571
		if got != want {
			t.Errorf("partOne(input) = %d, want %d", got, want)
		}
	})
}
