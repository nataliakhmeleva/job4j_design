package ru.job4j.ood.lsp;

public class Dog extends Animals {
    public Dog(int calories, int weight) {
        super(calories, weight);
    }

    @Override
    public void eat() {
        /**
         * Нарушает принцип LSP, т.к. не сделана проверка на валидное состояние.
         */
        if (weight < 5 && weight > 20) {
            calories = 2000;
        }
    }
}
