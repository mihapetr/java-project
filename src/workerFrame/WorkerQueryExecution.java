/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workerFrame;

import general.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingWorker;

/**
 *
 * @author Filip
 * @param <T>
 * WorkerQueryExecution je apstraktna klasa koja implementira genericke metode
 izvrsavanja naredbe na bazu (executeSelect) te ispisa pripadne log-poruke.
 */

public abstract class WorkerQueryExecution<T> extends SwingWorker<T, Void> {
    public String sql; // String koji oznacava upit na bazu
    public String finalLogMessage; // poruka koja ce se ispisati po izvrsavanju SQL naredbe

    // izvrsava select-upit na bazu podataka, vraca tablicu (ResultSet)
    public ResultSet executeSelect(String sql) throws SQLException {
        // ostvarujemo konekciju s bazom podataka
        DatabaseConnection database = new DatabaseConnection();
        
        // izvrsavamo upit, a dobivenu tablicu dajemo kao povratnu vrijednost
        return database.executeSelect(sql);
    }
    
    // oznacava da je radnja koja prati naredbu na bazu izvrsena
    public void actionIsExecuted() throws SQLException {
        System.out.println(finalLogMessage);
        DatabaseConnection.conn.close(); // zatvaramo konekciju
    }
}
