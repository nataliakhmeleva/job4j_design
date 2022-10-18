package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.PassengerCar.SIZE_OF_PASSENGER_CAR;

public class TruckParking implements Parking {
    private int placeCount;
    private final List<Car> cars;

    public TruckParking(int placeCount) {
        this.placeCount = placeCount;
        cars = new ArrayList<>(placeCount);
    }

    @Override
    public boolean add(Car car) {
        if (car.getSize() <= SIZE_OF_PASSENGER_CAR) {
            return false;
        }
        if (placeCount == 0) {
            return false;
        }
        cars.add(car);
        placeCount--;
        return true;
    }

    @Override
    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
