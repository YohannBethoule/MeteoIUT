package metier;

import static metier.ISensor.MAX_TEMP;
import static metier.ISensor.MIN_TEMP;

public class RelativeGeneration extends ITemperatureGenerator {


    public RelativeGeneration(int oldTemp, int interval){
        this.min=Math.max(oldTemp-interval, MIN_TEMP);
        this.max=Math.min(oldTemp+interval, MAX_TEMP);
    }

    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
