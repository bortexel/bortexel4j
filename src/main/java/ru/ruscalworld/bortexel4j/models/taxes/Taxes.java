package ru.ruscalworld.bortexel4j.models.taxes;

public class Taxes {
    private final int type;
    private final int weight;
    private final int current;

    public Taxes(int type, int weight, int current) {
        this.type = type;
        this.weight = weight;
        this.current = current;
    }

    public TaxType getType() {
        if (type == 1) return TaxType.LOCATION_DEPENDENT;
        return TaxType.FIXED;
    }

    public int getWeight() {
        return weight;
    }

    public int getCurrent() {
        return current;
    }
}
