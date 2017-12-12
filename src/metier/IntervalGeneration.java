package metier;

import static metier.ISensor.MAX_TEMP;
import static metier.ISensor.MIN_TEMP;

public class IntervalGeneration extends ITemperatureGenerator{

    public IntervalGeneration(){
        this.min= MIN_TEMP;
        this.max= MAX_TEMP;
    }

    public IntervalGeneration(int min, int max){
        this.min=min;
        this.max=max;
    }

    @Override
    public double generateTemperature() {
        return Math.round(min + Math.random() * (max-min));
    }
}
