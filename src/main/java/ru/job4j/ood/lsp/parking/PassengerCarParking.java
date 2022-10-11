package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.PassengerCar.SIZE_OF_PASSENGER_CAR;

public class PassengerCarParking implements Parking {
    private int places;
    private List<Car> cars;

    public PassengerCarParking(int places) {
        this.places = places;
        cars = new ArrayList<>(places);
    }

    @Override
    public boolean add(Car car) {
        boolean rsl = false;
        if (places >= SIZE_OF_PASSENGER_CAR && car.getSize() == SIZE_OF_PASSENGER_CAR) {
            cars.add(car);
            places--;
            rsl = true;
        }
        if (places >= car.getSize() && car.getSize() > SIZE_OF_PASSENGER_CAR) {
            cars.add(car);
            places -= car.getSize();
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
