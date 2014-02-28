/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import iSUI.MailManager;
import iSUI.WelcomeScreen;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Rashmika
 */
public class SpamFilter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /**
             * Set the Nimbus user interface theme
             */
             for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                   if ("Nimbus".equals(info.getName())) {
                       UIManager.setLookAndFeel(info.getClassName());
                       break;
                  }
            }
        } catch (Exception e) {
        // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        new WelcomeScreen().setVisible(true);
    }
    
    
}
