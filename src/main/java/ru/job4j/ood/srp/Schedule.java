package ru.job4j.ood.srp;

import java.util.Comparator;
import java.util.Date;

/**
 * Нарушает принцип SRP, т.к. корпаратор один вид сортировки.
 * При расширении могут потребоваться другие корпараторы.
 */
public class Schedule {
    void sortedDate() {
        Comparator<Date> comparator = Comparator.naturalOrder();
    }
}
