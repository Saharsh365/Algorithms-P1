/* *****************************************************************************
 *  Name:              Saharsh Maloo
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // instance fields
    private Node first = null;
    private Node last = null;
    private int size;

    // Node
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
    }

    private boolean firstElement(Item item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
            return true;
        }
        return false;
    }

    private void nullArg(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void emptyList() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        nullArg(item);

        size++;

        if (firstElement(item)) return;

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        oldFirst.prev = first;
    }

    // add the item to the back
    public void addLast(Item item) {
        nullArg(item);

        size++;

        if (firstElement(item)) return;

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        oldLast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        emptyList();

        Item item = first.item;
        first = first.next;
        if (first == null) last = null;
        if (first != null) first.prev = null;

        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        emptyList();

        Item item = last.item;
        last = last.prev;
        if (last == null) first = null;
        if (last != null) last.next = null;

        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();

            Item data = current.item;
            current = current.next;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();

        deck.addFirst(1);
        System.out.println(deck.removeFirst());

        System.out.println(deck.isEmpty());

        deck.addFirst(0);
        deck.addFirst(1);
        deck.addFirst(2);
        deck.addFirst(3);
        deck.addLast(6);
        deck.addLast(7);
        deck.addLast(8);
        deck.addLast(9);

        System.out.println(deck.isEmpty());

        System.out.println(deck.size());

        System.out.println(deck.removeFirst());
        System.out.println(deck.removeLast());

        for (int i : deck) {
            System.out.print(i + " ");
        }
    }
}
