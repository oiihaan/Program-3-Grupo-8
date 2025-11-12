package gui.ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

public final class AppUI {

    // 🎨 Paleta inspirada en tu imagen
    public static final Color BG_APP       = new Color(230, 233, 240);  // Fondo general claro (gris azulado)
    public static final Color BG_CARD      = new Color(70, 72, 75);     // Tarjeta oscura
    public static final Color FIELD_BG     = new Color(150, 152, 155);  // Campos gris medio
    public static final Color FIELD_FG     = Color.white;
    public static final Color TEXT_PRIMARY = Color.white;
    public static final Color TEXT_SECOND  = new Color(210, 210, 210);
    public static final Color TEXT_HINT    = new Color(200, 200, 200);

    public static final Color PRIMARY      = new Color(51, 153, 255);   // Azul botón
    public static final Color PRIMARY_HOV  = new Color(31, 133, 235);

    public static final Color BORDER_SOFT  = new Color(90, 92, 95);
    public static final Color BORDER_FOCUS = new Color(100, 170, 255);

    public static final Font FONT_BASE   = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_LABEL  = new Font("Segoe UI", Font.PLAIN, 13);

    private AppUI() {}

    public static void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.put("defaultFont", FONT_BASE);
        } catch (Exception ignore) {}
    }

    // Fondo de app
    public static JPanel appBackground() {
        JPanel bg = new JPanel(new GridBagLayout());
        bg.setBackground(BG_APP);
        return bg;
    }

    // Tarjeta redondeada
    public static JPanel card(int padding) {
        JPanel card = new RoundedPanel(40); // esquinas redondeadas
        card.setBackground(BG_CARD);
        card.setLayout(new GridBagLayout());
        card.setBorder(new EmptyBorder(padding, padding, padding, padding));
        return card;
    }

    public static JLabel title(String text) {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setFont(FONT_TITLE);
        l.setForeground(TEXT_PRIMARY);
        return l;
    }

    public static JLabel subtitle(String text) {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setFont(FONT_LABEL);
        l.setForeground(TEXT_SECOND);
        return l;
    }

    public static JTextField textField(String placeholder) {
        JTextField tf = new JTextField();
        styleInput(tf);
        installPlaceholder(tf, placeholder);
        return tf;
    }

    public static JPasswordField passwordField(String placeholder) {
        JPasswordField pf = new JPasswordField();
        styleInput(pf);
        installPasswordPlaceholder(pf, placeholder);
        pf.setEchoChar('•');
        return pf;
    }

    public static JButton primaryButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setForeground(Color.white);
        b.setBackground(PRIMARY);
        b.setBorder(new EmptyBorder(10, 14, 10, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { b.setBackground(PRIMARY_HOV); }
            @Override public void mouseExited (MouseEvent e) { b.setBackground(PRIMARY); }
        });
        return b;
    }

    public static JCheckBox styledCheckBox(String text) {
        JCheckBox box = new JCheckBox(text);
        box.setFocusPainted(false);
        box.setOpaque(false);
        box.setForeground(TEXT_PRIMARY);
        box.setFont(FONT_LABEL);
        return box;
    }

    private static void styleInput(JTextComponent c) {
        c.setBackground(FIELD_BG);
        c.setForeground(FIELD_FG);
        c.setCaretColor(Color.white);
        c.setBorder(new EmptyBorder(10, 12, 10, 12));
    }

    private static void installPlaceholder(JTextField field, String placeholder) {
        field.setForeground(TEXT_HINT);
        field.setText(placeholder);
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (field.getForeground().equals(TEXT_HINT)) {
                    field.setText("");
                    field.setForeground(FIELD_FG);
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(TEXT_HINT);
                    field.setText(placeholder);
                }
            }
        });
    }

    private static void installPasswordPlaceholder(JPasswordField field, String placeholder) {
        field.setForeground(TEXT_HINT);
        field.setEchoChar((char)0);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (field.getForeground().equals(TEXT_HINT)) {
                    field.setText("");
                    field.setForeground(FIELD_FG);
                    field.setEchoChar('•');
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setForeground(TEXT_HINT);
                    field.setText(placeholder);
                    field.setEchoChar((char)0);
                }
            }
        });
    }

    // Panel redondeado reutilizable
    private static class RoundedPanel extends JPanel {
        private final int radius;
        public RoundedPanel(int radius) { this.radius = radius; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
 // Coloca una tarjeta centrada dentro de un panel con GridBagLayout
    public static void centerCardIn(JPanel bg, JComponent card) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(16, 16, 16, 16);
        gbc.fill = GridBagConstraints.NONE;       // no estirar la tarjeta
        gbc.anchor = GridBagConstraints.CENTER;   // centrado
        bg.add(card, gbc);
    }
}
