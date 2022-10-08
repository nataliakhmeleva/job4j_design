package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> shop = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (Constaints.DOWN_LIMIT <= getPercentExpiry(food) && Constaints.TOP_LIMIT > getPercentExpiry(food)) {
            if (Constaints.MEDIUM_LIMIT <= getPercentExpiry(food) && Constaints.TOP_LIMIT > getPercentExpiry(food)) {
                food.setPrice((food.getPrice() * (100 - food.getDiscount())) / 100);
            }
            shop.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAllFood() {
        return List.copyOf(shop);
    }
}
