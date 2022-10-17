package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private List<Food> trash = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (Constaints.TOP_LIMIT <= getPercentExpiry(food)) {
            trash.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAllFood() {
        return List.copyOf(trash);
    }

    @Override
    public void clean() {
        trash.clear();
    }
}
