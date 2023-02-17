
package general;

import adminFrame.AdminFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import workerFrame.WorkerFrame;

/**
 *
 * @author Mihael
 */

public class Main {
    
    static String id;
    
    public static String firstName, lastName;

    /*
    Defines a thread which takes care of displaying the initial window frame
    where users login as "administrator" (admin) or "blagajnik" (worker).
    */
    static class LoginPrompt implements Runnable {
        
        LoginFrame lf = new LoginFrame();
        
        Runnable showFrame = new Runnable() {
            public void run() {
                lf.setVisible(true);
            }
        };
        
        @Override
        public void run() {
            java.awt.EventQueue.invokeLater(showFrame);
            while(!lf.done) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            lf.dispose();
        }
    }
    
    /*
    Defines a thread which takes care of displaying the frame for
    databases administrator.
    */
    static class AdminWorkspace implements Runnable {
        
        AdminFrame af;
        
        Runnable showFrame = new Runnable() {
            public void run() {
                af.setVisible(true);
            }
        };

        AdminWorkspace() throws SQLException {
            this.af = new AdminFrame();
        }
        
        @Override
        public void run() {
            java.awt.EventQueue.invokeLater(showFrame);
            while(!af.done) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            af.dispose();
        }
    }
    
    /*
    Defines a thread which takes care of displaying the frame for
    the cash register worker.
    */
    static class WorkerWorkspace implements Runnable {
        
        WorkerFrame wf;

        WorkerWorkspace() throws SQLException {
            this.wf = new WorkerFrame(firstName, lastName);
        }
        
        Runnable showFrame = new Runnable() {
            public void run() {
                wf.setVisible(true);
            }
        };
        
        @Override
        public void run() {
            java.awt.EventQueue.invokeLater(showFrame);
            while(!wf.done) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            wf.dispose();
        }
    }
    
    public static void main(String[] args) throws SQLException {
        
        /*
        This main loop allows multiple users to use the app one after another.
        Logging out of one workspace causes the program to prompt the user with
        the login screen again.
        */
        while(true) {
            
            Thread lp = new Thread(new LoginPrompt());
            lp.start();
            try {
                lp.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

            if (null == id) {
                System.out.println("id error after login");
            } else switch (id) {
                case "admin" -> {
                    Thread aws = new Thread(new AdminWorkspace());
                    aws.start();
                    try {
                        aws.join();
                    } catch (InterruptedException ex) {
                       System.out.println(ex.getMessage());
                    }
                }
                case "worker" -> {
                    Thread wws = new Thread(new WorkerWorkspace());
                    wws.start();
                    try {
                        wws.join();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                default -> System.out.println("id error after login");
            }
        }
        
    }
    
}
