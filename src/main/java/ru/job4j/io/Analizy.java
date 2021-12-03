package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            String start = null;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (start == null && (line.startsWith("400") || line.startsWith("500"))) {
                    out.print(line.split(" ")[1] + "; ");
                    start = line;
                } else if (start != null && (line.startsWith("200") || line.startsWith("300"))) {
                    out.println(line.split(" ")[1] + ";");
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
