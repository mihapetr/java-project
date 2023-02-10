
package javaproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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
public class workerFrame extends javax.swing.JFrame {

    boolean done;
    String databaseName;
    static String databaseURL;
    Connection conn;
    /**
     * Creates new form workerFrame
     */
    public workerFrame() {
        done = false;
        databaseName = "Menza.db";
        databaseURL = "jdbc:sqlite:" + databaseName;
        conn = getConnection(); 
        initComponents();
    }
    
    static Connection getConnection() {
        try (Connection connection = DriverManager.getConnection(
                databaseURL)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("Ime biblioteke za rad s bazom podataka "
                        + meta.getDriverName());
            }
            
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }        
    }
    
    // selekcija podataka o blagajnicima iz tablice Radnik
    ResultSet selectWorkersCredentials() {
        String sql = "SELECT KORISNICKO_IME, LOZINKA, IME, PREZIME "
                + "FROM Radnik "
                + "WHERE ULOGA = 'b'";
                
        try {
            Statement stmt = conn.createStatement();
            
            return stmt.executeQuery(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }        
    }
    
    // provjera unesenog korisnickog imena i lozinke - podudaraju li se s bazom
    int checkInputCredentials(ResultSet resultSet, 
            String inputUsername, String inputPassword) {
        try {
            while (resultSet.next()) {
                String databaseUsername = resultSet.getString(
                        "KORISNICKO_IME");
                String databasePassword = resultSet.getString(
                        "LOZINKA");

                if (databaseUsername.equals(inputUsername)) {
                    if (databasePassword.equals(inputPassword))
                        // korisnicko ime i lozinka poklapaju se s bazom
                        return 0;
                    /* uneseno korisnicko ime nalazi se u bazi,
                       no nije mu pridruzena lozinka koju je korisnik unio */
                    else return 1;
                }
            }
            /* prosli smo po citavoj bazi podataka,
               no uneseno korisnicko ime nije pronadjeno */
            return 2;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
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
        welcomePanel = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        explanationLabel = new javax.swing.JLabel();
        credentialsPanel = new javax.swing.JPanel();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        logInButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutButton.setText("logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Dobro došli!");

        explanationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        explanationLabel.setText("Prijavite se u sustav korištenjem svog korisničkog imena i lozinke:");

        javax.swing.GroupLayout welcomePanelLayout = new javax.swing.GroupLayout(welcomePanel);
        welcomePanel.setLayout(welcomePanelLayout);
        welcomePanelLayout.setHorizontalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(explanationLabel)
                .addContainerGap())
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(welcomeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        welcomePanelLayout.setVerticalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel)
                .addGap(18, 18, 18)
                .addComponent(explanationLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usernameTextField.setPreferredSize(new java.awt.Dimension(96, 22));

        passwordField.setPreferredSize(new java.awt.Dimension(96, 22));

        usernameLabel.setText("korisničko ime");

        passwordLabel.setText("lozinka");

        javax.swing.GroupLayout credentialsPanelLayout = new javax.swing.GroupLayout(credentialsPanel);
        credentialsPanel.setLayout(credentialsPanelLayout);
        credentialsPanelLayout.setHorizontalGroup(
            credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, credentialsPanelLayout.createSequentialGroup()
                .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(credentialsPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(credentialsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usernameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        credentialsPanelLayout.setVerticalGroup(
            credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(credentialsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addGap(18, 18, 18)
                .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        logInButton.setText("Prijava");
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 86, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(credentialsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(logInButton)
                                .addComponent(welcomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoutButton)
                .addGap(18, 18, 18)
                .addComponent(welcomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(credentialsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logInButton)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        done = true;
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        // TODO add your handling code here:
        String inputUsername = String.valueOf(usernameTextField.getText());
        String inputPassword = String.valueOf(passwordField.getPassword());
        
        if (inputUsername.equals("")) System.out.println("prazaaaan");
        
        int logInAttemptResult = checkInputCredentials(
                selectWorkersCredentials(),
                inputUsername,
                inputPassword);
    }//GEN-LAST:event_logInButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel credentialsPanel;
    private javax.swing.JLabel explanationLabel;
    private javax.swing.JButton logInButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables
}
