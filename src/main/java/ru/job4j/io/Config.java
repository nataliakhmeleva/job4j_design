package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                .filter(this::checkFormat)
                .map(line -> line.split("="))
                .forEach(str -> values.put(str[0], str[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean countSymbol(String line) {
        char[] chars = line.toCharArray();
        return IntStream.range(0, chars.length).filter(i -> chars[i] == '=').count() == 1;
    }

    public boolean checkFormat(String line) {
        if (line.startsWith("=") || line.endsWith("=") || !countSymbol(line)) {
            throw new IllegalArgumentException();
        }
        return true;

    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}