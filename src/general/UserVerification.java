/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package general;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import workerFrame.WorkerQueryExecution;

/**
 *
 * @author Filip
 */
public class UserVerification extends WorkerQueryExecution<Integer> {
    LoginFrame lf;
    String inputUsername, inputPassword, firstName, lastName;
    char inputRole;
    
    public UserVerification(
            LoginFrame loginFrame,
            String username,
            String password,
            char role) {
        sql = "SELECT KORISNICKO_IME, LOZINKA, IME, PREZIME, ULOGA "
                + "FROM Radnik "
                + "WHERE ULOGA = '" + role + "'";
        finalLogMessage = "User verification done.\n";
        
        this.lf = loginFrame;
        this.inputUsername = username;
        this.inputPassword = password;
        this.inputRole = role;
    }

    // provjerava valjanost unesenih korisnickog imena i lozinke
    int checkInputCredentials(ResultSet rs) throws SQLException {
        // prolazimo po dobivenoj tablici...
        while (rs.next()) {
            // za svaki zapis iz tablice dohvacamo pripadni inputUsername i inputPassword
            String databaseUsername = rs.getString(
                    "KORISNICKO_IME");
            String databasePassword = rs.getString(
                    "LOZINKA");

            if (databaseUsername.equals(inputUsername)) {
                if (databasePassword.equals(inputPassword)) {
                    // korisnicko ime i lozinka poklapaju se s bazom
                    
                    firstName = rs.getString("IME");
                    lastName = rs.getString("PREZIME");
                    
                    actionIsExecuted();
                    return 0;
                }
                /* uneseno korisnicko ime nalazi se u bazi,
                no nije mu pridruzena lozinka koju je korisnik unio */
                else {
                    actionIsExecuted();
                    return 1;
                }
            }
        }
        
        /* prosli smo po citavoj bazi podataka,
           no uneseno korisnicko ime nije pronadjeno */
        actionIsExecuted();
        return 2;
    }
    
    @Override
    public Integer doInBackground() throws SQLException {
        lf.busy = true;
        
        ResultSet resultSet = executeSelect(sql);
        
        // potreban nam je rezultat provjere unesenog usernamea i passworda
        return checkInputCredentials(resultSet);
    }
    
    @Override
    public void done() {
        lf.busy = false;
            
        try {
            int logInAttemptResult = get();
            
            // provjeravamo ishod procesa prijave...
            switch (logInAttemptResult) {
                case 0 -> { // korisnicko ime i lozinka poklapaju se s bazom
                    Main.firstName = firstName;
                    Main.lastName = lastName;
                    lf.displaySuccess();
                    lf.done = true;
                }
                case 1 -> { // korisnicko ime postoji, ali lozinka nije ispravna
                    String warningMessage = "Unesena lozinka nije ispravna!";
                    lf.displayWarning(warningMessage,
                            false, true);
                }
                case 2 -> { // uneseno korisnicko ime ne postoji u bazi podataka
                    String worker = "";
                    switch(inputRole) {

                        case 'a' -> worker = "administratora!";
                        case 'b' -> worker = "blagajnika!";
                        default -> lf.displayError();
                    }
                    String warningMessage = "\"" + inputUsername
                            + "\" ne postoji kao korisničko ime nekog "
                            + worker;
                    lf.displayWarning(warningMessage,
                            true, true);
                }
                default -> lf.displayError();
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UserVerification.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
