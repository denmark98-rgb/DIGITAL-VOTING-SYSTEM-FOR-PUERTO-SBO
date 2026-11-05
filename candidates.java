/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import java.sql.SQLException;

public class candidates extends javax.swing.JFrame {
    private int[] presidentCandidateIds = {-1, -1, -1};
    private int[] vicePresidentCandidateIds = {-1, -1, -1};
    private int[] secretaryCandidateIds = {-1, -1, -1};
    private int[] treasurerCandidateIds = {-1, -1, -1};
    private int[] auditorCandidateIds = {-1, -1, -1};
    private int[] internalPIOCandidateIds = {-1, -1, -1};
    private int[] externalPIOCandidateIds = {-1, -1, -1};
    private int[] year1RepCandidateIds = {-1, -1, -1};
    private int[] year2RepCandidateIds = {-1, -1, -1};
    private int[] year3RepCandidateIds = {-1, -1, -1};
    private int[] year4RepCandidateIds = {-1, -1, -1};
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(candidates.class.getName());
    
    private static final String[] POSITIONS = {
    "Governor", "Vice Governor", "Secretary", "Treasurer", "Auditor",
    "Internal P.I.O.", "External P.I.O.",
    "Year 1 Representative", "Year 2 Representative",
    "Year 3 Representative", "Year 4 Representative"
};
private final int[] extraRows = new int[POSITIONS.length];
private final java.util.List<ExtraCard> extraCards = new java.util.ArrayList<>();

    /**
     * Creates new form candidates
     */

public candidates() {
    initComponents();
    loadCandidates();
    loadVicePresidentCandidates();
    loadSecretaryCandidates();
    loadTreasurerCandidates();
    loadAuditorCandidates();
    loadInternalPIOCandidates();
    loadExternalPIOCandidates();
    loadYear1RepCandidates();
    loadYear2RepCandidates();
    loadYear3RepCandidates();
    loadYear4RepCandidates();
    setupResponsiveLayout();
    loadPhotos();
    addVoteConfirmation();
    loadExtraCards();     // <-- add this
}

// ===================== FULL-SCREEN / RESPONSIVE LAYOUT =====================

private static final int HEADER_BASE = 91;
private static final int CONTENT_W = 780;          // width of the candidate area
private static final int FIRST_SECTION_Y = 100;    // y of the first position title
private static final int SECTION_PITCH = 400;      // distance between positions
private static final double CONTENT_ZOOM = 0.85;   // make candidate area bigger/smaller

private javax.swing.JButton[] navButtons;
private final int[] navWidths = {60, 90, 60, 100, 70, 90};
private java.awt.Font navFont, titleFont, headingFont, sectionFont, cardNameFont, cardBtnFont;
private javax.swing.Icon logoOrig;

private javax.swing.JLabel[] sectionTitles;
private javax.swing.JPanel[] dividers;

private javax.swing.JPanel[] cardPanels;
private javax.swing.JLabel[] cardIcons;
private javax.swing.JLabel[] cardNames;
private javax.swing.JButton[] cardViews;
private javax.swing.JButton[] cardVotes;
private javax.swing.Icon[] cardIconOrig;

private void setupResponsiveLayout() {
    setLocationRelativeTo(null);

    // manual layout so everything can be positioned and scaled
    getContentPane().setLayout(null);
    jPanel4.setLayout(null);
    jPanel2.setLayout(null);
    jScrollPane1.setBorder(null);
    jScrollPane1.getVerticalScrollBar().setUnitIncrement(30);

    // ----- header -----
    navButtons = new javax.swing.JButton[] {
        jButton2, jButton3, jButton4, jButton5, jButton6, jButton1
    };
    navFont = jButton2.getFont();
    titleFont = jLabel3.getFont();
    logoOrig = jLabel2.getIcon();
    jLabel2.setText("");

    // ----- headings, section titles, yellow dividers -----
    headingFont = lblCandidateName5.getFont();
    lblCandidateName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    sectionTitles = new javax.swing.JLabel[] {
        lblCandidateName6,  lblCandidateName7,  lblCandidateName8,
        lblCandidateName9,  lblCandidateName10, lblCandidateName13,
        lblCandidateName16, lblCandidateName17, lblCandidateName18,
        lblCandidateName19, lblCandidateName4
    };
    sectionFont = lblCandidateName6.getFont();
    for (javax.swing.JLabel t : sectionTitles) {
        t.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    }

    dividers = new javax.swing.JPanel[] {
        jPanel6, jPanel9, jPanel8, jPanel11, jPanel12, jPanel13,
        jPanel14, jPanel35, jPanel39, jPanel43, jPanel47
    };

    // ----- the 33 candidate cards (11 positions x 3) -----
    cardPanels = new javax.swing.JPanel[] {
        jPanel16, jPanel5,  jPanel15,
        jPanel17, jPanel18, jPanel19,
        jPanel20, jPanel21, jPanel22,
        jPanel23, jPanel24, jPanel25,
        jPanel26, jPanel27, jPanel28,
        jPanel29, jPanel30, jPanel31,
        jPanel32, jPanel33, jPanel34,
        jPanel36, jPanel37, jPanel38,
        jPanel40, jPanel41, jPanel42,
        jPanel44, jPanel45, jPanel46,
        jPanel48, jPanel49, jPanel50
    };
    cardIcons = new javax.swing.JLabel[] {
        jLabel11, jLabel4,  jLabel10,
        jLabel12, jLabel13, jLabel14,
        jLabel15, jLabel16, jLabel17,
        jLabel18, jLabel19, jLabel20,
        jLabel21, jLabel22, jLabel23,
        jLabel24, jLabel25, jLabel26,
        jLabel27, jLabel28, jLabel29,
        jLabel30, jLabel31, jLabel32,
        jLabel33, jLabel34, jLabel35,
        jLabel36, jLabel37, jLabel38,
        jLabel39, jLabel40, jLabel41
    };
    cardNames = new javax.swing.JLabel[] {
        lblCandidateName12, lblCandidateName1, lblCandidateName11,
        lblVicePresident1,  lblCandidateName14, lblCandidateName15,
        lblSecretary1, lblSecretary2, lblSecretary3,
        lblTreasurer1, lblTreasurer2, lblTreasurer3,
        lblAuditor1, lblAuditor2, lblAuditor3,
        lblInternalPIO1, lblInternalPIO2, lblInternalPIO3,
        lblExternalPIO1, lblExternalPIO2, lblExternalPIO3,
        lblYear1Rep1, lblYear1Rep2, lblYear1Rep3,
        lblYear2Rep1, lblYear2Rep2, lblYear2Rep3,
        lblYear3Rep1, lblYear3Rep2, lblYear3Rep3,
        lblYear4Rep1, lblYear4Rep2, lblYear4Rep3
    };
    cardViews = new javax.swing.JButton[] {
        btnViewDetails9, btnViewDetails1, btnViewDetails8,
        btnViewVP1, btnViewDetails11, btnViewDetails12,
        btnViewSecretary1, btnViewSecretary2, btnViewSecretary3,
        btnViewTreasurer1, btnViewTreasurer2, btnViewTreasurer3,
        btnViewAuditor1, btnViewAuditor2, btnViewAuditor3,
        btnViewInternalPIO1, btnViewInternalPIO2, btnViewInternalPIO3,
        btnViewExternalPIO1, btnViewExternalPIO2, btnViewExternalPIO3,
        btnViewYear1Rep1, btnViewYear1Rep2, btnViewYear1Rep3,
        btnViewYear2Rep1, btnViewYear2Rep2, btnViewYear2Rep3,
        btnViewYear3Rep1, btnViewYear3Rep2, btnViewYear3Rep3,
        btnViewYear4Rep1, btnViewYear4Rep2, btnViewYear4Rep3
    };
    cardVotes = new javax.swing.JButton[] {
        btnVote9, btnVote1, btnVote8,
        btnVoteVP1, btnVote11, btnVote12,
        btnVoteSecretary1, btnVoteSecretary2, btnVoteSecretary3,
        btnVoteTreasurer1, btnVoteTreasurer2, btnVoteTreasurer3,
        btnVoteAuditor1, btnVoteAuditor2, btnVoteAuditor3,
        btnVoteInternalPIO1, btnVoteInternalPIO2, btnVoteInternalPIO3,
        btnVoteExternalPIO1, btnVoteExternalPIO2, btnVoteExternalPIO3,
        btnVoteYear1Rep1, btnVoteYear1Rep2, btnVoteYear1Rep3,
        btnVoteYear2Rep1, btnVoteYear2Rep2, btnVoteYear2Rep3,
        btnVoteYear3Rep1, btnVoteYear3Rep2, btnVoteYear3Rep3,
        btnVoteYear4Rep1, btnVoteYear4Rep2, btnVoteYear4Rep3
    };

    cardNameFont = lblCandidateName1.getFont();
    cardBtnFont = btnVote1.getFont();
    cardIconOrig = new javax.swing.Icon[cardPanels.length];

    for (int i = 0; i < cardPanels.length; i++) {
        cardPanels[i].setLayout(null);
        cardIconOrig[i] = cardIcons[i].getIcon();
        cardIcons[i].setText("");
        cardIcons[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardIcons[i].setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        cardNames[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    getContentPane().addComponentListener(new java.awt.event.ComponentAdapter() {
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
    int W = getContentPane().getWidth();
    int H = getContentPane().getHeight();
    if (W <= 0 || H <= 0) return;

    double s = Math.min(W / 960.0, H / 540.0);
    s = Math.max(1.0, Math.min(s, 2.5));
    double sc = s * CONTENT_ZOOM;

    int header = (int) (HEADER_BASE * s);

    jPanel4.setBounds(0, 0, W, H);
    jScrollPane1.setBounds(0, header, W, H - header);

    // ----- header: logo + title -----
    int logoW = (int) (61 * s), logoH = (int) (59 * s);
    int logoX = (int) (25 * s);
    jLabel2.setBounds(logoX, (header - logoH) / 2, logoW, logoH);
    if (logoOrig != null) jLabel2.setIcon(new ScaledIcon(logoOrig, s));

    jLabel3.setFont(titleFont.deriveFont((float) (titleFont.getSize2D() * s)));
    int titleH = (int) (30 * s);
    jLabel3.setBounds(logoX + logoW + (int) (10 * s), (header - titleH) / 2,
                      (int) (260 * s), titleH);

    // ----- header: nav buttons pinned right -----
    int btnH = (int) (30 * s);
    int btnY = (header - btnH) / 2;
    int gap = (int) (10 * s);
    int x = W - (int) (40 * s);
    for (int i = navButtons.length - 1; i >= 0; i--) {
        int bw = (int) (navWidths[i] * s);
        x -= bw;
        navButtons[i].setFont(navFont.deriveFont((float) (navFont.getSize2D() * s)));
        navButtons[i].setBounds(x, btnY, bw, btnH);
        x -= gap;
    }

    // ----- where each position starts (grows 300 for every extra row of cards) -----
    int n = sectionTitles.length;
    int[] secY = new int[n];
    int yy = FIRST_SECTION_Y;
    for (int k = 0; k < n; k++) {
        secY[k] = yy;
        yy += SECTION_PITCH + 300 * extraRows[k];
    }

    // ----- scrolling content area -----
    int sbw = jScrollPane1.getVerticalScrollBar().getPreferredSize().width;
    int vw = W - sbw;
    int totalH = (int) ((yy - SECTION_PITCH + 50 + 270 + 60) * sc);
    jPanel2.setPreferredSize(new java.awt.Dimension(vw, totalH));
    jPanel10.setBounds(0, 0, vw, totalH);
    int offX = (int) ((vw - CONTENT_W * sc) / 2);
    int contentW = (int) (CONTENT_W * sc);

    lblCandidateName5.setFont(headingFont.deriveFont((float) (headingFont.getSize2D() * sc)));
    lblCandidateName5.setBounds(offX, (int) (15 * sc), contentW, (int) (44 * sc));

    // yellow dividers: one at the top, then one under the LAST row of each position
    for (int i = 0; i < dividers.length; i++) {
        int y = (i == 0) ? 70 : secY[i - 1] + 360 + 300 * extraRows[i - 1];
        dividers[i].setBounds(offX, (int) (y * sc), contentW, (int) (15 * sc));
    }

    // position titles
    for (int k = 0; k < n; k++) {
        sectionTitles[k].setFont(sectionFont.deriveFont((float) (sectionFont.getSize2D() * sc)));
        sectionTitles[k].setBounds(offX, (int) (secY[k] * sc), (int) (500 * sc), (int) (36 * sc));
    }

    // the original 33 cards (first row of every position)
    for (int i = 0; i < cardPanels.length; i++) {
        int k = i / 3, c = i % 3;
        layoutCard(cardPanels[i], cardIcons[i], cardIconOrig[i], cardNames[i],
                   cardViews[i], cardVotes[i],
                   offX + (int) (c * 280 * sc), (int) ((secY[k] + 50) * sc), sc);
    }

    // extra cards (4th, 5th, 6th... candidate of a position)
    for (ExtraCard ec : extraCards) {
        int r = 1 + ec.index / 3, c = ec.index % 3;
        layoutCard(ec.panel, ec.icon, ec.iconOrig, ec.name, ec.view, ec.vote,
                   offX + (int) (c * 280 * sc),
                   (int) ((secY[ec.section] + 50 + 300 * r) * sc), sc);
    }

    jScrollPane1.revalidate();
    getContentPane().revalidate();
    getContentPane().repaint();
}

// Positions and sizes one candidate card
private void layoutCard(javax.swing.JPanel panel, javax.swing.JLabel icon, javax.swing.Icon base,
                        javax.swing.JLabel name, javax.swing.JButton view, javax.swing.JButton vote,
                        int cx, int cy, double sc) {
    int cw = (int) (210 * sc), ch = (int) (270 * sc);
    panel.setBounds(cx, cy, cw, ch);

    icon.setBounds(0, (int) (15 * sc), cw, (int) (90 * sc));
    if (base != null && base.getIconWidth() > 0 && base.getIconHeight() > 0) {
        double fit = Math.min(80.0 / base.getIconWidth(), 80.0 / base.getIconHeight());
        icon.setIcon(new ScaledIcon(base, fit * sc));
    }

    java.awt.Font f = cardNameFont.deriveFont((float) (cardNameFont.getSize2D() * sc));
    String t = name.getText();
    java.awt.FontMetrics fm = name.getFontMetrics(f);
    while (t != null && fm.stringWidth(t) > cw - 12 && f.getSize2D() > 10) {
        f = f.deriveFont(f.getSize2D() - 1f);
        fm = name.getFontMetrics(f);
    }
    name.setFont(f);
    name.setBounds(0, (int) (112 * sc), cw, (int) (32 * sc));

    java.awt.Font bf = cardBtnFont.deriveFont((float) (cardBtnFont.getSize2D() * sc));
    view.setFont(bf);
    vote.setFont(bf);
    view.setBounds((int) (20 * sc), (int) (155 * sc), (int) (170 * sc), (int) (40 * sc));
    vote.setBounds((int) (20 * sc), (int) (205 * sc), (int) (170 * sc), (int) (40 * sc));
}

// Draws any Icon at a scaled size
private static class ScaledIcon implements javax.swing.Icon {
    private final javax.swing.Icon base;
    private final double s;

    ScaledIcon(javax.swing.Icon base, double s) {
        this.base = base;
        this.s = s;
    }

    @Override public int getIconWidth()  { return (int) (base.getIconWidth() * s); }
    @Override public int getIconHeight() { return (int) (base.getIconHeight() * s); }

    @Override
    public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                            java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                            java.awt.RenderingHints.VALUE_RENDER_QUALITY);
        g2.translate(x, y);
        g2.scale(s, s);
        base.paintIcon(c, g2, 0, 0);
        g2.dispose();
    }
}


private boolean isElectionOngoing() {
    String sql = "SELECT election_status "
               + "FROM election_settings WHERE setting_id = 1";

    try (java.sql.Connection conn = DBConnection.connect()) {

        if (conn == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Database connection failed."
            );
            return false;
        }

        try (java.sql.PreparedStatement ps =
                 conn.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String status = rs.getString("election_status");

                return status != null
                    && "Ongoing".equalsIgnoreCase(status.trim());
            }

        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Unable to check election status: "
                + e.getMessage()
        );
    }

    return false;
}

// Asks "Are you sure?" before any VOTE button runs its normal code
private void addVoteConfirmation() {
    for (int i = 0; i < cardVotes.length; i++) {
        final javax.swing.JLabel nameLabel = cardNames[i];
        final javax.swing.JButton btn = cardVotes[i];

        // take the existing vote handler(s) off the button and keep them
        final java.awt.event.ActionListener[] originals = btn.getActionListeners();
        for (java.awt.event.ActionListener l : originals) {
            btn.removeActionListener(l);
        }

        btn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to vote for " + nameLabel.getText() + "?\n"
                + "You cannot change your vote afterwards.",
                "Confirm Vote",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                for (java.awt.event.ActionListener l : originals) {
                    l.actionPerformed(e);   // run the original vote code
                }
            }
        });
    }
}

