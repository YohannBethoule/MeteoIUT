/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.thread;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lulauriche
 */
public class ThreadManager {
    
    static List<Thread> listRunningThread = new ArrayList<>();

    /**
     * Add a new thread to the list of running threads.
      * @param threadAdded the thread to be added
     */
    public static void addThread(Thread threadAdded){
        
        listRunningThread.add(threadAdded);
        
    }

    /**
     * Stop all threads.
     */
    public static void stopThread(){
        if(listRunningThread!=null){
            for(Thread t : listRunningThread){
                t.interrupt();
            }
        }
    }
}
