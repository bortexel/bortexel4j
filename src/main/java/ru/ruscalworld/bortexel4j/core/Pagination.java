package ru.ruscalworld.bortexel4j.core;

import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName("current_page")
    private final int currentPage;
    @SerializedName("total_pages")
    private final int totalPages;
    @SerializedName("current_page_items")
    private final int currentPageItems;
    @SerializedName("max_items_per_page")
    private final int maxItemsPerPage;
    @SerializedName("total_items")
    private final int totalItems;

    public Pagination(int currentPage, int totalPages, int currentPageItems, int maxItemsPerPage, int totalItems) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.currentPageItems = currentPageItems;
        this.maxItemsPerPage = maxItemsPerPage;
        this.totalItems = totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPageItems() {
        return currentPageItems;
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
