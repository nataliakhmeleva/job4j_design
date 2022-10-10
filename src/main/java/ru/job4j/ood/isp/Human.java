package ru.job4j.ood.isp;

public class Human implements Action {
    private String name = "Иван";

    @Override
    public void eat() {
        System.out.println(name + " ест мясо и гречку.");
    }

    @Override
    public void sleep() {
        System.out.println(name + " спит с 12-ти до 7-ми.");
    }

    @Override
    public void run() {
        System.out.println(name + " бегает по утрам.");
    }

    @Override
    public void readBook() {
        System.out.println(name + " читает книги по выходным.");
    }
}
