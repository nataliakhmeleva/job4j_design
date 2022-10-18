package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Park {
    private List<UniversalParking> universalParkingList;

    public Park(List<UniversalParking> universalParkingList) {
        this.universalParkingList = universalParkingList;
    }

    public boolean execute(Car car) {
        return universalParkingList.stream().anyMatch(parking -> parking.add(car));
    }
}
