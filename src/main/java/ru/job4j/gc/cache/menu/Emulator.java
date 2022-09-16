package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;


import java.io.File;
import java.util.Scanner;

public class Emulator {
    public static final int LOAD_FILE_INTO_CACHE = 1;
    public static final int GET_FILE_FROM_CACHE = 2;

    public static final String SELECT = "Выберите меню";
    public static final String SHOW = "Укажите директорию для кэширования";
    public static final String LOAD = "Для загрузки содержимого файла в кэш, введите имя файла";
    public static final String GET = "Для получение содержимое файла из кэша, введите имя файла";
    public static final String SUCCESS = "Успешное кэширование";
    public static final String EXIT = "Конец работы";


    public static final String MENU = """
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;


    public static void main(String[] args) {
        DirFileCache dir = null;
        System.out.println(SHOW);
        Scanner scanner = new Scanner(System.in);
        String directory = scanner.nextLine();
        if (validate(directory)) {
            dir = new DirFileCache(directory);
        }

        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (LOAD_FILE_INTO_CACHE == userChoice) {
                System.out.println(LOAD);
                String text = scanner.nextLine();
                dir.put(text, dir.get(text));
                System.out.println(SUCCESS);
            } else if (GET_FILE_FROM_CACHE == userChoice) {
                System.out.println(GET);
                String text = scanner.nextLine();
                System.out.println(dir.get(text));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static boolean validate(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Directory not found");
        }
        return true;
    }
}
