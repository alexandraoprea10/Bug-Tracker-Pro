package main;

public enum MagicNumbersInt {
    minuscinci(-5),
    doi(2),
    trei(3),
    patru(4),
    zece(10),
    doisprezece(12);
    private final int value;
    MagicNumbersInt(final int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
