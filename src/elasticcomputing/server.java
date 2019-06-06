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
import elasticcomputing.Request;
/**
 *
 * @author root
 */
public class server extends Thread {
    public Queue<Request> q = new LinkedList<>();
    public int maxLimit;
    public int processingtime;
    private int server_id;
    public static int count = 1;
    
    public server(int maxlimit,int processingtime){
        this.maxLimit = maxlimit;
        this.processingtime = processingtime;
        server_id = count;
        count++;
    }
     public void process(){
         try{
             Thread.sleep(this.processingtime);
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
     public void insertIntoQ(Request r){
         Date date = new Date();
         long timeMilli = date.getTime();
         r.server = server_id;
         q.add(r);
//         map.put(i,timeMilli);
     }
     public boolean canExecute(){
         return q.size()<maxLimit;
     }
     public void run(){  
         System.out.println("thread is running...");
         while(true){
            if(!q.isEmpty()){
                process();
                Request r = q.remove();
                Date date = new Date();
                long timeMilli = date.getTime();
                r.response_time = timeMilli - r.response_time;
//                map.put(k, timeMilli-map.get(k));
            }
        }
         
     }  

     
}
