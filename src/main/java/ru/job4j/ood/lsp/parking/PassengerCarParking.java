package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class PassengerCarParking implements Parking {
    private int places;
    private List<Car> passengerCars = new ArrayList<>();

    public PassengerCarParking(int places) {
        this.places = places;
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean search(Car car) {
        return false;
    }

    @Override
    public List<Car> getCars() {
        return null;
    }
}
