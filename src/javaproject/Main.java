
package javaproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    static String id;

    static class LoginPrompt implements Runnable {
        
        loginFrame lf = new loginFrame();
        
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
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            lf.dispose();
        }
    }
    
    static class AdminWorkspace implements Runnable {
        
        adminFrame af = new adminFrame();
        
        Runnable showFrame = new Runnable() {
            public void run() {
                af.setVisible(true);
            }
        };
        
        @Override
        public void run() {
            java.awt.EventQueue.invokeLater(showFrame);
            while(!af.done) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            af.dispose();
        }
    }
    
    static class WorkerWorkspace implements Runnable {
        
        workerFrame wf = new workerFrame();
        
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
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            wf.dispose();
        }
    }
    
    public static void main(String[] args) {
        
        while(true) {
            
            Thread lp = new Thread(new LoginPrompt());
            lp.start();
            try {
                lp.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(id == "admin") {
                Thread aws = new Thread(new AdminWorkspace());
                aws.start();
                try {
                    aws.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (id == "worker") {
                Thread wws = new Thread(new WorkerWorkspace());
                wws.start();
                try {
                    wws.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("id error after login");
            }
        }
        
    }
    
}