private void loadPhotos() {
    int[][] ids = {
        presidentCandidateIds, vicePresidentCandidateIds, secretaryCandidateIds,
        treasurerCandidateIds, auditorCandidateIds, internalPIOCandidateIds,
        externalPIOCandidateIds, year1RepCandidateIds, year2RepCandidateIds,
        year3RepCandidateIds, year4RepCandidateIds
    };

    String sql = "SELECT photo_path FROM candidates WHERE candidate_id = ?";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        for (int k = 0; k < ids.length; k++) {
            for (int c = 0; c < 3; c++) {
                int id = ids[k][c];
                if (id == -1) continue;

                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        javax.swing.Icon photo = makePhotoIcon(rs.getString("photo_path"), 80);
                        if (photo != null) {
                            cardIconOrig[k * 3 + c] = photo;   // same order as cardPanels
                        }
                    }
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading photos: " + e.getMessage());
    }

    applyLayout();   // redraw the cards with the new pictures
}

// Loads an image and fits it inside a size x size box (keeps its proportions)
private javax.swing.Icon makePhotoIcon(String path, int size) {
    if (path == null || path.isEmpty()) return null;
    try {
        java.io.File f = new java.io.File(path);
        if (!f.exists()) return null;

        java.awt.image.BufferedImage src = javax.imageio.ImageIO.read(f);
        if (src == null) return null;

        double fit = Math.min((double) size / src.getWidth(), (double) size / src.getHeight());
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
        return null;
    }
}






private void loadCandidates() {
    String sql = "SELECT candidate_id, name, position FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection conn = DBConnection.connect()) {

        if (conn == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Could not connect to the database."
            );
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "Governor");

            try (ResultSet rs = pst.executeQuery()) {

                javax.swing.JLabel[] nameLabels = {
                    lblCandidateName12,
                    lblCandidateName1,
                    lblCandidateName11
                };
                
 javax.swing.JButton[] voteButtons = {
    btnVote9,
    btnVote1,
    btnVote8
};

javax.swing.JButton[] viewButtons = {
    btnViewDetails9,
    btnViewDetails1,
    btnViewDetails8
};

                int i = 0;

 while (rs.next() && i < 3) {
    presidentCandidateIds[i] = rs.getInt("candidate_id");
    nameLabels[i].setText(rs.getString("name"));

    voteButtons[i].setEnabled(true);
    viewButtons[i].setEnabled(true);

    i++;
}

for (; i < 3; i++) {
    presidentCandidateIds[i] = -1;
    nameLabels[i].setText("No Candidate");

    voteButtons[i].setEnabled(false);
    viewButtons[i].setEnabled(false);
}
            }
        }

    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error loading candidates: " + e.getMessage()
        );
    }
}


private void loadVicePresidentCandidates() {

    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    javax.swing.JLabel[] nameLabels = {
        lblVicePresident1,
        lblCandidateName14,
        lblCandidateName15
    };

    javax.swing.JButton[] viewButtons = {
        btnViewVP1,
        btnViewDetails11,
        btnViewDetails12
    };

    javax.swing.JButton[] voteButtons = {
        btnVoteVP1,
        btnVote11,
        btnVote12
    };

    try (Connection conn = DBConnection.connect()) {

        if (conn == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Could not connect to the database."
            );
            return;
        }

        String positionSql = sql;

        try (PreparedStatement pst =
                 conn.prepareStatement(positionSql)) {

            pst.setString(1, "Vice Governor");

            try (ResultSet rs = pst.executeQuery()) {

                int i = 0;

                while (rs.next() && i < 3) {

                    vicePresidentCandidateIds[i] =
                        rs.getInt("candidate_id");

                    nameLabels[i].setText(rs.getString("name"));

                    viewButtons[i].setEnabled(true);
                    voteButtons[i].setEnabled(true);

                    i++;
                }

                // Hide unused candidate slots
                for (; i < 3; i++) {

                    vicePresidentCandidateIds[i] = -1;

                    nameLabels[i].setText("No Candidate");

                    viewButtons[i].setEnabled(false);
                    voteButtons[i].setEnabled(false);
                }
            }
        }

    } catch (SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this, "Error loading Vice President candidates: "
            + e.getMessage()
        );
    }
}


private void loadSecretaryCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    JLabel[] nameLabels = {
        lblSecretary1,
        lblSecretary2,
        lblSecretary3
    };

    JButton[] voteButtons = {
        btnVoteSecretary1,
        btnVoteSecretary2,
        btnVoteSecretary3
    };

    JButton[] viewButtons = {
        btnViewSecretary1,
        btnViewSecretary2,
        btnViewSecretary3
    };

    for (int i = 0; i < 3; i++) {
        secretaryCandidateIds[i] = -1;
        nameLabels[i].setText("No Candidate");
        voteButtons[i].setEnabled(false);
        viewButtons[i].setEnabled(false);
    }

    try (
        Connection con = DBConnection.connect();
        PreparedStatement pst = con.prepareStatement(sql)
    ) {
        pst.setString(1, "Secretary");

        try (ResultSet rs = pst.executeQuery()) {
            int i = 0;

            while (rs.next() && i < 3) {
                secretaryCandidateIds[i] = rs.getInt("candidate_id");
                nameLabels[i].setText(rs.getString("name"));
                voteButtons[i].setEnabled(true);
                viewButtons[i].setEnabled(true);
                i++;
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this,
            "Error loading Secretary candidates: " + e.getMessage()
        );
    }
}


private void loadTreasurerCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    JLabel[] nameLabels = {
        lblTreasurer1,
        lblTreasurer2,
        lblTreasurer3
    };

    JButton[] voteButtons = {
        btnVoteTreasurer1,
        btnVoteTreasurer2,
        btnVoteTreasurer3
    };

    JButton[] viewButtons = {
        btnViewTreasurer1,
        btnViewTreasurer2,
        btnViewTreasurer3
    };

    for (int i = 0; i < 3; i++) {
        treasurerCandidateIds[i] = -1;
        nameLabels[i].setText("No Candidate");
        voteButtons[i].setEnabled(false);
        viewButtons[i].setEnabled(false);
    }

    String sqlQuery = sql;

    try (
        Connection con = DBConnection.connect();
        PreparedStatement pst = con.prepareStatement(sqlQuery)
    ) {
        pst.setString(1, "Treasurer");

        try (ResultSet rs = pst.executeQuery()) {
            int i = 0;

            while (rs.next() && i < 3) {
                treasurerCandidateIds[i] = rs.getInt("candidate_id");
                nameLabels[i].setText(rs.getString("name"));
                voteButtons[i].setEnabled(true);
                viewButtons[i].setEnabled(true);
                i++;
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this,
            "Error loading Treasurer candidates: " + e.getMessage()
        );
    }
}


private void loadAuditorCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    JLabel[] nameLabels = {
        lblAuditor1,
        lblAuditor2,
        lblAuditor3
    };

    JButton[] voteButtons = {
        btnVoteAuditor1,
        btnVoteAuditor2,
        btnVoteAuditor3
    };

    JButton[] viewButtons = {
        btnViewAuditor1,
        btnViewAuditor2,
        btnViewAuditor3
    };

    for (int i = 0; i < 3; i++) {
        auditorCandidateIds[i] = -1;
        nameLabels[i].setText("No Candidate");
        voteButtons[i].setEnabled(false);
        viewButtons[i].setEnabled(false);
    }

    try (
        Connection con = DBConnection.connect();
        PreparedStatement pst = con.prepareStatement(sql)
    ) {
        pst.setString(1, "Auditor");

        try (ResultSet rs = pst.executeQuery()) {
            int i = 0;

            while (rs.next() && i < 3) {
                auditorCandidateIds[i] = rs.getInt("candidate_id");
                nameLabels[i].setText(rs.getString("name"));
                voteButtons[i].setEnabled(true);
                viewButtons[i].setEnabled(true);
                i++;
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this,
            "Error loading Auditor candidates: " + e.getMessage()
        );
    }
}


private void loadInternalPIOCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "Internal P.I.O.");

        try (ResultSet rs = pst.executeQuery()) {
            JLabel[] names = {
                lblInternalPIO1,
                lblInternalPIO2,
                lblInternalPIO3
            };

            JButton[] voteButtons = {
                btnVoteInternalPIO1,
                btnVoteInternalPIO2,
                btnVoteInternalPIO3
            };

            JButton[] detailButtons = {
                btnViewInternalPIO1,
                btnViewInternalPIO2,
                btnViewInternalPIO3
            };

            java.util.Arrays.fill(internalPIOCandidateIds, -1);

            for (int i = 0; i < 3; i++) {
                if (rs.next()) {
                    internalPIOCandidateIds[i] = rs.getInt("candidate_id");
                    names[i].setText(rs.getString("name"));
                    voteButtons[i].setEnabled(true);
                    detailButtons[i].setEnabled(true);
                } else {
                    names[i].setText("No Candidate");
                    voteButtons[i].setEnabled(false);
                    detailButtons[i].setEnabled(false);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this, "Error loading Internal P.I.O. candidates: " + e.getMessage()
        );
    }
}


private void loadExternalPIOCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "External P.I.O.");

        try (ResultSet rs = pst.executeQuery()) {
            JLabel[] names = {
                lblExternalPIO1,
                lblExternalPIO2,
                lblExternalPIO3
            };

            JButton[] voteButtons = {
                btnVoteExternalPIO1,
                btnVoteExternalPIO2,
                btnVoteExternalPIO3
            };

            JButton[] detailButtons = {
                btnViewExternalPIO1,
                btnViewExternalPIO2,
                btnViewExternalPIO3
            };

            java.util.Arrays.fill(externalPIOCandidateIds, -1);

            for (int i = 0; i < 3; i++) {
                if (rs.next()) {
                    externalPIOCandidateIds[i] =
                        rs.getInt("candidate_id");

                    names[i].setText(rs.getString("name"));
                    voteButtons[i].setEnabled(true);
                    detailButtons[i].setEnabled(true);
                } else {
                    names[i].setText("No Candidate");
                    voteButtons[i].setEnabled(false);
                    detailButtons[i].setEnabled(false);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this,
            "Error loading External P.I.O. candidates: " + e.getMessage()
        );
    }
}


