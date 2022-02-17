package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        if (checkExistArgs(argsName)) {
            try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(System.lineSeparator())) {
                String[] lines = scanner.next().split(argsName.get("delimiter"));
                String[] filters = argsName.get("filter").split(",");
                List<Integer> numberColumns = new ArrayList<>();
                for (String filter : filters) {
                    for (int i = 0; i < lines.length; i++) {
                        if (lines[i].equals(filter)) {
                            numberColumns.add(i);
                        }
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.join(argsName.get("delimiter"), filters))
                    .append(System.lineSeparator());
                while (scanner.hasNext()) {
                    String[] line = scanner.next().split(argsName.get("delimiter"));
                    List<String> tmp = new ArrayList<>();
                    for (Integer numberColumn : numberColumns) {
                        tmp.add(line[numberColumn]);
                    }
                    stringBuilder.append(String.join(argsName.get("delimiter"), tmp))
                        .append(System.lineSeparator());
                }

                if (!"stdout".equals(argsName.get("out"))) {
                    saveToFile(stringBuilder, argsName.get("out"));
                } else {
                    System.out.println(stringBuilder);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean checkExistArgs(ArgsName argsName) {
        if ((argsName.get("path") == null) || (argsName.get("delimiter") == null) || (
            argsName.get("out") == null) || (argsName.get("filter") == null)) {
            throw new IllegalArgumentException("Invalid args.");
        }

        if (new File(argsName.get("path")).length() == 0 || new File(argsName.get("path"))
            .isDirectory() || !new File(argsName.get("path")).exists()) {
            throw new IllegalArgumentException(
                "The file is in the wrong directory or doesn't exist.");
        }

        return true;
    }

    private static void saveToFile(StringBuilder log, String path) {

        try (PrintWriter out = new PrintWriter(
            new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            out.write(String.valueOf(log));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String data = String.join(
            System.lineSeparator(),
            "name;age;last_name;education",
            "Tom;20;Smith;Bachelor",
            "Jack;25;Johnson;Undergraduate",
            "William;30;Brown;Secondary special"
        );
        File file = new File("source.csv");
        File target = new File("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
            "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=name,age"
        });
        ArgsName argsName2 = ArgsName.of(new String[]{
            "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(),
            "-filter=name,age"
        });
        try {
            Files.writeString(file.toPath(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        handle(argsName);
        handle(argsName2);
        try {
            Files.readString(target.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}