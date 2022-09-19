package ru.job4j.ood.ocp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Нарушает принцип OCP, т.к. в случае необходимости использования поиска по другим критериям,
 * потребуется изменить метод, добавить Predicate.
 */

public class Cat {
    private String name;
    private int age;
    private String breed;

    public String getName() {
        return name;
    }

    private static class SearchCat {
        List<Cat> sort(List<Cat> cats, String name) {
            return cats.stream()
                    .filter(n -> n.getName().contains(name))
                    .collect(Collectors.toList());
        }
    }
}
