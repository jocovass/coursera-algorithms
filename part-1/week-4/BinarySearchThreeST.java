/* *****************************************************************************
 *  Name:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

public class BinarySearchThreeST<Key extends Comparable<Key>, Value> {
    Node root;

    private class Node {
        public Key key;
        public Value val;
        public int count;
        public Node left, right;

        public Node(Key k, Value v, int c) {
            key = k;
            val = v;
            count = c;
        }
    }

    public int size() {
        return size(root);
    }

    /**
     * Returns the count of the root node if it exists 0 otherwise
     */
    private int size(Node x) {
        if (root == null) return 0;
        return root.count;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * Insert a new node into the ST
     */
    private Node put(Node root, Key key, Value val) {
        if (root == null) {
            return new Node(key, val, 1);
        }
        int comp = root.key.compareTo(key);
        if (comp < 0) {
            // we go right
            root.right = put(root.right, key, val);
        }
        else if (comp > 0) {
            // we go left
            root.left = put(root.left, key, val);
        }
        else {
            // key already exists update value
            root.val = val;
        }
        // Update count for each node after new node was inserted
        root.count = 1 + size(root.left) + size(root.right);
        return root;
    }

    /**
     * Go to the left until node.left is null, and return node.
     */
    public Value min() {
        Node x = min(root);
        if (x == null) return null;
        return x.val;
    }

    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * Go to the right until node.right is null, and return node.
     */
    public Value max() {
        Node x = max(root);
        if (x == null) return null;
        return x.val;
    }

    private Node max(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    /**
     * If key exists return it, otherwise return the key that is
     * before the key we are looking for.
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        // If we found a match
        if (comp == 0) return x;
        // If 'key' is less than the current node
        if (comp < 0) {
            return floor(x.left, key);
        }
        // If 'key' is greater than current node
        Node t = floor(x.right, key);
        if (t != null) return null;
        return x;
    }

    /**
     * Return key if it exists, otherwise return the key that is
     * the next item in order we are looking for.
     */
    public Key ceil(Key key) {
        Node x = ceil(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        // If we found a match
        if (comp == 0) return x;
        // If 'key' is bigger than the current node
        if (comp > 0) {
            return ceil(x.right, key);
        }
        // If 'key' is less than current node
        Node t = ceil(x.left, key);
        if (t != null) return null;
        return x;
    }

    /**
     * Drill down until we find a node that's left pointer is null, it means that
     * is the smallest value. Return current node with right side. As we go up
     * from the recursive function calls update the count of each node.
     * <p>
     * "deleteMax" should work the same way but on the other side.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * If key equals current node key:
     * - Current node has no left node return right side of current node.
     * - Current node has no right node return left side of current node.
     * - Else find the minimum of the right side of current node and assign it to
     * the current node.
     * - Delete the minimum of the right side of current node
     * - Fix pointers for current node, so that it points to the nodes where the
     * deleted node used to point.
     * <p>
     * Update counts for each node on the way up.
     */
    public void deleteKey(Key key) {
        root = deleteKey(root, key);
    }

    private Node deleteKey(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = deleteKey(x.left, key);
        }
        else if (comp > 0) {
            x.right = deleteKey(x.right, key);
        }
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Return the rank of a key.
     * For this we can use the 'count' property we store on each node.
     * <p>
     * If node is null return 0.
     * <p>
     * If we found the key return the count of the left node.
     * <p>
     * If key is less than the current node return and recursively
     * call rank with its left node.
     * <p>
     * If key is bigger than current node return 1(for the current node) + size(left side of current
     * node)
     * because if key is bigger than current node we know key is bigger than all nodes on the left
     * side
     * of current node + recursively call rank with right size of current node.
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return rank(x.left, key);
        }
        else if (comp > 0) {
            return 1 + size(x.left) + rank(x.right, key);
        }
        else {
            return size(x.left);
        }
    }

    /**
     * Do a depth for search traversal and return the keys in ascending order.
     */
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public static void main(String[] args) {
        BinarySearchThreeST<String, Integer> st = new BinarySearchThreeST<String, Integer>();
        st.put("five", 5);
    }
}
