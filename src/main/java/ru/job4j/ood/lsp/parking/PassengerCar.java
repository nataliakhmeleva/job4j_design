package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Car {
    public PassengerCar(int size) {
        super(size);
    }

    @Override
    public int getSize() {
        return 0;
    }
}