private void loadYear1RepCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "Year 1 Representative");

        try (ResultSet rs = pst.executeQuery()) {
            JLabel[] names = {
                lblYear1Rep1,
                lblYear1Rep2,
                lblYear1Rep3
            };

            JButton[] voteButtons = {
                btnVoteYear1Rep1,
                btnVoteYear1Rep2,
                btnVoteYear1Rep3
            };

            JButton[] detailButtons = {
                btnViewYear1Rep1,
                btnViewYear1Rep2,
                btnViewYear1Rep3
            };

            java.util.Arrays.fill(year1RepCandidateIds, -1);

            for (int i = 0; i < 3; i++) {
                if (rs.next()) {
                    year1RepCandidateIds[i] = rs.getInt("candidate_id");
                    names[i].setText(rs.getString("name"));
                    voteButtons[i].setEnabled(true);
                    detailButtons[i].setEnabled(true);
                } else {
                    names[i].setText("No Candidate");
                    voteButtons[i].setEnabled(false);
                    detailButtons[i].setEnabled(false);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this, "Error loading Year 1 Representative candidates: "
            + e.getMessage()
        );
    }
}


private void loadYear2RepCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "Year 2 Representative");

        try (ResultSet rs = pst.executeQuery()) {
            JLabel[] names = {
                lblYear2Rep1,
                lblYear2Rep2,
                lblYear2Rep3
            };

            JButton[] voteButtons = {
                btnVoteYear2Rep1,
                btnVoteYear2Rep2,
                btnVoteYear2Rep3
            };

            JButton[] detailButtons = {
                btnViewYear2Rep1,
                btnViewYear2Rep2,
                btnViewYear2Rep3
            };

            java.util.Arrays.fill(year2RepCandidateIds, -1);

            for (int i = 0; i < 3; i++) {
                if (rs.next()) {
                    year2RepCandidateIds[i] = rs.getInt("candidate_id");
                    names[i].setText(rs.getString("name"));
                    voteButtons[i].setEnabled(true);
                    detailButtons[i].setEnabled(true);
                } else {
                    names[i].setText("No Candidate");
                    voteButtons[i].setEnabled(false);
                    detailButtons[i].setEnabled(false);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this, "Error loading Year 2 Representative candidates: "
            + e.getMessage()
        );
    }
}


private void loadYear3RepCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "Year 3 Representative");

        try (ResultSet rs = pst.executeQuery()) {
            JLabel[] names = {
                lblYear3Rep1,
                lblYear3Rep2,
                lblYear3Rep3
            };

            JButton[] voteButtons = {
                btnVoteYear3Rep1,
                btnVoteYear3Rep2,
                btnVoteYear3Rep3
            };

            JButton[] detailButtons = {
                btnViewYear3Rep1,
                btnViewYear3Rep2,
                btnViewYear3Rep3
            };

            java.util.Arrays.fill(year3RepCandidateIds, -1);

            for (int i = 0; i < 3; i++) {
                if (rs.next()) {
                    year3RepCandidateIds[i] = rs.getInt("candidate_id");
                    names[i].setText(rs.getString("name"));
                    voteButtons[i].setEnabled(true);
                    detailButtons[i].setEnabled(true);
                } else {
                    names[i].setText("No Candidate");
                    voteButtons[i].setEnabled(false);
                    detailButtons[i].setEnabled(false);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this, "Error loading Year 3 Representative candidates: "
            + e.getMessage()
        );
    }
}


private void loadYear4RepCandidates() {
    String sql = "SELECT candidate_id, name FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "Year 4 Representative");

        try (ResultSet rs = pst.executeQuery()) {
            JLabel[] names = {
                lblYear4Rep1,
                lblYear4Rep2,
                lblYear4Rep3
            };

            JButton[] voteButtons = {
                btnVoteYear4Rep1,
                btnVoteYear4Rep2,
                btnVoteYear4Rep3
            };

            JButton[] detailButtons = {
                btnViewYear4Rep1,
                btnViewYear4Rep2,
                btnViewYear4Rep3
            };

            java.util.Arrays.fill(year4RepCandidateIds, -1);

            for (int i = 0; i < 3; i++) {
                if (rs.next()) {
                    year4RepCandidateIds[i] = rs.getInt("candidate_id");
                    names[i].setText(rs.getString("name"));
                    voteButtons[i].setEnabled(true);
                    detailButtons[i].setEnabled(true);
                } else {
                    names[i].setText("No Candidate");
                    voteButtons[i].setEnabled(false);
                    detailButtons[i].setEnabled(false);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this, "Error loading Year 4 Representative candidates: "
            + e.getMessage()
        );
    }
}

// One extra candidate card (4th candidate and beyond)
private static class ExtraCard {
    int section, index;
    javax.swing.JPanel panel = new javax.swing.JPanel(null);
    javax.swing.JLabel icon = new javax.swing.JLabel();
    javax.swing.JLabel name = new javax.swing.JLabel();
    javax.swing.JButton view = new javax.swing.JButton("View details");
    javax.swing.JButton vote = new javax.swing.JButton("VOTE");
    javax.swing.Icon iconOrig;
}

// Builds a card for every candidate after the first 3 of each position
private void loadExtraCards() {
    for (ExtraCard ec : extraCards) jPanel10.remove(ec.panel);
    extraCards.clear();
    java.util.Arrays.fill(extraRows, 0);

    String sql = "SELECT candidate_id, name, photo_path FROM candidates "
               + "WHERE position = ? ORDER BY candidate_id LIMIT 100 OFFSET 3";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        for (int k = 0; k < POSITIONS.length; k++) {
            pst.setString(1, POSITIONS[k]);
            int j = 0;

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    final int id = rs.getInt("candidate_id");
                    final String candName = rs.getString("name");
                    final String position = POSITIONS[k];

                    ExtraCard ec = new ExtraCard();
                    ec.section = k;
                    ec.index = j++;

                    ec.panel.setBackground(new java.awt.Color(0, 153, 51));

                    ec.icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    ec.icon.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                    javax.swing.Icon photo = extraPhoto(rs.getString("photo_path"), 80);
                    ec.iconOrig = (photo != null) ? photo : cardIconOrig[0];

                    ec.name.setText(candName);
                    ec.name.setForeground(java.awt.Color.WHITE);
                    ec.name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                    for (javax.swing.JButton b : new javax.swing.JButton[] { ec.view, ec.vote }) {
                        b.setBackground(new java.awt.Color(0, 102, 51));
                        b.setForeground(java.awt.Color.WHITE);
                    }

                    ec.view.addActionListener(e -> {
                        new CandidateDetails(id).setVisible(true);
                        dispose();
                    });

                    ec.vote.addActionListener(e -> {
                        int choice = JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to vote for " + candName + "?\n"
                            + "You cannot change your vote afterwards.",
                            "Confirm Vote",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                        if (choice == JOptionPane.YES_OPTION) {
                            castVote(position, id);
                        }
                    });

                    ec.panel.add(ec.icon);
                    ec.panel.add(ec.name);
                    ec.panel.add(ec.view);
                    ec.panel.add(ec.vote);
                    jPanel10.add(ec.panel);
                    extraCards.add(ec);
                }
            }
            extraRows[k] = (j + 2) / 3;   // 1-3 extras = 1 row, 4-6 = 2 rows...
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading extra candidates: " + e.getMessage());
    }

    applyLayout();
}

// Same rules as the other vote buttons: logged in, election ongoing, one vote per position
private void castVote(String position, int candidateId) {
    int userId = UserSession.getUserId();

    if (userId <= 0) {
        JOptionPane.showMessageDialog(this, "Please log in first.");
        return;
    }
    if (!isElectionOngoing()) {
        JOptionPane.showMessageDialog(this, "Voting is currently closed.");
        return;
    }

    try (Connection con = DBConnection.connect()) {

        String checkSql = "SELECT COUNT(*) FROM votes v "
                        + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                        + "WHERE v.user_id = ? AND c.position = ?";

        try (PreparedStatement check = con.prepareStatement(checkSql)) {
            check.setInt(1, userId);
            check.setString(2, position);

            try (ResultSet rs = check.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this,
                        "You have already voted for " + position + ".");
                    return;
                }
            }
        }

        String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                         + "VALUES (?, ?, NOW())";

        try (PreparedStatement pst = con.prepareStatement(insertSql)) {
            pst.setInt(1, userId);
            pst.setInt(2, candidateId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,
                "Your vote for " + position + " has been recorded!");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error recording vote: " + e.getMessage());
    }
}

