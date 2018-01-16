package metier.thread;

import javafx.application.Platform;
import metier.sensor.ISensor;

import static metier.thread.ThreadManager.addThread;

public class SensorThread extends Thread {
    private ISensor sensor;
    private int refreshRate;

    public SensorThread(ISensor joinedSensor, int refreshRate){
        this.sensor=joinedSensor;
        addThread(this);
        this.refreshRate=refreshRate;
    }

    /**
     * Start the thread.
     */
    @Override
    public void run(){
        try{
            while(true){
                this.sleep(refreshRate*1000);
                Platform.runLater(()->
                sensor.update());
            }
        }
        catch (InterruptedException e){
                System.err.println(e);
        }
    }
}
