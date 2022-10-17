package ru.job4j.ood.lsp.storage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    boolean add(Food food);

    List<Food> getAllFood();

    default double getPercentExpiry(Food food) {
        double maxExpiry = ChronoUnit.MINUTES.between(food.getCreateDate(), food.getExpiryDate());
        double minExpiry = ChronoUnit.MINUTES.between(food.getCreateDate(), LocalDateTime.now());
        return minExpiry / maxExpiry * 100;
    }

    void clean();
}
