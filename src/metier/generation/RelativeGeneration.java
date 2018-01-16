package metier.generation;

public class RelativeGeneration implements ITemperatureGenerator {
    private int min;
    private int max;

    public RelativeGeneration(Double oldTemp, int interval){
        this.min=oldTemp.intValue()-interval;
        this.max=oldTemp.intValue()+interval;
    }

    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }

    public int getInterval(){
        return (max-min)/2;
    }
}
