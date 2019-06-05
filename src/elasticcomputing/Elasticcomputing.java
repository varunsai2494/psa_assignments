/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.util.LinkedList;
import java.util.Queue;
import elasticcomputing.server;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author root
 */
public class Elasticcomputing {
    private static Queue<Integer> q = new LinkedList<>();
    private static List<server> l = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void Dispatcher(int requestrate,int serverCapacity,int processingTime) {
        server s = new server(serverCapacity,requestrate);
        l.add(s);
        s.start();
        for(int k=0;k<50;k++){
            q.add(k);
        }
        int c=0;
        while(true){
            List<server> emptyservers = new ArrayList<server>();
            if(!q.isEmpty()){
                
                int qval = q.remove();
                System.out.println(l.size());
                boolean toggle = false;
                for(server i: l){
                    if(i.canExecute() && !toggle){
                        toggle = true;
                        i.insertIntoQ(qval);
                    }
//                    if(i.qisEmpty()){
//                        if(l.size()>1){
//                            emptyservers.add(i);
//                        }
//                        
//                    }
               
                }
                
                if(!toggle){
                    server temp = new server(serverCapacity,requestrate);
                    l.add(temp);
                    temp.start();
                    temp.insertIntoQ(qval);
                }
                
            }
            else{
                
                if(c<3){
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    c++;
                try{
             Thread.sleep(10000);
         }
         catch(Exception e){
         }  
                    for(int k=0;k<50;k++){
                      q.add(k);
                    }
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
    
}
