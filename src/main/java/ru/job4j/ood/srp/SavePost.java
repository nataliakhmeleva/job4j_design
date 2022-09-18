package ru.job4j.ood.srp;

/**
 * Нарушает принцип SRP, т.к. объединяет в одной реализации разные абстракции.
 * Необходимо выделить интерфейс Save  и три его различные реализации.
 */
public class SavePost<T> {
    T saveToFile(String text) {
        return null;
    }

    T saveToHTML(String text) {
        return null;
    }

    T saveToDB(String text) {
        return null;
    }
}
