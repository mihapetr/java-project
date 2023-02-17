/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminFrame;

import general.DatabaseConnection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Mihael
 */
public class RemoveMeal extends SwingWorker<Void,Void>{
    
    int [] rows;
    AdminFrame parent;
    boolean error;
    
    public RemoveMeal(int[] _rows, AdminFrame _frame) {
        parent = _frame;
        rows = _rows;
    }
    
    @Override
    public Void doInBackground() {
        
        Connection c = DatabaseConnection.conn;
        String sql = "delete from Hrana where NAZIV = ?";
        try {
            
            for(int index : rows) {
                PreparedStatement s = c.prepareStatement(sql);
                s.setString(1, parent.model.getValueAt(index, 0).toString());
                //System.out.println(s.toString());
                s.executeUpdate();
            }
            error = false;
        } catch (SQLException ex) {
           error = true;
           JOptionPane.showMessageDialog(parent, ex.getMessage());
        }
        
        
        return null;
    }
    
    @Override 
    public void done() {
        String key;
        if(!error) {
            for(int index : rows) {
                key = parent.model.getValueAt(index, 0).toString().toLowerCase();
                parent.model.removeRow(index);
                parent.removeFromPanel(key);
            }
        }
    }
}
