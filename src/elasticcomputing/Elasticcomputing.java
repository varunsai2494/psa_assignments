/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author root
 */

public class Elasticcomputing  extends Thread{
    private static Queue<Integer> q = new LinkedList<>();
    private static List<server> l = new ArrayList<>();
    private List<Request> request = new ArrayList<>();
    public int serverCapacity;
    public int processingTime;
    /**
     * @param args the command line arguments
     */
    
    public void addtoQ(int i){
        q.add(i);
    }
    public void run() {
        int c=0;
        while(true){
            List<server> emptyservers = new ArrayList<server>();
            if(!q.isEmpty()){
                if(l.size()==0){
                    server s = new server(serverCapacity,processingTime);
                    l.add(s);
                    s.start();
                }
                int qval = q.remove();
                System.out.println(l.size());
                boolean toggle = false;
                for(server i: l){
                    if(i.canExecute() && !toggle){
                        toggle = true;
                        Date date = new Date();
                        long timeMilli = date.getTime();
                        Request r = new Request(timeMilli);
                        request.add(r);
                        i.insertIntoQ(r);
                    }
                }

                if(!toggle){
                    server temp = new server(serverCapacity,processingTime);
                    l.add(temp);
                    temp.start();
                    Date date = new Date();
                    long timeMilli = date.getTime();
                    Request r = new Request(timeMilli);
                    request.add(r);
                    temp.insertIntoQ(r);
                    
                }

            }
            for(server i: l){
                if(i.qisEmpty()){
                    if(l.size()>1){
                        emptyservers.add(i);
                    }
                }
            }
            l.removeAll(emptyservers);
        }  
    }    

    public List<Request> getRequest() {
        return request;
    }

    public int getServerCapacity() {
        return serverCapacity;
    }

    public void setServerCapacity(int serverCapacity) {
        this.serverCapacity = serverCapacity;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    public static List<server> getL() {
        return l;
    }

    public static void setL(List<server> l) {
        Elasticcomputing.l = l;
    }
}
