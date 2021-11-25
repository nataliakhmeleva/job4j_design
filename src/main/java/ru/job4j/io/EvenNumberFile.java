package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String line : text.toString().split(System.lineSeparator())) {
                int intLine = Integer.parseInt(line);
                if (intLine % 2 == 0) {
                    System.out.println(intLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
