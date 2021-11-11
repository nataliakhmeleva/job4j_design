package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

public class SimpleMapTest {

    Map<Integer, String> map = new SimpleMap<>();

    @Test
    public void whenPutFirstEntry() {
        Assert.assertTrue(map.put(1, "Ivan"));
    }

    @Test
    public void whenPutDuplicateEntry() {
        map.put(1, "Ivan");
        Assert.assertFalse(map.put(1, "Ivan"));
    }

    @Test
    public void whenGetEntry() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        Assert.assertEquals(map.get(1), "Ivan");
        Assert.assertEquals(map.get(2), "Stepan");
        Assert.assertNotEquals(map.get(2), "Maria");
    }

    @Test
    public void whenGetNull() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        Assert.assertNull(map.get(3));
    }

    @Test
    public void whenExpandTable() {
        for (int i = 0; i < 10; i++) {
            map.put(i, "Ivan" + i);
        }
        Assert.assertEquals(map.get(8), "Ivan8");
    }

    @Test
    public void whenRemoveTrue() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        Assert.assertEquals(map.remove(1), true);
        Assert.assertTrue(map.remove(2));
    }

    @Test
    public void whenRemoveFalse() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        Assert.assertFalse(map.remove(3));
    }

    @Test
    public void whenPutAndRemoveThenIterator() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        map.remove(1);
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenCheckIterator() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddThenRemove() {
        map.put(1, "Ivan");
        map.put(2, "Stepan");
        Iterator<Integer> it = map.iterator();
        map.remove(1);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElement() {
        Iterator<Integer> it = map.iterator();
        it.next();
    }
}