package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> biPredicate = (s1, s2) -> comparator.compare(s1, s2) > 0;
        return filter(value, biPredicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> biPredicate = (s1, s2) -> comparator.compare(s1, s2) < 0;
        return filter(value, biPredicate);
    }

    public <T> T filter(List<T> list, BiPredicate<T, T> biPredicate) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty.");
        }
        T result = list.get(0);
        for (T element : list) {
            if (biPredicate.test(element, result)) {
                result = element;
            }
        }
        return result;
    }
}