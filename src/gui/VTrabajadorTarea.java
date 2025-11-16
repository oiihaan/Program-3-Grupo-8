package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.BDTrabajador;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;

public class VTrabajadorTarea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VTrabajador1 parent;
	



	/**
	 * Create the frame.
	 */
	public VTrabajadorTarea(VTrabajador1 parent) {
		this.parent = parent;

		setTitle("Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {150, 300, 150};
		gbl_contentPane.rowHeights = new int[] {50, 300, 50};
		gbl_contentPane.columnWeights = new double[]{0.0,0.0,0.0};
		gbl_contentPane.rowWeights = new double[]{0.0,0.0,0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel Centro = new JPanel();
		GridBagConstraints gbc_Centro = new GridBagConstraints();
		gbc_Centro.insets = new Insets(0, 0, 5, 5);
		gbc_Centro.fill = GridBagConstraints.BOTH;
		gbc_Centro.gridx = 1;
		gbc_Centro.gridy = 1;
		contentPane.add(Centro, gbc_Centro);
		GridBagLayout gbl_Centro = new GridBagLayout();
		gbl_Centro.columnWidths = new int[] {150, 150};
		gbl_Centro.rowHeights = new int[] {50, 200, 50};
		gbl_Centro.columnWeights = new double[]{0.0,0.0};
		gbl_Centro.rowWeights = new double[]{0.0,0.0,0.0};
		Centro.setLayout(gbl_Centro);
		
		JPanel centroIz = new JPanel();
		GridBagConstraints gbc_centroIz = new GridBagConstraints();
		gbc_centroIz.insets = new Insets(0, 0, 5, 5);
		gbc_centroIz.fill = GridBagConstraints.BOTH;
		gbc_centroIz.gridx = 0;
		gbc_centroIz.gridy = 1;
		Centro.add(centroIz, gbc_centroIz);
		centroIz.setLayout(new GridLayout(1, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		centroIz.add(scrollPane);
		
		JList list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		scrollPane.setViewportView(list);
		
		JPanel centroDe = new JPanel();
		centroDe.setLayout(null);
		GridBagConstraints gbc_centroDe = new GridBagConstraints();
		gbc_centroDe.insets = new Insets(0, 0, 5, 0);
		gbc_centroDe.fill = GridBagConstraints.BOTH;
		gbc_centroDe.gridx = 1;
		gbc_centroDe.gridy = 1;
		Centro.add(centroDe, gbc_centroDe);
		
		
		JButton btnEmpezarTarea = new JButton("Empezar Tarea");
		btnEmpezarTarea.setEnabled(false);
		btnEmpezarTarea.setBounds(21, 63, 103, 21);
		centroDe.add(btnEmpezarTarea);
		
		JButton btnFinalizarTarea = new JButton("Finalizar Tarea");
		btnFinalizarTarea.setEnabled(false);
		btnFinalizarTarea.setBounds(21, 120, 101, 21);
		centroDe.add(btnFinalizarTarea);
	
	}
}
