package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Park {
    private List<PassengerCarParking> passengerCarParkingList;
    private List<TruckParking> truckParkingList;

    public Park(List<PassengerCarParking> passengerCarParkingList, List<TruckParking> truckParkingList) {
        this.passengerCarParkingList = passengerCarParkingList;
        this.truckParkingList = truckParkingList;
    }

    public boolean toParkTruck(Car car) {
        return truckParkingList.stream().anyMatch(parking -> parking.add(car));
    }

    public boolean toParkPassengerCar(Car car) {
        return passengerCarParkingList.stream().anyMatch(parking -> parking.add(car));
    }

    public boolean execute(Car car) {
        boolean rsl = false;
        if (toParkTruck(car)) {
            rsl = true;
        } else if (toParkPassengerCar(car)) {
            rsl = true;
        }
        return rsl;
    }
}
