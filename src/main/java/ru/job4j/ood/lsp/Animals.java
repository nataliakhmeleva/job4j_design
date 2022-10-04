package ru.job4j.ood.lsp;

public class Animals {
    protected int calories;
    protected int weight;

    public Animals(int calories, int weight) {
        this.calories = calories;
        this.weight = weight;
    }

    public void eat() {
        validate();
        /**
         * Нарушает принцип LSP, т.к. обращение к свойствам приводит к ограничению колтчества объектов.
         */
        if (weight < 5 && weight > 20) {
            calories = 2000;
        }
    }

    public void validate() {
        if (weight < 0) {
            throw new IllegalArgumentException("Invalid weight!");
        }
    }
}
