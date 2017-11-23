package metier;

import static metier.ISensor.maxTemp;
import static metier.ISensor.minTemp;

public class RelativeGeneration implements ITemperatureGenerator {
    private int min, max;

    public RelativeGeneration(int oldTemp, int interval){
        this.min=Math.max(oldTemp-interval, minTemp);
        this.max=Math.min(oldTemp+interval, maxTemp);
    }

    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
