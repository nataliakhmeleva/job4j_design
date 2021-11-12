package ru.job4j.collection.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
            tree.findBy(6).isPresent(),
            is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
            tree.findBy(7).isPresent(),
            is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenChildrenMoreTwo() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 5);
        tree.add(2, 6);
        tree.add(1, 8);
        assertFalse(tree.isBinary());
    }

    @Test
    public void whenTreeIsBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 8);
        tree.add(1, 7);
        tree.add(2, 4);
        assertTrue(tree.isBinary());
    }
}