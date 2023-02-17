/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Mihael
 */
public class SaveGrid extends SwingWorker<Void, Void>{
    
    AdminFrame parent;
    boolean error;
    
    public SaveGrid(AdminFrame _parent) {
        parent = _parent;
    }
    
    @Override
    public Void doInBackground() {
        
        try {
            saveGrid();
            error = false;
        } catch (SQLException ex) {
            error = true;
            JOptionPane.showMessageDialog(parent, ex.getMessage());
        }
        
        return null;
    }
    
    @Override 
    public void done() {
        if(!error) 
        parent.goodSave();
    }
    
    void saveGrid() throws SQLException{
        System.out.println("Ažuriranje dostupnosti u bazi.");
        Connection conn = parent.database.getConnection();
        JCheckBox box;
        String mealName;
        Object _available;
        boolean available;
        PreparedStatement stmt;
        String sql = "UPDATE Hrana SET DOSTUPAN = ? WHERE NAZIV = ?";
        for(Map.Entry<String,JCheckBox> e : parent.grid.entrySet()) {
            
            //promjena available atributa u bazi podataka
            box = e.getValue();
            mealName = e.getKey();
            _available = box.getSelectedObjects();
            available = _available==null ? false : true;
            //System.out.println(mealName + " : " + available);
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, available ? "true" : "false");
            stmt.setString(2, mealName.toUpperCase());
            //System.out.println("updating: " + stmt.toString());
            //ISKORISTI Bcg klasu
            stmt.executeUpdate();
            
        }
    }
    
}
