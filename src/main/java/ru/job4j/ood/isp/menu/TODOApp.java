package ru.job4j.ood.isp.menu;

import java.util.Objects;
import java.util.Scanner;

public class TODOApp {
    public static final int ADD_ITEM = 1;
    public static final int GET_MENU = 2;

    public static final int ADD_ROOT_ITEM = 1;
    public static final int ADD_SUB_ITEM = 2;

    public static final String SELECT = "Выберите меню";
    public static final String ENTER_ROOT = "Введите наименование корневого пункта меню";
    public static final String ENTER_SUB = "Введите наименование подпункта меню";
    public static final String EXIT = "Конец работы";
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static final String GENERAL_MENU = """
                Введите 1, чтобы добавить пункт в меню.
                Введите 2, чтобы вывести меню на экран.
                Введите любое другое число для выхода.
            """;

    public static final String SUB_MENU = """
                Введите 1, чтобы добавить корневой пункт в меню.
                Введите 2, чтобы добавить подпункт в меню.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        startGeneralMenu(scanner, menu);
    }

    public static void startSubmenu(Scanner scanner, Menu menu) {
        boolean run = true;
        while (run) {
            System.out.println(SUB_MENU);
            System.out.println(SELECT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (ADD_ROOT_ITEM == choice) {
                System.out.println(ENTER_ROOT);
                String text = scanner.nextLine();
                if (!Objects.equals(text, Menu.ROOT)) {
                    menu.add(Menu.ROOT, text, STUB_ACTION);
                }
            } else if (ADD_SUB_ITEM == choice) {
                System.out.println(ENTER_ROOT);
                String parent = scanner.nextLine();
                System.out.println(ENTER_SUB);
                String child = scanner.nextLine();
                menu.add(parent, child, STUB_ACTION);

            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void startGeneralMenu(Scanner scanner, Menu menu) {
        MenuPrinter printer = new ConsoleMenuPrinter();
        boolean run = true;
        while (run) {
            System.out.println(GENERAL_MENU);
            System.out.println(SELECT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (ADD_ITEM == choice) {
                startSubmenu(scanner, menu);
            } else if (GET_MENU == choice) {
                printer.print(menu);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
