package metier.generation;


public class IntervalGeneration implements ITemperatureGenerator{
    private int min;
    private int max;

    public IntervalGeneration(int min, int max){
        this.min=min;
        this.max=max;
    }

    /**
     * Generate a temperature between min and max.
     * @return a random double between min and max
     */
    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
