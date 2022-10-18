package ru.job4j.ood.lsp.parking;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UniversalParking implements Parking {

    private final Parking carParking;

    private final Parking truckParking;

    public UniversalParking(int carCount, int truckCount) {
        this.carParking = new PassengerCarParking(carCount);
        this.truckParking = new TruckParking(truckCount);
    }

    @Override
    public boolean add(Car car) {
        return truckParking.add(car) || carParking.add(car);
    }

    @Override
    public List<Car> getCars() {
        return Stream.concat(carParking.getCars().stream(), truckParking.getCars().stream())
                .collect(Collectors.toList());
    }
}
