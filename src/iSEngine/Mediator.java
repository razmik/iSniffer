/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iSEngine;

import SFBasic.SFProperties;
import SFBasic.iSLogger;
import java.util.ArrayList;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

/**
 * Work as a mediator between UserInterface, SpamAnalyser and the Database
 * @author Rashmika
 */
public class Mediator {
    
    private String ipfilename;
    private String tcpfilename;
    private iSLogger logger;

    public Mediator() {
        SFProperties prop = new SFProperties();
        this.ipfilename = prop.getIpfilename();
        this.tcpfilename = prop.getTcpfilename();
        this.logger = new iSLogger();
    }
    
    public void logIP(ArrayList<IPPacket> data){
        this.logger.addLineIP(ipfilename, data);
    }
    
    public void logTCP(ArrayList<TCPPacket> data){
        this.logger.addLineTCP(tcpfilename, data);
    }
    
 
}
