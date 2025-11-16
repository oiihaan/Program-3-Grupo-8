package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

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
		setBounds(100, 100, 604, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 200, 30};
		gbl_contentPane.rowHeights = new int[] {20, 300, 20};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JList listTareas = new JList();
		GridBagConstraints gbc_listTareas = new GridBagConstraints();
		gbc_listTareas.fill = GridBagConstraints.BOTH;
		gbc_listTareas.insets = new Insets(0, 0, 5, 5);
		gbc_listTareas.gridx = 0;
		gbc_listTareas.gridy = 1;
	
		
		JList listTrabajadores = new JList();
		GridBagConstraints gbc_listTrabajadores = new GridBagConstraints();
		gbc_listTrabajadores.fill = GridBagConstraints.BOTH;
		gbc_listTrabajadores.insets = new Insets(0, 0, 5, 0);
		gbc_listTrabajadores.gridx = 2;
		gbc_listTrabajadores.gridy = 1;
		
		
		JPanel Centro = new JPanel();
		GridBagConstraints gbc_Centro = new GridBagConstraints();
		gbc_Centro.insets = new Insets(0, 0, 5, 5);
		gbc_Centro.fill = GridBagConstraints.BOTH;
		gbc_Centro.gridx = 1;
		gbc_Centro.gridy = 1;
		contentPane.add(Centro, gbc_Centro);
		Centro.setLayout(new BorderLayout(0, 0));
		
		JPanel centroNorth = new JPanel();
		Centro.add(centroNorth, BorderLayout.NORTH);
		centroNorth.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTareas = new JLabel("Tareas");
		GridBagConstraints gbc_lblTareas = new GridBagConstraints();
		gbc_lblTareas.insets = new Insets(0, 0, 5, 5);
		gbc_lblTareas.gridx = 1;
		gbc_lblTareas.gridy = 0;
		centroNorth.add(lblTareas, gbc_lblTareas);
		lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTrabajadores = new JLabel("Trabajadores");
		GridBagConstraints gbc_lblTrabajadores = new GridBagConstraints();
		gbc_lblTrabajadores.insets = new Insets(0, 0, 5, 0);
		gbc_lblTrabajadores.gridx = 2;
		gbc_lblTrabajadores.gridy = 0;
		centroNorth.add(lblTrabajadores, gbc_lblTrabajadores);
		lblTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
				
	
		
		JPanel centroSouth = new JPanel();
		Centro.add(centroSouth, BorderLayout.SOUTH);
		
		JButton btnAsignarTarea = new JButton("Asignar Tarea");
		centroSouth.add(btnAsignarTarea);
		
		JPanel centroCentro = new JPanel();
		Centro.add(centroCentro, BorderLayout.CENTER);
		centroCentro.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel centroDe = new JPanel();
		centroCentro.add(centroDe, BorderLayout.EAST);
		centroDe.setLayout(new GridLayout(0, 1, 0, 0));
		centroDe.setPreferredSize(new Dimension(50, 0)); 
		
		JScrollPane scrollPaneCentroDe = new JScrollPane();
		GridBagConstraints gbc_scrollPaneCentroDe = new GridBagConstraints();
		gbc_scrollPaneCentroDe.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCentroDe.gridx = 0;
		gbc_scrollPaneCentroDe.gridy = 0;
		centroDe.add(scrollPaneCentroDe);
		scrollPaneCentroDe.setViewportView(listTrabajadores);
		
		JPanel centroIz = new JPanel();
		centroCentro.add(centroIz);
		centroIz.setLayout(new GridLayout(0, 1, 0, 0));
		centroIz.setPreferredSize(new Dimension(50, 0));
		
		JScrollPane scrollPaneCentroIz = new JScrollPane();
		centroIz.add(scrollPaneCentroIz);
		scrollPaneCentroIz.setViewportView(listTareas);

	}
}
