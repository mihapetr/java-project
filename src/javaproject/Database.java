/*
DriverManager::getConnection(String url)
Connection::createStatement(void)
Statement::execute(String sql_command)
Statement::executeQuery(String sql_command)
*/

package javaproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Database {
    
    Connection c;
    
    public Database(String fileName) {
        String url = "jdbc:sqlite:" + fileName;
        
        try {
            Connection _c = DriverManager.getConnection(url);
            if(_c != null) {
                c = _c;
                DatabaseMetaData mData = c.getMetaData();
                System.out.println("Connected to: " + mData.getURL());
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //used for user vaerification
    public boolean query(String korisnik, char uloga, String lozinka){
        String command = "select * from Radnik where KORISNICKO_IME = \"" + korisnik + "\"";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(command);            
            
            if(!rs.next()) return false; //result empty
            
            if(
                    !korisnik.equals( rs.getString("KORISNICKO_IME") ) ||
                    !Character.toString(uloga).equals( rs.getString("ULOGA") ) ||
                    !lozinka.equals( rs.getString("LOZINKA") )
                     
              )return false;
            
            return true; //all tests passed
            
        }
        catch (SQLException e){
            System.out.println("error");
            e.getMessage();
        }
        return false;
    }
         
    
}
