package gui;

import java.awt.EventQueue;
import gui.VPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.CardLayout;
import javax.swing.SpringLayout;
import domain.BDAdmin;
import domain.BDTrabajador;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VVerTrabajadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VAdmin1 parent;
	private BDAdmin admin;

	/**
	 * Create the frame.
	 */
	public VVerTrabajadores(VAdmin1 parent, BDAdmin admin) {
		this.parent = parent;
		this.admin = admin;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {100, 300, 100};
		gbl_contentPane.rowHeights = new int[] {50, 300, 50};
		gbl_contentPane.columnWeights = new double[]{0.0,0.0,0.0};
		gbl_contentPane.rowWeights = new double[]{0.0,0.0,0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel principal = new JPanel();
		GridBagConstraints gbc_principal = new GridBagConstraints();
		gbc_principal.fill = GridBagConstraints.VERTICAL;
		gbc_principal.gridx = 1;
		gbc_principal.gridy = 1;
		contentPane.add(principal, gbc_principal);
		GridBagLayout gbl_principal = new GridBagLayout();
		gbl_principal.columnWidths = new int[]{300, 0};
		gbl_principal.rowHeights = new int[]{269, 31, 0};
		gbl_principal.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_principal.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		principal.setLayout(gbl_principal);
		
		JPanel centro = new JPanel();
		GridBagConstraints gbc_centro = new GridBagConstraints();
		gbc_centro.insets = new Insets(0, 0, 5, 0);
		gbc_centro.fill = GridBagConstraints.BOTH;
		gbc_centro.gridx = 0;
		gbc_centro.gridy = 0;
		principal.add(centro, gbc_centro);
		GridBagLayout gbl_centro = new GridBagLayout();
		gbl_centro.columnWidths = new int[] {130, 1, 130};
		gbl_centro.rowHeights = new int[] {270};
		gbl_centro.columnWeights = new double[]{1.0,0.0,0.0};
		gbl_centro.rowWeights = new double[]{1.0};
		centro.setLayout(gbl_centro);
		
		JPanel left = new JPanel();
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.insets = new Insets(0, 0, 0, 5);
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.gridx = 0;
		gbc_left.gridy = 0;
		centro.add(left, gbc_left);
		left.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollListTrab = new JScrollPane();
		left.add(scrollListTrab);
		
		JList listTrabajadores = new JList();
		listTrabajadores.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				BDTrabajador trabajador = (BDTrabajador) listTrabajadores.getSelectedValue();
				HashMap<LocalDate, ArrayList<LocalDateTime>> fichajes = trabajador.getRegistrosFichaje();
			}
		});
		DefaultListModel<BDTrabajador> modeloTrabajadores = new DefaultListModel<BDTrabajador>();
		for (BDTrabajador t : VPrincipal.getTrabajadores()) {
			modeloTrabajadores.addElement(t);
		}
		listTrabajadores.setModel(modeloTrabajadores);
		scrollListTrab.add(listTrabajadores);
		scrollListTrab.setViewportView(listTrabajadores);
		
		JPanel right = new JPanel();
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.fill = GridBagConstraints.VERTICAL;
		gbc_right.gridx = 2;
		gbc_right.gridy = 0;
		centro.add(right, gbc_right);
		right.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollListFichajes = new JScrollPane();
		right.add(scrollListFichajes);
		
		JList listFichajes = new JList();
		scrollListFichajes.add(listFichajes);
		DefaultListModel<LocalDate> modelolistFichajes = new DefaultListModel<LocalDate>();
		listFichajes.setModel(modelolistFichajes);
		scrollListFichajes.setViewportView(listFichajes);
		
		JPanel south = new JPanel();
		GridBagConstraints gbc_south = new GridBagConstraints();
		gbc_south.anchor = GridBagConstraints.NORTH;
		gbc_south.fill = GridBagConstraints.HORIZONTAL;
		gbc_south.gridx = 0;
		gbc_south.gridy = 1;
		principal.add(south, gbc_south);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VVerTrabajadores.this.dispose();
			}
		});
		south.add(btnVolver);
		
		
	}

}
