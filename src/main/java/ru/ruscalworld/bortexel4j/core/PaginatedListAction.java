package ru.ruscalworld.bortexel4j.core;

import ru.ruscalworld.bortexel4j.Client;

import java.util.List;

public class PaginatedListAction<T> extends PaginatedAction<List<T>> {
    public PaginatedListAction(String endpoint, Client client) {
        super(endpoint, client);
    }

    public List<T> fetchAll() {
        List<T> result = this.execute();

        for (PaginatedAction<List<T>> action : this.getAllPages()) {
            List<T> currentResult = action.execute();
            if (currentResult == null) continue;
            result.addAll(currentResult);
        }

        return result;
    }
}
