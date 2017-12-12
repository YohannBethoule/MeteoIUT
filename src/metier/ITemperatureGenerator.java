package metier;

public abstract class ITemperatureGenerator {
    protected int min;
    protected int max;
    public abstract double generateTemperature();
}
