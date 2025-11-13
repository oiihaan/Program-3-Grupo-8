package gui;

import gui.ui.AppUI;
import javax.swing.*;
import java.awt.*;

public class VPrincipal extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JCheckBox chkRemember;
    private JButton btnLogin;

    public static void main(String[] args) {
        AppUI.initLookAndFeel();
        EventQueue.invokeLater(() -> new VPrincipal().setVisible(true));
    }

    public VPrincipal() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 400);
        setLocationRelativeTo(null);

        JPanel bg = AppUI.appBackground();
        setContentPane(bg);

        JPanel card = AppUI.card(30);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0; g.gridy = 0; g.fill = GridBagConstraints.HORIZONTAL; g.insets = new Insets(8,0,8,0);
        card.add(AppUI.title("Bienvenido"), g);

        txtUser = AppUI.textField("Usuario");
        txtPass = AppUI.passwordField("Contraseña");

        g.gridy++; card.add(txtUser, g);
        g.gridy++; card.add(txtPass, g);


        btnLogin = AppUI.primaryButton("LOG IN");
        g.gridy++; card.add(btnLogin, g);

        JLabel lblForgot = AppUI.subtitle("Olvidé mi contraseña");
        lblForgot.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy++; card.add(lblForgot, g);

        AppUI.centerCardIn(bg, card);
    }
}
