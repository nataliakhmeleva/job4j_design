package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerDown() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n"
                + "500 10:57:01\n"
                + "400 10:58:01\n"
                + "200 10:59:01\n"
                + "500 11:01:02\n"
                + "200 11:02:02\n"
                + "200 10:56:01\n"
                + "500 10:57:01\n"
                + "400 10:58:01\n"
                + "500 10:59:01\n"
                + "400 11:01:02\n"
                + "200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(),
            is("10:57:01; 10:59:01;" + "11:01:02; 11:02:02;" + "10:57:01; 11:02:02;"));
    }
}