
export class UnionFind {
    #id : number[] = [];
    #sizes : number[] = [];
    #count = 0;

    constructor(n: number) {
        this.#count = n;
        for (let i = 0; i < n; i++) {
            this.#id[i] = i;
            this.#sizes[i] = 1;
        }
    }

    count() {
        return this.#count;
    }

    sizes() {
        return this.#sizes;
    }

    connected(p: number, q: number) {
        return this.find(p) === this.find(q);
    }

    find(p: number) {
        let item = p;
        while (item !== this.#id[item]) {
            this.#id[item] = this.#id[this.#id[item]];
            item = this.#id[item];
        }
        return item;
    }

    union(p: number, q: number) {
        const rootP = this.find(p);
        const rootQ = this.find(q);
        if (rootP === rootQ) return;
        if (this.#sizes[rootP] < this.#sizes[rootQ]) {
            this.#id[rootP] = rootQ;
            this.#sizes[rootQ] += this.#sizes[rootP];
        } else {
            this.#id[rootQ] = rootP;
            this.#sizes[rootP] += this.#sizes[rootQ];
        }
        this.#count--;
    }

}