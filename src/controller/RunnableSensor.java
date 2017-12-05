/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static metier.ThreadManager.addThread;
import metier.ISensor;

/**
 *
 * @author lulauriche
 */
public class RunnableSensor implements Runnable {
    private Thread currentThread;
    private ISensor sensor;

    public RunnableSensor (ISensor sensor){
        this.sensor=sensor;
        currentThread = Thread.currentThread();  
        addThread(currentThread);
    }
    
    @Override
    public void run() {
        try {
            currentThread.sleep(1000);
            sensor.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}

