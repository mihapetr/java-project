/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package workerFrame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author Mihael
 */
public class WorkerFrameTest {
    
    public WorkerFrameTest() {
    }

    @Test
    public void testNewJLabel() throws SQLException {
        WorkerFrame wf = new WorkerFrame("","");

        String labelText = "Test Label";
        boolean setPreferredSize = true;
        boolean setBorder = true;

        JLabel label = wf.newJLabel(labelText, setPreferredSize, setBorder);

        assertNotNull(label);
        assertEquals(labelText, label.getText());
        assertEquals(SwingConstants.CENTER, label.getHorizontalAlignment());
        assertEquals(5, ((EmptyBorder) label.getBorder()).getBorderInsets().top);
        assertEquals(100, label.getPreferredSize().width);
    }
    
    @Test
    public void testValidInput() throws SQLException {
        WorkerFrame instance = new WorkerFrame("John", "Doe");
        assertTrue(instance.validateInput("1234567890"));
    }
    
    @Test
    public void testEmptyInput() throws SQLException {
        WorkerFrame instance = new WorkerFrame("John", "Doe");
        assertFalse(instance.validateInput(""));
    }
    
    @Test
    public void testNullInput() throws SQLException {
        WorkerFrame instance;
        
        instance = new WorkerFrame("John", "Doe");
        assertFalse(instance.validateInput(null));
        
    }
    
    
}
