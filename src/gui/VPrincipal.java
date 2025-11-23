package gui;

import gui.ui.AppUI;
import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import domain.Usuario;

import javax.swing.*;

import bd.AdminDAO;
import bd.ConexionSQLite;
import bd.InicializadorBaseDatos;
import bd.TareaDAO;
import bd.TrabajadorDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static HashSet<BDTrabajador> trabajadores;
    private static HashSet<BDTarea> tareas;
    private static HashSet<Usuario> personal; //Prueba Unai
    private static HashSet<BDAdmin> admins;

    public VPrincipal() {


        
     // ==== DATOS conectando a BD ====
        BDAdmin julio = new BDAdmin(2,"Julio", "123");
        AdminDAO.insertar(julio);
        admins = new HashSet<BDAdmin>();
        cargarAdmins();
        System.out.println(admins);
        
        
        BDTrabajador iker = new BDTrabajador(5,"Iker", "123");
        TrabajadorDAO.insertar(iker);
        trabajadores = new HashSet<BDTrabajador>();
        cargarTrabajadoresBD();
        System.out.println(trabajadores);
        
        
        BDTarea tarea1 = new BDTarea(1, "Llenar excel", 1, trabajadores);
        BDTarea tarea2 = new BDTarea(2, "Chequear emails", 2, trabajadores);
        BDTarea tarea3 = new BDTarea(3, "Programar módulo", 10, trabajadores);
        TareaDAO.insertar(tarea1);
        TareaDAO.insertar(tarea2);
        TareaDAO.insertar(tarea3);
        tareas = new HashSet<BDTarea>();
        cargarTareasBD();
        System.out.println(tareas);

        personal =new HashSet<Usuario>();
        cargarPersonal();
        System.out.println(personal);
        
        

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
        getRootPane().setDefaultButton(btnLogin); //Para poder entrar haciendo enter tb

        // --- LABEL OLVIDÉ CONTRASEÑA ---
        lblForgot = new JLabel("Olvidé mi contraseña");
        lblForgot.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblForgot = new GridBagConstraints();
        gbc_lblForgot.gridx = 0;
        gbc_lblForgot.gridy = 4;
        gbc_lblForgot.insets = new Insets(8, 0, 8, 0);
        gbc_lblForgot.fill = GridBagConstraints.HORIZONTAL;
        card.add(lblForgot, gbc_lblForgot);

        // --- La card al fondo (centrada) ---
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
                			VTrabajador1 v = new VTrabajador1(VPrincipal.this, (BDTrabajador) u);
                            v.setVisible(true);
                            VPrincipal.this.setVisible(false);
                            entrado = true;
                		} else if (u instanceof BDAdmin) {
                			VAdmin1 v = new VAdmin1(VPrincipal.this, (BDAdmin) u);
                            v.setVisible(true);
                            VPrincipal.this.setVisible(false);	
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
 
	public static void cargarTrabajadoresBD() {
        HashSet<BDTrabajador> trabajadoresBD = TrabajadorDAO.getAllTrabajadores();
        trabajadores.addAll(trabajadoresBD);
    }
    
	public static HashSet<BDTrabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(HashSet<BDTrabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
    public static void cargarTareasBD() {
    	HashSet<BDTarea> tareasBD = TareaDAO.getAllTareas();
    	tareas.addAll(tareasBD);
    }
	public static HashSet<BDTarea> getTareas() {
		return tareas;
	}
	public static void setTareas(HashSet<BDTarea> tareas) {
		VPrincipal.tareas = tareas;
	}
    
	public static void cargarAdmins() {
        HashSet<BDAdmin> adminsBD = AdminDAO.getAllAdmins();
        admins.addAll(adminsBD);
    }
	public static HashSet<BDAdmin> getAdmins() {
		return admins;
	}
	public static void setAdmins(HashSet<BDAdmin> admins) {
		VPrincipal.admins = admins;
	}
	public static void cargarPersonal() {
		for (BDTrabajador t : trabajadores) {
			personal.add(t);
		}
		for(BDAdmin a : admins) {
			personal.add(a);
		}
	}

	public static HashSet<Usuario> getPersonal() {
		return personal;
	}

	public static void setPersonal(HashSet<Usuario> personal) {
		VPrincipal.personal = personal;
	}
	
	
}
