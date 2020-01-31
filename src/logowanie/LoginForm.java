package logowanie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NazwaUzytkownika = new javax.swing.JTextField();
        Haslo = new javax.swing.JPasswordField();
        Zaloguj = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Utworz = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nazwa uzytkownika");

        jLabel2.setText("Haslo");

        Haslo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HasloActionPerformed(evt);
            }
        });

        Zaloguj.setText("Zaloguj");
        Zaloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZalogujActionPerformed(evt);
            }
        });

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        Utworz.setText("Utworz");
        Utworz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UtworzActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Haslo, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(NazwaUzytkownika)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(Zaloguj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Utworz)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NazwaUzytkownika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Haslo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Zaloguj)
                    .addComponent(Reset)
                    .addComponent(Utworz))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HasloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HasloActionPerformed

    }//GEN-LAST:event_HasloActionPerformed

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/akademia";
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);

            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    private void ZalogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZalogujActionPerformed
        try {

            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("Select * from logowanie where Nazwa=? and Haslo=?");
            pst.setString(1, NazwaUzytkownika.getText());
            pst.setString(2, Haslo.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Zalogowano");
            } else {
                JOptionPane.showMessageDialog(null, "Nazwa lub haslo nieprawidlowe");
                NazwaUzytkownika.setText("");
                Haslo.setText("");
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_ZalogujActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        NazwaUzytkownika.setText("");
        Haslo.setText("");
    }//GEN-LAST:event_ResetActionPerformed

    private void UtworzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UtworzActionPerformed
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("select Nazwa from logowanie where Nazwa = ?");
            pst.setString(1, NazwaUzytkownika.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Uzytkownik juz istnieje. Wybierz inna nazwa.");
            } else {
                String var1 = NazwaUzytkownika.getText();
                String var2 = Haslo.getText();
                PreparedStatement posted = con.prepareStatement("insert into logowanie (Nazwa, Haslo) values ('" + var1 + "', '" + var2 + "')");
                posted.executeUpdate();
                NazwaUzytkownika.setText("");
                Haslo.setText("");
                JOptionPane.showMessageDialog(null, "Utworzono nowe konto.");
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_UtworzActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Haslo;
    private javax.swing.JTextField NazwaUzytkownika;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Utworz;
    private javax.swing.JButton Zaloguj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
