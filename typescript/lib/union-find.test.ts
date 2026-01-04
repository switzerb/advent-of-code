import { describe, it, expect } from 'vitest';
import { UnionFind } from './union-find';

describe('UnionFind', () => {
	describe('constructor', () => {
		it('should initialize with n separate components', () => {
			const uf = new UnionFind(5);
			expect(uf.count()).toBe(5);
		});

		it('should initialize with 0 components', () => {
			const uf = new UnionFind(0);
			expect(uf.count()).toBe(0);
		});

		it('should initialize with 1 component', () => {
			const uf = new UnionFind(1);
			expect(uf.count()).toBe(1);
		});

		it('should initialize with large number of components', () => {
			const uf = new UnionFind(1000);
			expect(uf.count()).toBe(1000);
		});
	});

	describe('count', () => {
		it('should return initial count', () => {
			const uf = new UnionFind(5);
			expect(uf.count()).toBe(5);
		});

		it('should decrease after union operations', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			expect(uf.count()).toBe(4);
			uf.union(2, 3);
			expect(uf.count()).toBe(3);
			uf.union(0, 2);
			expect(uf.count()).toBe(2);
		});

		it('should not decrease when unioning already connected elements', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			expect(uf.count()).toBe(4);
			uf.union(0, 1); // same union again
			expect(uf.count()).toBe(4);
			uf.union(1, 0); // reverse order
			expect(uf.count()).toBe(4);
		});
	});

	describe('find', () => {
		it('should return the same element for unconnected elements', () => {
			const uf = new UnionFind(5);
			expect(uf.find(0)).toBe(uf.find(0));
			expect(uf.find(1)).toBe(uf.find(1));
			expect(uf.find(2)).toBe(uf.find(2));
		});

		it('should return different roots for unconnected elements', () => {
			const uf = new UnionFind(5);
			expect(uf.find(0)).not.toBe(uf.find(1));
			expect(uf.find(0)).not.toBe(uf.find(2));
			expect(uf.find(1)).not.toBe(uf.find(2));
		});

		it('should return same root after union', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			expect(uf.find(0)).toBe(uf.find(1));
		});

		it('should return consistent roots', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			const root1 = uf.find(0);
			const root2 = uf.find(1);
			const root3 = uf.find(0);
			expect(root1).toBe(root2);
			expect(root1).toBe(root3);
		});
	});

	describe('connected', () => {
		it('should return true for same element', () => {
			const uf = new UnionFind(5);
			expect(uf.connected(0, 0)).toBe(true);
			expect(uf.connected(2, 2)).toBe(true);
		});

		it('should return false for unconnected elements initially', () => {
			const uf = new UnionFind(5);
			expect(uf.connected(0, 1)).toBe(false);
			expect(uf.connected(0, 2)).toBe(false);
			expect(uf.connected(1, 2)).toBe(false);
		});

		it('should return true after union', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			expect(uf.connected(0, 1)).toBe(true);
			expect(uf.connected(1, 0)).toBe(true);
		});

		it('should return false for still unconnected elements', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			expect(uf.connected(0, 2)).toBe(false);
			expect(uf.connected(1, 2)).toBe(false);
		});

		it('should maintain transitivity', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			uf.union(1, 2);
			expect(uf.connected(0, 2)).toBe(true);
			expect(uf.connected(0, 1)).toBe(true);
			expect(uf.connected(1, 2)).toBe(true);
		});

		it('should handle multiple separate components', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			uf.union(2, 3);
			expect(uf.connected(0, 1)).toBe(true);
			expect(uf.connected(2, 3)).toBe(true);
			expect(uf.connected(0, 2)).toBe(false);
			expect(uf.connected(0, 3)).toBe(false);
			expect(uf.connected(1, 2)).toBe(false);
			expect(uf.connected(1, 3)).toBe(false);
			expect(uf.connected(4, 0)).toBe(false);
			expect(uf.connected(4, 2)).toBe(false);
		});
	});

	describe('union', () => {
		it('should connect two unconnected elements', () => {
			const uf = new UnionFind(5);
			expect(uf.connected(0, 1)).toBe(false);
			uf.union(0, 1);
			expect(uf.connected(0, 1)).toBe(true);
		});

		it('should be idempotent', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			expect(uf.connected(0, 1)).toBe(true);
			uf.union(0, 1);
			expect(uf.connected(0, 1)).toBe(true);
			uf.union(1, 0);
			expect(uf.connected(0, 1)).toBe(true);
		});

		it('should merge two components', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			uf.union(2, 3);
			expect(uf.count()).toBe(3);
			uf.union(0, 2);
			expect(uf.count()).toBe(2);
			expect(uf.connected(0, 1)).toBe(true);
			expect(uf.connected(0, 2)).toBe(true);
			expect(uf.connected(0, 3)).toBe(true);
			expect(uf.connected(1, 2)).toBe(true);
			expect(uf.connected(1, 3)).toBe(true);
			expect(uf.connected(2, 3)).toBe(true);
		});

		it('should handle sequential unions', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			uf.union(1, 2);
			uf.union(2, 3);
			uf.union(3, 4);
			expect(uf.count()).toBe(1);
			expect(uf.connected(0, 4)).toBe(true);
			expect(uf.connected(1, 3)).toBe(true);
		});

		it('should handle union of already connected elements', () => {
			const uf = new UnionFind(5);
			uf.union(0, 1);
			uf.union(1, 2);
			expect(uf.count()).toBe(3);
			uf.union(0, 2); // already connected
			expect(uf.count()).toBe(3);
			expect(uf.connected(0, 2)).toBe(true);
		});
	});

	describe('complex scenarios', () => {
		it('should handle a chain of connections', () => {
			const uf = new UnionFind(10);
			// Connect 0-1-2-3-4
			uf.union(0, 1);
			uf.union(1, 2);
			uf.union(2, 3);
			uf.union(3, 4);
			// Connect 5-6-7
			uf.union(5, 6);
			uf.union(6, 7);
			// Connect 8-9
			uf.union(8, 9);

			expect(uf.count()).toBe(3);
			expect(uf.connected(0, 4)).toBe(true);
			expect(uf.connected(5, 7)).toBe(true);
			expect(uf.connected(8, 9)).toBe(true);
			expect(uf.connected(0, 5)).toBe(false);
			expect(uf.connected(0, 8)).toBe(false);
			expect(uf.connected(5, 8)).toBe(false);
		});

		it('should handle star pattern connections', () => {
			const uf = new UnionFind(5);
			// Connect all to element 0
			uf.union(0, 1);
			uf.union(0, 2);
			uf.union(0, 3);
			uf.union(0, 4);

			expect(uf.count()).toBe(1);
			expect(uf.connected(1, 2)).toBe(true);
			expect(uf.connected(1, 3)).toBe(true);
			expect(uf.connected(1, 4)).toBe(true);
			expect(uf.connected(2, 3)).toBe(true);
			expect(uf.connected(2, 4)).toBe(true);
			expect(uf.connected(3, 4)).toBe(true);
		});

		it('should handle merging multiple components', () => {
			const uf = new UnionFind(8);
			// Create 4 pairs
			uf.union(0, 1);
			uf.union(2, 3);
			uf.union(4, 5);
			uf.union(6, 7);
			expect(uf.count()).toBe(4);

			// Merge pairs into 2 groups
			uf.union(0, 2);
			uf.union(4, 6);
			expect(uf.count()).toBe(2);

			// Merge the two groups
			uf.union(0, 4);
			expect(uf.count()).toBe(1);
			expect(uf.connected(0, 7)).toBe(true);
		});

		it('should maintain correctness with many operations', () => {
			const uf = new UnionFind(100);
			// Connect all even numbers
			for (let i = 0; i < 100; i += 2) {
				if (i + 2 < 100) {
					uf.union(i, i + 2);
				}
			}
			// Connect all odd numbers
			for (let i = 1; i < 100; i += 2) {
				if (i + 2 < 100) {
					uf.union(i, i + 2);
				}
			}

			expect(uf.count()).toBe(2);
			// All evens should be connected
			expect(uf.connected(0, 98)).toBe(true);
			expect(uf.connected(2, 96)).toBe(true);
			// All odds should be connected
			expect(uf.connected(1, 99)).toBe(true);
			expect(uf.connected(3, 97)).toBe(true);
			// Evens and odds should not be connected
			expect(uf.connected(0, 1)).toBe(false);
			expect(uf.connected(2, 3)).toBe(false);
		});
	});

	describe('edge cases', () => {
		it('should handle single element', () => {
			const uf = new UnionFind(1);
			expect(uf.count()).toBe(1);
			expect(uf.connected(0, 0)).toBe(true);
			uf.union(0, 0);
			expect(uf.count()).toBe(1);
			expect(uf.connected(0, 0)).toBe(true);
		});

		it('should handle two elements', () => {
			const uf = new UnionFind(2);
			expect(uf.count()).toBe(2);
			expect(uf.connected(0, 1)).toBe(false);
			uf.union(0, 1);
			expect(uf.count()).toBe(1);
			expect(uf.connected(0, 1)).toBe(true);
		});
	});
});

