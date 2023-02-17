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
public class RemoveWorker extends SwingWorker<Void,Void>{
    
    
    int [] rows;
    AdminFrame parent;
    boolean error;
    
    public RemoveWorker(int[] _rows, AdminFrame _frame) {
        parent = _frame;
        rows = _rows;
    }
    
    @Override
    public Void doInBackground() {
        
        Connection c = DatabaseConnection.conn;
        String sql = "delete from Radnik where KORISNICKO_IME = ?";
        try {
            
            for(int index : rows) {
                PreparedStatement s = c.prepareStatement(sql);
                s.setString(1, parent.modelRadnik.getValueAt(index, 0).toString());
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
                parent.modelRadnik.removeRow(index);
            }
        }
    }
}
