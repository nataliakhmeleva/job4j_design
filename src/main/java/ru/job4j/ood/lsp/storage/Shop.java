package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private static final double DOWN_LIMIT = 25;
    private static final double MEDIUM_LIMIT = 75;
    private static final double TOP_LIMIT = 100;
    private List<Food> shop = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (DOWN_LIMIT <= getPercentExpiry(food) && MEDIUM_LIMIT > getPercentExpiry(food)) {
            shop.add(food);
            rsl = true;
        }
        if (MEDIUM_LIMIT <= getPercentExpiry(food) && TOP_LIMIT > getPercentExpiry(food)) {
            food.setPrice((food.getPrice() * (100 - food.getDiscount())) / 100);
            shop.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAllFood() {
        return shop;
    }
}
