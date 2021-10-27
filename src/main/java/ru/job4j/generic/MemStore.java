package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        String index = indexOf(id);
        boolean rsl = index != null;
        if (rsl) {
            mem.replace(index, model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        String index = indexOf(id);
        boolean rsl = index != null;
        if (rsl) {
            mem.remove(index);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        String index = indexOf(id);
        return index != null ? mem.get(index) : null;
    }

    public String indexOf(String id) {
        for (String index : mem.keySet()) {
            if (mem.get(index).getId().equals(id)) {
                return index;
            }
        }
        return null;
    }
}
