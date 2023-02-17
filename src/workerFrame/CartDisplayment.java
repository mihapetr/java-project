/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workerFrame;

import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Filip
 */
public class CartDisplayment {
  WorkerFrame wf;
    
   public CartDisplayment(WorkerFrame workerFrame) {
       this.wf = workerFrame;
    }
    
    void addItemToCart(String mealName) {
        if (wf.cartPanelComponentCount() == 0) wf.displayCartHeader();
        
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(0, 5));
        
        itemPanel.add(
                wf.newJLabel(wf.getMealShortName(mealName), true, true));
        itemPanel.add(
                wf.newJLabel("1", false, false));
        itemPanel.add(
                wf.newJLabel(wf.getMealSubsidy(mealName), false, false));
        itemPanel.add(
                wf.newJLabel(wf.addDecimals(wf.getMealPrice(mealName)), false, false));
        itemPanel.add(
                wf.newJLabel(wf.addDecimals(wf.getMealPrice(mealName)), false, false));
        
        wf.addItemSubpanel(itemPanel);
                
        wf.updateFrameDisplay();
    }
    
    void modifyCartPanel(JPanel itemPanel, String operator, String mealName) {
        JLabel mealQuantityLabel = (JLabel) itemPanel.getComponent(1);
        JLabel summarizedPriceLabel = (JLabel) itemPanel.getComponent(4);
        JLabel priceLabel = (JLabel) itemPanel.getComponent(3);
        
        int mealQuantity = Integer.parseUnsignedInt(
                mealQuantityLabel.getText());
        float summarizedPrice = Float.parseFloat(
                summarizedPriceLabel.getText());
        float price = Float.parseFloat(
                priceLabel.getText());
        
        if (operator.equals("+")) {
            ++mealQuantity;
            summarizedPrice += price;
            
            wf.modifyAmounts(mealName, operator);
        } else {
            if (mealQuantity > 1) {
                --mealQuantity;
                mealQuantityLabel.setText(String.valueOf(mealQuantity));
                
                summarizedPrice -= price;
            } else {
                wf.deleteCartPanelSubpanel(itemPanel);
                if (wf.cartPanelComponentCount() == 1) wf.deleteCart();
            }
            
            wf.modifyAmounts(mealName, operator);
            
            wf.updateFrameDisplay();
        }
        
        mealQuantityLabel.setText(String.valueOf(mealQuantity));
        
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String newSummarizedPrice = decimalFormat.format(summarizedPrice);
        summarizedPriceLabel.setText(
                wf.addDecimals(newSummarizedPrice.replace(',','.')));
    }    
}
