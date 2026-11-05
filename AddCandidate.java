/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class AddCandidate extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddCandidate.class.getName());
    
    private java.io.File selectedPhoto;
    
    private ResponsiveScaler scaler;

    /**
     * Creates new form AddCandidate
     */
public AddCandidate() {
    initComponents();
    jLabel2.setText("");

    java.awt.Color green = new java.awt.Color(0, 102, 51);

    // ---------- 1. SIDEBAR: big buttons, big icons, even spacing ----------
    javax.swing.JPanel sidebar = new javax.swing.JPanel(new java.awt.GridBagLayout());
    sidebar.setBackground(green);
    sidebar.setPreferredSize(new java.awt.Dimension(340, 0));

    javax.swing.JLabel[] navIcons = { jLabel10, jLabel11, jLabel12, jLabel13, jLabel14 };
    javax.swing.JButton[] navButtons = { jButton2, jButton3, jButton4, jButton5, jButton6 };

    java.awt.GridBagConstraints sc = new java.awt.GridBagConstraints();
    sc.gridx = 0;
    sc.weightx = 1;
    sc.fill = java.awt.GridBagConstraints.HORIZONTAL;

    for (int i = 0; i < navButtons.length; i++) {
        scaleIcon(navIcons[i], 40);
        navIcons[i].setPreferredSize(new java.awt.Dimension(48, 48));
        navButtons[i].setFont(navButtons[i].getFont().deriveFont(java.awt.Font.BOLD, 18f));
        navButtons[i].setPreferredSize(new java.awt.Dimension(100, 52));

        javax.swing.JPanel row = new javax.swing.JPanel(new java.awt.BorderLayout(14, 0));
        row.setOpaque(false);
        row.add(navIcons[i], java.awt.BorderLayout.WEST);
        row.add(navButtons[i], java.awt.BorderLayout.CENTER);

        sc.gridy = i;
        sc.insets = new java.awt.Insets(i == 0 ? 40 : 0, 28, 20, 28);
        sidebar.add(row, sc);
    }
    // filler pushes the rows to the top
    sc.gridy = navButtons.length;
    sc.weighty = 1;
    sc.fill = java.awt.GridBagConstraints.VERTICAL;
    sidebar.add(javax.swing.Box.createVerticalGlue(), sc);

    // ---------- 2. FORM CARD: larger fonts and fields ----------
    javax.swing.JPanel card = new javax.swing.JPanel(new java.awt.GridBagLayout());
    card.setBackground(java.awt.Color.WHITE);
    card.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 50, 16, 50));

    java.awt.GridBagConstraints gc = new java.awt.GridBagConstraints();
    gc.gridx = 0;
    gc.weightx = 1;
    gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gc.anchor = java.awt.GridBagConstraints.WEST;
    gc.ipady = 2;   // makes every field a bit taller

    jLabel4.setFont(jLabel4.getFont().deriveFont(java.awt.Font.BOLD, 24f));
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gc.insets = new java.awt.Insets(0, 0, 8, 0);
    card.add(jLabel4, gc);

    jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
    jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 80));
    txtPlatform.setFont(txtPlatform.getFont().deriveFont(14f));
    jTextArea1.setFont(jTextArea1.getFont().deriveFont(14f));

    java.awt.Component[][] rows = {
        { jLabel1,  txtName },
        { jLabel5,  txtStudentID },
        { jLabel6,  cmbPosition },
        { jLabel7,  txtParty },
        { jLabel15, jComboBox1 },
        { jLabel16, jComboBox2 },
        { jLabel8,  jScrollPane1 },
        { jLabel17, jScrollPane3 },
        { jLabel9,  jButton1 }
    };
    for (java.awt.Component[] row : rows) {
        row[0].setFont(row[0].getFont().deriveFont(16f));        // label
        if (!(row[1] instanceof javax.swing.JScrollPane)) {
            row[1].setFont(row[1].getFont().deriveFont(14f));    // field
        }
        gc.insets = new java.awt.Insets(6, 0, 2, 0);
        card.add(row[0], gc);
        gc.insets = new java.awt.Insets(0, 0, 0, 0);
        card.add(row[1], gc);
    }

    javax.swing.JPanel buttonRow = new javax.swing.JPanel(new java.awt.GridLayout(1, 2, 14, 0));
    buttonRow.setOpaque(false);
    btnAdd.setFont(btnAdd.getFont().deriveFont(java.awt.Font.BOLD, 16f));
    btnBack.setFont(btnBack.getFont().deriveFont(java.awt.Font.BOLD, 16f));
    btnAdd.setPreferredSize(new java.awt.Dimension(100, 40));
    btnBack.setPreferredSize(new java.awt.Dimension(100, 40));
    buttonRow.add(btnAdd);
    buttonRow.add(btnBack);
    gc.ipady = 0;
    gc.insets = new java.awt.Insets(16, 0, 0, 0);
    card.add(buttonRow, gc);

    card.setPreferredSize(new java.awt.Dimension(760, card.getPreferredSize().height));

    // ---------- 3. Center the card in the green area ----------
    javax.swing.JPanel holder = new javax.swing.JPanel(new java.awt.GridBagLayout());
    holder.setBackground(green);
    java.awt.GridBagConstraints hc = new java.awt.GridBagConstraints();
    hc.insets = new java.awt.Insets(20, 20, 20, 20);
    holder.add(card, hc);

    javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(holder);
    scroll.setBorder(null);
    scroll.getVerticalScrollBar().setUnitIncrement(16);

    javax.swing.JPanel body = new javax.swing.JPanel(new java.awt.BorderLayout());
    body.setBackground(green);
    body.add(sidebar, java.awt.BorderLayout.WEST);
    body.add(scroll, java.awt.BorderLayout.CENTER);

    // ---------- 4. Header ----------
    jLabel3.setFont(jLabel3.getFont().deriveFont(java.awt.Font.BOLD, 26f));
    javax.swing.JPanel header = new javax.swing.JPanel(
            new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 14, 8));
    header.setBackground(java.awt.Color.WHITE);
    header.add(jLabel2);
    header.add(jLabel3);

    // ---------- 5. Put it all in the window ----------
    jPanel1.removeAll();
    jPanel1.setLayout(new java.awt.BorderLayout());
    jPanel1.add(header, java.awt.BorderLayout.NORTH);
    jPanel1.add(body, java.awt.BorderLayout.CENTER);

    getContentPane().removeAll();
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    jButton1.addActionListener(e -> choosePhoto());
    
    setSize(1200, 800);
    setLocationRelativeTo(null);
    java.awt.EventQueue.invokeLater(() ->
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH));
}

