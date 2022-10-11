package ru.job4j.ood.lsp.parking;

import static ru.job4j.ood.lsp.parking.PassengerCar.SIZE_OF_PASSENGER_CAR;

public class Truck implements Car {
    private int size;

    public Truck(int size) {
        validate(size);
        this.size = size;
    }

    public void validate(int size) {
        if (size <= SIZE_OF_PASSENGER_CAR) {
            throw new IllegalArgumentException("The size must be more than 1");
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
