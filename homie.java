package votingsystem;

import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author user
 */
public class homie extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(homie.class.getName());
    
    private static final int HEADER_BASE = 91;

private java.awt.Component[] heroItems;
private java.awt.Rectangle[] heroOrig;
private java.awt.Font[] heroFonts;

private javax.swing.JButton[] navButtons;
private final int[] navWidths = {60, 90, 60, 100, 70, 90};
private java.awt.Font navFont, titleFont;

private javax.swing.JLabel[] iconLabels;
private javax.swing.Icon[] iconOrig;

public homie() {
    initComponents();
    setLocationRelativeTo(null);

    // ----- hero items (everything inside the green panel) -----
    heroItems = new java.awt.Component[] {
        jLabel9, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14,
        jLabel15, jLabel16, jLabel1, jLabel5, jLabel7, jLabel4,
        jLabel8, jButton7
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
    iconLabels = new javax.swing.JLabel[] {
        jLabel1, jLabel5, jLabel7, jLabel4, jLabel8, jLabel2
    };
    iconOrig = new javax.swing.Icon[iconLabels.length];
    for (int i = 0; i < iconLabels.length; i++) {
        iconOrig[i] = iconLabels[i].getIcon();
        iconLabels[i].setText("");
    }

    // small polish on the "Running candidates" button
    jButton7.setBackground(new java.awt.Color(255, 255, 255));
    jButton7.setForeground(new java.awt.Color(0, 102, 51));
    jButton7.setFocusPainted(false);

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

    // scale factor based on a 1280 x 720 design
 double s = Math.min(W / 960.0, H / 540.0);
s = Math.max(1.0, Math.min(s, 2.5));

    int header = (int) (HEADER_BASE * s);

    jPanel1.setBounds(0, 0, W, H);
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
    int gx0 = 39, gy0 = 50;                 // top-left of the original group
    double groupW = (870 - 39) * s;
    double groupH = (367 - 50) * s;
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

    getContentPane().revalidate();
    getContentPane().repaint();
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(0, 102, 51));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Website");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(39, 122, 170, 48);

        jLabel10.setBackground(new java.awt.Color(0, 102, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phinma COC Voting ");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(39, 68, 380, 48);

        jLabel11.setBackground(new java.awt.Color(0, 102, 51));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 255, 0));
        jLabel11.setText("Empowering Students - Shaping the Future");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(39, 190, 390, 20);

        jLabel12.setBackground(new java.awt.Color(0, 102, 51));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cast your vote responsibly and help build a better");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(39, 236, 360, 20);

        jLabel13.setBackground(new java.awt.Color(0, 102, 51));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("campus community. This website platform makes it");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(39, 262, 360, 20);

        jLabel14.setBackground(new java.awt.Color(0, 102, 51));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("easy and secure for every student to participate in");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(39, 288, 350, 20);

        jLabel15.setBackground(new java.awt.Color(0, 102, 51));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("campus elections and choose the leaders who will");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(39, 314, 350, 20);

        jLabel16.setBackground(new java.awt.Color(0, 102, 51));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("represent them.");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(39, 340, 120, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\asdd (2).png")); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(640, 50, 180, 290);

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\asdd (2).png")); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(660, 90, 190, 230);

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Running candidates");
        jButton7.addActionListener(this::jButton7ActionPerformed);
        jPanel2.add(jButton7);
        jButton7.setBounds(600, 340, 270, 27);

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\asdd (2).png")); // NOI18N
        jLabel7.setText("jLabel4");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(620, 90, 60, 230);

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\asdd (2).png")); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(810, 110, 60, 210);

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\asdd (2).png")); // NOI18N
        jLabel8.setText("jLabel4");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(600, 110, 60, 210);

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

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 970, 538);

        setBounds(0, 0, 988, 543);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        homie home = new homie();
           home.setVisible(true);
           this.dispose();
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        candidates home = new candidates();
           home.setVisible(true);
           this.dispose();
            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        about home = new about();
           home.setVisible(true);
           this.dispose();
            
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       votingrules home = new votingrules();
               
           home.setVisible(true);
           this.dispose();
            
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       contact home = new contact();
           home.setVisible(true);
           this.dispose();
            
    }//GEN-LAST:event_jButton6ActionPerformed

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       candidates candidates = new candidates();
       candidates.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new homie().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
