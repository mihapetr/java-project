/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workerFrame;

import general.DatabaseConnection;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Filip
 */
public class Billing extends WorkerQueryExecution<Boolean> {
    WorkerFrame wf;
    int jmbag;
    float newSubsidy, toPayKnAmount;
    String knRepresentation, rightsUntil = "15.10.2023.";
    String billSubsidy, toPayAmount;
    String studentFullName, subsidyLeft;
    
    public Billing(WorkerFrame workerFrame, int jmbag) {
        sql = "UPDATE Student SET SUBVENCIJA = ? "
                + "WHERE JMBAG = ?";
        finalLogMessage = "Billing process done.\n";
        
        this.wf = workerFrame;
        this.jmbag =  jmbag;
        
        prepareBillData();
    }
    
    @Override
    public Boolean doInBackground() throws SQLException, IOException {
        // Ostvarujemo konekciju s bazom podataka
        DatabaseConnection database = new DatabaseConnection();
        
        boolean updateExecuted = true;
        // Izvrsavamo upit
        if (jmbag != -1) {
        updateExecuted = database.executeBillingUpdate(
                sql,
                newSubsidy,
                wf.inputJmbag);
        }
        
        printBill();
        
        actionIsExecuted();
        return updateExecuted;
    }
    
    private void prepareBillData() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (jmbag != -1) {
            this.newSubsidy = wf.studentSubsidy - wf.getCartSubsidy();
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            this.newSubsidy = Float.parseFloat(
                    decimalFormat.format(this.newSubsidy).replace(
                            ',', '.'));
            
            billSubsidy = wf.getBillSubsidy();
            
            toPayAmount = wf.getToPayAmount();           
            
            knRepresentation = "0.00";
        } else {
            billSubsidy = "0.00";
            
            toPayAmount = wf.getSummedPrice();
            
            toPayKnAmount = (float) (Float.parseFloat(wf.getToPayAmount()) * 7.5345);
            knRepresentation = decimalFormat.format(toPayKnAmount).replace(',', '.');
        }
        
        if (wf.studentName == null && wf.studentSurname == null) studentFullName = "N/A";
        else studentFullName = wf.studentName + " " + wf.studentSurname;
        
        if (newSubsidy == 0.0) subsidyLeft = "N/A";
        else subsidyLeft = String.valueOf(newSubsidy);        
    }
    
    String getLabelText(JPanel itemPanel, int i) {
        JLabel label = (JLabel) itemPanel.getComponent(i);
        return label.getText();
    }
    
    void printBill() throws IOException {
        String directoryPath = "bills";
        
        Date date = new Date();
        SimpleDateFormat fileNameFormatter = new SimpleDateFormat("E-MMM-dd-yyy-HH-mm-ss", Locale.ENGLISH);
        String billFileName = fileNameFormatter.format(date) + ".txt";
        
        SimpleDateFormat billFormatter = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
        String formattedDate = billFormatter.format(date);
                
        File billFile = new File(directoryPath + File.separator + billFileName);        

        try {
            if (!billFile.exists()) {
                new File(directoryPath).mkdirs();
                billFile.createNewFile();
            }
            
            FileWriter fileWriter = new FileWriter(billFile);
            
            fileWriter.write("\tSVEUČILIŠTE U NEMAGRADU\n");
            fileWriter.write("\tSC U NEMAGRADU, NG ALEJA PRAZNINE 52\n");
            fileWriter.write("\tNEMAGRAD, OIB: 54148779522\n");
            fileWriter.write("\tRest.st.dom Ruža Petrarković\n");
            fileWriter.write("\tLinija 2\n");
            fileWriter.write("\tUlica uspjeha 89\n\n");

            fileWriter.write("Kasa: 214-2\n");
            fileWriter.write("Datum: " + formattedDate + "\n\n");
            fileWriter.write("ARTIKL\t\tKOL\tSUBV.\tCIJENA\tIZNOS\n");
            for (int i = 0; i < 45; ++i) fileWriter.write("-");
            fileWriter.write("\n");
            
            Component[] itemPanels = wf.getCartComponents();
            boolean pastHeader = false;
            for (Component component : itemPanels) {
                if (!pastHeader) {
                    pastHeader = true;
                    continue;
                }
                String line = "";
                if (component instanceof JPanel itemPanel) {
                    line += getLabelText(itemPanel, 0).toUpperCase();
                    if (line.length() < 8) line += "\t\t";
                    else line += "\t";
                    
                    line += getLabelText(itemPanel, 1);
                    line += "\t";
                    
                    line += getLabelText(itemPanel, 2);
                    line += "\t";
                    
                    line += getLabelText(itemPanel, 3);
                    line += "\t";
                    
                    line += getLabelText(itemPanel, 4);
                    line += "\t\n";
                }
                fileWriter.write(line);
            }
            
            for (int i = 0; i < 45; ++i) fileWriter.write("-");
            fileWriter.write("\n");
            fileWriter.write("\t\t\t\tSuma:   " + wf.getSummedPrice() + "\n");
            fileWriter.write("Stud.subvencija\t\t\t\t" + billSubsidy + "\n");
            for (int i = 0; i < 45; ++i) fileWriter.write("-");            
            fileWriter.write("\n");
            fileWriter.write("ZA PLATITI:\t" + toPayAmount + "EUR\n");
            fileWriter.write("\t\t\t\t\t" + knRepresentation + "kn\n");
            fileWriter.write("Fiksni tečaj konverzije: 1 eur = 7.53450 kn\n\n");
            
            fileWriter.write("PDV nije uračunat po čl.39 Zakona\n\n\n");
            fileWriter.write("Korisnik: " + studentFullName + "\n");
            fileWriter.write("Preostalo subvencije: " + subsidyLeft + "\n");
            fileWriter.write("Prava do: " + rightsUntil + "\n\n");
            fileWriter.write("Blagajnik: " + wf.workerFirstName + " " + wf.workerLastName + "\n\n");
            fileWriter.write("\tHVALA NA POSJETI!!!");
            
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        wf.studentName = "";
        wf.studentSurname = "";
        wf.studentSubsidy = 0;
    }
    
    @Override
    public void done() {
        try {
            boolean billingProcessResult = get();
            
            // Provjeravamo ishod procesa ispisa racuna
            if (!billingProcessResult)
                wf.displayError("Greška prilikom proces naplate!",
                        "Greška");
            
            wf.resetFrame();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
