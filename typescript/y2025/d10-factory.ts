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

type State = {
    pattern: string;
    pressedButtons: Set<number>;
    steps: number;
};

const initialize = (length: number): State => ({
    pattern: '.'.repeat(length),
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

const getUnpressedButtons = (totalButtons: number, pressed: Set<number>): number[] =>
    Array.from({ length: totalButtons }, (_, i) => i)
        .filter(i => !pressed.has(i));

const pressButton = (current: State, buttonIndex: number, button: number[]): State => ({
    pattern: togglePositions(current.pattern, button),
    pressedButtons: new Set([...current.pressedButtons, buttonIndex]),
    steps: current.steps + 1
});

const generateNextStates = (
    current: State,
    buttons: number[][],
    visited: Set<string>
): State[] => {
    return getUnpressedButtons(buttons.length, current.pressedButtons)
        .map(buttonIndex => pressButton(current, buttonIndex, buttons[buttonIndex]))
        .filter(state => {
            const key = stateKey(state.pattern, state.pressedButtons);
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
    if (initial.pattern === machine.target) return 0;

    const visited = new Set([stateKey(initial.pattern, initial.pressedButtons)]);
    const queue: State[] = [initial];

    while (queue.length > 0) {
        const current = queue.shift();
        if (!current) break;

        const nextStates = generateNextStates(current, machine.buttons, visited);

        // Check if any next state reaches the target
        const solution = nextStates.find(state => state.pattern === machine.target);
        if (solution) return solution.steps;

        queue.push(...nextStates);
    }

    return -1; // Target unreachable
}


export function partOne(input: string) {
    const machines = parse(input);
    return machines.reduce((acc, machine) => acc + minButtonPresses(machine), 0);
}