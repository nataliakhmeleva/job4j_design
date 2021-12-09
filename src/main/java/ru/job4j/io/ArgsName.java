package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (checkIsEmptyArray(args)) {
            Arrays.stream(args)
                .filter(this::checkFormat)
                .map(arg -> arg.split("="))
                .forEach(str -> values.put(str[0].replace("-", ""), str[1]));
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

    public boolean checkIsEmptyArray(String[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
