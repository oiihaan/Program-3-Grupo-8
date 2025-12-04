package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.TrabajadorDAO;
import domain.BDAdmin;
import domain.BDTrabajador;
import gui.ui.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class VAnyadirTrabajador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField txtUsername;
	private VAdmin1 parent;
	private BDAdmin admin;

	/**
	 * Create the frame.
	 */
	public VAnyadirTrabajador(VAdmin1 parent, BDAdmin admin) {
		this.parent = parent;
		this.admin = admin;
		
		setTitle("Añadir Trabajador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 640, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 195, 195, 200, 0};
		//gbl_panel.rowHeights = new int[]{100, 95, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel left = new JPanel();
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.insets = new Insets(0, 0, 0, 5);
		gbc_left.gridx = 1;
		gbc_left.gridy = 1;
		panel.add(left, gbc_left);
		AppUI.styleTransparent(left);
		left.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblUsername = new JLabel("Nombre de usuario: ");
		left.add(lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		AppUI.styleLabel(lblUsername);
		
		JLabel lblContrasenya = new JLabel("Contraseña: ");
		lblContrasenya.setHorizontalAlignment(SwingConstants.RIGHT);
		left.add(lblContrasenya);
		AppUI.styleLabel(lblContrasenya);
		
		JPanel center = new JPanel();
		GridBagConstraints gbc_center = new GridBagConstraints();
		gbc_center.fill = GridBagConstraints.BOTH;
		gbc_center.insets = new Insets(0, 0, 0, 5);
		gbc_center.gridx = 2;
		gbc_center.gridy = 1;
		panel.add(center, gbc_center);
		AppUI.styleTransparent(center);
		center.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtUsername = new JTextField();
		center.add(txtUsername);
		txtUsername.setColumns(10);
		AppUI.styleTextField(txtUsername);
		
		txtPassword = new JTextField();
		center.add(txtPassword);
		txtPassword.setColumns(10);
		AppUI.styleTextField(txtPassword);
		
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(2, 1, 0, 10)); 
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 3;
		gbc_right.gridy = 1;
		panel.add(right, gbc_right);
		
				
																
		JButton btnAynadir = new JButton("Añadir");
		//btnAynadir.setBounds(12, 13, 148, 25);
		right.add(btnAynadir);
				
		btnAynadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username  = txtUsername.getText().trim();
				String contrasenya = txtPassword.getText().trim();

				if (username.isEmpty() || contrasenya.isEmpty()) {
					JOptionPane.showMessageDialog(
							VAnyadirTrabajador.this,
							"Rellena todos los campos.",
							"Error",
							JOptionPane.ERROR_MESSAGE
							);
					return;
				}

				try {
					BDTrabajador nuevo = new BDTrabajador(0, username, contrasenya, null, null, null);
				           

					// 1) Insertar en BD
					TrabajadorDAO.insertarTrabajador(nuevo);

					// 2) Añadir al conjunto estático de VPrincipal
					VPrincipal.getTrabajadores().add(nuevo);

					// 3) Avisar de éxito
					JOptionPane.showMessageDialog(
							VAnyadirTrabajador.this,
							"Trabajador añadido correctamente.",
							"OK",
							JOptionPane.INFORMATION_MESSAGE
							);

					// 4) Volver al menú admin
					parent.setVisible(true);
					VAnyadirTrabajador.this.dispose();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(
							VAnyadirTrabajador.this,
							"Error al insertar el trabajador:\n" + ex.getMessage(),
							"Error",
							JOptionPane.ERROR_MESSAGE
				            );
				}
			}
		});
		
		
		
		JButton btnVolver = new JButton("Volver");
		//btnVolver.setBounds(12, 57, 148, 25);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VAnyadirTrabajador.this.dispose();
			}
		});
		right.add(btnVolver);
		AppUI.styleTransparent(right);
		
		AppUI.stylePrimaryButton(btnVolver);
		AppUI.stylePrimaryButton(btnAynadir);
		
		
		
		
		
		//Estilo AppUI
				AppUI.styleBackground(contentPane);
				AppUI.styleCard(panel);
		
		//IMAGEN
		AppUI.establecerIcono(this);
		
		
		//Para que salga bien el tamaño
		this.pack();      
		
		//Para que se abra en el centro
	    this.setLocationRelativeTo(null);
	}
	
	
}
