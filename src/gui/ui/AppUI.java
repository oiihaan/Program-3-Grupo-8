package gui.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class AppUI {

	
	
    //FUENTE GLOBAL 
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 22);
    private static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);

    //COLORES
    private static final Color COLOR_BG = new Color(0x101020);
    private static final Color COLOR_CARD = new Color(0x1E1E2F);
    private static final Color COLOR_TEXT = Color.WHITE;
    private static final Color COLOR_SUBTEXT = new Color(180, 180, 200);

    private static final Color COLOR_PRIMARY = new Color(0x3F7FFF);   // azul botón
    private static final Color COLOR_FIELD_BG = new Color(0x252539);
    private static final Color COLOR_FIELD_BORDER = new Color(90, 90, 130);



    //FONDOS
    public static void styleBackground(JPanel bg) {
        bg.setBackground(COLOR_BG);
    }

    public static void styleCard(JPanel card) {
        card.setBackground(COLOR_CARD);
        card.setBorder(new EmptyBorder(30, 30, 30, 30));
    }

    public static void styleTransparent(JPanel panel) {
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder());
    }


    //TIPOGRAFÍA
    public static void styleTitle(JLabel lbl) {
        lbl.setFont(FONT_TITLE);
        lbl.setForeground(COLOR_TEXT);
    }

    public static void styleSubtitle(JLabel lbl) {
        lbl.setFont(FONT_SUBTITLE);
        lbl.setForeground(COLOR_SUBTEXT);
    }

    public static void styleLabel(JLabel lbl) {
        lbl.setFont(FONT_NORMAL);
        lbl.setForeground(COLOR_TEXT);
    }


    //CAMPOS DE TEXTO 
    public static void styleTextField(JTextField txt) {
        roundedTextComponent(txt);
    }

    public static void stylePasswordField(JPasswordField txt) {

        txt.setFont(FONT_NORMAL);
        txt.setForeground(COLOR_TEXT);
        txt.setCaretColor(Color.WHITE);
        txt.setOpaque(false); 

        txt.setBorder(new EmptyBorder(6, 10, 6, 10));

        txt.setUI(new javax.swing.plaf.basic.BasicPasswordFieldUI() {
            @Override
            protected void paintSafely(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo redondeado
                g2.setColor(COLOR_FIELD_BG);
                g2.fillRoundRect(0, 0, txt.getWidth(), txt.getHeight(), 18, 18);

                // Borde redondeado
                g2.setColor(COLOR_FIELD_BORDER);
                g2.drawRoundRect(0, 0, txt.getWidth() - 1, txt.getHeight() - 1, 18, 18);

                g2.dispose();
                super.paintSafely(g); // ¡aquí sí pinta puntitos!
            }
        });

        // Para que salgan puntos y no las letras
        txt.setEchoChar('●');
    }


    private static void roundedTextComponent(JTextComponent txt) {

        txt.setFont(FONT_NORMAL);
        txt.setForeground(COLOR_TEXT);
        txt.setCaretColor(Color.WHITE);
        txt.setOpaque(false);

        txt.setBorder(new EmptyBorder(6, 10, 6, 10));

        // UI personalizada para hacer bordes redondos
        txt.setUI(new BasicTextFieldUI() {
            @Override
            protected void paintSafely(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo redondeado
                g2.setColor(COLOR_FIELD_BG);
                g2.fillRoundRect(0, 0, txt.getWidth(), txt.getHeight(), 18, 18);

                // Borde redondeado
                g2.setColor(COLOR_FIELD_BORDER);
                g2.drawRoundRect(0, 0, txt.getWidth() - 1, txt.getHeight() - 1, 18, 18);

                g2.dispose();
                super.paintSafely(g);
            }
        });
    }

    //BOTONES  REDONDEADOS
 // BOTONES REDONDEADOS
    public static void stylePrimaryButton(JButton btn) {
        btn.setFont(FONT_NORMAL);
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);

        btn.setBorder(new EmptyBorder(8, 16, 8, 16));

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();


                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);   


                if (btn.isEnabled()) {
                    g2.setColor(COLOR_PRIMARY);
                } else {
                    g2.setColor(new Color(90, 90, 90)); // gris oscuro
                }
                g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), 18, 18);
                g2.setFont(btn.getFont());
                g2.setColor(Color.WHITE);       

                String text = btn.getText();
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                int x = (btn.getWidth()  - textWidth) / 2;
                int y = (btn.getHeight() + textHeight) / 2 - 2;

                g2.drawString(text, x, y);

                g2.dispose();
            }
        });
    }

    
 //JList
    public static void styleList(JList<?> list) {
        list.setFont(FONT_NORMAL);
        list.setBackground(COLOR_CARD);
        list.setForeground(COLOR_TEXT);
        list.setSelectionBackground(COLOR_PRIMARY.darker());
        list.setSelectionForeground(Color.WHITE);
        list.setBorder(new EmptyBorder(5, 5, 5, 5));

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                                                          Object value,
                                                          int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setFont(FONT_NORMAL);
                if (!isSelected) {
                    lbl.setBackground(COLOR_CARD);
                    lbl.setForeground(COLOR_TEXT);
                }
                return lbl;
            }
        });
    }
    
    

    //TABLAS (JTable)
    public static void styleTable(JTable table) {
        table.setFont(FONT_NORMAL);
        table.setForeground(COLOR_TEXT);
        table.setBackground(COLOR_CARD);
        table.setGridColor(new Color(70, 70, 110));
        table.setRowHeight(24);
        table.setSelectionBackground(COLOR_PRIMARY.darker());
        table.setSelectionForeground(Color.WHITE);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setFont(FONT_SUBTITLE.deriveFont(Font.BOLD));
            header.setBackground(new Color(0x26263A));
            header.setForeground(COLOR_TEXT);
            header.setReorderingAllowed(false);
        }
    }
    
    
  //IMAGEN
  	public static void establecerIcono(JFrame ventana) {
          ImageIcon baseIcon = new ImageIcon("./img/logo4.png"); 
                 
          ventana.setIconImage(baseIcon.getImage());
  	}
  	

    
}
