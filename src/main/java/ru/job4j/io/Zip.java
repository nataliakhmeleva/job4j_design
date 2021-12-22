package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
            new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path files : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(files)));
                try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(String.valueOf(files)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid args.");
        }

        ArgsName argsName = ArgsName.of(args);

        if ((argsName.get("d") == null) || (argsName.get("o") == null) || !(new File(
            argsName.get("d")).isDirectory())) {
            throw new IllegalArgumentException(
                "The file is in the wrong directory or doesn't exist.");
        }

        List<Path> file = new ArrayList<>(Search.search(Paths.get(argsName.get("d")),
            p -> !p.toFile().getName().endsWith(argsName.get("e"))));

        packFiles(file, new File(argsName.get("o")));
    }
}
