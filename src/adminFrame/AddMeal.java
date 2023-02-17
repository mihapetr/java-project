/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminFrame;

import general.DatabaseConnection;
import java.sql.PreparedStatement;
import javax.swing.SwingWorker;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mihael
 */
public class AddMeal extends SwingWorker<Void, Void>{
    
    PreparedStatement s;
    AdminFrame parent;
    Object[] row;
    boolean error;
    
    public AddMeal(PreparedStatement _s, AdminFrame _frame, Object[] _row) {
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
            parent.model.addRow(row);
            parent.addToPanel(row[0].toString().toLowerCase());
            parent.pack();
        }
    }
}
