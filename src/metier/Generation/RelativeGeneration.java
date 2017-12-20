package metier.generation;

public class RelativeGeneration implements ITemperatureGenerator {
    private int min;
    private int max;

    public RelativeGeneration(int oldTemp, int interval){
        this.min=oldTemp-interval;
        this.max=oldTemp+interval;
    }

    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
