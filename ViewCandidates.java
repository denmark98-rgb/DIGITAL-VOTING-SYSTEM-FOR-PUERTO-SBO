/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ViewCandidates extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewCandidates.class.getName());

    /**
     * Creates new form ViewCandidates
     */
public ViewCandidates() {
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
    jButton1.setFont(jButton1.getFont().deriveFont(java.awt.Font.BOLD, 14f));
    jButton1.setPreferredSize(new java.awt.Dimension(90, 34));

    javax.swing.JPanel top = new javax.swing.JPanel(new java.awt.BorderLayout());
    top.setOpaque(false);
    top.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 14, 0));
    top.add(jLabel4, java.awt.BorderLayout.WEST);
    javax.swing.JButton btnEdit = new javax.swing.JButton("Edit Selected");
btnEdit.setFont(btnEdit.getFont().deriveFont(java.awt.Font.BOLD, 14f));
btnEdit.setPreferredSize(new java.awt.Dimension(140, 34));
btnEdit.addActionListener(e -> editSelectedCandidate());

javax.swing.JButton btnDelete = new javax.swing.JButton("Delete");
btnDelete.setFont(btnDelete.getFont().deriveFont(java.awt.Font.BOLD, 14f));
btnDelete.setForeground(new java.awt.Color(180, 0, 0));
btnDelete.setPreferredSize(new java.awt.Dimension(100, 34));
btnDelete.addActionListener(e -> deleteSelectedCandidate());

javax.swing.JPanel btns = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
btns.setOpaque(false);
btns.add(btnEdit);
btns.add(btnDelete);
btns.add(jButton1);
top.add(btns, java.awt.BorderLayout.EAST);

    tblCandidates.setModel(new DefaultTableModel(
    new Object[] { "Photo", "ID", "Name", "Student ID", "Position", "Party",
                   "Platform", "Course", "Year Level", "Achievements" }, 0) {
    @Override public Class<?> getColumnClass(int c) {
        return c == 0 ? javax.swing.Icon.class : Object.class;
    }
    @Override public boolean isCellEditable(int r, int c) { return false; }
});
tblCandidates.setRowHeight(48);
    tblCandidates.setFont(tblCandidates.getFont().deriveFont(14f));
    tblCandidates.getTableHeader().setFont(
        tblCandidates.getTableHeader().getFont().deriveFont(java.awt.Font.BOLD, 14f));
    tblCandidates.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    int[] widths = { 60, 40, 150, 100, 130, 110, 200, 180, 80, 200   };
    for (int i = 0; i < widths.length; i++) {
        tblCandidates.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
    }
    
    tblCandidates.setDefaultEditor(Object.class, null);   // cells can't be typed into
tblCandidates.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getClickCount() == 2) editSelectedCandidate();
    }
});
    
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

    loadCandidates();
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

