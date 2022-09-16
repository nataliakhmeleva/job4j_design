package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    MaxMin maxMin = new MaxMin();
    List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
    List<String> stringList = List.of("apple", "orange", "tail");

    @Test
    public void whenMaxInteger() {
        Comparator<Integer> comparator = Integer::compareTo;
        assertThat(maxMin.max(list, comparator)).isEqualTo(7);
    }

    @Test
    public void whenMinInteger() {
        Comparator<Integer> comparator = Integer::compareTo;
        assertThat(maxMin.min(list, comparator)).isEqualTo(1);
    }

    @Test
    public void whenMaxString() {
        Comparator<String> comparator = String::compareTo;
        assertThat(maxMin.max(stringList, comparator)).isEqualTo("tail");
    }

    @Test
    public void whenMinString() {
        Comparator<String> comparator = Comparator.comparing(String::length);
        assertThat(maxMin.min(stringList, comparator)).isEqualTo("tail");
    }

    @Test
    public void whenListIsEmpty() {
        Comparator<String> comparator = Comparator.comparing(String::length);
        assertThatThrownBy(() -> maxMin.max(List.of(), comparator))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}