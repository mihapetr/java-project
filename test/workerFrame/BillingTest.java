/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package workerFrame;

import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Mihael
 */
public class BillingTest {
    
    public BillingTest() {
    }

   
    @Test
    public void testGetLabelText() throws SQLException {
        Billing instance = new Billing(new WorkerFrame("John", "Doe"), -1);
        JPanel panel = new JPanel();
        panel.add(new JLabel("First label"));
        panel.add(new JLabel("Second label"));

        String expected = "First label";
        String actual = instance.getLabelText(panel, 0);
        assertTrue(0 == expected.compareTo( actual));

        expected = "Second label";
        actual = instance.getLabelText(panel, 1);
        assertTrue(0 == expected.compareTo( actual));
    }

}

    

