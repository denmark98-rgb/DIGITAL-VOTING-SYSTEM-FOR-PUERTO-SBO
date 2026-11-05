/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class TotalVotes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TotalVotes.class.getName());

    /**
     * Creates new form TotalVotes
     */
public TotalVotes() {
    initComponents();
    jLabel2.setText("");

    java.awt.Color green = new java.awt.Color(0, 102, 51);

    // ---------- 1. SIDEBAR ----------
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
    sc.gridy = navButtons.length;
    sc.weighty = 1;
    sc.fill = java.awt.GridBagConstraints.VERTICAL;
    sidebar.add(javax.swing.Box.createVerticalGlue(), sc);

    // ---------- 2. WHITE CARD ----------
    jLabel4.setForeground(green);
    jLabel4.setFont(jLabel4.getFont().deriveFont(java.awt.Font.BOLD, 26f));
    btnBack.setFont(btnBack.getFont().deriveFont(java.awt.Font.BOLD, 14f));
    btnBack.setPreferredSize(new java.awt.Dimension(90, 34));

    javax.swing.JPanel top = new javax.swing.JPanel(new java.awt.BorderLayout());
    top.setOpaque(false);
    top.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 14, 0));
    top.add(jLabel4, java.awt.BorderLayout.WEST);
    top.add(btnBack, java.awt.BorderLayout.EAST);

    tblVotes.setModel(new DefaultTableModel(
    new Object[] { "Rank", "Candidate", "Position", "Course", "Year Level", "Total Votes" }, 0) {
    @Override public boolean isCellEditable(int r, int c) { return false; }
});
    tblVotes.setRowHeight(30);
    tblVotes.setFont(tblVotes.getFont().deriveFont(14f));
    tblVotes.getTableHeader().setFont(
        tblVotes.getTableHeader().getFont().deriveFont(java.awt.Font.BOLD, 14f));
    tblVotes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    int[] widths = {  60, 200, 150, 220, 100, 100 };
    for (int i = 0; i < widths.length; i++) {
        tblVotes.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
    }

    javax.swing.JPanel card = new javax.swing.JPanel(new java.awt.BorderLayout());
    card.setBackground(java.awt.Color.WHITE);
    card.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 30, 24, 30));
    card.add(top, java.awt.BorderLayout.NORTH);
    card.add(jScrollPane1, java.awt.BorderLayout.CENTER);

    javax.swing.JPanel cardHolder = new javax.swing.JPanel(new java.awt.BorderLayout());
    cardHolder.setBackground(green);
    cardHolder.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
    cardHolder.add(card, java.awt.BorderLayout.CENTER);

    javax.swing.JPanel body = new javax.swing.JPanel(new java.awt.BorderLayout());
    body.setBackground(green);
    body.add(sidebar, java.awt.BorderLayout.WEST);
    body.add(cardHolder, java.awt.BorderLayout.CENTER);

    // ---------- 3. HEADER ----------
    jLabel3.setFont(jLabel3.getFont().deriveFont(java.awt.Font.BOLD, 26f));
    javax.swing.JPanel header = new javax.swing.JPanel(
            new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 14, 8));
    header.setBackground(java.awt.Color.WHITE);
    header.add(jLabel2);
    header.add(jLabel3);

    // ---------- 4. PUT IT IN THE WINDOW ----------
    jPanel1.removeAll();
    jPanel1.setLayout(new java.awt.BorderLayout());
    jPanel1.add(header, java.awt.BorderLayout.NORTH);
    jPanel1.add(body, java.awt.BorderLayout.CENTER);

    getContentPane().removeAll();
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    setSize(1200, 800);
    setLocationRelativeTo(null);
    java.awt.EventQueue.invokeLater(() ->
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH));

    loadVotes();
}

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

