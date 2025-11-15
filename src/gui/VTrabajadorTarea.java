package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

public class VTrabajadorTarea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VTrabajadorTarea frame = new VTrabajadorTarea();
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
	public VTrabajadorTarea() {
		setTitle("Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 84, 172, 238);
		contentPane.add(scrollPane);
		
		JList lista = new JList();
		lista.setBounds(0, 0, 1, 1);
		scrollPane.add(lista);
		
		JButton btnEmpezarTarea = new JButton("Empezar Tarea");
		btnEmpezarTarea.setEnabled(false);
		btnEmpezarTarea.setBounds(258, 97, 114, 55);
		contentPane.add(btnEmpezarTarea);
		
		JButton btnFinalizarTarea = new JButton("Finalizar Tarea");
		btnFinalizarTarea.setEnabled(false);
		btnFinalizarTarea.setBounds(447, 97, 114, 55);
		contentPane.add(btnFinalizarTarea);
	}
}