// Loads a saved photo and fits it inside a size x size box
private javax.swing.Icon extraPhoto(String path, int size) {
    if (path == null || path.isEmpty()) return null;
    try {
        java.io.File f = new java.io.File(path);
        if (!f.exists()) return null;

        java.awt.image.BufferedImage src = javax.imageio.ImageIO.read(f);
        if (src == null) return null;

        double fit = Math.min((double) size / src.getWidth(), (double) size / src.getHeight());
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
        return null;
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

        jPanel7 = new javax.swing.JPanel();
        lblCandidateName2 = new javax.swing.JLabel();
        lblPosition2 = new javax.swing.JLabel();
        btnViewDetails2 = new javax.swing.JButton();
        btnVote2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblCandidateName1 = new javax.swing.JLabel();
        btnViewDetails1 = new javax.swing.JButton();
        btnVote1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblCandidateName4 = new javax.swing.JLabel();
        lblCandidateName5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblCandidateName6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblCandidateName11 = new javax.swing.JLabel();
        btnViewDetails8 = new javax.swing.JButton();
        btnVote8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lblCandidateName12 = new javax.swing.JLabel();
        btnViewDetails9 = new javax.swing.JButton();
        btnVote9 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lblCandidateName14 = new javax.swing.JLabel();
        btnViewDetails11 = new javax.swing.JButton();
        btnVote11 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lblCandidateName15 = new javax.swing.JLabel();
        btnViewDetails12 = new javax.swing.JButton();
        btnVote12 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblCandidateName7 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lblVicePresident1 = new javax.swing.JLabel();
        btnViewVP1 = new javax.swing.JButton();
        btnVoteVP1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblSecretary2 = new javax.swing.JLabel();
        btnViewSecretary2 = new javax.swing.JButton();
        btnVoteSecretary2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        lblSecretary3 = new javax.swing.JLabel();
        btnViewSecretary3 = new javax.swing.JButton();
        btnVoteSecretary3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblSecretary1 = new javax.swing.JLabel();
        btnViewSecretary1 = new javax.swing.JButton();
        btnVoteSecretary1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblCandidateName8 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        lblTreasurer1 = new javax.swing.JLabel();
        btnViewTreasurer1 = new javax.swing.JButton();
        btnVoteTreasurer1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lblTreasurer2 = new javax.swing.JLabel();
        btnViewTreasurer2 = new javax.swing.JButton();
        btnVoteTreasurer2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        lblTreasurer3 = new javax.swing.JLabel();
        btnViewTreasurer3 = new javax.swing.JButton();
        btnVoteTreasurer3 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblCandidateName9 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        lblAuditor1 = new javax.swing.JLabel();
        btnViewAuditor1 = new javax.swing.JButton();
        btnVoteAuditor1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        lblAuditor2 = new javax.swing.JLabel();
        btnViewAuditor2 = new javax.swing.JButton();
        btnVoteAuditor2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        lblAuditor3 = new javax.swing.JLabel();
        btnViewAuditor3 = new javax.swing.JButton();
        btnVoteAuditor3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblCandidateName10 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        lblInternalPIO1 = new javax.swing.JLabel();
        btnViewInternalPIO1 = new javax.swing.JButton();
        btnVoteInternalPIO1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        lblInternalPIO2 = new javax.swing.JLabel();
        btnViewInternalPIO2 = new javax.swing.JButton();
        btnVoteInternalPIO2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        lblInternalPIO3 = new javax.swing.JLabel();
        btnViewInternalPIO3 = new javax.swing.JButton();
        btnVoteInternalPIO3 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblCandidateName13 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        lblExternalPIO1 = new javax.swing.JLabel();
        btnViewExternalPIO1 = new javax.swing.JButton();
        btnVoteExternalPIO1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        lblExternalPIO2 = new javax.swing.JLabel();
        btnViewExternalPIO2 = new javax.swing.JButton();
        btnVoteExternalPIO2 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        lblExternalPIO3 = new javax.swing.JLabel();
        btnViewExternalPIO3 = new javax.swing.JButton();
        btnVoteExternalPIO3 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        lblCandidateName16 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        lblYear1Rep1 = new javax.swing.JLabel();
        btnViewYear1Rep1 = new javax.swing.JButton();
        btnVoteYear1Rep1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        lblYear1Rep2 = new javax.swing.JLabel();
        btnViewYear1Rep2 = new javax.swing.JButton();
        btnVoteYear1Rep2 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        lblYear1Rep3 = new javax.swing.JLabel();
        btnViewYear1Rep3 = new javax.swing.JButton();
        btnVoteYear1Rep3 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        lblCandidateName17 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        lblYear2Rep1 = new javax.swing.JLabel();
        btnViewYear2Rep1 = new javax.swing.JButton();
        btnVoteYear2Rep1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        lblYear2Rep2 = new javax.swing.JLabel();
        btnViewYear2Rep2 = new javax.swing.JButton();
        btnVoteYear2Rep2 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        lblYear2Rep3 = new javax.swing.JLabel();
        btnViewYear2Rep3 = new javax.swing.JButton();
        btnVoteYear2Rep3 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        lblCandidateName18 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        lblYear3Rep1 = new javax.swing.JLabel();
        btnViewYear3Rep1 = new javax.swing.JButton();
        btnVoteYear3Rep1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        lblYear3Rep2 = new javax.swing.JLabel();
        btnViewYear3Rep2 = new javax.swing.JButton();
        btnVoteYear3Rep2 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        lblYear3Rep3 = new javax.swing.JLabel();
        btnViewYear3Rep3 = new javax.swing.JButton();
        btnVoteYear3Rep3 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        lblCandidateName19 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        lblYear4Rep1 = new javax.swing.JLabel();
        btnViewYear4Rep1 = new javax.swing.JButton();
        btnVoteYear4Rep1 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        lblYear4Rep2 = new javax.swing.JLabel();
        btnViewYear4Rep2 = new javax.swing.JButton();
        btnVoteYear4Rep2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        lblYear4Rep3 = new javax.swing.JLabel();
        btnViewYear4Rep3 = new javax.swing.JButton();
        btnVoteYear4Rep3 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();

        jPanel7.setBackground(new java.awt.Color(255, 255, 102));

        lblCandidateName2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName2.setForeground(new java.awt.Color(0, 153, 102));
        lblCandidateName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName2.setText("CATHY RAMOS");
        lblCandidateName2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName2MouseMoved(evt);
            }
        });

        lblPosition2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPosition2.setText("PRESIDENT");

        btnViewDetails2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewDetails2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewDetails2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails2.setText("View details");
        btnViewDetails2.addActionListener(this::btnViewDetails2ActionPerformed);

        btnVote2.setBackground(new java.awt.Color(0, 102, 51));
        btnVote2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVote2.setForeground(new java.awt.Color(255, 255, 255));
        btnVote2.setText("VOTE");
        btnVote2.addActionListener(this::btnVote2ActionPerformed);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnViewDetails2)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblCandidateName2))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(lblPosition2))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnVote2)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lblCandidateName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosition2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewDetails2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVote2)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\ChatGPT Image Sep 25, 2026, 04_35_52 PM (2).png")); // NOI18N
        jLabel2.setText("jLabel2");

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Home");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Candidates");
        jButton3.setBorder(null);
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("About");
        jButton4.setBorder(null);
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setBackground(new java.awt.Color(0, 102, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Voting Rules");
        jButton5.setBorder(null);
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setBackground(new java.awt.Color(0, 102, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Contact");
        jButton6.setBorder(null);
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Log Out");
        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel10.setBackground(new java.awt.Color(0, 102, 51));
        jPanel10.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel6);
        jPanel6.setBounds(110, 70, 780, 15);

        jPanel5.setBackground(new java.awt.Color(0, 153, 51));

        lblCandidateName1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName1.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName1.setText("CATHY RAMOS");
        lblCandidateName1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName1MouseMoved(evt);
            }
        });

        btnViewDetails1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewDetails1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewDetails1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails1.setText("View details");
        btnViewDetails1.addActionListener(this::btnViewDetails1ActionPerformed);

        btnVote1.setBackground(new java.awt.Color(0, 102, 51));
        btnVote1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVote1.setForeground(new java.awt.Color(255, 255, 255));
        btnVote1.setText("VOTE");
        btnVote1.addActionListener(this::btnVote1ActionPerformed);

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel4.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVote1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidateName1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewDetails1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCandidateName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewDetails1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVote1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel5);
        jPanel5.setBounds(390, 150, 210, 270);

        lblCandidateName4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName4.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName4.setText("4th Year Representative");
        lblCandidateName4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName4MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName4);
        lblCandidateName4.setBounds(110, 4260, 210, 32);

        lblCandidateName5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCandidateName5.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName5.setText("Running Candidates");
        lblCandidateName5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName5MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName5);
        lblCandidateName5.setBounds(370, 20, 240, 32);

        jPanel9.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel9);
        jPanel9.setBounds(110, 460, 780, 15);

        lblCandidateName6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName6.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName6.setText("Governor");
        lblCandidateName6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName6MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName6);
        lblCandidateName6.setBounds(110, 100, 90, 32);

        jPanel15.setBackground(new java.awt.Color(0, 153, 51));

        lblCandidateName11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName11.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName11.setText("CATHY RAMOS");
        lblCandidateName11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName11MouseMoved(evt);
            }
        });

        btnViewDetails8.setBackground(new java.awt.Color(0, 102, 51));
        btnViewDetails8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewDetails8.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails8.setText("View details");
        btnViewDetails8.addActionListener(this::btnViewDetails8ActionPerformed);

        btnVote8.setBackground(new java.awt.Color(0, 102, 51));
        btnVote8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVote8.setForeground(new java.awt.Color(255, 255, 255));
        btnVote8.setText("VOTE");
        btnVote8.addActionListener(this::btnVote8ActionPerformed);

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel10.setText("jLabel1");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVote8))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidateName11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewDetails8))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCandidateName11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewDetails8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVote8)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel15);
        jPanel15.setBounds(680, 150, 210, 270);

        jPanel16.setBackground(new java.awt.Color(0, 153, 51));

        lblCandidateName12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName12.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName12.setText("CATHY RAMOS");
        lblCandidateName12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName12MouseMoved(evt);
            }
        });

        btnViewDetails9.setBackground(new java.awt.Color(0, 102, 51));
        btnViewDetails9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewDetails9.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails9.setText("View details");
        btnViewDetails9.addActionListener(this::btnViewDetails9ActionPerformed);

        btnVote9.setBackground(new java.awt.Color(0, 102, 51));
        btnVote9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVote9.setForeground(new java.awt.Color(255, 255, 255));
        btnVote9.setText("VOTE");
        btnVote9.addActionListener(this::btnVote9ActionPerformed);

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel11.setText("jLabel1");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVote9))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidateName12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewDetails9))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCandidateName12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewDetails9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVote9)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel16);
        jPanel16.setBounds(110, 150, 210, 270);

        jPanel18.setBackground(new java.awt.Color(0, 153, 51));

        lblCandidateName14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName14.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName14.setText("CATHY RAMOS");
        lblCandidateName14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName14MouseMoved(evt);
            }
        });

        btnViewDetails11.setBackground(new java.awt.Color(0, 102, 51));
        btnViewDetails11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewDetails11.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails11.setText("View details");
        btnViewDetails11.addActionListener(this::btnViewDetails11ActionPerformed);

        btnVote11.setBackground(new java.awt.Color(0, 102, 51));
        btnVote11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVote11.setForeground(new java.awt.Color(255, 255, 255));
        btnVote11.setText("VOTE");
        btnVote11.addActionListener(this::btnVote11ActionPerformed);

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel13.setText("jLabel1");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVote11))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidateName14, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewDetails11))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCandidateName14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewDetails11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVote11)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel18);
        jPanel18.setBounds(390, 550, 210, 270);

        jPanel19.setBackground(new java.awt.Color(0, 153, 51));

        lblCandidateName15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName15.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName15.setText("CATHY RAMOS");
        lblCandidateName15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName15MouseMoved(evt);
            }
        });

        btnViewDetails12.setBackground(new java.awt.Color(0, 102, 51));
        btnViewDetails12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewDetails12.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails12.setText("View details");
        btnViewDetails12.addActionListener(this::btnViewDetails12ActionPerformed);

        btnVote12.setBackground(new java.awt.Color(0, 102, 51));
        btnVote12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVote12.setForeground(new java.awt.Color(255, 255, 255));
        btnVote12.setText("VOTE");
        btnVote12.addActionListener(this::btnVote12ActionPerformed);

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel14.setText("jLabel1");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVote12))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCandidateName15, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewDetails12))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCandidateName15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewDetails12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVote12)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel19);
        jPanel19.setBounds(680, 550, 210, 270);

        jPanel8.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel8);
        jPanel8.setBounds(110, 860, 780, 15);

        lblCandidateName7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName7.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName7.setText("Vice Governor");
        lblCandidateName7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName7MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName7);
        lblCandidateName7.setBounds(110, 500, 130, 32);

        jPanel17.setBackground(new java.awt.Color(0, 153, 51));

        lblVicePresident1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblVicePresident1.setForeground(new java.awt.Color(255, 255, 255));
        lblVicePresident1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVicePresident1.setText("CATHY RAMOS");
        lblVicePresident1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblVicePresident1MouseMoved(evt);
            }
        });

        btnViewVP1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewVP1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewVP1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewVP1.setText("View details");
        btnViewVP1.addActionListener(this::btnViewVP1ActionPerformed);

        btnVoteVP1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteVP1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteVP1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteVP1.setText("VOTE");
        btnVoteVP1.addActionListener(this::btnVoteVP1ActionPerformed);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel12.setText("jLabel1");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteVP1))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVicePresident1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewVP1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVicePresident1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewVP1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteVP1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel17);
        jPanel17.setBounds(110, 550, 210, 270);

        jPanel21.setBackground(new java.awt.Color(0, 153, 51));

        lblSecretary2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSecretary2.setForeground(new java.awt.Color(255, 255, 255));
        lblSecretary2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSecretary2.setText("CATHY RAMOS");
        lblSecretary2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSecretary2MouseMoved(evt);
            }
        });

        btnViewSecretary2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewSecretary2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewSecretary2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewSecretary2.setText("View details");
        btnViewSecretary2.addActionListener(this::btnViewSecretary2ActionPerformed);

        btnVoteSecretary2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteSecretary2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteSecretary2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteSecretary2.setText("VOTE");
        btnVoteSecretary2.addActionListener(this::btnVoteSecretary2ActionPerformed);

        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel16.setText("jLabel1");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteSecretary2))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSecretary2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewSecretary2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSecretary2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewSecretary2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteSecretary2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel21);
        jPanel21.setBounds(390, 960, 210, 270);

        jPanel22.setBackground(new java.awt.Color(0, 153, 51));

        lblSecretary3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSecretary3.setForeground(new java.awt.Color(255, 255, 255));
        lblSecretary3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSecretary3.setText("CATHY RAMOS");
        lblSecretary3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSecretary3MouseMoved(evt);
            }
        });

        btnViewSecretary3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewSecretary3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewSecretary3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewSecretary3.setText("View details");
        btnViewSecretary3.addActionListener(this::btnViewSecretary3ActionPerformed);

        btnVoteSecretary3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteSecretary3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteSecretary3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteSecretary3.setText("VOTE");
        btnVoteSecretary3.addActionListener(this::btnVoteSecretary3ActionPerformed);

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel17.setText("jLabel1");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteSecretary3))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSecretary3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewSecretary3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSecretary3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewSecretary3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteSecretary3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel22);
        jPanel22.setBounds(680, 960, 210, 270);

        jPanel20.setBackground(new java.awt.Color(0, 153, 51));

        lblSecretary1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSecretary1.setForeground(new java.awt.Color(255, 255, 255));
        lblSecretary1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSecretary1.setText("CATHY RAMOS");
        lblSecretary1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSecretary1MouseMoved(evt);
            }
        });

        btnViewSecretary1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewSecretary1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewSecretary1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewSecretary1.setText("View details");
        btnViewSecretary1.addActionListener(this::btnViewSecretary1ActionPerformed);

        btnVoteSecretary1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteSecretary1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteSecretary1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteSecretary1.setText("VOTE");
        btnVoteSecretary1.addActionListener(this::btnVoteSecretary1ActionPerformed);

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel15.setText("jLabel1");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteSecretary1))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSecretary1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewSecretary1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSecretary1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewSecretary1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteSecretary1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel20);
        jPanel20.setBounds(110, 960, 210, 270);

        jPanel11.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel11);
        jPanel11.setBounds(110, 1270, 780, 15);

        lblCandidateName8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName8.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName8.setText("Secretary");
        lblCandidateName8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName8MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName8);
        lblCandidateName8.setBounds(110, 900, 90, 32);

        jPanel23.setBackground(new java.awt.Color(0, 153, 51));

        lblTreasurer1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTreasurer1.setForeground(new java.awt.Color(255, 255, 255));
        lblTreasurer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTreasurer1.setText("CATHY RAMOS");
        lblTreasurer1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTreasurer1MouseMoved(evt);
            }
        });

        btnViewTreasurer1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewTreasurer1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewTreasurer1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewTreasurer1.setText("View details");
        btnViewTreasurer1.addActionListener(this::btnViewTreasurer1ActionPerformed);

        btnVoteTreasurer1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteTreasurer1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteTreasurer1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteTreasurer1.setText("VOTE");
        btnVoteTreasurer1.addActionListener(this::btnVoteTreasurer1ActionPerformed);

        jLabel18.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel18.setText("jLabel1");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteTreasurer1))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTreasurer1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewTreasurer1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTreasurer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewTreasurer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteTreasurer1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel23);
        jPanel23.setBounds(110, 1370, 210, 270);

        jPanel24.setBackground(new java.awt.Color(0, 153, 51));

        lblTreasurer2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTreasurer2.setForeground(new java.awt.Color(255, 255, 255));
        lblTreasurer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTreasurer2.setText("CATHY RAMOS");
        lblTreasurer2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTreasurer2MouseMoved(evt);
            }
        });

        btnViewTreasurer2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewTreasurer2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewTreasurer2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewTreasurer2.setText("View details");
        btnViewTreasurer2.addActionListener(this::btnViewTreasurer2ActionPerformed);

        btnVoteTreasurer2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteTreasurer2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteTreasurer2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteTreasurer2.setText("VOTE");
        btnVoteTreasurer2.addActionListener(this::btnVoteTreasurer2ActionPerformed);

        jLabel19.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel19.setText("jLabel1");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteTreasurer2))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTreasurer2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewTreasurer2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTreasurer2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewTreasurer2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteTreasurer2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel24);
        jPanel24.setBounds(390, 1370, 210, 270);

        jPanel25.setBackground(new java.awt.Color(0, 153, 51));

        lblTreasurer3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTreasurer3.setForeground(new java.awt.Color(255, 255, 255));
        lblTreasurer3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTreasurer3.setText("CATHY RAMOS");
        lblTreasurer3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTreasurer3MouseMoved(evt);
            }
        });

        btnViewTreasurer3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewTreasurer3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewTreasurer3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewTreasurer3.setText("View details");
        btnViewTreasurer3.addActionListener(this::btnViewTreasurer3ActionPerformed);

        btnVoteTreasurer3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteTreasurer3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteTreasurer3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteTreasurer3.setText("VOTE");
        btnVoteTreasurer3.addActionListener(this::btnVoteTreasurer3ActionPerformed);

        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel20.setText("jLabel1");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteTreasurer3))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTreasurer3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewTreasurer3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTreasurer3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewTreasurer3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteTreasurer3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel25);
        jPanel25.setBounds(680, 1370, 210, 270);

        jPanel12.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel12);
        jPanel12.setBounds(110, 1680, 780, 15);

        lblCandidateName9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName9.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName9.setText("Treasurer");
        lblCandidateName9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName9MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName9);
        lblCandidateName9.setBounds(110, 1310, 90, 32);

        jPanel26.setBackground(new java.awt.Color(0, 153, 51));

        lblAuditor1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAuditor1.setForeground(new java.awt.Color(255, 255, 255));
        lblAuditor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAuditor1.setText("CATHY RAMOS");
        lblAuditor1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblAuditor1MouseMoved(evt);
            }
        });

        btnViewAuditor1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewAuditor1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewAuditor1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAuditor1.setText("View details");
        btnViewAuditor1.addActionListener(this::btnViewAuditor1ActionPerformed);

        btnVoteAuditor1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteAuditor1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteAuditor1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteAuditor1.setText("VOTE");
        btnVoteAuditor1.addActionListener(this::btnVoteAuditor1ActionPerformed);

        jLabel21.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel21.setText("jLabel1");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteAuditor1))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAuditor1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewAuditor1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAuditor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewAuditor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteAuditor1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel26);
        jPanel26.setBounds(110, 1780, 210, 270);

        jPanel27.setBackground(new java.awt.Color(0, 153, 51));

        lblAuditor2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAuditor2.setForeground(new java.awt.Color(255, 255, 255));
        lblAuditor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAuditor2.setText("CATHY RAMOS");
        lblAuditor2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblAuditor2MouseMoved(evt);
            }
        });

        btnViewAuditor2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewAuditor2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewAuditor2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAuditor2.setText("View details");
        btnViewAuditor2.addActionListener(this::btnViewAuditor2ActionPerformed);

        btnVoteAuditor2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteAuditor2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteAuditor2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteAuditor2.setText("VOTE");
        btnVoteAuditor2.addActionListener(this::btnVoteAuditor2ActionPerformed);

        jLabel22.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel22.setText("jLabel1");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteAuditor2))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAuditor2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewAuditor2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAuditor2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewAuditor2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteAuditor2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel27);
        jPanel27.setBounds(390, 1780, 210, 270);

        jPanel28.setBackground(new java.awt.Color(0, 153, 51));

        lblAuditor3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAuditor3.setForeground(new java.awt.Color(255, 255, 255));
        lblAuditor3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAuditor3.setText("CATHY RAMOS");
        lblAuditor3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblAuditor3MouseMoved(evt);
            }
        });

        btnViewAuditor3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewAuditor3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewAuditor3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAuditor3.setText("View details");
        btnViewAuditor3.addActionListener(this::btnViewAuditor3ActionPerformed);

        btnVoteAuditor3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteAuditor3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteAuditor3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteAuditor3.setText("VOTE");
        btnVoteAuditor3.addActionListener(this::btnVoteAuditor3ActionPerformed);

        jLabel23.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel23.setText("jLabel1");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteAuditor3))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAuditor3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewAuditor3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAuditor3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewAuditor3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteAuditor3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel28);
        jPanel28.setBounds(680, 1780, 210, 270);

        jPanel13.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel13);
        jPanel13.setBounds(110, 2100, 780, 15);

        lblCandidateName10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName10.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName10.setText("Auditor");
        lblCandidateName10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName10MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName10);
        lblCandidateName10.setBounds(110, 1720, 90, 32);

        jPanel29.setBackground(new java.awt.Color(0, 153, 51));

        lblInternalPIO1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInternalPIO1.setForeground(new java.awt.Color(255, 255, 255));
        lblInternalPIO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInternalPIO1.setText("CATHY RAMOS");
        lblInternalPIO1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblInternalPIO1MouseMoved(evt);
            }
        });

        btnViewInternalPIO1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewInternalPIO1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewInternalPIO1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewInternalPIO1.setText("View details");
        btnViewInternalPIO1.addActionListener(this::btnViewInternalPIO1ActionPerformed);

        btnVoteInternalPIO1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteInternalPIO1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteInternalPIO1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteInternalPIO1.setText("VOTE");
        btnVoteInternalPIO1.addActionListener(this::btnVoteInternalPIO1ActionPerformed);

        jLabel24.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel24.setText("jLabel1");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteInternalPIO1))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInternalPIO1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewInternalPIO1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInternalPIO1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewInternalPIO1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteInternalPIO1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel29);
        jPanel29.setBounds(110, 2210, 210, 270);

        jPanel30.setBackground(new java.awt.Color(0, 153, 51));

        lblInternalPIO2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInternalPIO2.setForeground(new java.awt.Color(255, 255, 255));
        lblInternalPIO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInternalPIO2.setText("CATHY RAMOS");
        lblInternalPIO2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblInternalPIO2MouseMoved(evt);
            }
        });

        btnViewInternalPIO2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewInternalPIO2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewInternalPIO2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewInternalPIO2.setText("View details");
        btnViewInternalPIO2.addActionListener(this::btnViewInternalPIO2ActionPerformed);

        btnVoteInternalPIO2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteInternalPIO2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteInternalPIO2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteInternalPIO2.setText("VOTE");
        btnVoteInternalPIO2.addActionListener(this::btnVoteInternalPIO2ActionPerformed);

        jLabel25.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel25.setText("jLabel1");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteInternalPIO2))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInternalPIO2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewInternalPIO2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInternalPIO2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewInternalPIO2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteInternalPIO2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel30);
        jPanel30.setBounds(390, 2210, 210, 270);

        jPanel31.setBackground(new java.awt.Color(0, 153, 51));

        lblInternalPIO3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInternalPIO3.setForeground(new java.awt.Color(255, 255, 255));
        lblInternalPIO3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInternalPIO3.setText("CATHY RAMOS");
        lblInternalPIO3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblInternalPIO3MouseMoved(evt);
            }
        });

        btnViewInternalPIO3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewInternalPIO3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewInternalPIO3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewInternalPIO3.setText("View details");
        btnViewInternalPIO3.addActionListener(this::btnViewInternalPIO3ActionPerformed);

        btnVoteInternalPIO3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteInternalPIO3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteInternalPIO3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteInternalPIO3.setText("VOTE");
        btnVoteInternalPIO3.addActionListener(this::btnVoteInternalPIO3ActionPerformed);

        jLabel26.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel26.setText("jLabel1");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteInternalPIO3))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInternalPIO3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewInternalPIO3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInternalPIO3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewInternalPIO3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteInternalPIO3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel31);
        jPanel31.setBounds(680, 2210, 210, 270);

        jPanel14.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel14);
        jPanel14.setBounds(110, 2520, 780, 15);

        lblCandidateName13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName13.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName13.setText("Internal P.I.O.");
        lblCandidateName13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName13MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName13);
        lblCandidateName13.setBounds(110, 2140, 130, 32);

        jPanel32.setBackground(new java.awt.Color(0, 153, 51));

        lblExternalPIO1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblExternalPIO1.setForeground(new java.awt.Color(255, 255, 255));
        lblExternalPIO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExternalPIO1.setText("CATHY RAMOS");
        lblExternalPIO1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblExternalPIO1MouseMoved(evt);
            }
        });

        btnViewExternalPIO1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewExternalPIO1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewExternalPIO1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewExternalPIO1.setText("View details");
        btnViewExternalPIO1.addActionListener(this::btnViewExternalPIO1ActionPerformed);

        btnVoteExternalPIO1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteExternalPIO1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteExternalPIO1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteExternalPIO1.setText("VOTE");
        btnVoteExternalPIO1.addActionListener(this::btnVoteExternalPIO1ActionPerformed);

        jLabel27.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel27.setText("jLabel1");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteExternalPIO1))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExternalPIO1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewExternalPIO1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblExternalPIO1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewExternalPIO1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteExternalPIO1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel32);
        jPanel32.setBounds(110, 2630, 210, 270);

        jPanel33.setBackground(new java.awt.Color(0, 153, 51));

        lblExternalPIO2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblExternalPIO2.setForeground(new java.awt.Color(255, 255, 255));
        lblExternalPIO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExternalPIO2.setText("CATHY RAMOS");
        lblExternalPIO2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblExternalPIO2MouseMoved(evt);
            }
        });

        btnViewExternalPIO2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewExternalPIO2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewExternalPIO2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewExternalPIO2.setText("View details");
        btnViewExternalPIO2.addActionListener(this::btnViewExternalPIO2ActionPerformed);

        btnVoteExternalPIO2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteExternalPIO2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteExternalPIO2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteExternalPIO2.setText("VOTE");
        btnVoteExternalPIO2.addActionListener(this::btnVoteExternalPIO2ActionPerformed);

        jLabel28.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel28.setText("jLabel1");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteExternalPIO2))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExternalPIO2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewExternalPIO2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblExternalPIO2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewExternalPIO2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteExternalPIO2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel33);
        jPanel33.setBounds(390, 2630, 210, 270);

        jPanel34.setBackground(new java.awt.Color(0, 153, 51));

        lblExternalPIO3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblExternalPIO3.setForeground(new java.awt.Color(255, 255, 255));
        lblExternalPIO3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExternalPIO3.setText("CATHY RAMOS");
        lblExternalPIO3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblExternalPIO3MouseMoved(evt);
            }
        });

        btnViewExternalPIO3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewExternalPIO3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewExternalPIO3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewExternalPIO3.setText("View details");
        btnViewExternalPIO3.addActionListener(this::btnViewExternalPIO3ActionPerformed);

        btnVoteExternalPIO3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteExternalPIO3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteExternalPIO3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteExternalPIO3.setText("VOTE");
        btnVoteExternalPIO3.addActionListener(this::btnVoteExternalPIO3ActionPerformed);

        jLabel29.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel29.setText("jLabel1");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteExternalPIO3))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExternalPIO3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewExternalPIO3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblExternalPIO3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewExternalPIO3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteExternalPIO3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel34);
        jPanel34.setBounds(680, 2630, 210, 270);

        jPanel35.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel35);
        jPanel35.setBounds(110, 2950, 780, 15);

        lblCandidateName16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName16.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName16.setText("External P.I.O.");
        lblCandidateName16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName16MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName16);
        lblCandidateName16.setBounds(110, 2560, 130, 32);

        jPanel36.setBackground(new java.awt.Color(0, 153, 51));

        lblYear1Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear1Rep1.setForeground(new java.awt.Color(255, 255, 255));
        lblYear1Rep1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear1Rep1.setText("CATHY RAMOS");
        lblYear1Rep1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear1Rep1MouseMoved(evt);
            }
        });

        btnViewYear1Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear1Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear1Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear1Rep1.setText("View details");
        btnViewYear1Rep1.addActionListener(this::btnViewYear1Rep1ActionPerformed);

        btnVoteYear1Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear1Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear1Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear1Rep1.setText("VOTE");
        btnVoteYear1Rep1.addActionListener(this::btnVoteYear1Rep1ActionPerformed);

        jLabel30.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel30.setText("jLabel1");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear1Rep1))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear1Rep1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear1Rep1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear1Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear1Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear1Rep1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel36);
        jPanel36.setBounds(110, 3060, 210, 270);

        jPanel37.setBackground(new java.awt.Color(0, 153, 51));

        lblYear1Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear1Rep2.setForeground(new java.awt.Color(255, 255, 255));
        lblYear1Rep2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear1Rep2.setText("CATHY RAMOS");
        lblYear1Rep2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear1Rep2MouseMoved(evt);
            }
        });

        btnViewYear1Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear1Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear1Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear1Rep2.setText("View details");
        btnViewYear1Rep2.addActionListener(this::btnViewYear1Rep2ActionPerformed);

        btnVoteYear1Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear1Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear1Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear1Rep2.setText("VOTE");
        btnVoteYear1Rep2.addActionListener(this::btnVoteYear1Rep2ActionPerformed);

        jLabel31.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel31.setText("jLabel1");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear1Rep2))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear1Rep2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear1Rep2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear1Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear1Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear1Rep2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel37);
        jPanel37.setBounds(390, 3060, 210, 270);

        jPanel38.setBackground(new java.awt.Color(0, 153, 51));

        lblYear1Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear1Rep3.setForeground(new java.awt.Color(255, 255, 255));
        lblYear1Rep3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear1Rep3.setText("CATHY RAMOS");
        lblYear1Rep3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear1Rep3MouseMoved(evt);
            }
        });

        btnViewYear1Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear1Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear1Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear1Rep3.setText("View details");
        btnViewYear1Rep3.addActionListener(this::btnViewYear1Rep3ActionPerformed);

        btnVoteYear1Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear1Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear1Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear1Rep3.setText("VOTE");
        btnVoteYear1Rep3.addActionListener(this::btnVoteYear1Rep3ActionPerformed);

        jLabel32.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel32.setText("jLabel1");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear1Rep3))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear1Rep3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear1Rep3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear1Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear1Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear1Rep3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel38);
        jPanel38.setBounds(680, 3060, 210, 270);

        jPanel39.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel39);
        jPanel39.setBounds(110, 3380, 780, 15);

        lblCandidateName17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName17.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName17.setText("1st Year Representative");
        lblCandidateName17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName17MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName17);
        lblCandidateName17.setBounds(110, 2990, 210, 32);

        jPanel40.setBackground(new java.awt.Color(0, 153, 51));

        lblYear2Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear2Rep1.setForeground(new java.awt.Color(255, 255, 255));
        lblYear2Rep1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear2Rep1.setText("CATHY RAMOS");
        lblYear2Rep1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear2Rep1MouseMoved(evt);
            }
        });

        btnViewYear2Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear2Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear2Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear2Rep1.setText("View details");
        btnViewYear2Rep1.addActionListener(this::btnViewYear2Rep1ActionPerformed);

        btnVoteYear2Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear2Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear2Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear2Rep1.setText("VOTE");
        btnVoteYear2Rep1.addActionListener(this::btnVoteYear2Rep1ActionPerformed);

        jLabel33.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel33.setText("jLabel1");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear2Rep1))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear2Rep1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear2Rep1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear2Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear2Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear2Rep1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel40);
        jPanel40.setBounds(110, 3490, 210, 270);

        jPanel41.setBackground(new java.awt.Color(0, 153, 51));

        lblYear2Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear2Rep2.setForeground(new java.awt.Color(255, 255, 255));
        lblYear2Rep2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear2Rep2.setText("CATHY RAMOS");
        lblYear2Rep2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear2Rep2MouseMoved(evt);
            }
        });

        btnViewYear2Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear2Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear2Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear2Rep2.setText("View details");
        btnViewYear2Rep2.addActionListener(this::btnViewYear2Rep2ActionPerformed);

        btnVoteYear2Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear2Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear2Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear2Rep2.setText("VOTE");
        btnVoteYear2Rep2.addActionListener(this::btnVoteYear2Rep2ActionPerformed);

        jLabel34.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel34.setText("jLabel1");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear2Rep2))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear2Rep2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear2Rep2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear2Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear2Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear2Rep2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel41);
        jPanel41.setBounds(390, 3490, 210, 270);

        jPanel42.setBackground(new java.awt.Color(0, 153, 51));

        lblYear2Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear2Rep3.setForeground(new java.awt.Color(255, 255, 255));
        lblYear2Rep3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear2Rep3.setText("CATHY RAMOS");
        lblYear2Rep3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear2Rep3MouseMoved(evt);
            }
        });

        btnViewYear2Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear2Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear2Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear2Rep3.setText("View details");
        btnViewYear2Rep3.addActionListener(this::btnViewYear2Rep3ActionPerformed);

        btnVoteYear2Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear2Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear2Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear2Rep3.setText("VOTE");
        btnVoteYear2Rep3.addActionListener(this::btnVoteYear2Rep3ActionPerformed);

        jLabel35.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel35.setText("jLabel1");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear2Rep3))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear2Rep3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear2Rep3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear2Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear2Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear2Rep3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel42);
        jPanel42.setBounds(680, 3490, 210, 270);

        jPanel43.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel43);
        jPanel43.setBounds(110, 3800, 780, 15);

        lblCandidateName18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName18.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName18.setText("2nd Year Representative");
        lblCandidateName18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName18MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName18);
        lblCandidateName18.setBounds(110, 3430, 210, 32);

        jPanel44.setBackground(new java.awt.Color(0, 153, 51));

        lblYear3Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear3Rep1.setForeground(new java.awt.Color(255, 255, 255));
        lblYear3Rep1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear3Rep1.setText("CATHY RAMOS");
        lblYear3Rep1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear3Rep1MouseMoved(evt);
            }
        });

        btnViewYear3Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear3Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear3Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear3Rep1.setText("View details");
        btnViewYear3Rep1.addActionListener(this::btnViewYear3Rep1ActionPerformed);

        btnVoteYear3Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear3Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear3Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear3Rep1.setText("VOTE");
        btnVoteYear3Rep1.addActionListener(this::btnVoteYear3Rep1ActionPerformed);

        jLabel36.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel36.setText("jLabel1");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear3Rep1))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear3Rep1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear3Rep1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear3Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear3Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear3Rep1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel44);
        jPanel44.setBounds(110, 3900, 210, 270);

        jPanel45.setBackground(new java.awt.Color(0, 153, 51));

        lblYear3Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear3Rep2.setForeground(new java.awt.Color(255, 255, 255));
        lblYear3Rep2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear3Rep2.setText("CATHY RAMOS");
        lblYear3Rep2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear3Rep2MouseMoved(evt);
            }
        });

        btnViewYear3Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear3Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear3Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear3Rep2.setText("View details");
        btnViewYear3Rep2.addActionListener(this::btnViewYear3Rep2ActionPerformed);

        btnVoteYear3Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear3Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear3Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear3Rep2.setText("VOTE");
        btnVoteYear3Rep2.addActionListener(this::btnVoteYear3Rep2ActionPerformed);

        jLabel37.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel37.setText("jLabel1");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear3Rep2))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear3Rep2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear3Rep2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear3Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear3Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear3Rep2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel45);
        jPanel45.setBounds(390, 3900, 210, 270);

        jPanel46.setBackground(new java.awt.Color(0, 153, 51));

        lblYear3Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear3Rep3.setForeground(new java.awt.Color(255, 255, 255));
        lblYear3Rep3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear3Rep3.setText("CATHY RAMOS");
        lblYear3Rep3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear3Rep3MouseMoved(evt);
            }
        });

        btnViewYear3Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear3Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear3Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear3Rep3.setText("View details");
        btnViewYear3Rep3.addActionListener(this::btnViewYear3Rep3ActionPerformed);

        btnVoteYear3Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear3Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear3Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear3Rep3.setText("VOTE");
        btnVoteYear3Rep3.addActionListener(this::btnVoteYear3Rep3ActionPerformed);

        jLabel38.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel38.setText("jLabel1");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear3Rep3))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear3Rep3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear3Rep3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear3Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear3Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear3Rep3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel46);
        jPanel46.setBounds(680, 3900, 210, 270);

        jPanel47.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel47);
        jPanel47.setBounds(110, 4220, 780, 15);

        lblCandidateName19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCandidateName19.setForeground(new java.awt.Color(255, 255, 255));
        lblCandidateName19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateName19.setText("3rd Year Representative");
        lblCandidateName19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCandidateName19MouseMoved(evt);
            }
        });
        jPanel10.add(lblCandidateName19);
        lblCandidateName19.setBounds(110, 3840, 210, 32);

        jPanel48.setBackground(new java.awt.Color(0, 153, 51));

        lblYear4Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear4Rep1.setForeground(new java.awt.Color(255, 255, 255));
        lblYear4Rep1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear4Rep1.setText("CATHY RAMOS");
        lblYear4Rep1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear4Rep1MouseMoved(evt);
            }
        });

        btnViewYear4Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear4Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear4Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear4Rep1.setText("View details");
        btnViewYear4Rep1.addActionListener(this::btnViewYear4Rep1ActionPerformed);

        btnVoteYear4Rep1.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear4Rep1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear4Rep1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear4Rep1.setText("VOTE");
        btnVoteYear4Rep1.addActionListener(this::btnVoteYear4Rep1ActionPerformed);

        jLabel39.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel39.setText("jLabel1");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear4Rep1))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear4Rep1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear4Rep1))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear4Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear4Rep1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear4Rep1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel48);
        jPanel48.setBounds(110, 4330, 210, 270);

        jPanel49.setBackground(new java.awt.Color(0, 153, 51));

        lblYear4Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear4Rep2.setForeground(new java.awt.Color(255, 255, 255));
        lblYear4Rep2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear4Rep2.setText("CATHY RAMOS");
        lblYear4Rep2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear4Rep2MouseMoved(evt);
            }
        });

        btnViewYear4Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear4Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear4Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear4Rep2.setText("View details");
        btnViewYear4Rep2.addActionListener(this::btnViewYear4Rep2ActionPerformed);

        btnVoteYear4Rep2.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear4Rep2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear4Rep2.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear4Rep2.setText("VOTE");
        btnVoteYear4Rep2.addActionListener(this::btnVoteYear4Rep2ActionPerformed);

        jLabel40.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel40.setText("jLabel1");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear4Rep2))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear4Rep2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear4Rep2))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear4Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear4Rep2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear4Rep2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel49);
        jPanel49.setBounds(390, 4330, 210, 270);

        jPanel50.setBackground(new java.awt.Color(0, 153, 51));

        lblYear4Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblYear4Rep3.setForeground(new java.awt.Color(255, 255, 255));
        lblYear4Rep3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear4Rep3.setText("CATHY RAMOS");
        lblYear4Rep3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblYear4Rep3MouseMoved(evt);
            }
        });

        btnViewYear4Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnViewYear4Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnViewYear4Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewYear4Rep3.setText("View details");
        btnViewYear4Rep3.addActionListener(this::btnViewYear4Rep3ActionPerformed);

        btnVoteYear4Rep3.setBackground(new java.awt.Color(0, 102, 51));
        btnVoteYear4Rep3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoteYear4Rep3.setForeground(new java.awt.Color(255, 255, 255));
        btnVoteYear4Rep3.setText("VOTE");
        btnVoteYear4Rep3.addActionListener(this::btnVoteYear4Rep3ActionPerformed);

        jLabel41.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\profile-icon-design-free-vector (1).jpg")); // NOI18N
        jLabel41.setText("jLabel1");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnVoteYear4Rep3))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear4Rep3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewYear4Rep3))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYear4Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewYear4Rep3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteYear4Rep3)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel50);
        jPanel50.setBounds(680, 4330, 210, 270);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 5273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1247, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (choice == JOptionPane.YES_OPTION) {
        login loginPage = new login();
        loginPage.setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        contact home = new contact();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        votingrules home = new votingrules();

        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        about home = new about();
        home.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        candidates home = new candidates();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        homie home = new homie();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnVote2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVote2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVote2ActionPerformed

    private void btnViewDetails2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetails2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewDetails2ActionPerformed

    private void lblCandidateName2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName2MouseMoved

    private void btnVote12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVote12ActionPerformed
          int userId = UserSession.getUserId();
    int candidateId = vicePresidentCandidateIds[2];

    if (candidateId == -1) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "No Vice Governor candidate available."
        );
        return;
    }

    if (userId <= 0) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Please log in before voting."
        );
        return;
    }
    
    if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

    String sql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
            + "VALUES (?, ?, NOW())";

    try (java.sql.Connection conn = DBConnection.connect()) {
        if (conn == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Database connection failed."
            );
            return;
        }

        String checkSql =
                "SELECT COUNT(*) FROM votes v "
                + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                + "WHERE v.user_id = ? AND c.position = ?";

        try (java.sql.PreparedStatement check =
                conn.prepareStatement(checkSql)) {
            check.setInt(1, userId);
            check.setString(2, "Vice Governor");

            try (java.sql.ResultSet rs = check.executeQuery()) {
                rs.next();

                if (rs.getInt(1) > 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this, "You have already voted for Vice Governor."
                    );
                    return;
                }
            }
        }

        try (java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setInt(2, candidateId);
            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(
                this, "Your Vice Governor vote has been recorded!"
            );
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error recording vote: " + e.getMessage()
        );
    }

    }//GEN-LAST:event_btnVote12ActionPerformed

    private void btnViewDetails12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetails12ActionPerformed
       if (vicePresidentCandidateIds[2] != -1) {
    CandidateDetails details =
        new CandidateDetails(vicePresidentCandidateIds[2]);
    details.setVisible(true);
    this.dispose();
}
    }//GEN-LAST:event_btnViewDetails12ActionPerformed

    private void lblCandidateName15MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName15MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName15MouseMoved

    private void btnVote11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVote11ActionPerformed
           int userId = UserSession.getUserId();
    int candidateId = vicePresidentCandidateIds[1];

    if (candidateId == -1) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "No Vice Governor candidate available."
        );
        return;
    }

    if (userId <= 0) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Please log in before voting."
        );
        return;
    }
    
    if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

    String sql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
            + "VALUES (?, ?, NOW())";

    try (java.sql.Connection conn = DBConnection.connect()) {
        if (conn == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Database connection failed."
            );
            return;
        }

        String checkSql =
                "SELECT COUNT(*) FROM votes v "
                + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                + "WHERE v.user_id = ? AND c.position = ?";

        try (java.sql.PreparedStatement check =
                conn.prepareStatement(checkSql)) {
            check.setInt(1, userId);
            check.setString(2, "Vice Governor");

            try (java.sql.ResultSet rs = check.executeQuery()) {
                rs.next();

                if (rs.getInt(1) > 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this, "You have already voted for Vice Governor."
                    );
                    return;
                }
            }
        }

        try (java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setInt(2, candidateId);
            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(
                this, "Your Vice Governor vote has been recorded!"
            );
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error recording vote: " + e.getMessage()
        );
    }

    }//GEN-LAST:event_btnVote11ActionPerformed

    private void btnViewDetails11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetails11ActionPerformed
        if (vicePresidentCandidateIds[1] != -1) {
    CandidateDetails details =
        new CandidateDetails(vicePresidentCandidateIds[1]);
    details.setVisible(true);
    this.dispose();
}
    }//GEN-LAST:event_btnViewDetails11ActionPerformed

    private void lblCandidateName14MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName14MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName14MouseMoved

    private void btnVoteVP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteVP1ActionPerformed
            int userId = UserSession.getUserId();
    int candidateId = vicePresidentCandidateIds[0];

    if (candidateId == -1) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "No Vice Governor candidate available."
        );
        return;
    }

    if (userId <= 0) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Please log in before voting."
        );
        return;
    }
    
    if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

    String sql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
            + "VALUES (?, ?, NOW())";

    try (java.sql.Connection conn = DBConnection.connect()) {
        if (conn == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Database connection failed."
            );
            return;
        }

        String checkSql =
                "SELECT COUNT(*) FROM votes v "
                + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                + "WHERE v.user_id = ? AND c.position = ?";

        try (java.sql.PreparedStatement check =
                conn.prepareStatement(checkSql)) {
            check.setInt(1, userId);
            check.setString(2, "Vice Governor");

            try (java.sql.ResultSet rs = check.executeQuery()) {
                rs.next();

                if (rs.getInt(1) > 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this, "You have already voted for Vice Governor."
                    );
                    return;
                }
            }
        }

        try (java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setInt(2, candidateId);
            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(
                this, "Your Vice Governor vote has been recorded!"
            );
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Error recording vote: " + e.getMessage()
        );
    }

    }//GEN-LAST:event_btnVoteVP1ActionPerformed

    private void btnViewVP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewVP1ActionPerformed
       if (vicePresidentCandidateIds[0] != -1) {
    CandidateDetails details =
        new CandidateDetails(vicePresidentCandidateIds[0]);
    details.setVisible(true);
    this.dispose();
}
    }//GEN-LAST:event_btnViewVP1ActionPerformed

    private void lblVicePresident1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVicePresident1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblVicePresident1MouseMoved

    private void btnVote9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVote9ActionPerformed

