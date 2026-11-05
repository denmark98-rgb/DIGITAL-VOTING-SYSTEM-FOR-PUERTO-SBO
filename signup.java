/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class signup extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(signup.class.getName());
    
    private javax.swing.JLabel lblContact;
    private javax.swing.JTextField txtContact;

    /**
     * Creates new form signup
     */
private ResponsiveScaler scaler;

public signup() {
    initComponents();
    setLocationRelativeTo(null);

    final int W = 900, H = 750, HEADER = 110, BODY = H - HEADER;   // 640

    // ---------- HEADER (centered crest + title) ----------
    jLabel5.setText("");
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jPanel2.setLayout(null);
    jPanel2.setBounds(0, 0, W, HEADER);
    jLabel5.setBounds(70, 12, 430, 62);   // 70..500 = the white form area
    jLabel1.setBounds(70, 78, 430, 20);
    // ---------- BODY ----------
    jPanel1.setLayout(null);
    jPanel1.setBounds(0, HEADER, W, BODY);

    jPanel4.setBounds(0, 0, 70, BODY);              // left green strip
    jPanel5.setBounds(70, BODY - 50, 430, 50);      // bottom green bar under the form
    jPanel3.setBounds(500, 0, W - 500, BODY);       // right green picture panel

    // form column: x = 115 .. 455
    int x = 115, w = 340;
    jLabel3.setBounds(x, 25, w, 20);
    txtStudentID.setBounds(x, 47, w, 30);
    jLabel4.setBounds(x, 85, w, 20);
    txtPassword.setBounds(x, 107, w, 30);
    chkShowPassword.setBounds(x, 140, 130, 20);
    jLabel7.setBounds(x, 165, w, 20);
    txtConfirmPassword.setBounds(x, 187, w, 30);
    chkShowConfirmPassword.setBounds(x, 220, 130, 20);
    jLabel8.setBounds(x, 245, w, 20);
    cmbCourse.setBounds(x, 267, w, 30);
    jLabel2.setBounds(x, 305, w, 20);
    cmbYearLevel.setBounds(x, 327, w, 30);
    lblContact = new javax.swing.JLabel("Contact Number (11 digits)");
txtContact = new javax.swing.JTextField();
lblContact.setBounds(x, 365, w, 20);
txtContact.setBounds(x, 387, w, 30);
jPanel1.add(lblContact);
jPanel1.add(txtContact);

// only allow digits, max 11
txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || txtContact.getText().length() >= 11) {
            e.consume();
        }
    }
});

btnSignUp.setBounds(x, 435, w, 30);
jButton1.setBounds(x, 480, w, 30);
    // right panel: picture + logo, centered
    int pw = W - 500;
    jPanel3.setLayout(null);
    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    java.awt.Dimension pic = jLabel6.getPreferredSize();
    java.awt.Dimension logo = jLabel9.getPreferredSize();
    jLabel6.setBounds(0, 0, pw, pic.height);
    jLabel9.setBounds(0, pic.height + 15, pw, logo.height);

// ---------- ROOT: fixed size, centered ----------
javax.swing.JPanel root = new javax.swing.JPanel(null);
root.setBackground(new java.awt.Color(0, 102, 51));
root.setPreferredSize(new java.awt.Dimension(W, H));
root.add(jPanel2);
root.add(jPanel1);

// Window background is green, root sits in the middle
getContentPane().removeAll();
getContentPane().setBackground(new java.awt.Color(0, 102, 51));
getContentPane().setLayout(new java.awt.GridBagLayout());   // centers its single child
getContentPane().add(root);

