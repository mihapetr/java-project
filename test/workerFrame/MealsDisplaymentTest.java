/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package workerFrame;

import java.awt.Button;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import javax.swing.JButton;

/**
 *
 * @author Mihael
 */
public class MealsDisplaymentTest {
    
    static void print(String s) {
        System.out.println(s);
    }
    
    static void print(Boolean s) {
        System.out.println(s);
    }
    
    public MealsDisplaymentTest() {
    }

    @Test
    public void testCreateQuantityLabel() throws SQLException {
        MealsDisplayment instance = new MealsDisplayment(new WorkerFrame("John", "Doe"));
        JLabel label = instance.createQuantityLabel();

        assertTrue( 0 == label.getText().compareTo("0"));
    }
    
    @Test
    public void testFillInMealPanel() throws SQLException {
        WorkerFrame wf = new WorkerFrame("netko","netko");
        MealsDisplayment md = new MealsDisplayment(wf);
        JPanel mealPanel = new JPanel();
        String mealName = "Chicken Curry";
        
        md.fillInMealPanel(mealPanel, mealName);
        
        //assertEquals(4, mealPanel.getComponentCount());
        assertTrue(mealPanel.getComponent(0) instanceof JLabel);
        assertTrue(mealPanel.getComponent(1) instanceof Button);
        assertTrue(mealPanel.getComponent(2) instanceof Button);
        assertTrue(mealPanel.getComponent(3) instanceof JLabel);
        
        
        
        JLabel quantityLabel = (JLabel) mealPanel.getComponent(0);
        assertTrue(quantityLabel.getText().compareTo("0") == 0);
        
        
        Button minusButton = (Button) mealPanel.getComponent(1);
        assertTrue(minusButton.getLabel().compareTo("-") == 0);
        
        
        Button plusButton = (Button) mealPanel.getComponent(2);
        assertTrue(plusButton.getLabel().compareTo("+") == 0);
        
        JLabel nameLabel = (JLabel) mealPanel.getComponent(3);
        assertTrue(0 == mealName.toLowerCase().compareTo( nameLabel.getText()));
    }
    
    @Test
    public void testModifyMealPanel() throws SQLException{
        JPanel mealPanel = new JPanel();
        JLabel quantityLabel = new JLabel();
        quantityLabel.setText("0");
        mealPanel.add(quantityLabel);
        MealsDisplayment md = new MealsDisplayment(new WorkerFrame("",""));

        md.modifyMealPanel(mealPanel, "+");
        assertEquals(quantityLabel.getText(), "1");

        md.modifyMealPanel(mealPanel, "+");
        assertEquals(quantityLabel.getText(), "2");

        md.modifyMealPanel(mealPanel, "-");
        assertEquals(quantityLabel.getText(), "1");

        md.modifyMealPanel(mealPanel, "-");
        assertEquals("0",quantityLabel.getText());

        md.modifyMealPanel(mealPanel, "-");
        assertEquals(quantityLabel.getText(), "0");
    }
    
}
