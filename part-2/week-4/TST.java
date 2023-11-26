/* *****************************************************************************
 *  Name: Ternary search tries
 *  Details:
 *  - Follow links corresponding to each character in the key
 *  - if less take left link, if greater take right link, if equal take the middle
 *    link and move to the next key char.
 *
 *  Search:
 *  - hit: node where search ends and has a non null value.
 *  - miss: reach a null link or a node where search ends has a null value.
 *
 *
 **************************************************************************** */

public class TST<Value> {
    private Node root;

    private class Node {
        private Value value;
        private char c;
        private Node left, middle, right;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        }
        else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        }
        else if (d < key.length() - 1) {
            x.middle = put(x.middle, key, val, d + 1);
        }
        else {
            x.value = val;
        }
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        }
        else if (c > x.c) {
            return get(x.right, key, d);
        }
        else if (d < key.length() - 1) {
            return get(x.middle, key, d + 1);
        }
        else {
            return x;
        }
    }

    public static void main(String[] args) {

    }
}