private void loadVotes() {
    DefaultTableModel model = (DefaultTableModel) tblVotes.getModel();
    model.setRowCount(0);

    String[] order = {
        "Governor", "Vice Governor", "Secretary", "Treasurer", "Auditor",
        "Internal P.I.O.", "External P.I.O.",
        "Year 1 Representative", "Year 2 Representative",
        "Year 3 Representative", "Year 4 Representative"
    };

    String sql = "SELECT c.name, c.position, c.course, c.year_level, "
               + "COUNT(v.vote_id) AS total_votes "
               + "FROM candidates c "
               + "LEFT JOIN votes v ON c.candidate_id = v.candidate_id "
               + "GROUP BY c.candidate_id, c.name, c.position, "
               + "c.course, c.year_level "
               + "ORDER BY c.position, total_votes DESC, c.name";

    java.util.Map<String, java.util.List<Object[]>> byPosition = new java.util.LinkedHashMap<>();

    try (Connection con = DBConnection.connect();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String position = rs.getString("position");
            byPosition.computeIfAbsent(position, k -> new java.util.ArrayList<>())
                .add(new Object[]{
                    null,
                    rs.getString("name"),
                    position,
                    rs.getString("course"),
                    rs.getString("year_level"),
                    rs.getInt("total_votes")
                });
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error loading votes: " + e.getMessage());
        return;
    }

    // add positions in election order, numbering each group 1, 2, 3...
    for (String pos : order) {
        java.util.List<Object[]> rows = byPosition.remove(pos);
        if (rows == null) continue;
        int rank = 1;
        for (Object[] r : rows) {
            r[0] = rank++;
            model.addRow(r);
        }
    }
    // any position not in the list above goes at the end
    for (java.util.List<Object[]> rows : byPosition.values()) {
        int rank = 1;
        for (Object[] r : rows) {
            r[0] = rank++;
            model.addRow(r);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVotes = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Viewing Total Votes");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(250, 20, 330, 32);

        tblVotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Candidate", "Position", "Course", "Year Level", "Total Votes"
            }
        ));
        jScrollPane1.setViewportView(tblVotes);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(250, 70, 680, 410);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);
        jPanel2.add(btnBack);
        btnBack.setBounds(850, 30, 75, 23);

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\monitoring-icon-logo-illustration-dashboard-admin-symbol-template-for-graphic-and-web-design-collection-free-vector (1).jpg")); // NOI18N
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 70, 25, 25);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Dashboard");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel2.add(jButton2);
        jButton2.setBounds(60, 70, 150, 27);

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\images (2) (1).png")); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(20, 110, 25, 25);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Add Candidate");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel2.add(jButton3);
        jButton3.setBounds(60, 110, 150, 27);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\sign-of-candidate-symbol-is-isolated-on-a-white-background-icon-color-editable-free-vector (1).jpg")); // NOI18N
        jPanel2.add(jLabel12);
        jLabel12.setBounds(20, 150, 30, 25);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("View Candidates");
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel2.add(jButton4);
        jButton4.setBounds(60, 150, 150, 27);

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\images (3) (1).png")); // NOI18N
        jPanel2.add(jLabel13);
        jLabel13.setBounds(20, 190, 30, 27);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("View Total Votes");
        jButton5.addActionListener(this::jButton5ActionPerformed);
        jPanel2.add(jButton5);
        jButton5.setBounds(60, 190, 150, 27);

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\images (4) (1).png")); // NOI18N
        jPanel2.add(jLabel14);
        jLabel14.setBounds(20, 230, 30, 30);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Logout");
        jButton6.addActionListener(this::jButton6ActionPerformed);
        jPanel2.add(jButton6);
        jButton6.setBounds(60, 230, 150, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\ChatGPT Image Sep 25, 2026, 04_35_52 PM (2).png")); // NOI18N

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AddCandidate addCandidate = new AddCandidate();
        addCandidate.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        ViewCandidates view = new ViewCandidates();
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        TotalVotes totalVotes = new TotalVotes();
        totalVotes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     int choice = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.QUESTION_MESSAGE
    );
    if (choice == javax.swing.JOptionPane.YES_OPTION) {
        new login().setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_jButton6ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TotalVotes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVotes;
    // End of variables declaration//GEN-END:variables
}
