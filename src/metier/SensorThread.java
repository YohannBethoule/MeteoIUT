package metier;

import static metier.ThreadManager.addThread;

public class SensorThread extends Thread {
    private ISensor sensor;
    public SensorThread(ISensor joinedSensor){
        this.sensor=joinedSensor;
        addThread(this);
    }
    @Override
    public void run(){
        try{
            while(true){
                this.sleep(1000);
                sensor.update();
            }
        }
        catch (InterruptedException e){
                System.out.println(e);
        }
    }
}
