package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class JSONReport implements Report {
    private Store store;
    private Gson gson;

    public JSONReport(Store store) {
        this.store = store;
        gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
