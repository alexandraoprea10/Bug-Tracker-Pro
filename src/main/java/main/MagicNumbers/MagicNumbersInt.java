package main.MagicNumbers;

public enum MagicNumbersInt {
    minuscinci(-5),
    doi(2),
    trei(3),
    patru(4),
    cinci(5),
    sase(6),
    zece(10),
    unsprezece(11),
    doisprezece(12),
    cincisprezece(15),
    treizeci(30),
    treizecisidoi(32);
    private final int value;
    MagicNumbersInt(final int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
