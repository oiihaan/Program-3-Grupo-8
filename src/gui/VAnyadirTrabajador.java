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
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField textField;
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
		setBounds(100, 100, 640, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowHeights = new int[] {30, 400, 30};
		gbl_contentPane.columnWidths = new int[] {20, 422, 3};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0,0.0,0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel centro = new JPanel();
		GridBagConstraints gbc_centro = new GridBagConstraints();
		gbc_centro.insets = new Insets(0, 0, 5, 5);
		gbc_centro.fill = GridBagConstraints.BOTH;
		gbc_centro.gridx = 1;
		gbc_centro.gridy = 1;
		contentPane.add(centro, gbc_centro);
		centro.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel northIz = new JPanel();
		centro.add(northIz);
		northIz.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(41, 21, 106, 31);
		northIz.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(41, 62, 118, 31);
		northIz.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel northDe = new JPanel();
		centro.add(northDe);
		northDe.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(41, 21, 69, 31);
		northDe.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(37, 62, 118, 31);
		northDe.add(txtApellido);
		txtApellido.setColumns(10);
		
		JPanel centroIz = new JPanel();
		centro.add(centroIz);
		centroIz.setLayout(null);
		
		JLabel lblUsername = new JLabel("Nombre de usuario: ");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(41, 21, 133, 21);
		centroIz.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(41, 62, 118, 31);
		centroIz .add(txtUsername);
		txtUsername.setColumns(10);
		
		JPanel centroDe = new JPanel();
		centro.add(centroDe);
		centroDe.setLayout(null);
		
		JLabel lblContrasenya = new JLabel("Contraseña: ");
		lblContrasenya.setBounds(41, 21, 145, 22);
		centroDe.add(lblContrasenya);
		
		textField = new JTextField();
		textField.setBounds(36, 62, 118, 31);
		centroDe.add(textField);
		textField.setColumns(10);
		
		JPanel southIz = new JPanel();
		centro.add(southIz);

		
		JPanel southDe = new JPanel();
		centro.add(southDe);

		//Estilo AppUI
		AppUI.styleBackground(contentPane);
		AppUI.styleCard(centro);

		AppUI.styleTransparent(northIz);
		AppUI.styleTransparent(northDe);
		AppUI.styleTransparent(centroIz);
		AppUI.styleTransparent(centroDe);
		AppUI.styleTransparent(southIz);
		AppUI.styleTransparent(southDe);

		AppUI.styleLabel(lblNombre);
		AppUI.styleLabel(lblApellido);
		AppUI.styleLabel(lblUsername);
		AppUI.styleLabel(lblContrasenya);

		AppUI.styleTextField(txtNombre);
		AppUI.styleTextField(txtApellido);
		AppUI.styleTextField(txtUsername);
		AppUI.styleTextField(textField);
		southIz.setLayout(null);
														
		JButton btnAynadir = new JButton("Añadir");
		btnAynadir.setBounds(60, 0, 84, 21);
		southIz.add(btnAynadir);
														
		btnAynadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre    = txtNombre.getText().trim();
				String apellido  = txtApellido.getText().trim();
				String username  = txtUsername.getText().trim();
				String contrasenya = textField.getText().trim();

				if (nombre.isEmpty() || apellido.isEmpty() || username.isEmpty() || contrasenya.isEmpty()) {
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
														
		AppUI.stylePrimaryButton(btnAynadir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(59, 0, 73, 21);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VAnyadirTrabajador.this.dispose();
			}
		});
		southDe.setLayout(null);
		southDe.add(btnVolver);
		AppUI.stylePrimaryButton(btnVolver);
		
		//IMAGEN
		AppUI.establecerIcono(this);
	}
	
	
}
