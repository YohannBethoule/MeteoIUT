package metier.Generation;

public abstract class ITemperatureGenerator {
    static final int MIN_TEMP =-20;
    static final int MAX_TEMP =40;

    protected int min;
    protected int max;
    public abstract double generateTemperature();
}
