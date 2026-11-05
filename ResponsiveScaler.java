/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package votingsystem;

/**
 *
 * @author user
 */


import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public final class ResponsiveScaler {
    private final JPanel root;
    private final int designWidth, designHeight;
    private final boolean keepAspect;

    private final Map<Component, Rectangle> bounds = new IdentityHashMap<>();
    private final Map<JComponent, Font> fonts = new IdentityHashMap<>();
    private final Map<JComponent, ImageIcon> icons = new IdentityHashMap<>();

    public ResponsiveScaler(JPanel root, int designWidth, int designHeight) {
        this(root, designWidth, designHeight, false);
    }

    public ResponsiveScaler(JPanel root, int designWidth, int designHeight, boolean keepAspect) {
        this.root = root;
        this.designWidth = designWidth;
        this.designHeight = designHeight;
        this.keepAspect = keepAspect;
        layoutAll(root);
        capture(root);
        update();
    }

    private static void layoutAll(Container c) {
        c.doLayout();
        for (Component child : c.getComponents()) {
            if (child instanceof Container) layoutAll((Container) child);
        }
    }

    private void capture(Container parent) {
        for (Component comp : parent.getComponents()) {
            bounds.put(comp, new Rectangle(comp.getBounds()));

            if (comp instanceof JComponent) {
                JComponent jc = (JComponent) comp;
                if (jc.getFont() != null) fonts.put(jc, jc.getFont());

                Icon icon = null;
                if (jc instanceof JLabel) icon = ((JLabel) jc).getIcon();
                else if (jc instanceof AbstractButton) icon = ((AbstractButton) jc).getIcon();
                if (icon instanceof ImageIcon) icons.put(jc, (ImageIcon) icon);
            }

            if (comp instanceof JScrollPane) continue;

            if (comp instanceof JPanel) {
                capture((JPanel) comp);
                ((JPanel) comp).setLayout(null);
            }
        }
    }

    public void update() {
        if (root.getWidth() <= 0 || root.getHeight() <= 0) return;

        double sx = root.getWidth() / (double) designWidth;
        double sy = root.getHeight() / (double) designHeight;
        double su = Math.min(sx, sy);

        double bx = keepAspect ? su : sx;
        double by = keepAspect ? su : sy;
        double offX = keepAspect ? (root.getWidth()  - designWidth  * su) / 2 : 0;
        double offY = keepAspect ? (root.getHeight() - designHeight * su) / 2 : 0;

        for (Map.Entry<Component, Rectangle> e : bounds.entrySet()) {
            Rectangle r = e.getValue();
            Component c = e.getKey();
            boolean topLevel = (c.getParent() == root);
            double ox = topLevel ? offX : 0;
            double oy = topLevel ? offY : 0;
            c.setBounds(
                (int) Math.round(r.x * bx + ox), (int) Math.round(r.y * by + oy),
                Math.max(1, (int) Math.round(r.width * bx)),
                Math.max(1, (int) Math.round(r.height * by)));
        }

        for (Map.Entry<JComponent, Font> e : fonts.entrySet()) {
            Font f = e.getValue();
            e.getKey().setFont(f.deriveFont(f.getStyle(),
                Math.max(8f, (float) Math.round(f.getSize2D() * su))));
        }

        for (Map.Entry<JComponent, ImageIcon> e : icons.entrySet()) {
            ImageIcon ic = e.getValue();
            int w = Math.max(1, (int) Math.round(ic.getIconWidth() * su));
            int h = Math.max(1, (int) Math.round(ic.getIconHeight() * su));
            ImageIcon scaled = new ImageIcon(
                ic.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            if (e.getKey() instanceof JLabel) ((JLabel) e.getKey()).setIcon(scaled);
            else if (e.getKey() instanceof AbstractButton) ((AbstractButton) e.getKey()).setIcon(scaled);
        }

        root.revalidate();
        root.repaint();
    }
}