package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VAdministrador1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAdministrador1 frame = new VAdministrador1();
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
	public VAdministrador1() {
		setTitle("MENU de Adminisrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenida = new JLabel("Bienvenido + (nombreAdmin)");
		lblBienvenida.setBounds(10, 11, 162, 14);
		contentPane.add(lblBienvenida);
		
		JButton btnAnyadirTareas = new JButton("Añadir Tareas");
		btnAnyadirTareas.setBounds(10, 36, 142, 36);
		contentPane.add(btnAnyadirTareas);
		
		JButton btnVerTareas = new JButton("Ver Tareas");
		btnVerTareas.setBounds(10, 83, 142, 36);
		contentPane.add(btnVerTareas);
		
		JButton btnAnyadirEmpleados = new JButton("Añadir Empleados");
		btnAnyadirEmpleados.setBounds(215, 36, 142, 36);
		contentPane.add(btnAnyadirEmpleados);
		
		JButton btnVisualizarEmpleados = new JButton("Ver Empleados");
		btnVisualizarEmpleados.setBounds(215, 83, 142, 36);
		contentPane.add(btnVisualizarEmpleados);

	}
}
