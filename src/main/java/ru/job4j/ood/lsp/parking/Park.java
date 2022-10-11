package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Park {
    private List<Parking> parkingList;

    public Park(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    public boolean execute(Car car) {
        boolean rsl = false;
        for (Parking parking : parkingList) {
            if (parking.add(car)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}
