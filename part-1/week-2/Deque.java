import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // Save the old reference
        Node second = first;
        // Create the new first node
        first = new Node();
        first.item = item;
        first.prev = null;
        // Set the pointer to the previous first element
        first.next = second;

        if (second == null) {
            // If last is null that means this is the first item in the queue,
            // so we set the last to point to the first node
            last = first;
        }
        else {
            // Second points back to the first
            second.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node penult = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = penult;
        // If the oldLast was null that means the queue was empty before,
        // so we set the first to point to the same node otherwise the
        // penultimate points to the last node
        if (penult == null) {
            first = last;
        }
        else {
            penult.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        else {
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> q = new Deque<Integer>();
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                q.addFirst(i);
            }
            else {
                q.addLast(i);
            }
        }
        StdOut.println(q.size());
        for (int i = 0; i < 12; i++) {
            if (i % 3 == 0) {
                q.removeLast();
            }
            else {
                q.removeFirst();
            }
        }
        StdOut.println(q.size());
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                q.addFirst(i);
            }
            else {
                q.addLast(i);
            }
        }
        StdOut.println(q.size());
    }
}
