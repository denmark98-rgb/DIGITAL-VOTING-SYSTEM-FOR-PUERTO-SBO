/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package votingsystem;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class contact extends javax.swing.JFrame {
    


    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(contact.class.getName());

    private javax.swing.JPanel root;
    private double s = 1.0;
    private javax.swing.Icon logoIcon;
    private final java.util.Map<javax.swing.JComponent, java.awt.Font> baseFonts =
            new java.util.IdentityHashMap<>();

    private javax.swing.JPanel[] cards;
    private javax.swing.JLabel[] titles, addr1, addr2, phones, mails;
    private javax.swing.JLabel[][] icons;
    private javax.swing.Icon[][] iconBases;
    private javax.swing.JButton[] navButtons;
    private final int[] navWidths = {60, 90, 60, 100, 70, 90};

    public contact() {
        initComponents();
        setLocationRelativeTo(null);
        buildFullScreen();
    }

    private int sc(double v) { return (int) Math.round(v * s); }

    private void buildFullScreen() {
        cards  = new javax.swing.JPanel[] { jPanel3, jPanel4, jPanel5 };
        titles = new javax.swing.JLabel[] { jLabel11, jLabel12, jLabel13 };
        addr1  = new javax.swing.JLabel[] { jLabel16, jLabel22, jLabel26 };
        addr2  = new javax.swing.JLabel[] { jLabel19, jLabel23, jLabel27 };
        phones = new javax.swing.JLabel[] { jLabel20, jLabel24, jLabel28 };
        mails  = new javax.swing.JLabel[] { jLabel21, jLabel25, jLabel29 };
        // order per card: location, phone, email
        icons  = new javax.swing.JLabel[][] {
            {jLabel1, jLabel7, jLabel6},
            {jLabel4, jLabel8, jLabel14},
            {jLabel5, jLabel9, jLabel15}
        };
        navButtons = new javax.swing.JButton[] { jButton2, jButton3, jButton4, jButton5, jButton6, jButton1 };

        iconBases = new javax.swing.Icon[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                iconBases[i][j] = icons[i][j].getIcon();
                icons[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            }

        jLabel2.setText("");
        logoIcon = jLabel2.getIcon();
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // cards: switch from GroupLayout to absolute so we can scale them
        for (int i = 0; i < cards.length; i++) {
            cards[i].setLayout(null);
            cards[i].add(titles[i]);
            cards[i].add(addr1[i]);
            cards[i].add(addr2[i]);
            cards[i].add(phones[i]);
            cards[i].add(mails[i]);
            for (javax.swing.JLabel ic : icons[i]) cards[i].add(ic);
        }

        // green body panel keeps its own null layout
        jPanel2.add(jLabel10);
        jPanel2.add(jPanel6);
        for (javax.swing.JPanel c : cards) jPanel2.add(c);

        root = new javax.swing.JPanel(null);
        root.setBackground(java.awt.Color.WHITE);
        root.add(jLabel2);
        root.add(jLabel3);
        for (javax.swing.JButton b : navButtons) root.add(b);
        root.add(jPanel2);

        java.util.List<javax.swing.JComponent> all = new java.util.ArrayList<>();
        all.add(jLabel3); all.add(jLabel10);
        all.addAll(java.util.Arrays.asList(navButtons));
        for (int i = 0; i < cards.length; i++) {
            all.add(titles[i]); all.add(addr1[i]); all.add(addr2[i]);
            all.add(phones[i]); all.add(mails[i]);
        }
        for (javax.swing.JComponent c : all) baseFonts.put(c, c.getFont());

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

        s = Math.max(1.0, Math.min(Math.min(W / 970.0, H / 540.0), 2.5));

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

        int btnH = sc(30), btnY = (header - btnH) / 2, gap = sc(10);
        int x = W - sc(40);
        for (int i = navButtons.length - 1; i >= 0; i--) {
            int bw = sc(navWidths[i]);
            x -= bw;
            navButtons[i].setBounds(x, btnY, bw, btnH);
            x -= gap;
        }

        // ---- green body ----
        int bodyH = H - header;
        jPanel2.setBounds(0, header, W, bodyH);
        int extra = Math.max(0, (bodyH - sc(441)) / 2);

        int tw = sc(343);
        jLabel10.setBounds((W - tw) / 2, sc(20) + extra, tw, sc(48));
        int uw = sc(210);
        jPanel6.setBounds((W - uw) / 2, sc(70) + extra, uw, sc(10));

        // ---- cards ----
        int cw = sc(270), ch = sc(260), cgap = sc(30);
        int total = cw * 3 + cgap * 2;
        int cx = (W - total) / 2;
        int cy = sc(120) + extra;
        int box = sc(37);
        int textX = sc(65), textW = cw - sc(75);

        for (int i = 0; i < cards.length; i++) {
            cards[i].setBounds(cx + i * (cw + cgap), cy, cw, ch);

            titles[i].setBounds(sc(20), sc(25), cw - sc(40), sc(28));

            // location (two text lines)
            icons[i][0].setBounds(sc(20), sc(75), box, box);
            addr1[i].setBounds(textX, sc(75), textW, sc(18));
            addr2[i].setBounds(textX, sc(94), textW, sc(18));

            // phone
            icons[i][1].setBounds(sc(20), sc(135), box, box);
            phones[i].setBounds(textX, sc(144), textW, sc(20));

            // email
            icons[i][2].setBounds(sc(20), sc(190), box, box);
            mails[i].setBounds(textX, sc(199), textW, sc(20));

            // scale icons to fit their box
            for (int j = 0; j < 3; j++) {
                javax.swing.Icon base = iconBases[i][j];
                if (base != null && base.getIconWidth() > 0 && base.getIconHeight() > 0) {
                    double k = (double) sc(30) / Math.max(base.getIconWidth(), base.getIconHeight());
                    icons[i][j].setIcon(new ScaledIcon(base, k));
                }
            }
        }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(null);

        jLabel10.setBackground(new java.awt.Color(0, 102, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Contact Us");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(370, 20, 343, 48);

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));

        jLabel16.setBackground(new java.awt.Color(0, 102, 51));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Max Suniel, Carmen, Cagayan");

        jLabel11.setBackground(new java.awt.Color(0, 102, 51));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 255, 0));
        jLabel11.setText("Carmen Campus");

        jLabel19.setBackground(new java.awt.Color(0, 102, 51));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("de Oro City, 9000, Mis Or");

        jLabel20.setBackground(new java.awt.Color(0, 102, 51));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("0917-376-5105");

        jLabel21.setBackground(new java.awt.Color(0, 102, 51));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("info.coc@phinmaed.com");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\9-97495_gps-icon-png-vector-location-logo-png-transparent (2).png")); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\pngtree-phone-icon-png-image_1757859_1_25x25.jpg")); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\google-gmail-logo-symbol-design-illustration-free-vector_25x25.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel6))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(60, 120, 240, 260);

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));

        jLabel12.setBackground(new java.awt.Color(0, 102, 51));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 255, 0));
        jLabel12.setText("Puerto Campus");

        jLabel22.setBackground(new java.awt.Color(0, 102, 51));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Purok 6,  Puerto Cdo");

        jLabel23.setBackground(new java.awt.Color(0, 102, 51));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("9000, Mis Or");

        jLabel24.setBackground(new java.awt.Color(0, 102, 51));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("09166-131-8980");

        jLabel25.setBackground(new java.awt.Color(0, 102, 51));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("info.coc@phinmaed.com");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\9-97495_gps-icon-png-vector-location-logo-png-transparent (2).png")); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\pngtree-phone-icon-png-image_1757859_1_25x25.jpg")); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\google-gmail-logo-symbol-design-illustration-free-vector_25x25.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel14))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(360, 120, 240, 260);

        jPanel5.setBackground(new java.awt.Color(0, 153, 0));

        jLabel13.setBackground(new java.awt.Color(0, 102, 51));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 255, 0));
        jLabel13.setText("Iligan Campus");

        jLabel26.setBackground(new java.awt.Color(0, 102, 51));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Pedro Permites Rd, Brg");

        jLabel27.setBackground(new java.awt.Color(0, 102, 51));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Pala-o Iligan City");

        jLabel28.setBackground(new java.awt.Color(0, 102, 51));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("09166-131-8980");

        jLabel29.setBackground(new java.awt.Color(0, 102, 51));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("info.coc@phinmaed.com");

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\9-97495_gps-icon-png-vector-location-logo-png-transparent (2).png")); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\pngtree-phone-icon-png-image_1757859_1_25x25.jpg")); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\google-gmail-logo-symbol-design-illustration-free-vector_25x25.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel15))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(660, 120, 231, 260);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(360, 70, 210, 10);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 91, 970, 441);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Downloads\\ChatGPT Image Sep 25, 2026, 04_35_52 PM (2).png")); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(17, 20, 61, 59);

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Phinma Voting Website");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(84, 34, 230, 25);

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Log Out");
        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1);
        jButton1.setBounds(862, 35, 90, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(0, 102, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Contact");
        jButton6.setBorder(null);
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jButton5.setBackground(new java.awt.Color(0, 102, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Voting Rules");
        jButton5.setBorder(null);
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("About");
        jButton4.setBorder(null);
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Candidates");
        jButton3.setBorder(null);
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Home");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(422, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 970, 100);

        setBounds(0, 0, 985, 539);
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
        // TODO add your handling code here:
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
        java.awt.EventQueue.invokeLater(() -> new contact().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
