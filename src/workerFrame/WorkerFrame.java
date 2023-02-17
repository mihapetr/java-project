
package workerFrame;

import general.Main;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mihael
 */
/*
This is an example of a frame for the cash register worker. 
It doesn't have the "main" function. The "Main" class takes care of displaying and 
destruction.
Variable "done" is used for signaling to Main when to transition to the previous frame.
Setting "done" to "true" will cause the frame's distruction and the end user will
be prompted with the login window again.
A full implementation is needed for this class. See documentation. 
*/
public class WorkerFrame extends javax.swing.JFrame {

    public boolean done;
    
    // Spremamo dostupna jela: kljuc je puni naziv jela, a vrijednost podaci
    TreeMap<String, ArrayList<String>> meals;
    
    int inputJmbag;
    String input, studentName = null, studentSurname = null;
    String workerFirstName, workerLastName;
    float studentSubsidy;

    MealsDisplayment mealsDisplayment;
    
    /**
     * Creates new form workerFrame
     * @param workerFirstName
     * @param workerLastName
     * @throws java.sql.SQLException
     */
    public WorkerFrame(String workerFirstName, String workerLastName) throws SQLException {
        done = false;
        meals = new TreeMap();
        initComponents();
        this.workerFirstName = workerFirstName;
        this.workerLastName = workerLastName;
        
        //mealsPanel.setLayout(new BoxLayout(mealsPanel, BoxLayout.Y_AXIS));
        cartPanel.setLayout(new GridLayout(0, 1));
        cartPanel.setBorder(null);
        
        //cartTotalsPanel.setVisible(false);
        cartTotalsPanel.setLayout(new GridLayout(3, 2));
        
        
        studentDataLabel.setVisible(false);
        studentDataPanel.setLayout(new GridLayout(4, 2));
        studentDataPanel.setVisible(false);
        
        inputJmbag = -1;
        
        setCurrentDate();
        setWelcomeLabel();
        
        imagePanel.setVisible(false);
        
        // Konstrutkor MealsDisplayment klase izradi prikaz dostupnih jela baze
        mealsDisplayment = new MealsDisplayment(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoutButton = new javax.swing.JButton();
        mealsPanel = new javax.swing.JPanel();
        cartPanel = new javax.swing.JPanel();
        cartTotalsPanel = new javax.swing.JPanel();
        sumLabel = new javax.swing.JLabel();
        cartSubsidyAmountLabel = new javax.swing.JLabel();
        cartSubsidyLabel = new javax.swing.JLabel();
        toPayLabel = new javax.swing.JLabel();
        toPayAmountLabel = new javax.swing.JLabel();
        sumAmountLabel = new javax.swing.JLabel();
        studentDataPanel = new javax.swing.JPanel();
        jmbagLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        subsidyLabel = new javax.swing.JLabel();
        studentJmbagLabel = new javax.swing.JLabel();
        studentNameLabel = new javax.swing.JLabel();
        studentSurnameLabel = new javax.swing.JLabel();
        studentSubsidyAmountLabel = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        jmbagButton = new javax.swing.JButton();
        executeButton = new javax.swing.JButton();
        cancelJmbag = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        studentDataLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutButton.setText("Odjava");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        mealsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout mealsPanelLayout = new javax.swing.GroupLayout(mealsPanel);
        mealsPanel.setLayout(mealsPanelLayout);
        mealsPanelLayout.setHorizontalGroup(
            mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mealsPanelLayout.setVerticalGroup(
            mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        cartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        cartTotalsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        sumLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sumLabel.setText("Suma:");
        sumLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));

        cartSubsidyAmountLabel.setText("0.00");
        cartSubsidyAmountLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        cartSubsidyLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cartSubsidyLabel.setText("Stud. subvencija:");
        cartSubsidyLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 5, 5));

        toPayLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        toPayLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toPayLabel.setText("ZA PLATITI:");
        toPayLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        toPayAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        toPayAmountLabel.setText("0.00");
        toPayAmountLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        sumAmountLabel.setText("0.00");
        sumAmountLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        javax.swing.GroupLayout cartTotalsPanelLayout = new javax.swing.GroupLayout(cartTotalsPanel);
        cartTotalsPanel.setLayout(cartTotalsPanelLayout);
        cartTotalsPanelLayout.setHorizontalGroup(
            cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartTotalsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartTotalsPanelLayout.createSequentialGroup()
                        .addComponent(sumLabel)
                        .addGap(59, 59, 59)
                        .addComponent(sumAmountLabel))
                    .addGroup(cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartTotalsPanelLayout.createSequentialGroup()
                            .addComponent(cartSubsidyLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cartSubsidyAmountLabel))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartTotalsPanelLayout.createSequentialGroup()
                            .addComponent(toPayLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toPayAmountLabel))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cartTotalsPanelLayout.setVerticalGroup(
            cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartTotalsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sumAmountLabel)
                    .addComponent(sumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cartSubsidyAmountLabel)
                    .addComponent(cartSubsidyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartTotalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toPayLabel)
                    .addComponent(toPayAmountLabel)))
        );

        studentDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jmbagLabel.setText("JMBAG:");
        jmbagLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        nameLabel.setText("Ime:");
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        surnameLabel.setText("Prezime:");
        surnameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 5, 1));

        subsidyLabel.setText("Subvencija:");
        subsidyLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 1, 1));

        studentJmbagLabel.setText("studJmbag");
        studentJmbagLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 5));

        studentNameLabel.setText("studName");
        studentNameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 5));

        studentSurnameLabel.setText("studSurname");
        studentSurnameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 5));

        studentSubsidyAmountLabel.setText("studSubsidy");
        studentSubsidyAmountLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 1, 1, 5));

        javax.swing.GroupLayout studentDataPanelLayout = new javax.swing.GroupLayout(studentDataPanel);
        studentDataPanel.setLayout(studentDataPanelLayout);
        studentDataPanelLayout.setHorizontalGroup(
            studentDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDataPanelLayout.createSequentialGroup()
                .addComponent(subsidyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentSubsidyAmountLabel))
            .addGroup(studentDataPanelLayout.createSequentialGroup()
                .addComponent(jmbagLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentJmbagLabel))
            .addGroup(studentDataPanelLayout.createSequentialGroup()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentNameLabel))
            .addGroup(studentDataPanelLayout.createSequentialGroup()
                .addComponent(surnameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentSurnameLabel))
        );
        studentDataPanelLayout.setVerticalGroup(
            studentDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDataPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(studentDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmbagLabel)
                    .addComponent(studentJmbagLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(studentNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLabel)
                    .addComponent(studentSurnameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subsidyLabel)
                    .addComponent(studentSubsidyAmountLabel))
                .addContainerGap())
        );

        buttonsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jmbagButton.setText("Unesi JMBAG");
        jmbagButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmbagButtonActionPerformed(evt);
            }
        });

        executeButton.setText("Ispiši račun");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });

        cancelJmbag.setText("Poništi JMBAG");
        cancelJmbag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJmbagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(executeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jmbagButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addComponent(cancelJmbag)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jmbagButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelJmbag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(executeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        studentDataLabel.setText("Podaci o studentu:");

        dateLabel.setText("currentDate");

        welcomeLabel.setText("welcomeLabel");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mealsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addGap(18, 18, 18)
                        .addComponent(dateLabel)
                        .addGap(18, 18, 18)
                        .addComponent(welcomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cartTotalsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(studentDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(studentDataLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(resetButton)
                    .addComponent(dateLabel)
                    .addComponent(welcomeLabel))
                .addGap(18, 18, 18)
                .addComponent(mealsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartTotalsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(studentDataLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        done = true;
        Main.firstName = null;
        Main.lastName = null;
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void jmbagButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmbagButtonActionPerformed
        // TODO add your handling code here:
        input = JOptionPane.showInputDialog(
                null, "Unesite studentov JMBAG: ");
        
        if (!validateInput(input)) return;
        
        inputJmbag = Integer.parseUnsignedInt(input);
        
        JmbagVerification jmbagVerification = new JmbagVerification(
                this, inputJmbag);
        // Izvrsavamo verifikaciju unesenog JMBAG-a
        System.out.println("Verifying JMBAG " + inputJmbag + "...");        
        jmbagVerification.execute();
    }//GEN-LAST:event_jmbagButtonActionPerformed

    float getCartSubsidy() {
        return Float.parseFloat(cartSubsidyAmountLabel.getText());
    }
    
    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showOptionDialog(
                null,
                "Jeste li sigurni da želite ispisati račun?",
                "Potvrda ispisa računa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] {"Da", "Ne"},
                "Da");
        
        if (result == JOptionPane.YES_OPTION) {
            float cartSubsidy = getCartSubsidy();
            
            System.out.println("Billing in process...");
            // Izvrsavamo proces promjene stanja studentove subvencije
            // Ispisujemo racun
            if (inputJmbag == -1) {
                Billing billing = new Billing(this, inputJmbag);
                billing.execute();
            } else {
                if (studentSubsidy < cartSubsidy) displayError(
                        "Student/ica s unesenim JMBAG-om " + studentJmbagLabel.getText() + " nema dovoljno subvencije!",
                        "Nedovoljan iznos subvencije studenta");
                else {
                    Billing billing = new Billing(this, inputJmbag);
                    billing.execute();
                }
            }
        }
    }//GEN-LAST:event_executeButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        resetFrame();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void cancelJmbagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJmbagActionPerformed
        // TODO add your handling code here:
        resetStudentData();
    }//GEN-LAST:event_cancelJmbagActionPerformed

    void resetCartTotalsPanel() {
        sumAmountLabel.setText("0.00");
        cartSubsidyAmountLabel.setText("0.00");
        toPayAmountLabel.setText("0.00");
    }
    
    boolean validateInput(String input) {
        if (input == null) return false; // Korisnik je kliknuo na Cancel

        if (input.isEmpty()) {
            resetStudentData();
            return false;
        }

        if (!input.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Unesena vrijednost nije valjana. Potrebno je koristiti isključivo znamenke.",
                    "Upozorenje",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (input.length() > 10) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Uneseni JMBAG ima previše znamenaka (više od 10)",
                    "Upozorenje",
                    JOptionPane.WARNING_MESSAGE);
            return false;            
        }

        if (!input.matches("^[0-3].*")) {
            JOptionPane.showMessageDialog(
                    null,
                    "JMBAG mora započeti jednom od znamenaka 0, 1, 2 ili 3",
                    "Upozorenje",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
    
    void displayStudentData() {
        studentJmbagLabel.setText(String.valueOf(inputJmbag));
        studentNameLabel.setText(studentName);
        studentSurnameLabel.setText(studentSurname);
        studentSubsidyAmountLabel.setText(String.valueOf(studentSubsidy));
        
        System.out.println("Loading student image...");
        PictureCreating pictureCreating = new PictureCreating(this);
        
        studentDataLabel.setVisible(true);
        studentDataPanel.setVisible(true);
        System.out.println("Student image loaded.");
        
        updateFrameDisplay();
    }
    
    void addImage(Image image) {
        imagePanel.setLayout(null);
        int imageWidth = image.getWidth(null), 
                imageHeight = image.getHeight(null);
        imagePanel.setPreferredSize(new Dimension(imageWidth, imageHeight));
        
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(0, 0, imageWidth, imageHeight);
        imagePanel.add(label);
        imagePanel.setVisible(true);
    }
    
    void displayError(String message, String title) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }
    
    // Setter za layout panela s popisom dostupnih jela
    void setMealsPanelLayout(int cols) {
        mealsPanel.setLayout(new GridLayout(0, cols));
    }
    
    // Metoda koja panelu s popisom dostupnih jela dodaje pod-panel s jelom
    void addMealSubpanel(JPanel mealPanel) {
        mealsPanel.add(mealPanel);
    }
    
    // Getter za polje pod-panela sadrzanih unutar mealsPanel-a
    Component[] getMealsPanelComponents() {
        return mealsPanel.getComponents();
    }
    // Metoda koja panelu koji simulira prikaz racuna dodaje podpanel s artiklom
    String getMealShortName(String mealName) {
        return meals.get(mealName).get(0);
    }
    
    String getMealPrice(String mealName) {
        return meals.get(mealName).get(1);
    }
    
    String getMealSubsidy(String mealName) {
        return meals.get(mealName).get(2);
    }
    
    void addItemSubpanel(JPanel itemPanel) {
        cartPanel.add(itemPanel);
    }
    
    // Getter za polje pod-panela sadrzanih unutar cartPanel-a
    Component[] getCartPanelComponents() {
        return cartPanel.getComponents();
    }
    
    // Metoda koja brise stavku s prikaza racuna
    void deleteCartPanelSubpanel(JPanel itemSubpanel) {
        cartPanel.remove(itemSubpanel);
    }
    
    Component[] getCartComponents() {
        return cartPanel.getComponents();
    }

    JLabel newJLabel(String labelText,
            boolean setPreferredSize,
            boolean setBorder) {
        JLabel newLabel = new JLabel(labelText);
        
        if (setPreferredSize) {
            Dimension dimension = newLabel.getSize();
            newLabel.setPreferredSize(
                new Dimension(dimension.width + 100, dimension.height));
        }
        
        if (setBorder) newLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        newLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        return newLabel;
    }    
    
    void displayCartHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(0, 5));
        
        headerPanel.add(newJLabel("Artikl", false, true));
        headerPanel.add(newJLabel("Kolicina", false, true));
        headerPanel.add(newJLabel("Subvencija", false, true));
        headerPanel.add(newJLabel("Cijena", false, true));
        headerPanel.add(newJLabel("Iznos", false, true));
        
        cartPanel.add(headerPanel);
        cartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));
        
        updateFrameDisplay();
    }
    
    void deleteCart() {
        cartPanel.setBorder(null);
        cartPanel.removeAll();
    }
    
    int cartPanelComponentCount() {
        return cartPanel.getComponentCount();
    }
    
    void updateFrameDisplay() {
        pack();
        revalidate();
        repaint();
    }
    
    void modifyAmounts(String mealName, String operator) {
        float currentTotalSum = Float.parseFloat(
                sumAmountLabel.getText());
        float currentTotalSubsidy = Float.parseFloat(
                cartSubsidyAmountLabel.getText());
        
        float mealPrice = Float.parseFloat(getMealPrice(mealName));
        float mealSubsidy = Float.parseFloat(getMealSubsidy(mealName));
        
        if (operator.equals("+")) {
            currentTotalSum += mealPrice;
            currentTotalSubsidy += mealSubsidy * mealPrice;
        } else {
            currentTotalSum -= mealPrice;
            currentTotalSubsidy -= mealSubsidy * mealPrice;
            
            currentTotalSum = Math.abs(currentTotalSum);
            currentTotalSubsidy = Math.abs(currentTotalSubsidy);
        }
        
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String newTotalSum = decimalFormat.format(
                currentTotalSum);
        String newTotalSubsidy = decimalFormat.format(
                currentTotalSubsidy);
        String newToPayAmount = decimalFormat.format(
                Math.abs(currentTotalSum - currentTotalSubsidy));
        
        if (newTotalSum.equals("0")) newTotalSum += ".00";
        if (newTotalSubsidy.equals("0")) newTotalSubsidy += ".00";
        if (newToPayAmount.equals("0")) newToPayAmount += ".00";
        
        sumAmountLabel.setText(
                addDecimals(newTotalSum.replace(',', '.')));
        cartSubsidyAmountLabel.setText(
                addDecimals(newTotalSubsidy.replace(',', '.')));
        toPayAmountLabel.setText(
                addDecimals(String.valueOf(newToPayAmount).replace(',', '.')));
    }
    
    String getSummedPrice() {
        return sumAmountLabel.getText();
    }
    
    String getBillSubsidy() {
        return cartSubsidyAmountLabel.getText();
    }
    
    String getToPayAmount() {
        return toPayAmountLabel.getText();
    }
    
    private void setCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy");
        String stringToSet = dateFormat.format(date);
        
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        
        switch(dayOfWeek) {
            case MONDAY -> stringToSet += ", ponedjeljak";
            case TUESDAY -> stringToSet += ", utorak";
            case WEDNESDAY -> stringToSet += ", srijeda";
            case THURSDAY -> stringToSet += ", četvrtak";
            case FRIDAY -> stringToSet += ", petak";
            case SATURDAY -> stringToSet += ", subota";
            case SUNDAY -> stringToSet += ", nedjelja";
        }
        
        dateLabel.setText(stringToSet);        
    }
    
    private void setWelcomeLabel() {
        welcomeLabel.setText("Dobro došli, " + workerFirstName + " " + workerLastName);
    }
    
    void resetStudentData() {
        inputJmbag = -1;
        studentName = null; studentSurname = null;
        studentDataPanel.setVisible(false);
        studentDataLabel.setVisible(false);
        imagePanel.setVisible(false);
    }
    
    void resetFrame() {
        resetStudentData();
        mealsDisplayment.resetMealPanel();
        deleteCart();
        resetCartTotalsPanel();
        updateFrameDisplay();         
    }
    
    String addDecimals(String numberRepresentation) {
        int numberOfDecimals = numberRepresentation.contains(".") ? numberRepresentation.split("\\.")[1].length() : 0;
        
        switch (numberOfDecimals) {
            case 0 -> numberRepresentation += ".00";
            case 1 -> numberRepresentation += "0";
        }
        
        return numberRepresentation;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelJmbag;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JLabel cartSubsidyAmountLabel;
    private javax.swing.JLabel cartSubsidyLabel;
    private javax.swing.JPanel cartTotalsPanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton executeButton;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JButton jmbagButton;
    private javax.swing.JLabel jmbagLabel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mealsPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel studentDataLabel;
    private javax.swing.JPanel studentDataPanel;
    private javax.swing.JLabel studentJmbagLabel;
    private javax.swing.JLabel studentNameLabel;
    private javax.swing.JLabel studentSubsidyAmountLabel;
    private javax.swing.JLabel studentSurnameLabel;
    private javax.swing.JLabel subsidyLabel;
    private javax.swing.JLabel sumAmountLabel;
    private javax.swing.JLabel sumLabel;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JLabel toPayAmountLabel;
    private javax.swing.JLabel toPayLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
