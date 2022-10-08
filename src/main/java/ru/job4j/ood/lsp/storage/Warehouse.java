package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> wareHouse = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (Constaints.DOWN_LIMIT > getPercentExpiry(food)) {
            wareHouse.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAllFood() {
        return List.copyOf(wareHouse);
    }
}
