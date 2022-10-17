package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void execute(Food food) {
        for (Store store : storeList) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> newListFood = new ArrayList<>();
        for (Store store : storeList) {
            newListFood.addAll(store.getAllFood());
            store.clean();
        }
        for (Food food : newListFood) {
            execute(food);
        }
    }
}
