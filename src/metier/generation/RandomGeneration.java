package metier.generation;

public class RandomGeneration  implements ITemperatureGenerator{

    private int min;
    private int max;

    public RandomGeneration(){
        this.min= MIN_TEMP;
        this.max= MAX_TEMP;
    }

    /**
     * Generate a random temperature
     * @return a random double between MIN_TEMP and MAX_TEMP
     */
    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
