package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
            new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File files : sources) {
                zip.putNextEntry(new ZipEntry(files.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(files))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        File directory = new File(argsName.get("d"));
        File output = new File(argsName.get("o"));

        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid args.");
        }

        if (argsName.get("d") == null || argsName.get("o") == null || !directory.isDirectory()) {
            throw new IllegalArgumentException(
                "The file is in the wrong directory or doesn't exist.");
        }
        List<Path> file = Search.search(Paths.get(argsName.get("d")),
            p -> !p.toFile().getName().endsWith(argsName.get("e")));

        List<File> fileList = file.stream().map(Path::toFile).collect(Collectors.toList());

        packFiles(fileList, output);
    }
}
