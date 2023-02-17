/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workerFrame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class JmbagVerification extends WorkerQueryExecution<Boolean> {
    WorkerFrame wf;
    int inputJmbag;
            
    public JmbagVerification(
            WorkerFrame workerFrame,
            int inputJmbag) {
        sql = "SELECT JMBAG, IME, PREZIME, SUBVENCIJA "
                + "FROM Student ";
        finalLogMessage = "JMBAG verification done.\n";
        
        this.wf = workerFrame;
        this.inputJmbag = inputJmbag;
    }
    
    boolean checkInputJmbag(ResultSet rs) throws SQLException {
        while(rs.next()) {
            int databaseJmbag = rs.getInt("JMBAG");
            
            if (databaseJmbag == inputJmbag) {
                wf.studentName = rs.getString("IME");
                wf.studentSurname = rs.getString("PREZIME");
                wf.studentSubsidy = rs.getFloat("SUBVENCIJA");
                
                actionIsExecuted();
                return true;
            }
        }
        
        actionIsExecuted();
        return false;
    }
    
    @Override
    public Boolean doInBackground() throws SQLException {
        ResultSet resultSet = executeSelect(sql);
        
        return checkInputJmbag(resultSet);
    }
    
    @Override
    public void done() {
        try {
            boolean jmbagInputResult = get();
            
            // Provjeravamo ishod procesa unosa JMBAG-a...
            if (jmbagInputResult) wf.displayStudentData();
            else wf.displayError(
                    "Uneseni JMBAG " + inputJmbag + " ne postoji u bazi podataka",
                    "Nepostojeći JMBAG");
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(JmbagVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
