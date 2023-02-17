/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workerFrame;

import general.DatabaseConnection;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Filip
 */
// klasa za prikaz dostupnih jela unutar workFrame-a
public class MealsDisplayment {
    WorkerFrame wf;
    CartDisplayment cd;
    
    public MealsDisplayment(WorkerFrame workerFrame) throws SQLException {
        this.wf = workerFrame;
        this.cd = new CartDisplayment(this.wf);
        
        DisplayAvailableMeals(); // Prikazujemo popis dostupnih jela
    }
    
    // Metoda koja unutar framea prikazuje dostupna jela koja je moguce odabrati
    private void DisplayAvailableMeals() throws SQLException {
        // dohvacamo dostupna jela u bazi u atribut meals
        System.out.println("Retrieving avaiable meals from database...");
        retrieveMealsFromDatabase();
        
        /* Od panela koji ce sadrzavati popis dostupnih jela iz baze
        nacinit cemo otprilike kvadratnu mrezu */
        int sideLength = (int) Math.sqrt(wf.meals.size());
        if (sideLength < 4) sideLength = 4;
        wf.setMealsPanelLayout(sideLength - 1);
        
        // Prolazimo po svakom dohvacenom jelu...
        for (String mealName : wf.meals.keySet()) {
            // Stvaramo pod-panel koji ce sadrzavati +, -, kolicinu i naziv jela
            JPanel mealPanel = new JPanel();
            // Popunjavamo pod-panel s +/- gumbima, kolicinom i nazivom jela
            fillInMealPanel(mealPanel, mealName);
            // Nad-panelu dodajemo upravo stvoreni panel
            wf.addMealSubpanel(mealPanel);
        }
        
        // Prilagodjavamo velicinu framea
        wf.pack();
    }
    
    // Metoda koja iz baze podataka dohvaca dostupna jela
    void retrieveMealsFromDatabase() throws SQLException {
        String sqlStatement = "SELECT NAZIV, KRATKI_NAZIV, CIJENA, RAZINA_SUBVENCIJE "
                + "FROM Hrana WHERE DOSTUPAN = \"true\"";
        
        // Ostvarujemo konekciju s bazom podataka
        DatabaseConnection database = new DatabaseConnection();
        
        // Izvrsavamo upit i pohranjujemo dobivenu tablicu
        ResultSet resultSet = database.executeSelect(sqlStatement);        
        System.out.println("Meals retrievement done.\n");        
        
        // Prolazimo po dobivenoj tablici...
        while (resultSet.next()) {
            String name = resultSet.getString("NAZIV");
            String shortName = resultSet.getString("KRATKI_NAZIV");
            String price = wf.addDecimals(Float.toString(resultSet.getFloat("CIJENA")));
            String subsidy = Float.toString(resultSet.getFloat("RAZINA_SUBVENCIJE"));
            
            ArrayList<String> data = new ArrayList<>(
                    Arrays.asList(shortName, price, subsidy));

            // Svako jelo dodajemo u mapu dostupnih obroka:
            wf.meals.put(name.toLowerCase(), data);
        }
        DatabaseConnection.conn.close();
    }
    
    // Metoda koja popunjava pod-panel unutar panela s dostupnim jelima
    void fillInMealPanel(JPanel mealPanel, String mealName) {
        // Dodajemo label u kojem ce biti prikazana kolicina pojedinog jela
        mealPanel.add(createQuantityLabel());

        // Dodajemo gumbe + i -
        mealPanel.add(createPlusMinusButton("-"));
        mealPanel.add(createPlusMinusButton("+"));

        // Dodajemo label s nazivom pojedinog jela
        mealPanel.add(new JLabel(mealName.toLowerCase()));

        // Postavljamo poravnanje unutar kvadratne mreze dostupnih jela
        mealPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    
    // Metoda za stvaranje textFielda koji prikazuje kolicinu pojedinog jela
    JLabel createQuantityLabel() {
        JLabel mealQuantityLabel = new JLabel();
        mealQuantityLabel.setText("0");

        return mealQuantityLabel;
    }
    
    // metoda za stvaranje gumba + i - koji sluze unosu kolicine pojedinog jela
    Button createPlusMinusButton(String operator) { // operator: + ili -
        Button button = new Button(operator);
        button.setPreferredSize(new Dimension(20, 20));
        button.setFont(new Font("Segoe UI 12", Font.BOLD, 14));
        
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusMinusButtonActionPerformed(evt);
            }
        });
        
        return button;
    }
    
    // Event handler za klikove na plus/minus gumbice
    private void plusMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Object source = evt.getSource();
        
        boolean sourceFound = false;
        String mealName = null;
        String operator = "";
        Component[] mealsComponents = wf.getMealsPanelComponents();
        for (Component component : mealsComponents) {
            JPanel mealPanel = (JPanel) component;
            Component[] subpanelComponents = mealPanel.getComponents();
            for (Component subcomponent : subpanelComponents) {
                if (subcomponent instanceof Button 
                        && source == subcomponent) {
                    Button pressedButton = (Button) subcomponent;
                    operator = pressedButton.getLabel();
                    modifyMealPanel(mealPanel, operator);
                    
                    sourceFound = true;
                    break;
                }
            }
            if (sourceFound) {
                JLabel label = (JLabel) mealPanel.getComponent(3);
                mealName = label.getText();
                break;
            }
        }
        
        boolean panelToInspectFound = false;
        JPanel panelToInspect = null;
        String shortMealName = wf.getMealShortName(mealName);
        Component[] cartComponents = wf.getCartPanelComponents();
        for (Component component : cartComponents) {
            JPanel itemPanel = (JPanel) component;
            Component[] subpanelComponents = itemPanel.getComponents();
            for (Component subcomponent : subpanelComponents) {
                if (subcomponent instanceof JLabel label) {
                    if (label.getText().equals(shortMealName)) {
                        panelToInspect = itemPanel;
                        panelToInspectFound = true;
                        break;
                    }
                }
            }
            if (panelToInspectFound) break;
        }
        if (panelToInspectFound) {
            cd.modifyCartPanel(panelToInspect, operator, mealName);
        } else {
            if (operator.equals("+")) {
                cd.addItemToCart(mealName);
                wf.modifyAmounts(mealName, operator);
            }
        }
    }    
    
    void modifyMealPanel(JPanel mealPanel, String operator) {
        JLabel mealQuantityLabel = (JLabel) mealPanel.getComponent(0);
        int mealQuantity = Integer.parseUnsignedInt(
                mealQuantityLabel.getText());
        
        if (operator.equals("+")) {
            ++mealQuantity;
        } else {
            if (mealQuantity > 0) --mealQuantity;
        }
        
        mealQuantityLabel.setText(String.valueOf(mealQuantity));
    }
    
    void resetMealPanel() {
        Component[] mealsComponents = wf.getMealsPanelComponents();
        for (Component component : mealsComponents) {
            if (component instanceof JPanel mealPanel) {
                JLabel mealQuantityLabel = (JLabel) mealPanel.getComponent(0);
                mealQuantityLabel.setText("0");
            }
        }
    }
}
