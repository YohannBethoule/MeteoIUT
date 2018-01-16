package metier.generation;

public class RelativeGeneration implements ITemperatureGenerator {
    private int min;
    private int max;

    public RelativeGeneration(Double oldTemp, int interval){
        this.min=oldTemp.intValue()-interval;
        this.max=oldTemp.intValue()+interval;
    }

    /**
     * Generate a temperature in the set interval.
     * @return a random double in an interval from the current temperature
     */
    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }

    /**
     * Getter to the interval.
     * @return the interval of the generator
     */
    public int getInterval(){
        return (max-min)/2;
    }
}
