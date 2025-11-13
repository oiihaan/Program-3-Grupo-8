package gui;

import gui.ui.AppUI;
import multiplesVentanas.ventanaLogIn;

import javax.swing.*;

import domain.Trabajador;
import domain.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VPrincipal extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JCheckBox chkRemember;
    private JButton btnLogin;
    private ArrayList<Usuario> personal; //Creo esta propiedad para poder hacer simulaciones, pero igual sale mejor tener una clase empresa que tenga listas de personal y dentro de ella una de trbajadores y otra de admins

    public static void main(String[] args) {   
        AppUI.initLookAndFeel();
        EventQueue.invokeLater(() -> new VPrincipal().setVisible(true));
    }

    public VPrincipal() {
    	this.personal= new ArrayList<Usuario>();// SOLO PARA SIMULAR
    	Trabajador Eneko = new Trabajador("Eneko", "Gil Jimenez", "Enekore", "aupa"); //SOLO PARA SIMULAR
    	personal.add(Eneko);
    	
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
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String contraseyna = String.copyValueOf(txtPass.getPassword());
            	String usuario = txtUser.getName();
            	boolean deLaEmpresa = false;
            	while (!deLaEmpresa){
            	for (Usuario u : personal) {
            		if (contraseyna.equals(u.getContraseyna()) && usuario.equals(u.getUsuario())) {
            			if (u instanceof Trabajador) {
            				VTrabajador1 VTrabajador1 = new VTrabajador1 (VPrincipal.this, (Trabajador) u);
            				VTrabajador1.setVisible(true);
            				VPrincipal.this.setVisible(false);
                			deLaEmpresa = true;
            			} else {
            				JOptionPane.showMessageDialog(VPrincipal.this, "El usuario o la contraseña no son válidos");
            			}
            		}
            	}
            }
            }
        });
        

        JLabel lblForgot = AppUI.subtitle("Olvidé mi contraseña");
        lblForgot.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy++; card.add(lblForgot, g);

        AppUI.centerCardIn(bg, card);
        
        
    }
}
