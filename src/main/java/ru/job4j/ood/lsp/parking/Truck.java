package ru.job4j.ood.lsp.parking;

public class Truck extends Car {
    public Truck(int size) {
        super(size);
    }

    @Override
    public int getSize() {
        return 0;
    }
}
