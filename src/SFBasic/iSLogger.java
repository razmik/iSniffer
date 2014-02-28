/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SFBasic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Rashmika
 */
public class iSLogger {
    
    DateFormat dateFormat;
    Calendar cal;

    public iSLogger() {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        cal = Calendar.getInstance();
    }
    
    public void addLineTCP(String fileName, ArrayList<TCPPacket> data){
        
        String line = "";
        
        if(data != null){            
            TCPPacket tcp;
            for(int i=0; i<data.size(); i++){
                tcp = data.get(i);
                String temp = new String(tcp.data); 
                line += "Time: "+ dateFormat.format(cal.getTime()) + "\n" +tcp.ack_num + "\t" + tcp.src_ip + "\t" + tcp.src_port+ "\t" + tcp.dst_ip+ "\t" + tcp.dst_port+ "\t" + tcp.sequence+ "\t" + temp;
            }
        }
        
        try{
            
          FileWriter fstream = new FileWriter(fileName,true);
          BufferedWriter out = new BufferedWriter(fstream);
          out.write(line);
          out.close();
        }catch (Exception e){
          System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void addLineIP(String fileName, ArrayList<IPPacket> data){
        
        String line = "";
        
        if(data != null){            
            IPPacket ipp;
            for(int i=0; i<data.size(); i++){
                ipp = data.get(i);
                String temp = new String(ipp.data); 
                line += "Time: "+ dateFormat.format(cal.getTime()) + "\n" +ipp.src_ip + "\t" + ipp.dst_ip + "\t" + ipp.hop_limit + "\t" + ipp.length + "\t" + ipp.protocol + "\t" + temp;
            }
        }
        
        try{            
          FileWriter fstream = new FileWriter(fileName,true);
          BufferedWriter out = new BufferedWriter(fstream);
          out.write(line);
          out.close();
        }catch (Exception e){
          System.err.println("Error: " + e.getMessage());
        }
    }
}
