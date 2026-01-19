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

type MachineState = {
    indicator: string;
    joltage: number[];
    pressedButtons: Set<number>;
    steps: number;
};

const initialize = (length: number): MachineState => ({
    indicator: '.'.repeat(length),
    joltage: Array.from({ length }).fill(0) as number[],
    pressedButtons: new Set(),
    steps: 0
});

// generate key for deduplication
const stateKey = (pattern: string, pressed: Set<number>): string =>
    `${pattern}|${[...pressed].sort().join(',')}`;

const togglePositions = (pattern: string, positions: number[]): string =>
    pattern.split('')
        .map((char, idx) => positions.includes(idx) ? (char === '#' ? '.' : '#') : char)
        .join('');

export const incrementJoltage = (joltage: number[], positions: number[]): number[] =>
    joltage.map((value, idx) => positions.includes(idx) ? value + 1 : value);

const getUnpressedButtons = (totalButtons: number, pressed: Set<number>): number[] =>
    Array.from({ length: totalButtons }, (_, i) => i)
        .filter(i => !pressed.has(i));

const pressButton = (current: MachineState, buttonIndex: number, button: number[]): MachineState => ({
    indicator: togglePositions(current.indicator, button),
    joltage: incrementJoltage(current.joltage,button),
    pressedButtons: new Set([...current.pressedButtons, buttonIndex]),
    steps: current.steps + 1
});

const getNextIndicatorState = (
    current: MachineState,
    buttons: number[][],
    visited: Set<string>
): MachineState[] => {
    return getUnpressedButtons(buttons.length, current.pressedButtons)
        .map(buttonIndex => pressButton(current, buttonIndex, buttons[buttonIndex]))
        .filter(state => {
            const key = stateKey(state.indicator, state.pressedButtons);
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
    const initial = initialize(machine.target.length);
    if (initial.indicator === machine.target) return 0;

    const visited = new Set([stateKey(initial.indicator, initial.pressedButtons)]);
    const queue: MachineState[] = [initial];

    while (queue.length > 0) {
        const current = queue.shift();
        if (!current) break;

        const nextStates = getNextIndicatorState(current, machine.buttons, visited);

        // Check if any next state reaches the target
        const solution = nextStates.find(state => state.indicator === machine.target);
        if (solution) return solution.steps;

        queue.push(...nextStates);
    }

    return -1; // Target unreachable
}


export function partOne(input: string) {
    const machines = parse(input);
    return machines.reduce((acc, machine) => acc + minButtonPresses(machine), 0);
}

export function partTwo(input: string) {
    const machines = parse(input);
    for(const machine of machines) {
        console.log(machine);
    }
    return 0;
}