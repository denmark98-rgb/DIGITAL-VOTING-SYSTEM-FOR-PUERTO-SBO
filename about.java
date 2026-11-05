package votingsystem;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class about extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(about.class.getName());

    private static final int HEADER_BASE = 91;

    private java.awt.Component[] heroItems;
    private java.awt.Rectangle[] heroOrig;
    private java.awt.Font[] heroFonts;

    private javax.swing.JButton[] navButtons;
    private final int[] navWidths = {60, 90, 60, 100, 70, 90};
    private java.awt.Font navFont, titleFont;

    private javax.swing.JLabel[] iconLabels;
    private javax.swing.Icon[] iconOrig;

    public about() {
        initComponents();
        setLocationRelativeTo(null);

        // ----- hero items (everything inside the green panel) -----
        heroItems = new java.awt.Component[] {
            jLabel29, jLabel30, jLabel31, jLabel26, jLabel24, jLabel32,
            jLabel33, jLabel34, jLabel35, jLabel36, jLabel25, jButton15
        };
        heroOrig = new java.awt.Rectangle[heroItems.length];
        heroFonts = new java.awt.Font[heroItems.length];
        for (int i = 0; i < heroItems.length; i++) {
            heroOrig[i] = heroItems[i].getBounds();
            heroFonts[i] = heroItems[i].getFont();
        }

        // ----- header -----
        navButtons = new javax.swing.JButton[] {
            jButton2, jButton3, jButton4, jButton5, jButton6, jButton1
        };
        navFont = jButton2.getFont();
        titleFont = jLabel3.getFont();

        // ----- images: remember the originals so they can be scaled -----
        iconLabels = new javax.swing.JLabel[] { jLabel30, jLabel2 };
        iconOrig = new javax.swing.Icon[iconLabels.length];
        for (int i = 0; i < iconLabels.length; i++) {
            iconOrig[i] = iconLabels[i].getIcon();
            iconLabels[i].setText("");
        }

        // small polish on the "Learn More" button
        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setForeground(new java.awt.Color(0, 102, 51));
        jButton15.setFocusPainted(false);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
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
        int W = jPanel1.getWidth();
        int H = jPanel1.getHeight();
        if (W <= 0 || H <= 0) return;

        // scale factor based on the original 960 x 540 design
        double s = Math.min(W / 960.0, H / 540.0);
        s = Math.max(1.0, Math.min(s, 2.5));

        int header = (int) (HEADER_BASE * s);

        jPanel2.setBounds(0, header, W, H - header);

        // ----- header: logo + title on the left -----
        int logoW = (int) (61 * s), logoH = (int) (59 * s);
        int logoX = (int) (25 * s);
        jLabel2.setBounds(logoX, (header - logoH) / 2, logoW, logoH);

        jLabel3.setFont(titleFont.deriveFont((float) (titleFont.getSize2D() * s)));
        int titleH = (int) (30 * s);
        jLabel3.setBounds(logoX + logoW + (int) (10 * s), (header - titleH) / 2,
                          (int) (260 * s), titleH);

        // ----- header: nav buttons pinned to the right -----
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

        // ----- hero: scale the group, then center it -----
        int gx0 = 60, gy0 = 30;                 // top-left of the original group
        double groupW = (940 - 60) * s;         // original group: x 60..940
        double groupH = (383 - 30) * s;         // original group: y 30..383
        double offX = (W - groupW) / 2;
        double offY = (H - header - groupH) / 2;

        for (int i = 0; i < heroItems.length; i++) {
            java.awt.Rectangle r = heroOrig[i];
            int nx = (int) ((r.x - gx0) * s + offX);
            int ny = (int) ((r.y - gy0) * s + offY);
            int nw = (int) (r.width * s);
            int nh = (int) (r.height * s);

            // text labels get a little extra width so nothing turns into "..."
            if (heroItems[i] instanceof javax.swing.JLabel) {
                String t = ((javax.swing.JLabel) heroItems[i]).getText();
                if (t != null && !t.isEmpty()) nw += 30;
            }

            heroItems[i].setFont(heroFonts[i].deriveFont((float) (heroFonts[i].getSize2D() * s)));
            heroItems[i].setBounds(nx, ny, nw, nh);
        }

        // ----- scale the images too -----
        for (int i = 0; i < iconLabels.length; i++) {
            if (iconOrig[i] != null) {
                iconLabels[i].setIcon(new ScaledIcon(iconOrig[i], s));
            }
        }

        jPanel1.revalidate();
        jPanel1.repaint();
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

    /**
     * NOTE: this form was cleaned up by hand (duplicate header panels/buttons removed).
     * Don't edit it in the NetBeans Design tab, or the Form Editor may regenerate
     * the old layout over it.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        jLabel29.setBackground(new java.awt.Color(0, 102, 51));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("About this website");
        jPanel2.add(jLabel29);
        jLabel29.setBounds(480, 30, 343, 48);

        jLabel30.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\tao.jpe")); // NOI18N
        jLabel30.setText("jLabel30");
        jPanel2.add(jLabel30);
        jLabel30.setBounds(60, 80, 370, 240);

        jLabel31.setBackground(new java.awt.Color(0, 102, 51));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("This website voting platform allows students of Phinma Cagayan");
        jPanel2.add(jLabel31);
        jLabel31.setBounds(480, 100, 460, 20);

        jLabel26.setBackground(new java.awt.Color(0, 102, 51));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("de Oro College to participate in campus elections easily and ");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(480, 120, 460, 20);

        jLabel24.setBackground(new java.awt.Color(0, 102, 51));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("securely.");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(480, 140, 460, 20);

        jLabel32.setBackground(new java.awt.Color(0, 102, 51));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("The website provides a simple and transparent way to cast votes");
        jPanel2.add(jLabel32);
        jLabel32.setBounds(480, 180, 460, 20);

        jLabel33.setBackground(new java.awt.Color(0, 102, 51));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("for students leader, ensuring every eligible student can have their");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(480, 200, 460, 20);

        jLabel34.setBackground(new java.awt.Color(0, 102, 51));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("voice without complication.");
        jPanel2.add(jLabel34);
        jLabel34.setBounds(480, 220, 460, 20);

        jLabel35.setBackground(new java.awt.Color(0, 102, 51));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Designed with user-friendliness in mind, it offers clear navigation,");
        jPanel2.add(jLabel35);
        jLabel35.setBounds(480, 260, 460, 20);

        jLabel36.setBackground(new java.awt.Color(0, 102, 51));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("candidate information, and a smooth voting experience for the");
        jPanel2.add(jLabel36);
        jLabel36.setBounds(480, 280, 460, 20);

        jLabel25.setBackground(new java.awt.Color(0, 102, 51));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("whole student body.");
        jPanel2.add(jLabel25);
        jLabel25.setBounds(480, 300, 460, 20);

        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setText("Learn More");
        jButton15.setOpaque(true);
        jButton15.addActionListener(this::jButton15ActionPerformed);
        jPanel2.add(jButton15);
        jButton15.setBounds(630, 360, 140, 23);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 91, 970, 441);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\ChatGPT Image Sep 25, 2026, 04_35_52 PM (2).png")); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(17, 20, 61, 59);

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(84, 34, 230, 25);

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Log Out");
        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel1.add(jButton1);
        jButton1.setBounds(860, 40, 90, 30);

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Home");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);
        jButton2.setBounds(430, 40, 60, 30);

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Candidates");
        jButton3.setBorder(null);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel1.add(jButton3);
        jButton3.setBounds(500, 40, 90, 30);

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("About");
        jButton4.setBorder(null);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel1.add(jButton4);
        jButton4.setBounds(600, 40, 60, 30);

        jButton5.setBackground(new java.awt.Color(0, 102, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Voting Rules");
        jButton5.setBorder(null);
        jButton5.addActionListener(this::jButton5ActionPerformed);
        jPanel1.add(jButton5);
        jButton5.setBounds(670, 40, 100, 30);

        jButton6.setBackground(new java.awt.Color(0, 102, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Contact");
        jButton6.setBorder(null);
        jButton6.addActionListener(this::jButton6ActionPerformed);
        jPanel1.add(jButton6);
        jButton6.setBounds(780, 40, 70, 30);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 988, 543);
    }// </editor-fold>

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        homie home = new homie();
        home.setVisible(true);
        this.dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        candidates home = new candidates();
        home.setVisible(true);
        this.dispose();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        about home = new about();
        home.setVisible(true);
        this.dispose();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        votingrules home = new votingrules();
        home.setVisible(true);
        this.dispose();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        contact home = new contact();
        home.setVisible(true);
        this.dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
      
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

    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new about().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration
}
