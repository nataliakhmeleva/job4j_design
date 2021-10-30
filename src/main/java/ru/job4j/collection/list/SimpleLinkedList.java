package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;
    private int modCount;
    private int size;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<E>(value, null);
        if (first == null) {
            first = newNode;
        } else {
            first.next = newNode;
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
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return newNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                nextNode = newNode;
                newNode = nextNode.next;
                return nextNode.item;
            }

        };
    }

    private class Node<E> {

        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}