java.awt.EventQueue.invokeLater(() ->
    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH));
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSignUp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        chkShowConfirmPassword = new javax.swing.JCheckBox();
        chkShowPassword = new javax.swing.JCheckBox();
        cmbCourse = new javax.swing.JComboBox<>();
        cmbYearLevel = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setForeground(new java.awt.Color(51, 204, 0));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\Cagayan de Oro College Crest (2).png")); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CAGAYAN DE ORO COLLEGE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 110);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnSignUp.setBackground(new java.awt.Color(0, 51, 0));
        btnSignUp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Sign Up");
        btnSignUp.addActionListener(this::btnSignUpActionPerformed);

        jLabel3.setText("Student ID");

        jLabel4.setText("Password");

        txtConfirmPassword.addActionListener(this::txtConfirmPasswordActionPerformed);

        jLabel7.setText("Confirm Password");

        jLabel8.setText("Course");

        jLabel2.setText("Year Level");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Do you have an account already? Sign In ");
        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jPanel3.setBackground(new java.awt.Color(0, 102, 51));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\Cagayan de Oro Campus Portrait (2).png")); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\{A2A748E8-3202-4AC6-B312-C3B9BCA6736E} (3).png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        chkShowConfirmPassword.setText("Show Password");
        chkShowConfirmPassword.addActionListener(this::chkShowConfirmPasswordActionPerformed);

        chkShowPassword.setText("Show Password");
        chkShowPassword.addActionListener(this::chkShowPasswordActionPerformed);

        cmbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Management and Accountancy", "Education", "Criminology and Criminal Justice", "Engineering and Architect", "Information Technology", "Allied Health Sciences" }));

        cmbYearLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year 1", "Year 2", "Year 3", "Year 4" }));

        jPanel5.setBackground(new java.awt.Color(0, 102, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(chkShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(btnSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(txtStudentID, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chkShowConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
                            .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkShowPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkShowConfirmPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnSignUp)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 110, 860, 640);

        setBounds(0, 0, 803, 717);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login home = new login();
           home.setVisible(true);
           this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed

String studentID = txtStudentID.getText().trim();
String password = new String(txtPassword.getPassword());
String confirmPassword = new String(txtConfirmPassword.getPassword());

String course = (String) cmbCourse.getSelectedItem();
String yearLevel = (String) cmbYearLevel.getSelectedItem();

String contact = txtContact.getText().trim();

if (contact.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your contact number.");
    return;
}
if (!contact.matches("\\d{11}")) {
    JOptionPane.showMessageDialog(this, "Contact number must be exactly 11 digits.");
    return;
}
if (password.length() < 6) {
    JOptionPane.showMessageDialog(this, "Password must be at least 6 characters.");
    return;
}

// CHECK IF REQUIRED FIELDS ARE EMPTY
if (studentID.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your Student ID.");
    return;
}

if (password.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your password.");
    return;
}

if (confirmPassword.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please confirm your password.");
    return;
}

if (cmbCourse.getSelectedIndex() == -1) {
    JOptionPane.showMessageDialog(this, "Please select your course.");
    return;
}

if (cmbYearLevel.getSelectedIndex() == -1) {
    JOptionPane.showMessageDialog(this, "Please select your year level.");
    return;
}

// KEEP YOUR STUDENT ID VALIDATION AND OTHER CODE BELOW THIS



if (!studentID.matches("\\d+")) {
    JOptionPane.showMessageDialog(
        this,
        "Student ID must contain numbers only."
    );
    return;
}

if (studentID.length() <= 11) {
    JOptionPane.showMessageDialog(
        this,
        "Student ID must exceed 11 digits."
    );
    return;
}


if (password.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Password is required.");
    return;
}

if (confirmPassword.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please confirm your password.");
    return;
}

if (!password.equals(confirmPassword)) {
    JOptionPane.showMessageDialog(this, "Passwords do not match.");
    return;
}

if (course == null || yearLevel == null) {
    JOptionPane.showMessageDialog(this, "Please select your course and year level.");
    return;
}

String url = "jdbc:mysql://localhost:3306/svoting_db";
String user = "root";
String pass = "";

try {
    Class.forName("com.mysql.cj.jdbc.Driver");

    try (Connection con = DriverManager.getConnection(url, user, pass)) {

        // Check whether the Student ID is already registered
        String checkSql =
            "SELECT COUNT(*) FROM tbl_users WHERE student_id = ?";

        try (java.sql.PreparedStatement check =
                con.prepareStatement(checkSql)) {

            check.setString(1, studentID);

            try (java.sql.ResultSet rs = check.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(
                        this, "This Student ID is already registered."
                    );
                    return;
                }
            }
        }

        // Save the new student account
       String query =
    "INSERT INTO tbl_users "
    + "(student_id, password, role, course, year_level, contact_number) "
    + "VALUES (?, ?, ?, ?, ?, ?)";

        try (java.sql.PreparedStatement pst =
                con.prepareStatement(query)) {

            pst.setString(1, studentID);
            pst.setString(2, password);
            pst.setInt(3, 1);
            pst.setString(4, course);
            pst.setString(5, yearLevel);
            pst.setString(6, contact);

            pst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Sign up successful!");

        login loginForm = new login();
        loginForm.setVisible(true);
        this.dispose();
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error signing up: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnSignUpActionPerformed

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed

if (chkShowPassword.isSelected()) {
    txtPassword.setEchoChar((char) 0);
} else {
    txtPassword.setEchoChar('*');
}

    }//GEN-LAST:event_chkShowPasswordActionPerformed

    private void chkShowConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowConfirmPasswordActionPerformed
        
if (chkShowConfirmPassword.isSelected()) {
    txtConfirmPassword.setEchoChar((char) 0);
} else {
    txtConfirmPassword.setEchoChar('*');
}

    }//GEN-LAST:event_chkShowConfirmPasswordActionPerformed

    private void txtConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new signup().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignUp;
    private javax.swing.JCheckBox chkShowConfirmPassword;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JComboBox<String> cmbCourse;
    private javax.swing.JComboBox<String> cmbYearLevel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtStudentID;
    // End of variables declaration//GEN-END:variables
}
