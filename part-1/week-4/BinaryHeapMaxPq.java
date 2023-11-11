/*
 * Binary Max Heap
 *
 * Analysis:
 * - insert: O(log N)
 * - delete: O(log N)
 * - max: O(1)
 *
 * Heap sort
 * - can be done in place
 * - not stable
 * - worst case 2 N log N, best case N log N
 */
import java.util.Comparato;

public class BinaryHeapMaxPq<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    // This is just an example how heap sort could be implemented
    // It should accept an array that needs to be sorted
    public Key[] sort() {
        int L = N;
        Key[] sortedPq = (Key[]) new Comparable[N];
        // Build a heap using bottom up method
        // We start from the last sub heap and building up from
        // there
        for (int k = L / 2; k > 1; k--)
            sink(k);

        // After we have a valid max heap
        // We create a sorted array from max item to lowest item
        while (L > 1) {
            // First element is the largest in a MAX heap
            // and it is the last item in an ASC array
            sortedPq[L - 1] = pq[1];
            // After we saved the larges item in the sorted array
            // swap max with smallest in the heap
            swap(1, L);
            // Ensure we have a valid max heap
            sink(1);
            L--;
        }
        return sortedPq;
    }

    public BinaryHeapMaxPq(int capacity) {
        // We add 1 to the initial capacity to make arithmetic operations
        // For finding child/parent easier
        pq = (Key[]) new Comparator[capacity + 1];
    }

    public void insert(Key item) {
        pq[++N] = item;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1) {
            if (less(pq[k], pq[k / 2])) break;
            swap(k / 2, k);
            k = k / 2;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key delMax() {
        Key max = pq[1];
        swap(1, N - 1);
        pq[N--] = null;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(pq[j], pq[j + 1])) j++;
            if (less(pq[j], pq[k])) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int a, int b) {
        Key temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    private boolean less(Key a, Key b) {
        int comp = a.compareTo(b);
        return comp < 0;
    }

    public static void main(String[] args) {
        // some logic here
    }
}
