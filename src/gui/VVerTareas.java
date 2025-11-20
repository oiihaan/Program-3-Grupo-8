package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.BDAdmin;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VVerTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VAdmin1 parent;
	private BDAdmin admin;


	public VVerTareas(VAdmin1 parent, BDAdmin admin) {
		this.parent = parent;
		this.admin = admin;
		
		setTitle("Ver Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100, 299, 100};
		gridBagLayout.rowHeights = new int[] {50, 300, 50};
		gridBagLayout.columnWeights = new double[]{0.0,0.0,0.0};
		gridBagLayout.rowWeights = new double[]{0.0,1.0,0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel centro = new JPanel();
		GridBagConstraints gbc_centro = new GridBagConstraints();
		gbc_centro.insets = new Insets(0, 0, 5, 5);
		gbc_centro.fill = GridBagConstraints.BOTH;
		gbc_centro.gridx = 1;
		gbc_centro.gridy = 1;
		getContentPane().add(centro, gbc_centro);
		centro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel left = new JPanel();
		centro.add(left);
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.insets = new Insets(0, 0, 5, 5);
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.gridx = 0;
		gbc_left.gridy = 1;
		centro.add(left, gbc_left);
		left.setLayout(new GridLayout(1, 1, 0, 0));
		
		
		JScrollPane scrollPane = new JScrollPane();
		left.add(scrollPane);
		
		JList list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		scrollPane.setViewportView(list);
		
		JPanel right = new JPanel();
		centro.add(right);
		right.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel firstRowCe = new JPanel();
		right.add(firstRowCe);
		firstRowCe.setLayout(null);
		
		JLabel lblEstado = new JLabel("Estado de la tarea: ");
		lblEstado.setBounds(10, 10, 90, 13);
		firstRowCe.add(lblEstado);
		
		JLabel lblEstadoVar = new JLabel("\"Estado\"");
		lblEstadoVar.setBounds(10, 33, 90, 13);
		firstRowCe.add(lblEstadoVar);
		
		JPanel secondRowCe = new JPanel();
		right.add(secondRowCe);
		
		JPanel thirdRowCe = new JPanel();
		right.add(thirdRowCe);
		thirdRowCe.setLayout(null);
		
		JLabel lblProgreso = new JLabel("Progreso de la tarea:");
		lblProgreso.setBounds(10, 10, 98, 13);
		thirdRowCe.add(lblProgreso);
		
		JProgressBar progressBarTarea = new JProgressBar();
		progressBarTarea.setBounds(10, 32, 98, 14);
		thirdRowCe.add(progressBarTarea);
		
		JPanel forthRowCe = new JPanel();
		right.add(forthRowCe);
		forthRowCe.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VVerTareas.this.dispose();
			}
		});
		btnVolver.setBounds(28, 51, 85, 21);
		forthRowCe.add(btnVolver);
		

	}
}
