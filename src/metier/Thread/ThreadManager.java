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
    
    public static void addThread(Thread threadAdded){
        
        listRunningThread.add(threadAdded);
        
    }
    
    public static void stopThread(){
        if(listRunningThread!=null){
            for(Thread t : listRunningThread){
                t.interrupt();
            }
        }
    }
}
