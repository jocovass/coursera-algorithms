/* *****************************************************************************
 *  Name: R way tries
 *
 *  Details:
 *  - use 'Nodes' to store chars.
 *  - each node has R children, on for each possible char
 *
 *  Search in a trie:
 *  - follow links corresponding to each character in the key
 *  - hit: when node has a non null value, return the value
 *  - miss: reach null link or node where search ends has null value, return null
 *
 *  Insertion:
 *  - follow links corresponding to each character in the key
 *  - encounter a null link: create a new node
 *  - encounter the last character of the key: set value in that node
 *
 *  Deletion:
 *  - Find the node corresponding to key and set value to null.
 *  - If node has null value and all null links, remove that node and recur.
 *
 *  Con:
 *  - uses a lot of space, R null links at each leaf.
 **************************************************************************** */

public class RWayTries<Value> {
    private static final int R = 256; // extended ASCII
    private Node root = new Node();

    /**
     * A node has a value plus references to R nodes.
     * <p>
     * value: - we use Object for the value type since there is no generic array
     * creation in Java(we will have to cast the value when we return)
     * <p>
     * next: - an array of nodes for each char.
     */
    private static class Node {
        Object value;
        private Node[] next = new Node[R];
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    /**
     * Follows the links corresponding to each char., if the value already
     * exists it will update with the new value otherwise it will insert a new
     * node for each char. that does not exist on the path.
     *
     * @param x   - The root Node from where to insert the new key.
     * @param key - The string to insert into the 'trie'.
     * @param val - The value to assign to the last char. Node of the 'key'.
     * @param p   - The position of the current char. in the 'key'.
     * @return Node - The new root with the updated values.
     */
    private Node put(Node x, String key, Value val, int p) {
        if (x == null) x = new Node();
        if (p == key.length()) {
            x.value = val;
            return x;
        }
        char c = key.charAt(p);
        x.next[c] = put(x.next[c], key, val, p + 1);
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    /**
     * Follows the links corresponding to each char., if position equals the length of
     * the key return 'x', if it runs into a null link return null, if x.value is null
     * return null.
     *
     * @param x   - The current node visited.
     * @param key - The string to check if it exists in the trie.
     * @param p   - The position of the current char. in the 'key'.
     * @return - The node at position key.length or null.
     */
    private Node get(Node x, String key, int p) {
        if (x == null) return null;
        if (p == key.length()) return x;
        char c = key.charAt(p);
        return get(x.next[c], key, p + 1);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int p) {
        if (x == null) return null;
        if (p == key.length()) {
            x.value = null;
            if (isEmpty(x)) return null;
            else return x;
        }
        char c = key.charAt(p);
        x.next[c] = delete(x.next[c], key, p + 1);
        return x;
    }

    private boolean isEmpty(Node x) {
        for (Node n : x.next) {
            if (n != null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RWayTries<Integer> trie = new RWayTries<Integer>();
        trie.put("apple", 1);
        trie.put("app", 2);

        System.out.println(trie.get("apple"));
        System.out.println(trie.get("app"));
        trie.delete("apple");
        System.out.println(trie.get("apple"));
        System.out.println(trie.get("app"));
    }
}
