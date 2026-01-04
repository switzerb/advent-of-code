import { describe, it, expect } from 'vitest';
import { PQueue } from './priority-queue';

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
			pq.pop();
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
			pq.pop();
			expect(pq.size()).toBe(2);
			pq.pop();
			expect(pq.size()).toBe(1);
			pq.pop();
			expect(pq.size()).toBe(0);
		});
	});

	describe('insert and pop', () => {
		it('should insert and pop a single element', () => {
			const pq = new PQueue<number>();
			pq.insert(42);
			expect(pq.pop()).toBe(42);
		});

		it('should return elements in descending order', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			pq.insert(10);
			pq.insert(3);
			pq.insert(7);
			pq.insert(1);

			expect(pq.pop()).toBe(10);
			expect(pq.pop()).toBe(7);
			expect(pq.pop()).toBe(5);
			expect(pq.pop()).toBe(3);
			expect(pq.pop()).toBe(1);
		});

		it('should handle duplicate values', () => {
			const pq = new PQueue<number>();
			pq.insert(5);
			pq.insert(5);
			pq.insert(5);

			expect(pq.pop()).toBe(5);
			expect(pq.pop()).toBe(5);
			expect(pq.pop()).toBe(5);
		});

		it('should maintain max-heap property with random insertions', () => {
			const pq = new PQueue<number>();
			const values = [15, 3, 8, 20, 1, 12, 5, 18, 7, 10];
			
			for (const val of values) {
				pq.insert(val);
			}

			const popped = [];
			while (!pq.isEmpty()) {
				popped.push(pq.pop());
			}

			const sorted = [...values].sort((a, b) => b - a);
			expect(popped).toEqual(sorted);
		});

		it('should handle large numbers', () => {
			const pq = new PQueue<number>();
			pq.insert(Number.MAX_SAFE_INTEGER);
			pq.insert(1000);
			pq.insert(Number.MAX_SAFE_INTEGER - 1);

			expect(pq.pop()).toBe(Number.MAX_SAFE_INTEGER);
			expect(pq.pop()).toBe(Number.MAX_SAFE_INTEGER - 1);
			expect(pq.pop()).toBe(1000);
		});

		it('should handle negative numbers', () => {
			const pq = new PQueue<number>();
			pq.insert(-5);
			pq.insert(-10);
			pq.insert(-1);
			pq.insert(-3);

			expect(pq.pop()).toBe(-1);
			expect(pq.pop()).toBe(-3);
			expect(pq.pop()).toBe(-5);
			expect(pq.pop()).toBe(-10);
		});

		it('should handle zero', () => {
			const pq = new PQueue<number>();
			pq.insert(0);
			pq.insert(5);
			pq.insert(-5);

			expect(pq.pop()).toBe(5);
			expect(pq.pop()).toBe(0);
			expect(pq.pop()).toBe(-5);
		});
	});

	describe('interleaved operations', () => {
		it('should maintain correct order with interleaved insert and pop', () => {
			const pq = new PQueue<number>();
			pq.insert(10);
			pq.insert(5);
			expect(pq.pop()).toBe(10);
			pq.insert(15);
			pq.insert(3);
			expect(pq.pop()).toBe(15);
			expect(pq.pop()).toBe(5);
			pq.insert(7);
			expect(pq.pop()).toBe(7);
			expect(pq.pop()).toBe(3);
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
				const current = pq.pop();
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
				results.push(pq.pop());
			}

			expect(results).toEqual([5, 4, 3, 2, 1]);
		});

		it('should throw error when popping from empty queue', () => {
			const pq = new PQueue<number>();
			expect(() => pq.pop()).toThrow('Cannot pop from empty queue');
		});

		it('should correctly track size through complex operations', () => {
			const pq = new PQueue<number>();
			
			// Start empty
			expect(pq.size()).toBe(0);
			expect(pq.isEmpty()).toBe(true);
			
			// Insert one element
			pq.insert(10);
			expect(pq.size()).toBe(1);
			expect(pq.isEmpty()).toBe(false);
			
			// Insert more elements
			pq.insert(5);
			expect(pq.size()).toBe(2);
			pq.insert(15);
			expect(pq.size()).toBe(3);
			pq.insert(7);
			expect(pq.size()).toBe(4);
			
			// Pop one element
			const val1 = pq.pop();
			expect(pq.size()).toBe(3);
			expect(val1).toBe(15);
			
			// Insert more
			pq.insert(20);
			expect(pq.size()).toBe(4);
			
			// Pop all remaining
			const val2 = pq.pop();
			expect(pq.size()).toBe(3);
			expect(val2).toBe(20);
			
			pq.pop();
			expect(pq.size()).toBe(2);
			
			pq.pop();
			expect(pq.size()).toBe(1);
			
			pq.pop();
			expect(pq.size()).toBe(0);
			expect(pq.isEmpty()).toBe(true);
		});

		it('should only keep top maxSize elements and ignore others', () => {
			const pq = new PQueue<number>((a, b) => a < b, 3);
			
			// Insert 5 elements, but queue should only keep top 3
			pq.insert(10);
			pq.insert(5);
			pq.insert(15);
			expect(pq.size()).toBe(3);
			
			// Insert a value that should be kept (better than minimum)
			pq.insert(12); // Should replace 5
			expect(pq.size()).toBe(3);
			
			// Insert a value that should be ignored (worse than all)
			pq.insert(3); // Should be ignored
			expect(pq.size()).toBe(3);
			
			// Insert another value that should be kept
			pq.insert(20); // Should replace the minimum
			expect(pq.size()).toBe(3);
			
			// Verify we have the top 3 elements
			const results = [];
			while (!pq.isEmpty()) {
				results.push(pq.pop());
			}
			// Should have 20, 15, 12 (top 3)
			expect(results).toEqual([20, 15, 12]);
		});

		it('should handle maxSize of 1', () => {
			const pq = new PQueue<number>((a, b) => a < b, 1);
			
			pq.insert(10);
			expect(pq.size()).toBe(1);
			
			pq.insert(5); // Should be ignored (worse than 10)
			expect(pq.size()).toBe(1);
			expect(pq.pop()).toBe(10);
			
			pq.insert(15);
			expect(pq.size()).toBe(1);
			pq.insert(20); // Should replace 15
			expect(pq.size()).toBe(1);
			expect(pq.pop()).toBe(20);
		});

		it('should maintain heap property when replacing minimum', () => {
			const pq = new PQueue<number>((a, b) => a < b, 5);
			
			// Fill to capacity
			const values = [10, 20, 15, 5, 25];
			for (const val of values) {
				pq.insert(val);
			}
			expect(pq.size()).toBe(5);
			
			// Insert values that should replace minimums
			pq.insert(12); // Should replace 5
			pq.insert(18); // Should replace something
			pq.insert(3); // Should be ignored (worse than all)
			
			expect(pq.size()).toBe(5);
			
			// Verify heap property is maintained (descending order)
			const results = [];
			while (!pq.isEmpty()) {
				results.push(pq.pop());
			}
			
			// Should be in descending order
			for (let i = 0; i < results.length - 1; i++) {
				expect(results[i]).toBeGreaterThanOrEqual(results[i + 1]);
			}
			
			// Should contain top 5 from [10, 20, 15, 5, 25, 12, 18, 3]
			// Top 5: 25, 20, 18, 15, 12
			expect(results).toEqual([25, 20, 18, 15, 12]);
		});
	});
});