private void choosePhoto() {
    javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
    fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
        "Images (jpg, png, gif)", "jpg", "jpeg", "png", "gif"));
    if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
        selectedPhoto = fc.getSelectedFile();
        jButton1.setText(selectedPhoto.getName());
    }
}

// Resizes a label's icon to a square of the given size (does nothing if the image is missing)
private void scaleIcon(javax.swing.JLabel label, int size) {
    if (label.getIcon() instanceof javax.swing.ImageIcon) {
        javax.swing.ImageIcon ic = (javax.swing.ImageIcon) label.getIcon();
        if (ic.getIconWidth() > 0) {
            label.setIcon(new javax.swing.ImageIcon(
                ic.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        }
    }
    label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtParty = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbPosition = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPlatform = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\ChatGPT Image Sep 25, 2026, 04_35_52 PM (2).png")); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Add Candidate");

        jLabel1.setText("Candidate Name:");

        jLabel5.setText("Student ID:");

        jLabel6.setText("Running Position:");

        cmbPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Governor", "Vice Governor", "Secretary", "Treasurer", "Auditor", "Internal P.I.O.", "External P.I.O.", "Year 1 Representative", "Year 2 Representative", "Year 3 Representative", "Year 4 Representative" }));
        cmbPosition.addActionListener(this::cmbPositionActionPerformed);

        jLabel7.setText("Party / Group:");

        jLabel8.setText("Platform:");

        txtPlatform.setColumns(20);
        txtPlatform.setRows(5);
        jScrollPane1.setViewportView(txtPlatform);

        jLabel9.setText("Candidate Photo:");

        jButton1.setText("Choose Photo");

        btnAdd.setText("Add Candidate");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel15.setText("Course:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Management and Accountancy", "Education", "Criminology and Criminal Justice", "Engineering and Architect", "Information Technology", "Allied Health Sciences" }));

        jLabel16.setText("Year Level:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year 1", "Year 2", "Year 3", "Year 4" }));

        jLabel17.setText("Achievements:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtParty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbPosition, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStudentID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(310, 30, 420, 770);

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\monitoring-icon-logo-illustration-dashboard-admin-symbol-template-for-graphic-and-web-design-collection-free-vector (1).jpg")); // NOI18N
        jPanel2.add(jLabel10);
        jLabel10.setBounds(30, 30, 25, 25);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Dashboard");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel2.add(jButton2);
        jButton2.setBounds(70, 30, 150, 27);

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\images (2) (1).png")); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(30, 70, 25, 25);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Add Candidate");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel2.add(jButton3);
        jButton3.setBounds(70, 70, 150, 27);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\sign-of-candidate-symbol-is-isolated-on-a-white-background-icon-color-editable-free-vector (1).jpg")); // NOI18N
        jPanel2.add(jLabel12);
        jLabel12.setBounds(30, 110, 30, 25);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("View Candidates");
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel2.add(jButton4);
        jButton4.setBounds(70, 110, 150, 27);

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\images (3) (1).png")); // NOI18N
        jPanel2.add(jLabel13);
        jLabel13.setBounds(30, 150, 30, 27);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("View Total Votes");
        jButton5.addActionListener(this::jButton5ActionPerformed);
        jPanel2.add(jButton5);
        jButton5.setBounds(70, 150, 150, 27);

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\images (4) (1).png")); // NOI18N
        jPanel2.add(jLabel14);
        jLabel14.setBounds(30, 190, 30, 30);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Logout");
        jButton6.addActionListener(this::jButton6ActionPerformed);
        jPanel2.add(jButton6);
        jButton6.setBounds(70, 190, 150, 30);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 148, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(530, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    if (choice == JOptionPane.YES_OPTION) {
        new login().setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        TotalVotes totalVotes = new TotalVotes();
        totalVotes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        ViewCandidates view = new ViewCandidates();
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AddCandidate addCandidate = new AddCandidate();
        addCandidate.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

     String name = txtName.getText().trim();
    String studentID = txtStudentID.getText().trim();
    String position = cmbPosition.getSelectedItem().toString();
    String party = txtParty.getText().trim();
    String platform = txtPlatform.getText().trim();

    if (name.isEmpty() || studentID.isEmpty() || party.isEmpty() || platform.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        return;
    }

    try {
        // copy the chosen photo into a folder next to the project
        String photoPath = null;
        if (selectedPhoto != null) {
            java.io.File dir = new java.io.File("candidate_photos");
            dir.mkdirs();
            String fileName = selectedPhoto.getName();
            String ext = fileName.contains(".")
                    ? fileName.substring(fileName.lastIndexOf('.')) : "";
            java.io.File dest = new java.io.File(dir,
                    studentID + "_" + System.currentTimeMillis() + ext);
            java.nio.file.Files.copy(selectedPhoto.toPath(), dest.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            photoPath = dest.getPath();
        }

        String sql = "INSERT INTO candidates "
           + "(name, student_id, position, party, platform, course, year_level, achievements, photo_path) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setString(2, studentID);
            pst.setString(3, position);
            pst.setString(4, party);
            pst.setString(5, platform);
            pst.setString(6, jComboBox1.getSelectedItem().toString());
            pst.setString(7, jComboBox2.getSelectedItem().toString());
            pst.setString(8, jTextArea1.getText().trim());
            pst.setString(9, photoPath);
            pst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Candidate added successfully!");

        txtName.setText("");
        txtStudentID.setText("");
        txtParty.setText("");
        txtPlatform.setText("");
        jTextArea1.setText("");
        cmbPosition.setSelectedIndex(0);
        selectedPhoto = null;
        jButton1.setText("Choose Photo");

        } catch (java.sql.SQLException e) {
        if (e.getErrorCode() == 1062) {
            JOptionPane.showMessageDialog(this, "This student is already registered as a candidate.");
        } else {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cmbPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPositionActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new AddCandidate().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cmbPosition;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtParty;
    private javax.swing.JTextArea txtPlatform;
    private javax.swing.JTextField txtStudentID;
    // End of variables declaration//GEN-END:variables
}
