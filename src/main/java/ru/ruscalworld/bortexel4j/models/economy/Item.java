package ru.ruscalworld.bortexel4j.models.economy;

import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.util.BortexelCDN;

import java.sql.Timestamp;
import java.util.List;

public class Item {
    private final String id;
    private String name;
    private String category;

    public Item(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public static Action<Item> getByID(String id) {
        return getByID(id, new Bortexel4J());
    }

    public static Action<Item> getByID(String id, Bortexel4J client) {
        Action<Item> action = new Action<>("/economy/items/" + id, client);
        action.setResponseType(Item.class);
        return action;
    }

    public Action<ItemPrices> getPrices() {
        return getPrices(new Bortexel4J());
    }

    public Action<ItemPrices> getPrices(Bortexel4J client) {
        Action<ItemPrices> action = new Action<>("/economy/items/" + id + "/prices", client);
        action.setResponseType(ItemPrices.class);
        return action;
    }

    public static class ItemPrices {
        private final Item item;
        private final List<ItemPrice> prices;

        public ItemPrices(Item item, List<ItemPrice> prices) {
            this.item = item;
            this.prices = prices;
        }

        public Item getItem() {
            return item;
        }

        public List<ItemPrice> getPrices() {
            return prices;
        }
    }

    public static class ItemPrice {
        private final Timestamp time;
        private final float price;

        public ItemPrice(Timestamp time, float price) {
            this.time = time;
            this.price = price;
        }

        public Timestamp getTime() {
            return time;
        }

        public float getPrice() {
            return price;
        }
    }

    public String getIconURL() {
        return BortexelCDN.getItemIconURL(this.getId());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