int userId = UserSession.getUserId();
int candidateId = presidentCandidateIds[0];

if (candidateId == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "No Governor candidate available."
    );
    return;
}

if (userId <= 0) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Please log in before voting."
    );
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

String sql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
           + "VALUES (?, ?, NOW())";

try (java.sql.Connection conn = DBConnection.connect()) {

    if (conn == null) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Database connection failed."
        );
        return;
    }

    // STEP 1: Check whether voting is currently open
    String statusSql =
        "SELECT election_status FROM election_settings "
      + "WHERE setting_id = 1";

    try (java.sql.PreparedStatement statusCheck =
             conn.prepareStatement(statusSql);
         java.sql.ResultSet statusRs = statusCheck.executeQuery()) {

        if (!statusRs.next()
                || !"Ongoing".equalsIgnoreCase(
                    statusRs.getString("election_status").trim())) {

            javax.swing.JOptionPane.showMessageDialog(
                this, "Voting is currently closed."
            );
            return;
        }
    }

    // STEP 2: Check whether the student already voted
    String checkSql =
        "SELECT COUNT(*) FROM votes v "
      + "JOIN candidates c ON v.candidate_id = c.candidate_id "
      + "WHERE v.user_id = ? AND c.position = ?";

    try (java.sql.PreparedStatement check =
             conn.prepareStatement(checkSql)) {

        check.setInt(1, userId);
        check.setString(2, "Governor");

        try (java.sql.ResultSet rs = check.executeQuery()) {
            rs.next();

            if (rs.getInt(1) > 0) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "You have already voted for Governor."
                );
                return;
            }
        }
    }

    // STEP 3: Record the vote only when voting is ongoing
    try (java.sql.PreparedStatement pst =
             conn.prepareStatement(sql)) {

        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        javax.swing.JOptionPane.showMessageDialog(
            this, "Your vote has been recorded!"
        );
    }

} catch (java.sql.SQLException e) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting error: " + e.getMessage()
    );
    
    
}

    }//GEN-LAST:event_btnVote9ActionPerformed

    private void btnViewDetails9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetails9ActionPerformed
       int candidateId = presidentCandidateIds[0];

