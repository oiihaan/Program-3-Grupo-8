package gui;

import java.awt.EventQueue;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;

import domain.BDAdmin;
import domain.BDTrabajador;
import gui.ui.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VAdmin1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BDAdmin admin;
	private VPrincipal parent;  //--(Danel): LE HE AÑADIDO PARENT PARA PODER CAMBIAR DE ADMIN--> TRABAJADOR SIN TENER QUE CERRAR EL PROGRAMA
	
	


	/**
	 * Create the frame.
	 */
	public VAdmin1(VPrincipal parent, BDAdmin admin) {
		
		this.admin = admin;
		setTitle("MENU de Adminisrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {150, 150, 150};
		gbl_contentPane.rowHeights = new int[] {50, 300, 50};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		setLocationRelativeTo(null);
		
		JPanel centro = new JPanel();
		GridBagConstraints gbc_centro = new GridBagConstraints();
		gbc_centro.anchor = GridBagConstraints.WEST;
		gbc_centro.fill = GridBagConstraints.VERTICAL;
		gbc_centro.insets = new Insets(0, 0, 5, 5);
		gbc_centro.gridx = 1;
		gbc_centro.gridy = 1;
		contentPane.add(centro, gbc_centro);
		centro.setLayout(new BorderLayout(0, 0));
		
		JPanel centroNorth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) centroNorth.getLayout();
		flowLayout.setVgap(20);
		centro.add(centroNorth, BorderLayout.NORTH);
		
		JLabel lblBienvenida = new JLabel("Bienvenid@: ");
		centroNorth.add(lblBienvenida);
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNombreAdmin = new JLabel("");
		lblNombreAdmin.setText(admin.getNombre());
		centroNorth.add(lblNombreAdmin);
		
		JPanel centroCenter = new JPanel();
		centro.add(centroCenter, BorderLayout.CENTER);
		centroCenter.setLayout(new GridLayout(2, 2, 10, 30));
		
		JButton btnAnyadirTareas = new JButton("Añadir Tareas");
		btnAnyadirTareas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VAsignarTareas VAsignarTareas = new VAsignarTareas (VAdmin1.this, admin);
				VAsignarTareas.setVisible(true);
				VAdmin1.this.setVisible(false);
			}
		});
		centroCenter.add(btnAnyadirTareas);
		btnAnyadirTareas.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnAnyadirEmpleados = new JButton("Añadir Empleados");
		btnAnyadirEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VAnyadirTrabajador VAnyadirTrabajador = new VAnyadirTrabajador (VAdmin1.this, admin);
				VAnyadirTrabajador.setVisible(true);
				VAdmin1.this.setVisible(false);
			}
		});
		centroCenter.add(btnAnyadirEmpleados);
		
		JButton btnVerTareas = new JButton("Ver Tareas");
		btnVerTareas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VVerTareas VVerTareas = new VVerTareas (VAdmin1.this, admin);
				VVerTareas.setVisible(true);
				VAdmin1.this.setVisible(false);
			}
		});
		centroCenter.add(btnVerTareas);
		
		JButton btnVisualizarEmpleados = new JButton("Ver Empleados");
		btnVisualizarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VVerTrabajadores VVerTrabajadores = new VVerTrabajadores(VAdmin1.this, admin);
				VVerTrabajadores.setVisible(true);
				VAdmin1.this.setVisible(false);
			}
		});
		centroCenter.add(btnVisualizarEmpleados);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VAdmin1.this.dispose();
			}
		});
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrarSesion.gridx = 1;
		gbc_btnCerrarSesion.gridy = 2;
		contentPane.add(btnCerrarSesion, gbc_btnCerrarSesion);

		
		AppUI.styleBackground(contentPane);
		AppUI.styleCard(centro);

		AppUI.styleTransparent(centroNorth);
		AppUI.styleTransparent(centroCenter);

		AppUI.styleSubtitle(lblBienvenida);
		AppUI.styleTitle(lblNombreAdmin);

		AppUI.stylePrimaryButton(btnAnyadirTareas);
		AppUI.stylePrimaryButton(btnAnyadirEmpleados);
		AppUI.stylePrimaryButton(btnVerTareas);
		AppUI.stylePrimaryButton(btnVisualizarEmpleados);
		AppUI.stylePrimaryButton(btnCerrarSesion);

	}
	

}
