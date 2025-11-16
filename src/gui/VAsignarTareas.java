package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VAsignarTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAsignarTareas frame = new VAsignarTareas();
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
	public VAsignarTareas() {
		setTitle("Asignar Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 244, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listTareas = new JList();
		listTareas.setBounds(10, 28, 87, 222);
		contentPane.add(listTareas);
		
		JList listTrabajadores = new JList();
		listTrabajadores.setBounds(128, 28, 87, 222);
		contentPane.add(listTrabajadores);
		
		JLabel lblTareas = new JLabel("Tareas");
		lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTareas.setBounds(10, 11, 87, 14);
		contentPane.add(lblTareas);
		
		JLabel lblTrabajadores = new JLabel("Trabajadores");
		lblTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrabajadores.setBounds(128, 11, 87, 14);
		contentPane.add(lblTrabajadores);
		
		JButton btnAsignarTarea = new JButton("Asignar Tarea");
		btnAsignarTarea.setBounds(52, 273, 114, 23);
		contentPane.add(btnAsignarTarea);

	}
}
