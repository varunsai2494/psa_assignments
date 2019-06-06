/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

/**
 *
 * @author root
 */
public class Request {
    public int server;
    public int request_id;
    public long response_time;
    public static int count = 1;
    public Request(long response_time){
//            this.server = server;
            this.request_id = count;
            count++;
            this.response_time = response_time;
    }
}
