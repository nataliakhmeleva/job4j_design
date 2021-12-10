package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        File firstFile = new File(args[0]);
        File secondFile = new File(args[1]);

        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid args.");
        }

        if (!firstFile.exists() || !firstFile.isDirectory()) {
            throw new IllegalArgumentException(
                "The file is in the wrong directory or doesn't exist.");
        }

        if (!Objects.equals(secondFile, new File(".js"))) {
            throw new IllegalArgumentException("The file must have the extension \".js\".");
        }

        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
