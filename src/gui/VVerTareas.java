package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;

public class VVerTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public VVerTareas() {
		setTitle("Ver Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {300, 300};
		gbl_contentPane.rowHeights = new int[] {400};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JList listTareas = new JList();
		GridBagConstraints gbc_listTareas = new GridBagConstraints();
		gbc_listTareas.fill = GridBagConstraints.BOTH;
		gbc_listTareas.insets = new Insets(0, 0, 5, 5);
		gbc_listTareas.gridx = 0;
		gbc_listTareas.gridy = 0;
		contentPane.add(listTareas, gbc_listTareas);
		
		JPanel right = new JPanel();
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.insets = new Insets(0, 0, 5, 0);
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 1;
		gbc_right.gridy = 0;
		contentPane.add(right, gbc_right);
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		JLabel lblEstado = new JLabel("Estado de la tarea: ");
		right.add(lblEstado);
		
		JLabel lblEstadoVar = new JLabel("\"Aqui aparecera el estado de la tarea seleccionada\"");
		right.add(lblEstadoVar);
		
		JLabel lblTrabajador = new JLabel("Trabajando en la tarea: ");
		right.add(lblTrabajador);
		
		JLabel lblTrabajadores = new JLabel("\"Aqui apareceran el/los trabajador/es  a cargo de la tarea\"");
		right.add(lblTrabajadores);
		
		JLabel lblProgreso = new JLabel("Progreso de la tarea:");
		right.add(lblProgreso);
		
		JProgressBar progressBarTarea = new JProgressBar();
		right.add(progressBarTarea);
		
		JButton btnAsignarTareas = new JButton("AsignarTareas");
		right.add(btnAsignarTareas);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		contentPane.add(label, gbc_label);
		
		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		contentPane.add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		contentPane.add(label_2, gbc_label_2);
		
		JLabel label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		contentPane.add(label_3, gbc_label_3);
		
		JLabel label_4 = new JLabel("");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 5;
		contentPane.add(label_4, gbc_label_4);
		
		JLabel label_5 = new JLabel("");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 6;
		contentPane.add(label_5, gbc_label_5);

	}
}
