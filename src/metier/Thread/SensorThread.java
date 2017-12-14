package metier.Thread;

import metier.Sensor.ISensor;

import static metier.Thread.ThreadManager.addThread;

public class SensorThread extends Thread {
    private ISensor sensor;
    private int refreshRate;

    public SensorThread(ISensor joinedSensor, int refreshRate){
        this.sensor=joinedSensor;
        addThread(this);
        this.refreshRate=refreshRate;
    }

    @Override
    public void run(){
        try{
            while(true){
                this.sleep(refreshRate*1000);
                sensor.update();
            }
        }
        catch (InterruptedException e){
                System.out.println(e);
        }
    }
}