if (candidateId == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "No President candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnViewDetails9ActionPerformed

    private void lblCandidateName12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName12MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName12MouseMoved

    private void btnVote8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVote8ActionPerformed
        int userId = UserSession.getUserId();
        int candidateId = presidentCandidateIds[2];

if (candidateId == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "No Governor candidate available."
    );
    return;
}

        if (userId <= 0) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Please log in before voting."
            );
            return;
        }
        
        if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

        String sql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
        + "VALUES (?, ?, NOW())";

        try (java.sql.Connection conn = DBConnection.connect()) {
            if (conn == null) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "Database connection failed."
                );
                return;
            }

            String checkSql =
            "SELECT COUNT(*) FROM votes v "
            + "JOIN candidates c ON v.candidate_id = c.candidate_id "
            + "WHERE v.user_id = ? AND c.position = ?";

            try (java.sql.PreparedStatement check =
                conn.prepareStatement(checkSql)) {
                check.setInt(1, userId);
                check.setString(2, "Governor");

                try (java.sql.ResultSet rs = check.executeQuery()) {
                    rs.next();

                    if (rs.getInt(1) > 0) {
                        javax.swing.JOptionPane.showMessageDialog(
                            this, "You have already voted for Governor."
                        );
                        return;
                    }
                }
            }

            try (java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, userId);
                pst.setInt(2, candidateId);
                pst.executeUpdate();

                javax.swing.JOptionPane.showMessageDialog(
                    this, "Your vote has been recorded!"
                );
            }

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Voting error: " + e.getMessage()
            );

        }

    }//GEN-LAST:event_btnVote8ActionPerformed

    private void btnViewDetails8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetails8ActionPerformed
        int candidateId = presidentCandidateIds[2];

