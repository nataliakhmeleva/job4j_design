package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.PassengerCar.SIZE_OF_PASSENGER_CAR;

public class PassengerCarParking implements Parking {
    private int placeCount;
    private final List<Car> cars;

    public PassengerCarParking(int placeCount) {
        this.placeCount = placeCount;
        cars = new ArrayList<>(placeCount);
    }

    @Override
    public boolean add(Car car) {
        if (addPassCar(car)) {
            return true;
        }
        return addTruck(car);
    }

    private boolean addPassCar(Car car) {
        if (car.getSize() != SIZE_OF_PASSENGER_CAR) {
            return false;
        }
        if (placeCount == 0) {
            return false;
        }
        cars.add(car);
        placeCount--;
        return true;
    }

    private boolean addTruck(Car car) {
        if (car.getSize() <= SIZE_OF_PASSENGER_CAR) {
            return false;
        }
        if (placeCount < car.getSize()) {
            return false;
        }
        cars.add(car);
        placeCount -= car.getSize();
        return true;
    }

    @Override
    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
