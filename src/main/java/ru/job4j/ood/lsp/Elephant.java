package ru.job4j.ood.lsp;

public class Elephant extends Animals {
    public Elephant(int calories, int weight) {
        super(calories, weight);
    }

    @Override
    public void eat() {
        validate();
        /**
         * Нарушает принцип LSP, т.к. было усилено предусловие.
         */
        if (weight < 50) {
            calories = 5000;
        }
    }
}
