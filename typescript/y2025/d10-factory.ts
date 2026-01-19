type Machine = {
    target: string;
    buttons: number[][];
    joltage: number[];
}

export function parse(input: string): Machine[] {
    return input.split('\n').map(line => {
        // Extract target from [...]
        const targetMatch = line.match(/\[([^\]]+)\]/);
        const target = targetMatch ? targetMatch[1] : '';

        // Extract buttons from (...) patterns
        const buttonMatches = [...line.matchAll(/\(([^)]+)\)/g)];
        const buttons = buttonMatches.map(match =>
            match[1].split(',').map(n => Number.parseInt(n.trim()))
        );

        // Extract joltage from {...}
        const joltageMatch = line.match(/\{([^}]+)\}/);
        const joltage = joltageMatch
            ? joltageMatch[1].split(',').map(n => Number.parseInt(n.trim()))
            : [];

        return { target, buttons, joltage };
    });
}

type IndicatorState = {
    indicator: string;
    pressedButtons: Set<number>;
    steps: number;
};

type JoltageState = {
    joltage: number[];
    steps: number;
};

const initIndicator = (length: number): IndicatorState => ({
    indicator: '.'.repeat(length),
    pressedButtons: new Set(),
    steps: 0
});

const initJoltage = (length: number) : JoltageState => ({
    joltage: Array(length).fill(0),
    steps: 0
})

// because javascript can't do sets correctly with arrays
const indicatorKey = (pattern: string, pressed: Set<number>): string =>
    `${pattern}|${[...pressed].sort().join(',')}`;

const joltageKey = (joltage: number[]): string => joltage.join(',');

const togglePositions = (pattern: string, positions: number[]): string =>
    pattern.split('')
        .map((char, idx) => positions.includes(idx) ? (char === '#' ? '.' : '#') : char)
        .join('');

export const incrementJoltage = (joltage: number[], positions: number[]): number[] =>
    joltage.map((value, idx) => positions.includes(idx) ? value + 1 : value);

const getUnpressedButtons = (totalButtons: number, pressed: Set<number>): number[] =>
    Array.from({ length: totalButtons }, (_, i) => i)
        .filter(i => !pressed.has(i));

const pressButton = (current: IndicatorState, buttonIndex: number, button: number[]): IndicatorState => ({
    indicator: togglePositions(current.indicator, button),
    pressedButtons: new Set([...current.pressedButtons, buttonIndex]),
    steps: current.steps + 1
});

const getNextIndicatorState = (
    current: IndicatorState,
    buttons: number[][],
    visited: Set<string>
): IndicatorState[] => {
    return getUnpressedButtons(buttons.length, current.pressedButtons)
        .map(buttonIndex => pressButton(current, buttonIndex, buttons[buttonIndex]))
        .filter(state => {
            const key = indicatorKey(state.indicator, state.pressedButtons);
            if (visited.has(key)) return false;
            visited.add(key);
            return true;
        });
};

/**
 * Find the minimum number of button presses needed to reach the target using BFS.
 * @param machine - The machine with target and available buttons
 * @returns The minimum number of button presses, or -1 if impossible
 */
export function minButtonPresses(machine: Machine): number {
    const initial = initIndicator(machine.target.length);
    if (initial.indicator === machine.target) return 0;

    const visited = new Set([indicatorKey(initial.indicator, initial.pressedButtons)]);
    const queue: IndicatorState[] = [initial];

    while (queue.length > 0) {
        const current = queue.shift();
        if (!current) break;

        const nextStates = getNextIndicatorState(current, machine.buttons, visited);

        // Check if any next state reaches the target
        const solution = nextStates.find(state => state.indicator === machine.target);
        if (solution) return solution.steps;

        queue.push(...nextStates);
    }

    throw new Error('Target unreachable');
}

const matchesTarget = (current: number[], target: number[]): boolean =>
    current.length === target.length && current.every((val, idx) => val === target[idx]);

const exceedsTarget = (current: number[], target: number[]): boolean =>
    current.some((val, idx) => val > target[idx]);

// Calculate heuristic: total remaining deficit
const remainingDeficit = (current: number[], target: number[]): number =>
    target.reduce((sum, val, idx) => sum + Math.max(0, val - current[idx]), 0);

/**
 * Find minimum button presses using beam search (BFS with limited states per level).
 * @param machine - The machine with joltage requirements
 * @returns The minimum number of button presses
 */
export function minJoltagePresses(machine: Machine): number {
    const initial = initJoltage(machine.joltage.length);
    if (matchesTarget(initial.joltage, machine.joltage)) return 0;

    const BEAM_WIDTH = 50000; // Keep top 5000 states per level
    let currentLevel: JoltageState[] = [initial];
    const visited = new Set([joltageKey(initial.joltage)]);

    while (currentLevel.length > 0) {
        const nextLevel: JoltageState[] = [];

        for (const state of currentLevel) {
            for (const button of machine.buttons) {
                const next = incrementJoltage(state.joltage, button);

                if (exceedsTarget(next, machine.joltage)) continue;
                if (matchesTarget(next, machine.joltage)) {
                    return state.steps + 1;
                }

                const key = joltageKey(next);
                if (!visited.has(key)) {
                    visited.add(key);
                    nextLevel.push({
                        joltage: next,
                        steps: state.steps + 1
                    });
                }
            }
        }

        // Prune: keep only top BEAM_WIDTH states by heuristic (lowest remaining deficit)
        nextLevel.sort((a, b) =>
            remainingDeficit(a.joltage, machine.joltage) - remainingDeficit(b.joltage, machine.joltage)
        );
        currentLevel = nextLevel.slice(0, BEAM_WIDTH);
    }

    throw new Error('Target unreachable');
}

export function partOne(input: string) {
    const machines = parse(input);
    return machines.reduce((acc, machine) => acc + minButtonPresses(machine), 0);
}

export function partTwo(input: string) {
    const machines = parse(input);
    return machines.reduce((acc, machine) => acc + minJoltagePresses(machine), 0);
}