package gui.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AppUI {

    public static void initLookAndFeel() {
        try {
            // Puedes dejar Nimbus, FlatLaf o lo que queráis
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // === FONDO GENERAL ===
    public static void styleBackground(JPanel bg) {
        bg.setBackground(new Color(0x101020)); // azul oscuro / gris
    }

    // === TARJETA CENTRAL ===
    public static void styleCard(JPanel card) {
        card.setBackground(new Color(0x1E1E2F));
        card.setBorder(new EmptyBorder(30, 30, 30, 30));
    }

    // === TÍTULOS ===
    public static void styleTitle(JLabel lbl) {
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 22f));
        lbl.setForeground(Color.WHITE);
    }

    public static void styleSubtitle(JLabel lbl) {
        lbl.setFont(lbl.getFont().deriveFont(Font.PLAIN, 12f));
        lbl.setForeground(new Color(180, 180, 200));
    }

    // === CAMPOS DE TEXTO ===
    public static void styleTextField(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 120)),
                new EmptyBorder(5, 8, 5, 8)
        ));
        txt.setBackground(new Color(0x252539));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
    }

    public static void stylePasswordField(JPasswordField txt) {
        styleTextField(txt);
    }

    // === BOTONES ===
    public static void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(0x3F7FFF));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
