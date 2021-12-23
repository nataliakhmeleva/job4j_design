package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botAnswer = readPhrases();
        List<String> logs = new ArrayList<>();
        boolean stopBot = false;

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            String phrase = buffer.readLine();
            logs.add(phrase);
            while (!phrase.equals(OUT)) {
                if (phrase.equals(STOP)) {
                    stopBot = true;
                }
                if (phrase.equals(CONTINUE)) {
                    stopBot = false;
                }
                if (!stopBot) {
                    String randomAnswer = botAnswer.get(new Random().nextInt(botAnswer.size() - 1));
                    System.out.println(randomAnswer);
                    logs.add(randomAnswer);
                }
                phrase = buffer.readLine();
                logs.add(phrase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> listPhrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            listPhrases = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
            new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/dialog.txt", "./data/answers.txt");
        cc.run();
    }
}