if (candidateId == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "No President candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnViewDetails8ActionPerformed

    private void lblCandidateName11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName11MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName11MouseMoved

    private void lblCandidateName6MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName6MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName6MouseMoved

    private void lblCandidateName5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName5MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName5MouseMoved

    private void lblCandidateName4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName4MouseMoved

    private void btnVote1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVote1ActionPerformed
        int userId = UserSession.getUserId();
        int candidateId = presidentCandidateIds[1];

if (candidateId == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "No Governor candidate available."
    );
    return;
} // First candidate for testing

        if (userId <= 0) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Please log in before voting."
            );
            return;
        }
        
        if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

        String sql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
        + "VALUES (?, ?, NOW())";

        try (java.sql.Connection conn = DBConnection.connect()) {
            if (conn == null) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "Database connection failed."
                );
                return;
            }

            String checkSql =
            "SELECT COUNT(*) FROM votes v "
            + "JOIN candidates c ON v.candidate_id = c.candidate_id "
            + "WHERE v.user_id = ? AND c.position = ?";

            try (java.sql.PreparedStatement check =
                conn.prepareStatement(checkSql)) {
                check.setInt(1, userId);
                check.setString(2, "Governor");

                try (java.sql.ResultSet rs = check.executeQuery()) {
                    rs.next();

                    if (rs.getInt(1) > 0) {
                        javax.swing.JOptionPane.showMessageDialog(
                            this, "You have already voted for Governor."
                        );
                        return;
                    }
                }
            }

            try (java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, userId);
                pst.setInt(2, candidateId);
                pst.executeUpdate();

                javax.swing.JOptionPane.showMessageDialog(
                    this, "Your vote has been recorded!"
                );
            }

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Voting error: " + e.getMessage()
            );

        }
    }//GEN-LAST:event_btnVote1ActionPerformed

    private void btnViewDetails1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetails1ActionPerformed
       int candidateId = presidentCandidateIds[1];

if (candidateId == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "No President candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnViewDetails1ActionPerformed

    private void lblCandidateName1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName1MouseMoved

    private void lblCandidateName7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName7MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName7MouseMoved

    private void lblSecretary1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSecretary1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSecretary1MouseMoved

    private void btnViewSecretary1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSecretary1ActionPerformed
        int candidateId = secretaryCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Secretary candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewSecretary1ActionPerformed

    private void btnVoteSecretary1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteSecretary1ActionPerformed
       
int candidateId = secretaryCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Secretary candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Secretary");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Secretary."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Secretary has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteSecretary1ActionPerformed

    private void lblSecretary2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSecretary2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSecretary2MouseMoved

    private void btnViewSecretary2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSecretary2ActionPerformed
        int candidateId = secretaryCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Secretary candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewSecretary2ActionPerformed

    private void btnVoteSecretary2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteSecretary2ActionPerformed
       
int candidateId = secretaryCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Secretary candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Secretary");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Secretary."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Secretary has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteSecretary2ActionPerformed

    private void lblSecretary3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSecretary3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSecretary3MouseMoved

    private void btnViewSecretary3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSecretary3ActionPerformed
        int candidateId = secretaryCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Secretary candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewSecretary3ActionPerformed

    private void btnVoteSecretary3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteSecretary3ActionPerformed
        
int candidateId = secretaryCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Secretary candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Secretary");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Secretary."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Secretary has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteSecretary3ActionPerformed

    private void lblCandidateName8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName8MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName8MouseMoved

    private void lblTreasurer1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTreasurer1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTreasurer1MouseMoved

    private void btnViewTreasurer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTreasurer1ActionPerformed
       int candidateId = treasurerCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(
        this, "No Treasurer candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewTreasurer1ActionPerformed

    private void btnVoteTreasurer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteTreasurer1ActionPerformed
       
int candidateId = treasurerCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Treasurer candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Treasurer");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Treasurer."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Treasurer has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteTreasurer1ActionPerformed

    private void lblTreasurer2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTreasurer2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTreasurer2MouseMoved

    private void btnViewTreasurer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTreasurer2ActionPerformed
        int candidateId = treasurerCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(
        this, "No Treasurer candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewTreasurer2ActionPerformed

    private void btnVoteTreasurer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteTreasurer2ActionPerformed
        
int candidateId = treasurerCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Treasurer candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Treasurer");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Treasurer."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Treasurer has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteTreasurer2ActionPerformed

    private void lblTreasurer3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTreasurer3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTreasurer3MouseMoved

    private void btnViewTreasurer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTreasurer3ActionPerformed
        int candidateId = treasurerCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(
        this, "No Treasurer candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewTreasurer3ActionPerformed

    private void btnVoteTreasurer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteTreasurer3ActionPerformed
        
int candidateId = treasurerCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Treasurer candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Treasurer");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Treasurer."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Treasurer has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteTreasurer3ActionPerformed

    private void lblCandidateName9MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName9MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName9MouseMoved

    private void lblAuditor1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAuditor1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAuditor1MouseMoved

    private void btnViewAuditor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAuditor1ActionPerformed
      int candidateId = auditorCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(
        this, "No Auditor candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewAuditor1ActionPerformed

    private void btnVoteAuditor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteAuditor1ActionPerformed
          
int candidateId = auditorCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Auditor candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Auditor");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Auditor."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Auditor has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}
        
    }//GEN-LAST:event_btnVoteAuditor1ActionPerformed

    private void lblAuditor2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAuditor2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAuditor2MouseMoved

    private void btnViewAuditor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAuditor2ActionPerformed
        int candidateId = auditorCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(
        this, "No Auditor candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewAuditor2ActionPerformed

    private void btnVoteAuditor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteAuditor2ActionPerformed
      
int candidateId = auditorCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Auditor candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Auditor");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Auditor."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Auditor has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteAuditor2ActionPerformed

    private void lblAuditor3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAuditor3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAuditor3MouseMoved

    private void btnViewAuditor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAuditor3ActionPerformed
        int candidateId = auditorCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(
        this, "No Auditor candidate available."
    );
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();
    }//GEN-LAST:event_btnViewAuditor3ActionPerformed

    private void btnVoteAuditor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteAuditor3ActionPerformed
       
int candidateId = auditorCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Auditor candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Auditor");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Auditor."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Auditor has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteAuditor3ActionPerformed

    private void lblCandidateName10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName10MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName10MouseMoved

    private void lblInternalPIO1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInternalPIO1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblInternalPIO1MouseMoved

    private void btnViewInternalPIO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewInternalPIO1ActionPerformed
        
int candidateId = internalPIOCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewInternalPIO1ActionPerformed

    private void btnVoteInternalPIO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteInternalPIO1ActionPerformed
       
int candidateId = internalPIOCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Internal P.I.O. candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Internal P.I.O.");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Internal P.I.O."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Internal P.I.O. has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteInternalPIO1ActionPerformed

    private void lblInternalPIO2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInternalPIO2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblInternalPIO2MouseMoved

    private void btnViewInternalPIO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewInternalPIO2ActionPerformed
       
int candidateId = internalPIOCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewInternalPIO2ActionPerformed

    private void btnVoteInternalPIO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteInternalPIO2ActionPerformed
       
int candidateId = internalPIOCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Internal P.I.O. candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Internal P.I.O.");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Internal P.I.O."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Internal P.I.O. has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteInternalPIO2ActionPerformed

    private void lblInternalPIO3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInternalPIO3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblInternalPIO3MouseMoved

    private void btnViewInternalPIO3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewInternalPIO3ActionPerformed
        
int candidateId = internalPIOCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewInternalPIO3ActionPerformed

    private void btnVoteInternalPIO3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteInternalPIO3ActionPerformed
      
int candidateId = internalPIOCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Internal P.I.O. candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Internal P.I.O.");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Internal P.I.O."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Internal P.I.O. has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteInternalPIO3ActionPerformed

    private void lblCandidateName13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName13MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName13MouseMoved

    private void lblExternalPIO1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExternalPIO1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblExternalPIO1MouseMoved

    private void btnViewExternalPIO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewExternalPIO1ActionPerformed
       
int candidateId = externalPIOCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewExternalPIO1ActionPerformed

    private void btnVoteExternalPIO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteExternalPIO1ActionPerformed
       
int candidateId = externalPIOCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No External P.I.O. candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "External P.I.O.");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for External P.I.O."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for External P.I.O. has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteExternalPIO1ActionPerformed

    private void lblExternalPIO2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExternalPIO2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblExternalPIO2MouseMoved

    private void btnViewExternalPIO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewExternalPIO2ActionPerformed
       
int candidateId = externalPIOCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewExternalPIO2ActionPerformed

    private void btnVoteExternalPIO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteExternalPIO2ActionPerformed
        
int candidateId = externalPIOCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No External P.I.O. candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "External P.I.O.");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for External P.I.O."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for External P.I.O. has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteExternalPIO2ActionPerformed

    private void lblExternalPIO3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExternalPIO3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblExternalPIO3MouseMoved

    private void btnViewExternalPIO3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewExternalPIO3ActionPerformed
        
int candidateId = externalPIOCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewExternalPIO3ActionPerformed

    private void btnVoteExternalPIO3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteExternalPIO3ActionPerformed
        
int candidateId = externalPIOCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No External P.I.O. candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "External P.I.O.");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for External P.I.O."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for External P.I.O. has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteExternalPIO3ActionPerformed

    private void lblCandidateName16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName16MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName16MouseMoved

    private void lblYear1Rep1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear1Rep1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear1Rep1MouseMoved

    private void btnViewYear1Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear1Rep1ActionPerformed
       
