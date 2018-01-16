package metier.generation;

public interface ITemperatureGenerator {
    static final int MIN_TEMP =-10;
    static final int MAX_TEMP =40;

    double generateTemperature();
}
