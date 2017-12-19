package metier.generation;

public interface ITemperatureGenerator {
    static final int MIN_TEMP =-20;
    static final int MAX_TEMP =40;

    public abstract double generateTemperature();
}
