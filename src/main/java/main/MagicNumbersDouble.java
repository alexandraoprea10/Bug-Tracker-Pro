package main;

public enum MagicNumbersDouble {
    zerotrei(0.3),
    zerocinci(0.5),
    zerosapte(0.7),
    trrei(3.0),
    zece(10.0),
    doisprezece(12.0),
    douazeci(20.0),
    douazecisipatru(24.0),
    douazecisicinci(25.0),
    patruzecisiopt(48.0),
    patruzecisinoua(49.0),
    cincizeci(50.0),
    saptezeci(70.0),
    saptezecisipatru(74.0),
    osuta(100.0);
    private final double value;
    MagicNumbersDouble(final double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
}
