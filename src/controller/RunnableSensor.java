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
public class RunnableSensor extends Controller implements Runnable {
    private Thread currentThread;

    public RunnableSensor (ISensor sensor){
        currentThread = Thread.currentThread();  
        addThread(currentThread);
    }
    
    @Override
    public void run() {
        
    }

    
}
