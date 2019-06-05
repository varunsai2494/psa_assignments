/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author root
 */
public class server extends Thread {
    public Queue<Integer> q = new LinkedList<>();
    HashMap<Integer, Long> map = new HashMap<>(); 
    public int maxLimit = 10;
     public static void proccess(){
         try{
             Thread.sleep(10);
         }
         catch(Exception e){
         }
         
     }
     public int qsize(){
         return q.size();
     }
     public boolean qisEmpty(){
         return q.size()==0;
     }
     public void insertIntoQ(int i){
         Date date = new Date();
         long timeMilli = date.getTime();
         q.add(i);
         map.put(i,timeMilli);
     }
     public boolean canExecute(){
         return q.size()<maxLimit;
     }
     public void run(){  
         System.out.println("thread is running...");
         while(true){
            if(!q.isEmpty()){
                proccess();
                int k = q.remove();
                Date date = new Date();
                long timeMilli = date.getTime();
                map.put(k, timeMilli-map.get(k));
            }
        }
         
     }  
     
}
