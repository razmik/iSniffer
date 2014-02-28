/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SFBasic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Get the properties from the configuration file
 * @author Rashmika
 */
public class SFProperties {
    
    private String username;
    private String password;
    private String ipfilename;
    private String tcpfilename;
    
    /**
     * At the construction time, read the app.config file and load its data to the application
     */
    public SFProperties() {
        
        InputStream is = null;
        
        try {
            Properties prop = new Properties();
            String fileName = "app.config";
            is = new FileInputStream(fileName);
            prop.load(is);
            
            this.username =  prop.getProperty("app.username");
            this.password =  prop.getProperty("app.password");
            this.ipfilename =  prop.getProperty("app.ipfilename");
            this.tcpfilename =  prop.getProperty("app.tcpfilename");
            
        } catch (Exception ex) {
            Logger.getLogger(SFProperties.class.getName()).log(Level.SEVERE, null, ex);
            this.ipfilename =  "iplog.txt";
            this.tcpfilename =  "tcplog.txt";
            this.username = "cse";
            this.password = "admin";
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(SFProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the ipfilename
     */
    public String getIpfilename() {
        return ipfilename;
    }

    /**
     * @return the tcpfilename
     */
    public String getTcpfilename() {
        return tcpfilename;
    }
    
}
