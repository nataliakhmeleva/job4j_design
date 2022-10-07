package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private static final double LIMIT = 100;
    private List<Food> trash = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (LIMIT <= getPercentExpiry(food)) {
            trash.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAllFood() {
        return trash;
    }
}
