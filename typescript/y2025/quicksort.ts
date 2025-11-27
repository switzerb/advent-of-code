function quicksort(arr: number[], lowIdx = 0, highIdx = arr.length - 1): void {
    if(arr.length <= 1) return;
    
    if (lowIdx < highIdx) {
        const pivotIndex = partition(arr, lowIdx, highIdx);
        quicksort(arr, lowIdx, pivotIndex - 1);
        quicksort(arr, pivotIndex + 1, highIdx);
    }
}

function partition(arr: number[], lowIdx: number, highIdx: number): number {
    const pivot = arr[highIdx];
    let i = lowIdx - 1;

    for (let j = lowIdx; j < highIdx; j++) {
        if (arr[j] <= pivot) {
            i++;
            [arr[i], arr[j]] = [arr[j], arr[i]];
        }
    }

    [arr[i + 1], arr[highIdx]] = [arr[highIdx], arr[i + 1]];
    return i + 1;
}

const arr = [3, 6, 8, 10, 1, 2, 1];
quicksort(arr);
console.log(arr);