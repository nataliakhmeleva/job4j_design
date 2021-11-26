package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines().filter(LogFilter::findEl).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean findEl(String line) {
        String[] str = line.split(" ");
        return str.length > 1 && str[str.length - 2].equals("404");
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
            new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(s -> out.printf("%s%n", s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}