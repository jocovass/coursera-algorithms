import java.util.Comparator;

public class UnorderedMaxPq<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public UnorderedMaxPq(int capacity) {
        pq = (Key[]) new Comparator[capacity];
    }

    public void insert(Key item) {
        pq[N++] = item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(pq[max], pq[i])) {
                max = i;
            }
        }
        swap(max, N - 1);
        return pq[--N];
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
