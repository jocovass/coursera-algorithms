import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int size;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == q.length) {
            resize(2 * q.length);
        }

        q[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniformInt(0, size);
        Item item = q[index];
        q[index] = q[--size];
        q[size] = null;

        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniformInt(0, size);
        return q[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = size;

        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (current > 0) {
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniformInt(0, current);
            Item item = q[index];
            q[index] = q[--current];
            q[size] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        StdOut.println("Queue is empty: " + rq.isEmpty());
        for (int i = 0; i < 30; i++) {
            rq.enqueue(i);
            if (i % 3 == 0) {
                rq.dequeue();
            }
        }
        StdOut.println("Size: " + rq.size());
        StdOut.print("Random pick ups: ");
        for (int j = 0; j < 5; j++) {
            StdOut.print(rq.sample() + " ");
        }
    }
}
