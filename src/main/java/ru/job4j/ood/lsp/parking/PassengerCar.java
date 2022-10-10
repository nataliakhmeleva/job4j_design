package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Car {
    public final static int SIZE_OF_PASSENGER_CAR = 1;

    @Override
    public int getSize() {
        return SIZE_OF_PASSENGER_CAR;
    }
}
