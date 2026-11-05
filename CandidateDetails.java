/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class CandidateDetails extends javax.swing.JFrame {
    
    private int candidateId;
    private String photoPath;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CandidateDetails.class.getName());

    private javax.swing.JPanel root;
    private double s = 1.0;
    private javax.swing.Icon logoIcon, profileIcon;
    private final java.util.Map<javax.swing.JComponent, java.awt.Font> baseFonts =
            new java.util.IdentityHashMap<>();

    public CandidateDetails(int candidateId) {
        initComponents();
        this.candidateId = candidateId;

        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        loadCandidateDetails();
        buildFullScreen();
    }

    private int sc(double v) { return (int) Math.round(v * s); }

    private void buildFullScreen() {
        // captions left-aligned; picture, name and position stay centered
        for (javax.swing.JLabel l : new javax.swing.JLabel[] {
                lblCandidateName1, lblCandidateName2, lblCourse,
                lblYearLevel, lblAchievementsTitle, lblCandidateName6 }) {
            l.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        }

        logoIcon = jLabel2.getIcon();

        jLabel1.setText("");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profileIcon = jLabel1.getIcon();

        // use the candidate's saved photo instead of the placeholder
        javax.swing.Icon photo = makePhotoIcon(photoPath, 300);
        if (photo != null) profileIcon = photo;

        jPanel2.setLayout(null);   // green body
        jPanel4.setLayout(null);   // candidate card (children are kept)

        root = new javax.swing.JPanel(null);
        root.setBackground(java.awt.Color.WHITE);
        root.add(jLabel2);
        root.add(jLabel3);
        root.add(jPanel2);

        javax.swing.JComponent[] scalable = {
            jLabel3, jLabel6, btnBack,
            lblCandidateName, lblPosition, lblStudentID, lblParty,
            lblCandidateName1, lblCandidateName2, lblCourse, lblYearLevel,
            lblAchievementsTitle, lblCandidateName6,
            txtPlatform, txtAchievements
        };
        for (javax.swing.JComponent c : scalable) baseFonts.put(c, c.getFont());

        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(root, java.awt.BorderLayout.CENTER);

        root.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                applyLayout();
            }
        });

        applyLayout();
        java.awt.EventQueue.invokeLater(() ->
            setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH));
    }

    private void applyLayout() {
        int W = root.getWidth(), H = root.getHeight();
        if (W <= 0 || H <= 0) return;

        // design size is about 970 x 830; allow shrinking a bit for small screens
        s = Math.max(0.7, Math.min(Math.min(W / 970.0, H / 830.0), 2.5));

        for (java.util.Map.Entry<javax.swing.JComponent, java.awt.Font> e : baseFonts.entrySet()) {
            java.awt.Font f = e.getValue();
            e.getKey().setFont(f.deriveFont((float) (f.getSize2D() * s)));
        }

        // ---- header ----
        int header = sc(91);
        int logoW = sc(61), logoH = sc(59), logoX = sc(25);
        jLabel2.setBounds(logoX, (header - logoH) / 2, logoW, logoH);
        if (logoIcon != null) jLabel2.setIcon(new ScaledIcon(logoIcon, s));

        int titleH = sc(30);
        jLabel3.setBounds(logoX + logoW + sc(10), (header - titleH) / 2, sc(280), titleH);

        // ---- green body ----
        int bodyH = H - header;
        jPanel2.setBounds(0, header, W, bodyH);
        int extra = Math.max(0, (bodyH - sc(735)) / 2);

        int cw = sc(490), ch = sc(635);
        int cx = (W - cw) / 2;
        jLabel6.setBounds(cx, sc(20) + extra, sc(300), sc(32));
        btnBack.setBounds(cx + cw - sc(75), sc(22) + extra, sc(75), sc(30));
        jPanel4.setBounds(cx, sc(70) + extra, cw, ch);

        // ---- inside the card ----
        int left = sc(40), fullW = cw - 2 * left, capW = sc(140), rowH = sc(32);

        int pic = sc(100);
        jLabel1.setBounds((cw - pic) / 2, sc(20), pic, pic);
        if (profileIcon != null && profileIcon.getIconWidth() > 0 && profileIcon.getIconHeight() > 0) {
            double k = (double) pic / Math.max(profileIcon.getIconWidth(), profileIcon.getIconHeight());
            jLabel1.setIcon(new ScaledIcon(profileIcon, k));
        }

        lblCandidateName.setBounds(0, sc(130), cw, rowH);
        lblPosition.setBounds(0, sc(166), cw, rowH);

        lblCandidateName1.setBounds(left, sc(215), capW, rowH);
        lblStudentID.setBounds(left + capW, sc(215), fullW - capW, rowH);

        lblCandidateName2.setBounds(left, sc(253), capW, rowH);
        lblParty.setBounds(left + capW, sc(253), fullW - capW, rowH);

        lblCourse.setBounds(left, sc(291), fullW, rowH);
        lblYearLevel.setBounds(left, sc(329), fullW, rowH);

        lblAchievementsTitle.setBounds(left, sc(367), fullW, rowH);
        jScrollPane3.setBounds(left, sc(403), fullW, sc(70));

        lblCandidateName6.setBounds(left, sc(483), fullW, rowH);
        jScrollPane2.setBounds(left, sc(519), fullW, sc(90));

        root.revalidate();
        root.repaint();
    }

    private static class ScaledIcon implements javax.swing.Icon {
        private final javax.swing.Icon base;
        private final double k;

        ScaledIcon(javax.swing.Icon base, double k) { this.base = base; this.k = k; }

        @Override public int getIconWidth()  { return (int) (base.getIconWidth() * k); }
        @Override public int getIconHeight() { return (int) (base.getIconHeight() * k); }

        @Override
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                                java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.translate(x, y);
            g2.scale(k, k);
            base.paintIcon(c, g2, 0, 0);
            g2.dispose();
        }
    }

    // Loads the saved photo (kept at up to size x size so it stays sharp when scaled)
    private javax.swing.Icon makePhotoIcon(String path, int size) {
        System.out.println("PHOTO path = " + path);
        if (path == null || path.isEmpty()) return null;
        try {
            java.io.File f = new java.io.File(path);
            System.out.println("PHOTO full path = " + f.getAbsolutePath()
                               + " exists = " + f.exists());
            if (!f.exists()) return null;

            java.awt.image.BufferedImage src = javax.imageio.ImageIO.read(f);
            if (src == null) return null;

            double fit = Math.min(1.0, Math.min((double) size / src.getWidth(),
                                                (double) size / src.getHeight()));
            int w = Math.max(1, (int) Math.round(src.getWidth() * fit));
            int h = Math.max(1, (int) Math.round(src.getHeight() * fit));

            java.awt.image.BufferedImage out =
                new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g = out.createGraphics();
            g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                               java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.drawImage(src, 0, 0, w, h, null);
            g.dispose();

            return new javax.swing.ImageIcon(out);
        } catch (Exception e) {
            System.out.println("PHOTO error: " + e);
            return null;
        }
    }

    private void loadCandidateDetails() {
        String sql = "SELECT name, student_id, position, party, platform, "
                   + "course, year_level, achievements, photo_path "
                   + "FROM candidates WHERE candidate_id = ?";

        try (java.sql.Connection conn = DBConnection.connect()) {
            if (conn == null) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "Database connection failed."
                );
                return;
            }

            try (java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, candidateId);

                try (java.sql.ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        photoPath = rs.getString("photo_path");

                        lblCandidateName.setText(rs.getString("name"));
                        lblPosition.setText(rs.getString("position"));
                        lblStudentID.setText(rs.getString("student_id"));
                        lblParty.setText(rs.getString("party"));

                        lblCourse.setText("Course: " + rs.getString("course"));
                        lblYearLevel.setText("Year Level: " + rs.getString("year_level"));

                        txtPlatform.setText(rs.getString("platform"));
                        txtPlatform.setEditable(false);
                        txtPlatform.setLineWrap(true);
                        txtPlatform.setWrapStyleWord(true);

                        txtAchievements.setText(rs.getString("achievements"));
                        txtAchievements.setEditable(false);
                        txtAchievements.setLineWrap(true);
                        txtAchievements.setWrapStyleWord(true);
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(
                            this, "Candidate not found."
                        );
                    }
                }
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Error: " + e.getMessage()
            );
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCandidates = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        masterPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPlatform = new javax.swing.JTextArea();
        lblCandidateName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblStudentID = new javax.swing.JLabel();
        lblParty = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        lblCandidateName1 = new javax.swing.JLabel();
        lblCandidateName2 = new javax.swing.JLabel();
        lblCourse = new javax.swing.JLabel();
        lblYearLevel = new javax.swing.JLabel();
        lblAchievementsTitle = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAchievements = new javax.swing.JTextArea();
        lblCandidateName6 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        tblCandidates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Student ID", "Position", "Party", "Platform"
            }
        ));
        jScrollPane1.setViewportView(tblCandidates);

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Viewing Student Candidates");

        jButton1.setText("Back");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        masterPanel.setPreferredSize(new java.awt.Dimension(915, 1049));
        masterPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                masterPanelComponentResized(evt);
            }
        });
        masterPanel.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 1100));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\ChatGPT Image Sep 25, 2026, 04_35_52 PM (2).png")); // NOI18N

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 979, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel3);

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);
        jPanel2.add(btnBack);
        btnBack.setBounds(580, 30, 75, 23);

        jPanel4.setBackground(new java.awt.Color(0, 153, 51));

        txtPlatform.setColumns(20);
        txtPlatform.setRows(5);
        jScrollPane2.setViewportView(txtPlatform);

        lblCandidateName.setBackground(new java.awt.Color(0, 102, 51));
        lblCandidateName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName.setText("Candidate Name");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (2).jpg")); // NOI18N
        jLabel1.setText("jLabel1");

        lblStudentID.setBackground(new java.awt.Color(0, 102, 51));
        lblStudentID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblStudentID.setForeground(new java.awt.Color(255, 255, 255));

        lblParty.setBackground(new java.awt.Color(0, 102, 51));
        lblParty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblParty.setForeground(new java.awt.Color(255, 255, 255));

        lblPosition.setBackground(new java.awt.Color(0, 102, 51));
        lblPosition.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPosition.setForeground(new java.awt.Color(255, 255, 255));
        lblPosition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosition.setText("Position:");

        lblCandidateName1.setBackground(new java.awt.Color(0, 102, 51));
        lblCandidateName1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName1.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName1.setText("Student ID:");

        lblCandidateName2.setBackground(new java.awt.Color(0, 102, 51));
        lblCandidateName2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName2.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName2.setText("Party:");

        lblCourse.setBackground(new java.awt.Color(0, 102, 51));
        lblCourse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblCourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCourse.setText("Course:");

        lblYearLevel.setBackground(new java.awt.Color(0, 102, 51));
        lblYearLevel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYearLevel.setForeground(new java.awt.Color(255, 255, 255));
        lblYearLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearLevel.setText("Year Level:");

        lblAchievementsTitle.setBackground(new java.awt.Color(0, 102, 51));
        lblAchievementsTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAchievementsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblAchievementsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAchievementsTitle.setText("Achievements:");

        txtAchievements.setColumns(20);
        txtAchievements.setRows(5);
        jScrollPane3.setViewportView(txtAchievements);

        lblCandidateName6.setBackground(new java.awt.Color(0, 102, 51));
        lblCandidateName6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName6.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName6.setText("Platform:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCandidateName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addComponent(lblCandidateName6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAchievementsTitle, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYearLevel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCourse, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblCandidateName2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblParty, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblCandidateName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(52, 52, 52))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblCandidateName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidateName1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCandidateName2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblParty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAchievementsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCandidateName6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(170, 70, 490, 770);

        jLabel6.setBackground(new java.awt.Color(0, 102, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Candidate Details");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(170, 20, 330, 32);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        masterPanel.add(jPanel1);
        jPanel1.setBounds(6, 6, 1100, 1013);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(masterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(masterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        candidates candidates = new candidates();
    candidates.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void masterPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_masterPanelComponentResized
            // layout is handled by applyLayout()
    }//GEN-LAST:event_masterPanelComponentResized

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // layout is handled by applyLayout()
    }//GEN-LAST:event_formComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
java.awt.EventQueue.invokeLater(() -> {
 new CandidateDetails(1).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblAchievementsTitle;
    private javax.swing.JLabel lblCandidateName;
    private javax.swing.JLabel lblCandidateName1;
    private javax.swing.JLabel lblCandidateName2;
    private javax.swing.JLabel lblCandidateName6;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblParty;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblYearLevel;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JTable tblCandidates;
    private javax.swing.JTextArea txtAchievements;
    private javax.swing.JTextArea txtPlatform;
    // End of variables declaration//GEN-END:variables
}
