/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package general;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Filip
 */
public class DatabaseConnection {
    public static Connection conn;
    String databaseName = "Menza.db";
    String databaseURL = "jdbc:sqlite:" + databaseName;
    
    public DatabaseConnection() throws SQLException {
        conn = getConnection();
    }
    
    // Ostvarivanje konekcije s bazom podataka
    public Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(databaseURL);
        if (conn != null) {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("Ime biblioteke za rad s bazom podataka: "
                    + meta.getDriverName());
            System.out.println("Konekcija ostvarena s: " + meta.getURL());
        }

        return conn;
    }
    
    // S obzirom na zadani String, izvrsava select-upit na bazu podataka
    public ResultSet executeSelect(String sql) throws SQLException {
        Statement stmt = conn.createStatement();

        return stmt.executeQuery(sql);
    }
    
    // S obzirom na zadani String, izvrsava update-upit na bazu podataka
    public boolean executeBillingUpdate(
            String sql,
            float newSubsidy,
            int jmbag) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setFloat(1, newSubsidy);
        pstmt.setInt(2, jmbag);
        
        // Izvrsavamo upit
        pstmt.executeUpdate();
        
        // Upit je izvrsen
        return true;
    }
    
    /*
    MIHAEL EDIT:
    */
    
}