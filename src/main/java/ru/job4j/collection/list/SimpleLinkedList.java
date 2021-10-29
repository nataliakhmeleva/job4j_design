package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleLinkedList<E> implements List<E> {

    private Node<E> last;
    private Node<E> first;
    private int modCount;
    private int size;

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<E>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        Node<E> newNode = first;
        for (int i = 0; i < index; i++) {
            newNode = newNode.next;
        }
        return newNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> newNode = first;
            Node<E> nextNode;
            private int cursor = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                nextNode = newNode;
                newNode = nextNode.next;
                cursor++;
                return nextNode.item;
            }

        };
    }

    private class Node<E> {

        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}