package ru.ruscalworld.bortexel4j.core;

import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Client;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PaginatedAction<T> extends Action<List<T>> implements Cloneable {
    public PaginatedAction(String endpoint, Client client) {
        super(endpoint, client);
    }

    public PaginatedAction<T> getNext(int pages) {
        this.setPage(this.getCurrentPage() + pages);
        return this;
    }

    public PaginatedAction<T> getNext() {
        return this.getNext(1);
    }

    public PaginatedAction<T> getPrevious(int pages) {
        if (this.getCurrentPage() - pages < 0) throw new IllegalArgumentException("Page number must not be below zero");
        this.setPage(this.getCurrentPage() - pages);
        return this;
    }

    public PaginatedAction<T> getPrevious() {
        return this.getPrevious(1);
    }

    public void setPage(int page) {
        this.setQueryParam("page", "" + page);
    }

    /**
     * This method returns all actions you need to retrieve info from all pages
     * Is assumes that you have already fetched the first page, so it will not return action to fetch it
     * @throws IllegalStateException if there is no cached response, and you should fetch the first page manually
     * @return List of actions to retrieve info from pages from 1 to {@link Pagination#getTotalPages()}
     */
    @SuppressWarnings("unchecked")
    public List<PaginatedAction<T>> getAllPages() {
        this.ensureIsPaginated();
        Response.Meta meta = this.getLastResponse().getMeta();
        assert meta != null && meta.getPagination() != null;

        List<PaginatedAction<T>> actions = new ArrayList<>();
        Pagination pagination = meta.getPagination();
        for (int i = 2; i <= pagination.getTotalPages(); i++) {
            try {
                PaginatedAction<T> clone = (PaginatedAction<T>) this.clone();
                clone.setPage(i);
                actions.add(clone);
            } catch (CloneNotSupportedException ignored) { }
        }

        return actions;
    }


    public List<T> fetchAll() {
        List<T> result = new ArrayList<>();

        for (PaginatedAction<T> action : this.getAllPages()) {
            List<T> currentResult = action.execute();
            if (currentResult == null) continue;
            result.addAll(currentResult);
        }

        return result;
    }

    @Override
    public void setResponseType(Type type) {
        super.setResponseType(TypeToken.getParameterized(List.class, type).getType());
    }

    protected void ensureIsPaginated() {
        Response<List<T>> lastResponse = this.getLastResponse();
        if (lastResponse == null) throw new IllegalStateException("This action has no cached response yet");
        if (lastResponse.getMeta() == null || lastResponse.getMeta().getPagination() == null)
            throw new IllegalStateException("The endpoint this action related with doesn't provide pagination info");
    }

    public int getCurrentPage() {
        Response<List<T>> lastResponse = this.getLastResponse();
        if (lastResponse == null) return 1;
        if (lastResponse.getMeta() == null || lastResponse.getMeta().getPagination() == null) return 1;
        return lastResponse.getMeta().getPagination().getCurrentPage();
    }
}
