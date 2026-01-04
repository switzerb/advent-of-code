
export class PQueue<T> {
    #queue: T[] = [];
    #size = 0;
    #comparator: (a: T, b: T) => boolean;

    constructor(
        comparator: (a: T, b: T) => boolean = (a, b) => a < b,
        maxSize = 5000
    ) {
        this.#queue = new Array(maxSize + 1);
        this.#comparator = comparator;
    }

    pop() : T {
        if (this.#size === 0) {
            throw new Error('Cannot pop from empty queue');
        }
        const top = this.#queue[1];
        this.#swap(1, this.#size);
        this.#size--;
        this.#queue[this.#size + 1] = null;
        if (this.#size > 0) {
            this.#sink(1);
        }
        return top;
    }

    insert(key: T) : void {
        this.#size++;
        this.#queue[this.#size] = key;
        this.#swim(this.#size)
    }

    isEmpty() : boolean { return this.#size === 0; }

    size() : number {return this.#size;}

    #less(i: number, j: number) : boolean {
        return this.#comparator(this.#queue[i], this.#queue[j]);
    }

    #swap(i: number, j: number) : void {
        const swap = this.#queue[i];
        this.#queue[i] = this.#queue[j];
        this.#queue[j] = swap;
    }

    #swim(k: number) : void {
        let current = k;
        while (current > 1 && this.#less(Math.floor(current / 2), current)) {
            const parent = Math.floor(current / 2);
            this.#swap(current, parent);
            current = parent;
        }
    }
    
    #sink(k: number) : void {
        let current = k;
        while (2 * current <= this.#size) {
            let j = 2 * current;
            if (j < this.#size && this.#less(j, j + 1)) j++;
            if (!this.#less(current, j)) break;
            this.#swap(current, j);
            current = j;
        }
    }

    print() : void {
        console.log(this.#queue.slice(1, this.#size + 1).join(' '));
    }
}