package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    public static final String SPACE = "-";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo printer : menu) {
            System.out.println(insertSpaces(printer.getNumber()) + printer.getName());
        }
    }

    private String insertSpaces(String number) {
        int count = number.split("\\.").length - 1;
        return SPACE.repeat(count) + number;
    }
}
