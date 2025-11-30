package gui;

import java.awt.EventQueue;
import gui.VPrincipal;
import gui.ui.AppUI;

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
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.CardLayout;
import javax.swing.SpringLayout;
import domain.BDAdmin;
import domain.BDTrabajador;
import domain.Usuario;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import bd.TrabajadorDAO;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

public class VVerTrabajadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VAdmin1 parent;
	private BDAdmin admin;
	private JLabel lblTrabajador;
	private JList<BDTrabajador> listTrabajadores;
	private DefaultListModel<BDTrabajador> modeloTrabajadores;
	 DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	/**
	 * Create the frame.
	 */
	public VVerTrabajadores(VAdmin1 parent, BDAdmin admin , Integer trabajador) {
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
		setLocationRelativeTo(null);
		
		lblTrabajador = new JLabel("Ningun trabajador selecionado");
		GridBagConstraints gbc_lblTrabajador = new GridBagConstraints();
		gbc_lblTrabajador.insets = new Insets(0, 0, 5, 5);
		gbc_lblTrabajador.gridx = 1;
		gbc_lblTrabajador.gridy = 0;
		contentPane.add(lblTrabajador, gbc_lblTrabajador);
		
		JPanel principal = new JPanel();
		GridBagConstraints gbc_principal = new GridBagConstraints();
		gbc_principal.insets = new Insets(0, 0, 5, 5);
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
		listFichajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollListFichajes.add(listFichajes);
		DefaultListModel<String> modelolistFichajes = new DefaultListModel<String>();
		listFichajes.setModel(modelolistFichajes);
		scrollListFichajes.setViewportView(listFichajes);
		
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
		
		listTrabajadores = new JList();
		listTrabajadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollListTrab.add(listTrabajadores);
		scrollListTrab.setViewportView(listTrabajadores);
		modeloTrabajadores = new DefaultListModel<BDTrabajador>();

		for (BDTrabajador t : TrabajadorDAO.getAllTrabajadores()) {
				modeloTrabajadores.addElement(t);
			//QUE COJA LOS ELEMENTOS DE LA BASE DE DATOS MEJOR

		}
		listTrabajadores.setModel(modeloTrabajadores);
		
		if(trabajador == null) {
			listTrabajadores.setSelectedIndex(-1);
		}else {
			for (Object t: modeloTrabajadores.toArray()) {
				BDTrabajador tra = (BDTrabajador) t;
				if(tra.getId() == trabajador ) {
					Integer index = modeloTrabajadores.indexOf(tra);
					System.out.println(index);
					listTrabajadores.setSelectedIndex(index);
				}
			}

		}

		
		
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
		
		JButton btnDespedir = new JButton("Despedir");
		btnDespedir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    BDTrabajador seleccionado = listTrabajadores.getSelectedValue();

			    if (seleccionado == null) {
			        JOptionPane.showMessageDialog(
			                VVerTrabajadores.this,
			                "Selecciona primero un trabajador de la lista.",
			                "Ningún trabajador seleccionado",
			                JOptionPane.WARNING_MESSAGE
			        );
			        return;
			    }

			    // 2) Confirmar
			    int opcion = JOptionPane.showConfirmDialog(
			            VVerTrabajadores.this,
			            "¿Seguro que quieres despedir a '" + seleccionado.getNombre() + "'?",
			            "Confirmar despido",
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.WARNING_MESSAGE
			    );

			    if (opcion != JOptionPane.YES_OPTION) {
			        return; // el usuario se ha echado atrás
			    }

			    // 3) Borrar de la base de datos
			    // Usamos el DAO 
			    TrabajadorDAO.eliminarPorNombre(seleccionado.getNombre());

			    // 4) Borrar de las estructuras en memoria de VPrincipal
			    VPrincipal.getTrabajadores().remove(seleccionado);
			    VPrincipal.getPersonal().remove(seleccionado); // si tienes getter, o vuelve a cargarPersonal()

			    // 5) Borrar del modelo de la JList
			    modeloTrabajadores.removeElement(seleccionado);

			    // 6) Avisar
			    JOptionPane.showMessageDialog(
			            VVerTrabajadores.this,
			            "Trabajador despedido correctamente.",
			            "Despedido",
			            JOptionPane.INFORMATION_MESSAGE
			    );
			}
		});
		
		south.add(btnDespedir);
		south.add(btnVolver);
		
		
		
	//--- LÓGICA DE LISTAS ---
		
		//-- LISTA TRABAJADORES
		listTrabajadores.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
							modelolistFichajes.clear();
							if(!listTrabajadores.isSelectionEmpty()) {  // Entra si lo selecionado no es null
								BDTrabajador trabajador = (BDTrabajador) listTrabajadores.getSelectedValue();
								lblTrabajador.setText("Trabajador selecionado : " + trabajador.getNombre());
								HashMap<LocalDate, ArrayList<LocalDateTime>> fichajes = trabajador.getRegistrosFichaje();
								for(LocalDate clave : fichajes.keySet()) {
									ArrayList<LocalDateTime> lista = fichajes.get(clave);
								    for (int i = 0; i < lista.size(); i += 2) {
								    	
								    	if (i + 1 < lista.size()) { // Hay pareja: grupo de dos
								            String fichaje = lista.get(i).format(formato);
								            String desfichaje = lista.get(i + 1).format(formato);
								            modelolistFichajes.addElement(fichaje+ " - " + desfichaje);
								            
								        } else {
								            // Queda uno solo
								        	System.out.println("Entrado");
								            String fichaje = lista.get(i).format(formato);
								            modelolistFichajes.addElement(fichaje+ " - " + "()");
								        }	
									}
	
								}
								listFichajes.setModel(modelolistFichajes);

							}else { //Si lo selecionado es null
								lblTrabajador.setText("Ningun trabajador selecionado");

							}

			}
		});
		
		//Estilo AppUI
		AppUI.styleBackground(contentPane);
		AppUI.styleCard(principal);

		AppUI.styleTransparent(centro);
		AppUI.styleTransparent(left);
		AppUI.styleTransparent(right);
		AppUI.styleTransparent(south);

		AppUI.styleLabel(lblTrabajador);

		AppUI.styleList(listTrabajadores);
		AppUI.styleList(listFichajes);

		AppUI.stylePrimaryButton(btnVolver);
		AppUI.stylePrimaryButton(btnDespedir);
	

		//-- LISTA FICHAJES --

	}
}
