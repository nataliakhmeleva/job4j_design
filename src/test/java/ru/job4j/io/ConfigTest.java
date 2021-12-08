package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Natalia Khmeleva"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Natalia Khmeleva"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
        assertThat((Matchers.nullValue()), is("Natalia Khmeleva"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUncorrect() {
        String path = "./data/uncorrect.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Natalia Khmeleva"));
    }
}