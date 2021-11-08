package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfNEquals3() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeIf(input, (n -> n == 3));
        assertThat(input, is(Arrays.asList(1, 2, 4, 5)));
    }

    @Test
    public void whenReplaceIfNMore4() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, n -> n >= 4, 0);
        assertThat(input, is(Arrays.asList(1, 2, 3, 0, 0)));
    }

    @Test
    public void whenRemoveAllElementsFromList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(3, 4, 5)));
    }

    @Test
    public void whenAddAfterNotLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 1, 8);
        assertThat(input, is(Arrays.asList(0, 1, 8, 2)));

    }
}