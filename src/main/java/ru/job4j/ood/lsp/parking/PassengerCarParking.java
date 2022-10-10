package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class PassengerCarParking implements Parking {
    private int places;
    private List<Car> cars;

    public PassengerCarParking(int places) {
        this.places = places;
        cars = new ArrayList<>(places);
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
