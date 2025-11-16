package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.BDAdmin;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		gbl_contentPane.rowHeights = new int[] {30, 400, 50};
		gbl_contentPane.columnWidths = new int[] {170, 282, 170};
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
		lblNombre.setBounds(10, 21, 69, 31);
		northIz.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 62, 118, 31);
		northIz.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel northDe = new JPanel();
		centro.add(northDe);
		northDe.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 21, 69, 31);
		northDe.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(10, 62, 118, 31);
		northDe.add(txtApellido);
		txtApellido.setColumns(10);
		
		JPanel centroIz = new JPanel();
		centro.add(centroIz);
		centroIz.setLayout(null);
		
		JLabel lblUsername = new JLabel("Nombre de usuario: ");
		lblUsername.setBounds(10, 21, 94, 31);
		centroIz.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(10, 68, 118, 31);
		centroIz .add(txtUsername);
		txtUsername.setColumns(10);
		
		JPanel centroDe = new JPanel();
		centro.add(centroDe);
		centroDe.setLayout(null);
		
		JLabel lblContrasenya = new JLabel("Contraseña: ");
		lblContrasenya.setBounds(10, 21, 59, 31);
		centroDe.add(lblContrasenya);
		
		textField = new JTextField();
		textField.setBounds(10, 68, 118, 31);
		centroDe.add(textField);
		textField.setColumns(10);
		
		JPanel southIz = new JPanel();
		centro.add(southIz);
		southIz.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAynadir = new JButton("Añadir");
		southIz.add(btnAynadir);
		
		JPanel southDe = new JPanel();
		centro.add(southDe);
		southDe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VAnyadirTrabajador.this.dispose();
			}
		});
		southDe.add(btnVolver);

	}
}
