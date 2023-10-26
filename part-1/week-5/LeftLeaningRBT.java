/* *****************************************************************************
 *  Name:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

public class LeftLeaningRBT<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        public Key key;
        public Value val;
        public boolean color;
        public int count;
        public Node left, right;

        public Node(Key k, Value v, int cn, boolean c) {
            key = k;
            val = v;
            count = cn;
            color = c;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public boolean isEmpty() {
        return root == null;
    }

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

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return floor(x.left, key);
        }
        else if (comp > 0) {
            Node t = floor(x.right, key);
            if (t != null) return t;
        }
        return x;
    }

    public Key ciel(Key key) {
        Node x = ciel(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ciel(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp > 0) {
            return ciel(x.left, key);
        }
        else if (comp < 0) {
            Node t = ciel(x.right, key);
            if (t != null) return t;
        }
        return x;
    }

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
            return size(x);
        }
    }

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

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if (comp < 0) {
                x = x.left;
            }
            else if (comp > 0) {
                x = x.right;
            }
            else {
                return x.val;
            }
        }
        return null;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        // The order is very important
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        if (h == null) return;
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    private void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);
        int comp = key.compareTo(x.key);
        if (comp < 0) x.left = put(x.left, key, val);
        else if (comp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin()
    {
        root = deleteMin(root);
        root.color = BLACK;
    }
    private Node deleteMin(Node h)
    {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return fixUp(h);
    }

    private Node moveRedLeft(Node h)
    {
        colorFlip(h);
        if (isRed(h.right.left))
        {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            colorFlip(h);
        }
        return h;
    }
    private Node moveRedRight(Node h)
    {
        colorFlip(h);
        if (isRed(h.left.left))
        {
            h = rotateRight(h);
            colorFlip(h);
        }
        return h;
    }

    public Node fixUp(Node h)
    {
        if (isRead(h.right))
        {
            h = rotateLeft(h);
        }
        if (isRed(h.left) AND isRed(h.left.left))
        {
            h = rotateRight(h);
        }
        if (isRed(h.left) AND isRed(h.right))
        {
            flipColors(h);
        }
        return h;
    }

    public void delete(Key key)
    {
        root = delete(root, key);
        root.color = BLACK;
    }
    private Node delete(Node h, Key key)
    {
        if (key.compareTo(h.key) < 0)
        {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else
        {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0)
            {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return fixUp(h);
    }

    public static void main(String[] args) {

    }
}
