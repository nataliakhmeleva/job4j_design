package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private static final double LIMIT = 25;
    private List<Food> wareHouse = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (LIMIT > getPercentExpiry(food)) {
            wareHouse.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAllFood() {
        return wareHouse;
    }
}
