package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VAnyadirTrabajador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsername;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAnyadirTrabajador frame = new VAnyadirTrabajador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VAnyadirTrabajador() {
		setTitle("Añadir Trabajador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 257, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 83, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(7, 36, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(150, 11, 46, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(150, 36, 86, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblUsername = new JLabel("Nombre de usuario: ");
		lblUsername.setBounds(10, 72, 109, 14);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(10, 97, 86, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblContrasenya = new JLabel("Contraseña: ");
		lblContrasenya.setBounds(150, 72, 86, 14);
		contentPane.add(lblContrasenya);
		
		textField = new JTextField();
		textField.setBounds(150, 97, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.setBounds(4, 158, 89, 23);
		contentPane.add(btnAnyadir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(147, 158, 89, 23);
		contentPane.add(btnCancelar);

	}
}
