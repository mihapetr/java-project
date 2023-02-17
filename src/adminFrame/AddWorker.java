/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminFrame;

import general.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Mihael
 */
public class AddWorker extends SwingWorker<Void,Void>{
    
    PreparedStatement s;
    AdminFrame parent;
    Object[] row;
    boolean error;
    
    public AddWorker(PreparedStatement _s, AdminFrame _frame, Object[] _row) {
        //row = _row;
        s = _s;
        parent = _frame;
        row = _row;
    }
    
    @Override
    public Void doInBackground() {
        
        Connection c = DatabaseConnection.conn;
        try {
            s.executeUpdate();
            error = false;
        } catch (SQLException ex) {
           error = true;
           JOptionPane.showMessageDialog(parent, ex.getMessage());
        }
        
        
        return null;
    }
    
    @Override 
    public void done() {
        if(!error) {
            parent.modelRadnik.addRow(row);
        }
    }
}
