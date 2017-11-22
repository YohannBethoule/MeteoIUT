package metier;

import static metier.ISensor.maxTemp;
import static metier.ISensor.minTemp;

public class RandomGeneration implements  ITemperatureGenerator{
    private static final int PRECISION=100;

    public RandomGeneration(){

    }

    @Override
    public double getRandomTemperature() {
        return Math.round(minTemp + Math.random() * (maxTemp-minTemp));
    }
}
