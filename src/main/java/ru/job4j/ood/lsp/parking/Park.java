package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Park {
    private List<Parking> parkingList;

    public Park(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    public void execute(Car car) {
    }
}
