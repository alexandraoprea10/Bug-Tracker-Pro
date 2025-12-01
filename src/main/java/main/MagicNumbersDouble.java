package main;

public enum MagicNumbersDouble {
    osuta(100.0);
    private final double value;
    MagicNumbersDouble(final double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
}
