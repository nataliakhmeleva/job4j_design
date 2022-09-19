package ru.job4j.ood.ocp;

/**
 * Нарушает принцип OCP, т.к. в случае необходимости использования других сортировок,
 * потребуется изменить класс и добавить методы.
 */

public class Compare {
    boolean sort(String a, String b) {
        return a.length() > b.length();
    }
}
