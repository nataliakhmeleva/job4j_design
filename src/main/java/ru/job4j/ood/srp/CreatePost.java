package ru.job4j.ood.srp;

import java.util.List;

/**
 * Нарушает принцип SRP, т.к. объединяет в одном интерфейсе разные абстракции.
 */
public interface CreatePost<T> {
    void white();

    List<T> save();
}