int candidateId = year1RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear1Rep1ActionPerformed

    private void btnVoteYear1Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear1Rep1ActionPerformed
        
int candidateId = year1RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 1 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 1 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 1 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 1 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear1Rep1ActionPerformed

    private void lblYear1Rep2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear1Rep2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear1Rep2MouseMoved

    private void btnViewYear1Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear1Rep2ActionPerformed
       
int candidateId = year1RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear1Rep2ActionPerformed

    private void btnVoteYear1Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear1Rep2ActionPerformed
      
int candidateId = year1RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 1 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 1 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 1 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 1 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear1Rep2ActionPerformed

    private void lblYear1Rep3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear1Rep3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear1Rep3MouseMoved

    private void btnViewYear1Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear1Rep3ActionPerformed
        
int candidateId = year1RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear1Rep3ActionPerformed

    private void btnVoteYear1Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear1Rep3ActionPerformed
       
int candidateId = year1RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 1 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 1 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 1 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 1 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear1Rep3ActionPerformed

    private void lblCandidateName17MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName17MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName17MouseMoved

    private void lblYear2Rep1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear2Rep1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear2Rep1MouseMoved

    private void btnViewYear2Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear2Rep1ActionPerformed
       
int candidateId = year2RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear2Rep1ActionPerformed

    private void btnVoteYear2Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear2Rep1ActionPerformed
        
int candidateId = year2RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 2 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 2 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 2 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 2 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear2Rep1ActionPerformed

    private void lblYear2Rep2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear2Rep2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear2Rep2MouseMoved

    private void btnViewYear2Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear2Rep2ActionPerformed
       
int candidateId = year2RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear2Rep2ActionPerformed

    private void btnVoteYear2Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear2Rep2ActionPerformed
       
int candidateId = year2RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 2 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 2 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 2 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 2 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear2Rep2ActionPerformed

    private void lblYear2Rep3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear2Rep3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear2Rep3MouseMoved

    private void btnViewYear2Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear2Rep3ActionPerformed
        
int candidateId = year2RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear2Rep3ActionPerformed

    private void btnVoteYear2Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear2Rep3ActionPerformed
       int candidateId = year2RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 2 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 2 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 2 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 2 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}
    }//GEN-LAST:event_btnVoteYear2Rep3ActionPerformed

    private void lblCandidateName18MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName18MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName18MouseMoved

    private void lblYear3Rep1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear3Rep1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear3Rep1MouseMoved

    private void btnViewYear3Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear3Rep1ActionPerformed
      
int candidateId = year3RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear3Rep1ActionPerformed

    private void btnVoteYear3Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear3Rep1ActionPerformed
       
int candidateId = year3RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 3 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 3 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 3 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 3 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear3Rep1ActionPerformed

    private void lblYear3Rep2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear3Rep2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear3Rep2MouseMoved

    private void btnViewYear3Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear3Rep2ActionPerformed
       
int candidateId = year3RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear3Rep2ActionPerformed

    private void btnVoteYear3Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear3Rep2ActionPerformed
       
int candidateId = year3RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 3 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 3 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 3 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 3 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear3Rep2ActionPerformed

    private void lblYear3Rep3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear3Rep3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear3Rep3MouseMoved

    private void btnViewYear3Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear3Rep3ActionPerformed
       
int candidateId = year3RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear3Rep3ActionPerformed

    private void btnVoteYear3Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear3Rep3ActionPerformed
       
int candidateId = year3RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 3 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 3 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 3 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 3 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear3Rep3ActionPerformed

    private void lblCandidateName19MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCandidateName19MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCandidateName19MouseMoved

    private void lblYear4Rep1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear4Rep1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear4Rep1MouseMoved

    private void btnViewYear4Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear4Rep1ActionPerformed
       
int candidateId = year4RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear4Rep1ActionPerformed

    private void btnVoteYear4Rep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear4Rep1ActionPerformed
       
int candidateId = year4RepCandidateIds[0];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 4 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 4 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 4 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 4 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear4Rep1ActionPerformed

    private void lblYear4Rep2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear4Rep2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear4Rep2MouseMoved

    private void btnViewYear4Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear4Rep2ActionPerformed
       
int candidateId = year4RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear4Rep2ActionPerformed

    private void btnVoteYear4Rep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear4Rep2ActionPerformed
       
int candidateId = year4RepCandidateIds[1];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 4 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 4 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 4 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 4 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear4Rep2ActionPerformed

    private void lblYear4Rep3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYear4Rep3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblYear4Rep3MouseMoved

    private void btnViewYear4Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewYear4Rep3ActionPerformed
        
int candidateId = year4RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No candidate available.");
    return;
}

CandidateDetails details = new CandidateDetails(candidateId);
details.setVisible(true);
dispose();

    }//GEN-LAST:event_btnViewYear4Rep3ActionPerformed

    private void btnVoteYear4Rep3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteYear4Rep3ActionPerformed
       
int candidateId = year4RepCandidateIds[2];

if (candidateId == -1) {
    JOptionPane.showMessageDialog(this, "No Year 4 Representative candidate available.");
    return;
}

int userId = UserSession.getUserId();

if (userId <= 0) {
    JOptionPane.showMessageDialog(this, "Please log in first.");
    return;
}

if (!isElectionOngoing()) {
    javax.swing.JOptionPane.showMessageDialog(
        this, "Voting is currently closed."
    );
    return;
}

try (Connection con = DBConnection.connect()) {

    String checkSql = "SELECT COUNT(*) FROM votes v "
                    + "JOIN candidates c ON v.candidate_id = c.candidate_id "
                    + "WHERE v.user_id = ? AND c.position = ?";

    try (PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, userId);
        check.setString(2, "Year 4 Representative");

        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(
                    this, "You have already voted for Year 4 Representative."
                );
                return;
            }
        }
    }

    String insertSql = "INSERT INTO votes (user_id, candidate_id, vote_date) "
                     + "VALUES (?, ?, NOW())";

    try (PreparedStatement pst = con.prepareStatement(insertSql)) {
        pst.setInt(1, userId);
        pst.setInt(2, candidateId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Your vote for Year 4 Representative has been recorded!"
        );
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(
        this, "Error recording vote: " + e.getMessage()
    );
}

    }//GEN-LAST:event_btnVoteYear4Rep3ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new candidates().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnViewAuditor1;
    private javax.swing.JButton btnViewAuditor2;
    private javax.swing.JButton btnViewAuditor3;
    private javax.swing.JButton btnViewDetails1;
    private javax.swing.JButton btnViewDetails11;
    private javax.swing.JButton btnViewDetails12;
    private javax.swing.JButton btnViewDetails2;
    private javax.swing.JButton btnViewDetails8;
    private javax.swing.JButton btnViewDetails9;
    private javax.swing.JButton btnViewExternalPIO1;
    private javax.swing.JButton btnViewExternalPIO2;
    private javax.swing.JButton btnViewExternalPIO3;
    private javax.swing.JButton btnViewInternalPIO1;
    private javax.swing.JButton btnViewInternalPIO2;
    private javax.swing.JButton btnViewInternalPIO3;
    private javax.swing.JButton btnViewSecretary1;
    private javax.swing.JButton btnViewSecretary2;
    private javax.swing.JButton btnViewSecretary3;
    private javax.swing.JButton btnViewTreasurer1;
    private javax.swing.JButton btnViewTreasurer2;
    private javax.swing.JButton btnViewTreasurer3;
    private javax.swing.JButton btnViewVP1;
    private javax.swing.JButton btnViewYear1Rep1;
    private javax.swing.JButton btnViewYear1Rep2;
    private javax.swing.JButton btnViewYear1Rep3;
    private javax.swing.JButton btnViewYear2Rep1;
    private javax.swing.JButton btnViewYear2Rep2;
    private javax.swing.JButton btnViewYear2Rep3;
    private javax.swing.JButton btnViewYear3Rep1;
    private javax.swing.JButton btnViewYear3Rep2;
    private javax.swing.JButton btnViewYear3Rep3;
    private javax.swing.JButton btnViewYear4Rep1;
    private javax.swing.JButton btnViewYear4Rep2;
    private javax.swing.JButton btnViewYear4Rep3;
    private javax.swing.JButton btnVote1;
    private javax.swing.JButton btnVote11;
    private javax.swing.JButton btnVote12;
    private javax.swing.JButton btnVote2;
    private javax.swing.JButton btnVote8;
    private javax.swing.JButton btnVote9;
    private javax.swing.JButton btnVoteAuditor1;
    private javax.swing.JButton btnVoteAuditor2;
    private javax.swing.JButton btnVoteAuditor3;
    private javax.swing.JButton btnVoteExternalPIO1;
    private javax.swing.JButton btnVoteExternalPIO2;
    private javax.swing.JButton btnVoteExternalPIO3;
    private javax.swing.JButton btnVoteInternalPIO1;
    private javax.swing.JButton btnVoteInternalPIO2;
    private javax.swing.JButton btnVoteInternalPIO3;
    private javax.swing.JButton btnVoteSecretary1;
    private javax.swing.JButton btnVoteSecretary2;
    private javax.swing.JButton btnVoteSecretary3;
    private javax.swing.JButton btnVoteTreasurer1;
    private javax.swing.JButton btnVoteTreasurer2;
    private javax.swing.JButton btnVoteTreasurer3;
    private javax.swing.JButton btnVoteVP1;
    private javax.swing.JButton btnVoteYear1Rep1;
    private javax.swing.JButton btnVoteYear1Rep2;
    private javax.swing.JButton btnVoteYear1Rep3;
    private javax.swing.JButton btnVoteYear2Rep1;
    private javax.swing.JButton btnVoteYear2Rep2;
    private javax.swing.JButton btnVoteYear2Rep3;
    private javax.swing.JButton btnVoteYear3Rep1;
    private javax.swing.JButton btnVoteYear3Rep2;
    private javax.swing.JButton btnVoteYear3Rep3;
    private javax.swing.JButton btnVoteYear4Rep1;
    private javax.swing.JButton btnVoteYear4Rep2;
    private javax.swing.JButton btnVoteYear4Rep3;
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAuditor1;
    private javax.swing.JLabel lblAuditor2;
    private javax.swing.JLabel lblAuditor3;
    private javax.swing.JLabel lblCandidateName1;
    private javax.swing.JLabel lblCandidateName10;
    private javax.swing.JLabel lblCandidateName11;
    private javax.swing.JLabel lblCandidateName12;
    private javax.swing.JLabel lblCandidateName13;
    private javax.swing.JLabel lblCandidateName14;
    private javax.swing.JLabel lblCandidateName15;
    private javax.swing.JLabel lblCandidateName16;
    private javax.swing.JLabel lblCandidateName17;
    private javax.swing.JLabel lblCandidateName18;
    private javax.swing.JLabel lblCandidateName19;
    private javax.swing.JLabel lblCandidateName2;
    private javax.swing.JLabel lblCandidateName4;
    private javax.swing.JLabel lblCandidateName5;
    private javax.swing.JLabel lblCandidateName6;
    private javax.swing.JLabel lblCandidateName7;
    private javax.swing.JLabel lblCandidateName8;
    private javax.swing.JLabel lblCandidateName9;
    private javax.swing.JLabel lblExternalPIO1;
    private javax.swing.JLabel lblExternalPIO2;
    private javax.swing.JLabel lblExternalPIO3;
    private javax.swing.JLabel lblInternalPIO1;
    private javax.swing.JLabel lblInternalPIO2;
    private javax.swing.JLabel lblInternalPIO3;
    private javax.swing.JLabel lblPosition2;
    private javax.swing.JLabel lblSecretary1;
    private javax.swing.JLabel lblSecretary2;
    private javax.swing.JLabel lblSecretary3;
    private javax.swing.JLabel lblTreasurer1;
    private javax.swing.JLabel lblTreasurer2;
    private javax.swing.JLabel lblTreasurer3;
    private javax.swing.JLabel lblVicePresident1;
    private javax.swing.JLabel lblYear1Rep1;
    private javax.swing.JLabel lblYear1Rep2;
    private javax.swing.JLabel lblYear1Rep3;
    private javax.swing.JLabel lblYear2Rep1;
    private javax.swing.JLabel lblYear2Rep2;
    private javax.swing.JLabel lblYear2Rep3;
    private javax.swing.JLabel lblYear3Rep1;
    private javax.swing.JLabel lblYear3Rep2;
    private javax.swing.JLabel lblYear3Rep3;
    private javax.swing.JLabel lblYear4Rep1;
    private javax.swing.JLabel lblYear4Rep2;
    private javax.swing.JLabel lblYear4Rep3;
    // End of variables declaration//GEN-END:variables
}
