package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean add(Car car);

    boolean search(Car car);

    List<Car> getCars();
}
