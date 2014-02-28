/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iSEngine;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.*;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
/**
 *
 * @author Rashmika
 */
public class Sniffer extends Observable implements Runnable{
     
    ArrayList<Packet> pkts; 
    JpcapCaptor captor;
    NetworkInterface[] list;
    int networkID;
    
    public void setNetworkInterface(int id){
        this.networkID = id;
    }
    
    public NetworkInterface[] getNetworkInterfaces(){
        return JpcapCaptor.getDeviceList();
    }
    
    public void run(){
        list = JpcapCaptor.getDeviceList();
        while(true){
            
            pkts = new ArrayList<Packet>();

            try {
                captor=JpcapCaptor.openDevice(list[this.networkID], 65535, false, 20);
                //captor.setFilter("tcp", true);
            }catch(IOException ioe) { ioe.printStackTrace(); }

            int i=0;
            while (i<=100) { 
                    i++;			
                Packet p = captor.getPacket();
                pkts.add(p);
            }
            
            setChanged();
            notifyObservers(pkts);
            
        }
    }
    
    public boolean sendPacket(InetAddress srcip, InetAddress destip, int srcprt, int destprt, short ack, String data){
        
        try {
            
            //open a network interface to send a packet to
            JpcapSender sender=JpcapSender.openDevice(list[this.networkID]);
            
            /**
             * TCPPacket(int src_port, int dst_port, long sequence, long ack_num, 
             * boolean urg, boolean ack, boolean psh, boolean rst, boolean syn, 
             * boolean fin, boolean rsv1, boolean rsv2, int window, int urgent)
             */
            //create a TCP packet with specified port numbers, flags, and other parameters
            TCPPacket p=new TCPPacket(srcprt,destprt,56,ack,false,false,false,false,true,true,true,true,10,10);
            
            //specify IPv4 header parameters
            /**
             * etIPv4Parameter(int priority,
                             boolean d_flag,
                             boolean t_flag,
                             boolean r_flag,
                             int rsv_tos,
                             boolean rsv_frag,
                             boolean dont_frag,
                             boolean more_frag,
                             int offset,
                             int ident,
                             int ttl,
                             int protocol,
                             java.net.InetAddress src,
                             java.net.InetAddress dst)
             */
            p.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_TCP,
            srcip,destip);
            
            //set the data field of the packet
            p.data= data.getBytes();

            //create an Ethernet packet (frame)
            EthernetPacket ether=new EthernetPacket();
            //set frame type as IP
            ether.frametype=EthernetPacket.ETHERTYPE_IP;
            //set source and destination MAC addresses
            ether.src_mac=new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
            ether.dst_mac=new byte[]{(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};

            //set the datalink frame of the packet p as ether
            p.datalink=ether;

            //send the packet p
            sender.sendPacket(p);

            sender.close();
            
            return true;
            
        } catch (IOException ex) {
            Logger.getLogger(Sniffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
