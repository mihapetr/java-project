
package javaproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.SwingWorker;

public class VerifyUser extends SwingWorker<Boolean, Void> {
    
    LoginFrame lf;
    String korisnik, lozinka;
    Database db;
    char uloga;
    
    public VerifyUser(LoginFrame _ref, String _korisnik, String _lozinka, char _uloga, Database database) {
        lf = _ref;
        korisnik = _korisnik;
        lozinka = _lozinka;
        db = database;
        uloga = _uloga;
    }
    
    @Override
    public Boolean doInBackground() {
        
        lf.busy = true;
                 
        Boolean valid = db.query(korisnik, uloga, lozinka);
        System.out.println("checking korisnik:" + korisnik);
        
        return valid;
    }
    
    @Override
    public void done() {
        lf.busy = false;
        
        boolean result = false;
        try {
           result = get();
        } catch (Exception ex) {}
        if(result) {
            lf.done = true;
        } else {
            lf.displayLoginError();
        }
    } 
    
}
