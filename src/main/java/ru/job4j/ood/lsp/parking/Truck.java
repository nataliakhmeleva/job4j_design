package ru.job4j.ood.lsp.parking;

public class Truck extends Car {
    private int size;

    public Truck(int size) {
        validate(size);
        this.size = size;
    }

    public void validate(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("The size must be more than 1");
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
