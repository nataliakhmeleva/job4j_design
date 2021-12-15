package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toAbsolutePath().toFile().length(),
            file.toAbsolutePath().toFile().getName());
        if (duplicates.containsKey(fileProperty)) {
            duplicates.get(fileProperty).add(file.toAbsolutePath());
        } else {
            duplicates.put(fileProperty, new ArrayList<>(List.of(file.toAbsolutePath())));
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        return duplicates.values().stream()
            .filter(paths -> paths.size() > 1)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}