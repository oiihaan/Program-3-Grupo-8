package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class VVerTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VVerTareas frame = new VVerTareas();
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
	public VVerTareas() {
		setTitle("Ver Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listTareas = new JList();
		listTareas.setBounds(10, 11, 120, 239);
		contentPane.add(listTareas);
		
		JLabel lblEstado = new JLabel("Estado de la tarea: ");
		lblEstado.setBounds(140, 12, 95, 14);
		contentPane.add(lblEstado);
		
		JLabel lblEstadoVar = new JLabel("\"Aqui aparecera el estado de la tarea seleccionada\"");
		lblEstadoVar.setBounds(140, 37, 247, 33);
		contentPane.add(lblEstadoVar);
		
		JLabel lblTrabajador = new JLabel("Trabajando en la tarea: ");
		lblTrabajador.setBounds(140, 81, 247, 14);
		contentPane.add(lblTrabajador);
		
		JLabel lblTrabajadores = new JLabel("\"Aqui apareceran el/los trabajador/es  a cargo de la tarea\"");
		lblTrabajadores.setBounds(140, 106, 247, 14);
		contentPane.add(lblTrabajadores);
		
		JLabel lblProgreso = new JLabel("Progreso de la tarea:");
		lblProgreso.setBounds(140, 144, 120, 14);
		contentPane.add(lblProgreso);
		
		JProgressBar progressBarTarea = new JProgressBar();
		progressBarTarea.setBounds(140, 169, 146, 14);
		contentPane.add(progressBarTarea);
		
		JButton btnAsignarTareas = new JButton("AsignarTareas");
		btnAsignarTareas.setBounds(140, 216, 146, 23);
		contentPane.add(btnAsignarTareas);

	}
}