private void loadCandidates() {
    DefaultTableModel model = (DefaultTableModel) tblCandidates.getModel();
    model.setRowCount(0);

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement("SELECT * FROM candidates");
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            model.addRow(new Object[]{
                loadThumb(rs.getString("photo_path"), 40),
                rs.getInt("candidate_id"),
                rs.getString("name"),
                rs.getString("student_id"),
                rs.getString("position"),
                rs.getString("party"),
                rs.getString("platform"),
                rs.getString("course"),
                rs.getString("year_level"),
                rs.getString("achievements")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

// Returns the photo scaled to size x size, or null if there is no photo
private javax.swing.Icon loadThumb(String path, int size) {
    if (path == null || path.isEmpty()) return null;
    java.io.File f = new java.io.File(path);
    if (!f.exists()) return null;
    java.awt.Image img = new javax.swing.ImageIcon(path).getImage();
    return new javax.swing.ImageIcon(img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
}

private String cell(DefaultTableModel m, int row, String col) {
    Object v = m.getValueAt(row, m.findColumn(col));
    return v == null ? "" : v.toString();
}

private void editSelectedCandidate() {
    int row = tblCandidates.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a candidate in the table first.");
        return;
    }

    DefaultTableModel m = (DefaultTableModel) tblCandidates.getModel();
    int id = Integer.parseInt(cell(m, row, "ID"));
    String oldPosition = cell(m, row, "Position");

    // ----- the edit form -----
    javax.swing.JTextField txtName = new javax.swing.JTextField(cell(m, row, "Name"));
    javax.swing.JTextField txtStudentId = new javax.swing.JTextField(cell(m, row, "Student ID"));
    javax.swing.JComboBox<String> cmbPos = new javax.swing.JComboBox<>(new String[] {
        "Governor", "Vice Governor", "Secretary", "Treasurer", "Auditor",
        "Internal P.I.O.", "External P.I.O.", "Year 1 Representative",
        "Year 2 Representative", "Year 3 Representative", "Year 4 Representative" });
    cmbPos.setSelectedItem(oldPosition);
    javax.swing.JTextField txtParty = new javax.swing.JTextField(cell(m, row, "Party"));
    javax.swing.JComboBox<String> cmbCourse = new javax.swing.JComboBox<>(new String[] {
        "Management and Accountancy", "Education", "Criminology and Criminal Justice",
        "Engineering and Architect", "Information Technology", "Allied Health Sciences" });
    cmbCourse.setSelectedItem(cell(m, row, "Course"));
    javax.swing.JComboBox<String> cmbYear = new javax.swing.JComboBox<>(new String[] {
        "Year 1", "Year 2", "Year 3", "Year 4" });
    cmbYear.setSelectedItem(cell(m, row, "Year Level"));

    javax.swing.JTextArea txtPlat = new javax.swing.JTextArea(cell(m, row, "Platform"), 3, 25);
    txtPlat.setLineWrap(true);
    txtPlat.setWrapStyleWord(true);
    javax.swing.JTextArea txtAch = new javax.swing.JTextArea(cell(m, row, "Achievements"), 3, 25);
    txtAch.setLineWrap(true);
    txtAch.setWrapStyleWord(true);

    javax.swing.JPanel p = new javax.swing.JPanel(new java.awt.GridLayout(0, 1, 0, 4));
    p.add(new javax.swing.JLabel("Candidate Name:"));    p.add(txtName);
    p.add(new javax.swing.JLabel("Student ID:"));        p.add(txtStudentId);
    p.add(new javax.swing.JLabel("Running Position:"));  p.add(cmbPos);
    p.add(new javax.swing.JLabel("Party / Group:"));     p.add(txtParty);
    p.add(new javax.swing.JLabel("Course:"));            p.add(cmbCourse);
    p.add(new javax.swing.JLabel("Year Level:"));        p.add(cmbYear);
    p.add(new javax.swing.JLabel("Platform:"));          p.add(new javax.swing.JScrollPane(txtPlat));
    p.add(new javax.swing.JLabel("Achievements:"));      p.add(new javax.swing.JScrollPane(txtAch));

    javax.swing.JScrollPane sp = new javax.swing.JScrollPane(p);
    sp.setBorder(null);
    sp.setPreferredSize(new java.awt.Dimension(440, 560));

    // ----- ask, and re-open the form until the input is valid -----
    String name, studentId, newPosition, party, platform;
    while (true) {
        int ok = JOptionPane.showConfirmDialog(this, sp, "Edit Candidate",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (ok != JOptionPane.OK_OPTION) return;

        name = txtName.getText().trim();
        studentId = txtStudentId.getText().trim();
        newPosition = cmbPos.getSelectedItem().toString();
        party = txtParty.getText().trim();
        platform = txtPlat.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Candidate name is required.");
        } else if (!studentId.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Student ID must contain numbers only.");
        } else if (party.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Party / Group is required.");
        } else if (platform.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Platform is required.");
        } else {
            break;
        }
    }

    // ----- save -----
    try (Connection con = DBConnection.connect()) {

        // same Student ID used by a different candidate?
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT COUNT(*) FROM candidates WHERE student_id = ? AND candidate_id <> ?")) {
            ps.setString(1, studentId);
            ps.setInt(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this,
                        "This Student ID is already registered as a candidate.");
                    return;
                }
            }
        }

        // don't move a candidate to another position once they have votes
        if (!newPosition.equals(oldPosition)) {
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM votes WHERE candidate_id = ?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this,
                            "The position cannot be changed because this candidate already has votes.");
                        return;
                    }
                }
            }
        }

        String sql = "UPDATE candidates SET name = ?, student_id = ?, position = ?, party = ?, "
                   + "platform = ?, course = ?, year_level = ?, achievements = ? "
                   + "WHERE candidate_id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, studentId);
            pst.setString(3, newPosition);
            pst.setString(4, party);
            pst.setString(5, platform);
            pst.setString(6, cmbCourse.getSelectedItem().toString());
            pst.setString(7, cmbYear.getSelectedItem().toString());
            pst.setString(8, txtAch.getText().trim());
            pst.setInt(9, id);
            pst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Candidate updated successfully!");
        loadCandidates();   // refresh the table

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

private void deleteSelectedCandidate() {
    int row = tblCandidates.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a candidate in the table first.");
        return;
    }

    DefaultTableModel m = (DefaultTableModel) tblCandidates.getModel();
    int id = Integer.parseInt(cell(m, row, "ID"));
    String name = cell(m, row, "Name");

    int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete " + name + "?\nThis cannot be undone.",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    if (choice != JOptionPane.YES_OPTION) return;

    try (Connection con = DBConnection.connect()) {

        // do not delete a candidate who already has votes
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT COUNT(*) FROM votes WHERE candidate_id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this,
                        "This candidate cannot be deleted because they already have votes.");
                    return;
                }
            }
        }

        // remember the photo file so it can be removed too
        String photoPath = null;
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT photo_path FROM candidates WHERE candidate_id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) photoPath = rs.getString("photo_path");
            }
        }

        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM candidates WHERE candidate_id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        if (photoPath != null && !photoPath.isEmpty()) {
            new java.io.File(photoPath).delete();
        }

        JOptionPane.showMessageDialog(this, "Candidate deleted.");
        loadCandidates();   // refresh the table

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCandidates = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        tblCandidates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Student ID", "Position", "Party", "Platform", "Course", "Year Level", "Achievements"
            }
        ));
        jScrollPane1.setViewportView(tblCandidates);
        if (tblCandidates.getColumnModel().getColumnCount() > 0) {
            tblCandidates.getColumnModel().getColumn(0).setHeaderValue("ID");
        }

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(240, 70, 750, 360);

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Viewing Student Candidates");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(240, 20, 330, 32);

        jButton1.setText("Back");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel2.add(jButton1);
        jButton1.setBounds(910, 30, 75, 23);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         AdminDashboard dashboard = new AdminDashboard();
         dashboard.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ViewCandidates().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCandidates;
    // End of variables declaration//GEN-END:variables

   
}
