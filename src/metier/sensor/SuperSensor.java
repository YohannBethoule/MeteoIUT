package metier.sensor;

import java.util.HashMap;
import java.util.Map;

public class SuperSensor extends ISensor{
    protected Map<ISensor, Integer> sensors;

    public SuperSensor(String name){
        super(name);
        sensors=new HashMap<>();
    }

    /**
     * Update the temperature of the sensor by calculating the weighted average of the super sensor sensors.
     */
    @Override
    public void update() {
        double total=0;
        int count=0;
        for (Map.Entry<ISensor, Integer> s: sensors.entrySet()) {
            total+=(s.getKey().getTemperature())*s.getValue();
            count+=s.getValue();
        }
        setTemperature(total/count);
    }

    /**
     * Add a sensor to the super sensor.
     * @param s the sensor to be added
     * @param weight the weight of the sensor in the weighted average
     */
    public void addSensor(ISensor s, int weight){
        sensors.put(s, weight);
    }

    /**
     * Delete a sensor from the super sensor.
     * @param s the sensor to be deleted
     */
    public void deleteSensor(ISensor s){
        sensors.remove(s);
    }

    /**
     * Getter to the map of sensors and weights.
     * @return the super sensor sensors and their weight
     */
    public Map<ISensor, Integer> getSensors(){
        return sensors;
    }
}
