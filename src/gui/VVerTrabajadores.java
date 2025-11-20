package gui;

import java.awt.EventQueue;

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
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import domain.BDAdmin;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 566, 503);
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
		gbc_principal.fill = GridBagConstraints.BOTH;
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
		gbl_centro.columnWidths = new int[] {130, 40, 130};
		gbl_centro.rowHeights = new int[] {270};
		gbl_centro.columnWeights = new double[]{1.0,0.0,1.0};
		gbl_centro.rowWeights = new double[]{1.0};
		centro.setLayout(gbl_centro);
		
		JPanel left = new JPanel();
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.insets = new Insets(0, 0, 0, 5);
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.gridx = 0;
		gbc_left.gridy = 0;
		centro.add(left, gbc_left);
		SpringLayout sl_left = new SpringLayout();
		left.setLayout(sl_left);
		
		JScrollPane scrollListTrab = new JScrollPane();
		sl_left.putConstraint(SpringLayout.NORTH, scrollListTrab, 0, SpringLayout.NORTH, left);
		sl_left.putConstraint(SpringLayout.WEST, scrollListTrab, 0, SpringLayout.WEST, left);
		sl_left.putConstraint(SpringLayout.SOUTH, scrollListTrab, 270, SpringLayout.NORTH, left);
		left.add(scrollListTrab);
		
		JList listTrabajadores = new JList();
		scrollListTrab.add(listTrabajadores);
		scrollListTrab.setViewportView(listTrabajadores);
		
		JPanel right = new JPanel();
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 2;
		gbc_right.gridy = 0;
		centro.add(right, gbc_right);
		SpringLayout sl_right = new SpringLayout();
		right.setLayout(sl_right);
		
		JScrollPane scrollListFichajes = new JScrollPane();
		sl_right.putConstraint(SpringLayout.NORTH, scrollListFichajes, 0, SpringLayout.NORTH, right);
		sl_right.putConstraint(SpringLayout.WEST, scrollListFichajes, 0, SpringLayout.WEST, right);
		sl_right.putConstraint(SpringLayout.SOUTH, scrollListFichajes, 270, SpringLayout.NORTH, right);
		right.add(scrollListFichajes);
		
		JList listFichajes = new JList();
		scrollListFichajes.add(listFichajes);
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
