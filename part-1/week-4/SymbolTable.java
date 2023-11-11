/*
 * Symbol Tables
 *
 * Analysis:
 * - get: O(log N)
 * - put: O(log N)
 */
public class SymbolTable<Key extends Comparable<Key>, Value> {
    // Store keys and values in two array
    private Key[] keys;
    private Value[] vals;
    private int N;

    // Keys has to be comparable and immutable
    public SymbolTable(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // Returns the key value if exists or NULL if it doesn't
    public Value get(Key key) {
        if (is Empty()) return null;
        // Rank will return the position of the item if it exists otherwise
        // will return the position where it should be inserted
        // we can laverage this information and use the same function for the put method
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public void put(Key key, Value value) {
        if (N == keys.length) {
            resize(2 * keys.length); // Resize the arrays if they are full
        }

        int i = rank(key);
        // If item at position i matches key we just want to override the value
        // current implementation we are not storing duplicates
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }

        // Shift all items to the right of the item by one and insert the new item
        // In sorted order
        for (int l = N; l > i; l--) {
            keys[l] = keys[l - 1];
            vals[l] = vals[l - 1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    // Double the size of the array if it's full
    private void resize(int capacity) {
        Key[] newKeys = (Key[]) new Comparable[capacity];
        Value[] newVals = (Value[]) new Object[capacity];

        for (int i = 0; i < N; i++) {
            newKeys[i] = keys[i];
            newVals[i] = vals[i];
        }

        keys = newKeys;
        vals = newVals;
    }

    // Does sorting and searching, using binary search
    private int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comp = keys[mid].compareTo(key);
            if (comp < 0) {
                lo = mid + 1;
            }
            else if (comp > 0) {
                hi = mid - 1;
            }
            else return mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> st = new SymbolTable<String, Integer>(100);
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        System.out.println("Size: " + st.size());
        st.put("E", 6);

        System.out.println("E: " + st.get("E"));
        System.out.println("Size: " + 0 / 2);

        for (int i = 0; i < st.size(); i++) {
            // System.out.println(st.get(st.keys[i]));
        }
    }
}
