package ru.job4j.exam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

public class Finder {

    public static void run(ArgsName argsName) throws IOException {
        List<Path> pathList = new ArrayList<>();
        if ("name".equals(argsName.get("t"))) {
            pathList = Search.search(Paths.get(argsName.get("d")),
                p -> p.toFile().getName().equals(argsName.get("n")));
        }
        if ("mask".equals(argsName.get("t"))) {
            pathList = Search.search(Paths.get(argsName.get("d")),
                p -> p.toFile().getName().contains(argsName.get("n")));
        }
        if ("regex".equals(argsName.get("t"))) {
            pathList = Search.search(Paths.get(argsName.get("d")),
                p -> p.toFile().getName().matches(argsName.get("n")));
        }
        saveToFile(pathList, argsName.get("o"));
    }

    private static ArgsName checkExistArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length < 4 || (argsName.get("d") == null) || (argsName.get("n") == null) || (
            argsName.get("t") == null) || (argsName.get("o") == null)) {
            throw new IllegalArgumentException("Invalid args.");
        }
        if (new File(argsName.get("d")).length() == 0 || !new File(argsName.get("d"))
            .isDirectory() || !new File(argsName.get("d")).exists()) {
            throw new IllegalArgumentException(
                "The file is in the wrong directory or doesn't exist.");
        }
        return argsName;
    }

    private static void saveToFile(List<Path> log, String path) {
        try (PrintWriter out = new PrintWriter(
            new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = checkExistArgs(args);
        run(argsName);
    }
}
