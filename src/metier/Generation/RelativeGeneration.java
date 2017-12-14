package metier.Generation;

public class RelativeGeneration implements ITemperatureGenerator {
    private int min;
    private int max;

    public RelativeGeneration(int oldTemp, int interval){
        this.min=Math.max(oldTemp-interval, MIN_TEMP);
        this.max=Math.min(oldTemp+interval, MAX_TEMP);
    }

    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
