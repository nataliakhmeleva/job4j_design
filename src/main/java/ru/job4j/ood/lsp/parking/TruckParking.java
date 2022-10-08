package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class TruckParking implements Parking {
    private int places;
    private List<Car> trucks = new ArrayList<>();

    public TruckParking(int places) {
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
