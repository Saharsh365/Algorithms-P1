/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // instance fields
    private Item[] arr = (Item[]) new Object[1];
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    private void nullArg(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void emptyList() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        nullArg(item);
        if (size == arr.length) resize(arr.length * 2);

        arr[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        emptyList();

        int randomIndex = StdRandom.uniformInt(size);
        Item item = arr[randomIndex];
        arr[randomIndex] = arr[size - 1];
        arr[size - 1] = null;
        size--;

        if (size > 0 && size < arr.length / 4) resize(arr.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        emptyList();

        int randomIndex = StdRandom.uniformInt(size);
        Item item = arr[randomIndex];

        return item;
    }

    // return an iterator over items in random order
    public Iterator<Item> iterator() {
        // StdRandom.shuffle(arr, 0, size);
        return new RQueueIterator();
    }

    private class RQueueIterator implements Iterator<Item> {
        private Item[] copy = arr.clone();
        private int copySize = size;

        private RQueueIterator() {
            StdRandom.shuffle(copy, 0, copySize);
        }

        public boolean hasNext() {
            return copySize > 0;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();

            int randomIndex = StdRandom.uniformInt(copySize);
            Item item = copy[randomIndex];
            copy[randomIndex] = copy[copySize - 1];
            copy[copySize - 1] = null;
            copySize--;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        System.out.println(queue.isEmpty());

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);

        System.out.println(queue.isEmpty());

        System.out.println(queue.size());

        System.out.println(queue.sample());
        System.out.println(queue.dequeue());

        for (int i : queue) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i : queue) {
            System.out.print(i + " ");
        }
    }
}
