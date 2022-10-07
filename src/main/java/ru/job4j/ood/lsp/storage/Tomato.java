package ru.job4j.ood.lsp.storage;

import java.time.LocalDateTime;

public class Tomato extends Food {
    public Tomato(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
