package gui;

import gui.ui.AppUI;
import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import domain.Usuario;

import javax.swing.*;

import bd.InicializadorBaseDatos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class VPrincipal extends JFrame {

    private JPanel contentPane;
    private JPanel card;

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JLabel lblForgot;
    private ArrayList<Usuario> personal;
    

    public static void main(String[] args) {			
        AppUI.initLookAndFeel();
        EventQueue.invokeLater(() -> {
            VPrincipal frame = new VPrincipal();
            frame.setVisible(true);
        });
    }

    public VPrincipal() {
        // ==== DATOS DE PRUEBA ====
        personal = new ArrayList<>();
        BDTrabajador eneko = new BDTrabajador(1,"Eneko", "aupa");
        BDAdmin juan = new BDAdmin(2,"Juan", "123");

        personal.add(eneko);
        personal.add(juan);
        
        HashSet<BDTrabajador> trabajadores = new HashSet<>();
        trabajadores.add(eneko); // Tu instancia de trabajador
        
        BDTarea tarea1 = new BDTarea(1, "Llenar excel", 1, trabajadores);
        BDTarea tarea2 = new BDTarea(2, "Chequear emails", 2, trabajadores);
        BDTarea tarea3 = new BDTarea(3, "Programar módulo", 10, trabajadores);
        tarea3.setEstado("finalizado");
        eneko.getTareasAsignadas().add(tarea1);
        eneko.getTareasAsignadas().add(tarea3);
        eneko.getTareasAsignadas().add(tarea2);



     // === Login TITULO ===
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 555, 491);
        setLocationRelativeTo(null);

        // === FONDO ===
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        // === CARD CENTRAL ===
        card = new JPanel();
        card.setLayout(new GridBagLayout());

        // --- TÍTULO ---
        JLabel lblTitle = new JLabel("Bienvenido");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.gridx = 0;
        gbc_lblTitle.gridy = 0;
        gbc_lblTitle.insets = new Insets(8, 0, 8, 0);
        gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
        card.add(lblTitle, gbc_lblTitle);

        // --- USUARIO ---
        txtUser = new JTextField();
        txtUser.setColumns(15);
        GridBagConstraints gbc_txtUser = new GridBagConstraints();
        gbc_txtUser.gridx = 0;
        gbc_txtUser.gridy = 1;
        gbc_txtUser.insets = new Insets(8, 0, 8, 0);
        gbc_txtUser.fill = GridBagConstraints.HORIZONTAL;
        card.add(txtUser, gbc_txtUser);

        // --- PASSWORD ---
        txtPass = new JPasswordField();
        txtPass.setColumns(15);
        GridBagConstraints gbc_txtPass = new GridBagConstraints();
        gbc_txtPass.gridx = 0;
        gbc_txtPass.gridy = 2;
        gbc_txtPass.insets = new Insets(8, 0, 8, 0);
        gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
        card.add(txtPass, gbc_txtPass);

        // --- BOTÓN LOGIN ---
        btnLogin = new JButton("LOG IN");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 3;
        gbc_btnLogin.insets = new Insets(8, 0, 8, 0);
        gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
        card.add(btnLogin, gbc_btnLogin);

        // --- LABEL OLVIDÉ CONTRASEÑA ---
        lblForgot = new JLabel("Olvidé mi contraseña");
        lblForgot.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblForgot = new GridBagConstraints();
        gbc_lblForgot.gridx = 0;
        gbc_lblForgot.gridy = 4;
        gbc_lblForgot.insets = new Insets(8, 0, 8, 0);
        gbc_lblForgot.fill = GridBagConstraints.HORIZONTAL;
        card.add(lblForgot, gbc_lblForgot);

        // --- Añadimos la card al fondo (centrada) ---
        GridBagConstraints gbc_card = new GridBagConstraints();
        gbc_card.gridx = 0;
        gbc_card.gridy = 0;
        contentPane.add(card, gbc_card);

        // === ESTILO CON AppUI (solo “maquillaje”) ===
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(card);
        AppUI.styleTitle(lblTitle);
        AppUI.styleTextField(txtUser);
        AppUI.stylePasswordField(txtPass);
        AppUI.stylePrimaryButton(btnLogin);
        AppUI.styleSubtitle(lblForgot);

        // === LÓGICA DEL LOGIN ===
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUser.getText().trim();
                String contraseyna = String.copyValueOf(txtPass.getPassword());
                Boolean entrado = false;
                
                for (Usuario u : personal) {
                	if(u.getNombre().equals(usuario) && u.getContraseyna().equals(contraseyna) ) {
                		
                		if (u instanceof BDTrabajador) {
                			VTrabajador1 v = new VTrabajador1((BDTrabajador) u);
                            v.setVisible(true);
                            VPrincipal.this.dispose();
                            entrado = true;
                		} else if (u instanceof BDAdmin) {
                			VAdmin1 v = new VAdmin1((BDAdmin) u);
                            v.setVisible(true);
                            VPrincipal.this.dispose();	
                            entrado = true;

						}
                		
                		
                	}
                	
                }
                if (!entrado == true) {
        		JOptionPane.showMessageDialog(null, "El usuario o la contraseña no son válidos" );
                }
            }
            
            
        });
    }
}
