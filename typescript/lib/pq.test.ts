import { describe, it, expect } from 'vitest';
import { PQueue } from './pq';

describe('PQueue', () => {
	describe('isEmpty', () => {
		it('should return true for a newly created queue', () => {
			const pq = new PQueue<number>();
			expect(pq.isEmpty()).toBe(true);
		});

		it('should return false after inserting an element', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			expect(pq.isEmpty()).toBe(false);
		});

		it('should return true after popping all elements', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			pq.popMax();
			expect(pq.isEmpty()).toBe(true);
		});
	});

	describe('size', () => {
		it('should return 0 for an empty queue', () => {
			const pq = new PQueue<number>();
			expect(pq.size()).toBe(0);
		});

		it('should return correct size after insertions', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			expect(pq.size()).toBe(1);
			pq.insert(10);
			expect(pq.size()).toBe(2);
			pq.insert(3);
			expect(pq.size()).toBe(3);
		});

		it('should decrease size after popping', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			pq.insert(10);
			pq.insert(3);
			expect(pq.size()).toBe(3);
			pq.popMax();
			expect(pq.size()).toBe(2);
			pq.popMax();
			expect(pq.size()).toBe(1);
			pq.popMax();
			expect(pq.size()).toBe(0);
		});
	});

	describe('insert and popMax', () => {
		it('should insert and pop a single element', () => {
			const pq = new PQueue<number>();
			pq.insert(42);
			expect(pq.popMax()).toBe(42);
		});

		it('should return elements in descending order', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			pq.insert(10);
			pq.insert(3);
			pq.insert(7);
			pq.insert(1);

			expect(pq.popMax()).toBe(10);
			expect(pq.popMax()).toBe(7);
			expect(pq.popMax()).toBe(5);
			expect(pq.popMax()).toBe(3);
			expect(pq.popMax()).toBe(1);
		});

		it('should handle duplicate values', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			pq.insert(5);
			pq.insert(5);

			expect(pq.popMax()).toBe(5);
			expect(pq.popMax()).toBe(5);
			expect(pq.popMax()).toBe(5);
		});

		it('should maintain max-heap property with random insertions', () => {
			const pq = new PQueue<number>();
			const values = [15, 3, 8, 20, 1, 12, 5, 18, 7, 10];
			
			for (const val of values) {
				pq.insert(val);
			}

			const popped = [];
			while (!pq.isEmpty()) {
				popped.push(pq.popMax());
			}

			const sorted = [...values].sort((a, b) => b - a);
			expect(popped).toEqual(sorted);
		});

		it('should handle large numbers', () => {
			const pq = new PQueue<number>();
			pq.insert(Number.MAX_SAFE_INTEGER);
			pq.insert(1000);
			pq.insert(Number.MAX_SAFE_INTEGER - 1);

			expect(pq.popMax()).toBe(Number.MAX_SAFE_INTEGER);
			expect(pq.popMax()).toBe(Number.MAX_SAFE_INTEGER - 1);
			expect(pq.popMax()).toBe(1000);
		});

		it('should handle negative numbers', () => {
			const pq = new PQueue<number>();
			pq.insert(-5);
			pq.insert(-10);
			pq.insert(-1);
			pq.insert(-3);

			expect(pq.popMax()).toBe(-1);
			expect(pq.popMax()).toBe(-3);
			expect(pq.popMax()).toBe(-5);
			expect(pq.popMax()).toBe(-10);
		});

		it('should handle zero', () => {
			const pq = new PQueue<number>();
			pq.insert(0);
			pq.insert(5);
			pq.insert(-5);

			expect(pq.popMax()).toBe(5);
			expect(pq.popMax()).toBe(0);
			expect(pq.popMax()).toBe(-5);
		});
	});

	describe('interleaved operations', () => {
		it('should maintain correct order with interleaved insert and pop', () => {
			const pq = new PQueue<number>();
			pq.insert(10);
			pq.insert(5);
			expect(pq.popMax()).toBe(10);
			pq.insert(15);
			pq.insert(3);
			expect(pq.popMax()).toBe(15);
			expect(pq.popMax()).toBe(5);
			pq.insert(7);
			expect(pq.popMax()).toBe(7);
			expect(pq.popMax()).toBe(3);
		});

		it('should handle many operations', () => {
			const pq = new PQueue<number>();
			const operations = 100;
			
			// Insert many elements
			for (let i = 0; i < operations; i++) {
				pq.insert(Math.floor(Math.random() * 1000));
			}

			expect(pq.size()).toBe(operations);

			// Pop all elements and verify descending order
			let prev = Number.MAX_SAFE_INTEGER;
			while (!pq.isEmpty()) {
				const current = pq.popMax();
				expect(current).toBeLessThanOrEqual(prev);
				prev = current;
			}

			expect(pq.size()).toBe(0);
			expect(pq.isEmpty()).toBe(true);
		});
	});

	describe('edge cases', () => {
		it('should handle constructor with maxSize parameter', () => {
			const pq = new PQueue<number>((a, b) => a < b, 10);
			expect(pq.isEmpty()).toBe(true);
			expect(pq.size()).toBe(0);
		});

		it('should handle sequential pops after all inserts', () => {
			const pq = new PQueue<number>();
			const values = [1, 2, 3, 4, 5];
			
			for (const val of values) {
				pq.insert(val);
			}

			const results = [];
			while (!pq.isEmpty()) {
				results.push(pq.popMax());
			}

			expect(results).toEqual([5, 4, 3, 2, 1]);
		});
	});
});

