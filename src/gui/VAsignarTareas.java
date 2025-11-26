package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;

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
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.ListSelectionModel;

public class VAsignarTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VAdmin1 parent;
	private BDAdmin admin;

	/**
	 * Create the frame.
	 */
	public VAsignarTareas(VAdmin1 parent, BDAdmin admin) {
		this.parent = parent;
		this.admin = admin;
		
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
		
		JList listTrabajadores = new JList();
		GridBagConstraints gbc_listTareas = new GridBagConstraints();
		gbc_listTareas.fill = GridBagConstraints.BOTH;
		gbc_listTareas.insets = new Insets(0, 0, 5, 5);
		gbc_listTareas.gridx = 0;
		gbc_listTareas.gridy = 1;

	
		
		JList listaTareas = new JList();
		listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		FlowLayout flowLayout = (FlowLayout) centroSouth.getLayout();
		flowLayout.setHgap(30);
		Centro.add(centroSouth, BorderLayout.SOUTH);
		
		JButton btnAsignarTarea = new JButton("Asignar Tarea");
		centroSouth.add(btnAsignarTarea);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VAsignarTareas.this.dispose();
			}
		});
		centroSouth.add(btnVolver);
		
		JPanel centroCentro = new JPanel();
		Centro.add(centroCentro, BorderLayout.CENTER);
		centroCentro.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel centroIzq = new JPanel();
		centroCentro.add(centroIzq, BorderLayout.EAST);
		centroIzq.setPreferredSize(new Dimension(50, 0)); 
		
		JScrollPane scrollPaneCentroIzq = new JScrollPane();
		GridBagConstraints gbc_scrollPaneCentroDe = new GridBagConstraints();
		gbc_scrollPaneCentroDe.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCentroDe.gridx = 0;
		gbc_scrollPaneCentroDe.gridy = 0;
		SpringLayout sl_centroIzq = new SpringLayout();
		sl_centroIzq.putConstraint(SpringLayout.NORTH, scrollPaneCentroIzq, 0, SpringLayout.NORTH, centroIzq);
		sl_centroIzq.putConstraint(SpringLayout.WEST, scrollPaneCentroIzq, 0, SpringLayout.WEST, centroIzq);
		sl_centroIzq.putConstraint(SpringLayout.SOUTH, scrollPaneCentroIzq, 270, SpringLayout.NORTH, centroIzq);
		sl_centroIzq.putConstraint(SpringLayout.EAST, scrollPaneCentroIzq, 257, SpringLayout.WEST, centroIzq);
		centroIzq.setLayout(sl_centroIzq);
		centroIzq.add(scrollPaneCentroIzq);
		scrollPaneCentroIzq.setViewportView(listaTareas);
		
		JPanel centroDe = new JPanel();
		centroCentro.add(centroDe);
		centroDe.setPreferredSize(new Dimension(50, 0));
		SpringLayout sl_centroDe = new SpringLayout();
		centroDe.setLayout(sl_centroDe);
		
		JScrollPane scrollPaneCentroDe = new JScrollPane();
		sl_centroDe.putConstraint(SpringLayout.NORTH, scrollPaneCentroDe, 0, SpringLayout.NORTH, centroDe);
		sl_centroDe.putConstraint(SpringLayout.WEST, scrollPaneCentroDe, 0, SpringLayout.WEST, centroDe);
		sl_centroDe.putConstraint(SpringLayout.SOUTH, scrollPaneCentroDe, 270, SpringLayout.NORTH, centroDe);
		sl_centroDe.putConstraint(SpringLayout.EAST, scrollPaneCentroDe, 257, SpringLayout.WEST, centroDe);
		centroDe.add(scrollPaneCentroDe);
		scrollPaneCentroDe.setViewportView(listTrabajadores);
		
		
		//-- LOGICA DE LISTAS--
		
		DefaultListModel<BDTarea> modeloListTarea = new DefaultListModel<BDTarea>();
		for (BDTarea t : VPrincipal.getTareas()) {
			modeloListTarea.addElement(t);
		}
		listaTareas.setModel(modeloListTarea);
		DefaultListModel<BDTrabajador> modeloListaTrabajadores = new  DefaultListModel<BDTrabajador>();
		for ( BDTrabajador t : VPrincipal.getTrabajadores()) {
			modeloListaTrabajadores.addElement(t);
		}
		listTrabajadores.setModel(modeloListaTrabajadores);
		
		
		//Estilo AppUI
		AppUI.styleBackground(contentPane);
		AppUI.styleCard(Centro);

		AppUI.styleTransparent(centroNorth);
		AppUI.styleTransparent(centroSouth);
		AppUI.styleTransparent(centroCentro);
		AppUI.styleTransparent(centroIzq);
		AppUI.styleTransparent(centroDe);

		AppUI.styleTitle(lblTareas);
		AppUI.styleTitle(lblTrabajadores);

		AppUI.stylePrimaryButton(btnAsignarTarea);
		AppUI.stylePrimaryButton(btnVolver);

		AppUI.styleList(listaTareas);
		AppUI.styleList(listTrabajadores);
	}
}
