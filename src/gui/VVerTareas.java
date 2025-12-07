package gui;


//IMPORTS
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import bd.TareaDAO;
import bd.TrabajadorDAO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class VVerTareas extends VentanaConConfirmacion {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VAdmin1 parent;
	private BDAdmin admin;
	private JLabel lblEstadoVar;
	private JList listaTareas;
	private JTextField txtBuscadorTareas;
	private JLabel buscadorTareas;
	private JLabel lblTrabajadoresAsignados;
	private JLabel lblEstado;
	private Boolean finalizadasVer;


	public VVerTareas(VAdmin1 parent, BDAdmin admin) {
		super();
		this.parent = parent;
		this.admin = admin;
		this.finalizadasVer = false;
		
		setTitle("Ver Tareas");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {299};
		gridBagLayout.rowHeights = new int[] {300};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
		
		
		//DISEÑO
		//PANELES
		JPanel centro = new JPanel();
		GridBagConstraints gbc_centro = new GridBagConstraints();
		gbc_centro.fill = GridBagConstraints.BOTH;
		gbc_centro.gridx = 0;
		gbc_centro.gridy = 0;
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
		
		JPanel right = new JPanel();
		centro.add(right);
		right.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel firstRowCe = new JPanel();
		right.add(firstRowCe);
		firstRowCe.setLayout(null);
		buscadorTareas = new JLabel("Buscador de tareas: ");
		buscadorTareas.setBounds(10, 11, 127, 13);
		firstRowCe.add(buscadorTareas);
		
		JPanel secondRowCe = new JPanel();
		right.add(secondRowCe);
		secondRowCe.setLayout(null);
		
		JPanel thirdRowCe = new JPanel();
		right.add(thirdRowCe);
		thirdRowCe.setLayout(null);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 25, 216, 95);
		thirdRowCe.add(scrollPane_1);
		
		JPanel forthRowCe = new JPanel();
		right.add(forthRowCe);
		forthRowCe.setLayout(null);
		
		
		
		//SCROLLPANE
		JScrollPane scrollPane = new JScrollPane();
		left.add(scrollPane);
		listaTareas = new JList();

		//LISTA
		listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<BDTarea> modeloTareas = new DefaultListModel<BDTarea>();
		for (BDTarea t : TareaDAO.getAllTareas()) {
			modeloTareas.addElement(t);
		}
		listaTareas.setModel(modeloTareas);
		scrollPane.setViewportView(listaTareas);

		JList listaTrabajadores = new JList();
		scrollPane_1.setViewportView(listaTrabajadores); 
	
		DefaultListModel<BDTrabajador> modeloListaTrabajadores = new DefaultListModel<BDTrabajador>();
		
		listaTrabajadores.setModel(modeloListaTrabajadores);
		
		//TEXTFIELDS
		txtBuscadorTareas = new JTextField();

		txtBuscadorTareas.setBounds(20, 44, 222, 43);
		firstRowCe.add(txtBuscadorTareas);
		
		
		//LABELS
		lblEstado = new JLabel("Estado de la tarea: ");
		lblEstado.setBounds(10, 5, 124, 13);
		secondRowCe.add(lblEstado);
		
		lblEstadoVar = new JLabel();
		lblEstadoVar.setBounds(20, 29, 99, 25);
		secondRowCe.add(lblEstadoVar);

		lblTrabajadoresAsignados = new JLabel("Trabajadores asignados:");
		lblTrabajadoresAsignados.setBounds(10, 0, 286, 23);
		thirdRowCe.add(lblTrabajadoresAsignados);
		

		//BOTONES
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 61, 120, 41);
		forthRowCe.add(btnVolver);
		
		JButton btnAsignarTareas = new JButton("AsignarTareas");
		btnAsignarTareas.setEnabled(false);
		btnAsignarTareas.setBounds(10, 10, 120, 41);
		forthRowCe.add(btnAsignarTareas);

		
		//LOGICA de los BOTONES
		btnAsignarTareas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDTarea tarea =(BDTarea) listaTareas.getSelectedValue();

				VAsignarTareas VAsignarTareas = new VAsignarTareas (VVerTareas.this, admin , tarea );
				VAsignarTareas.setVisible(true);
				VVerTareas.this.setVisible(false);
				
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VVerTareas.this.dispose();
			}
		});
		
		
		
		//Aciones 
		
		
		//LOGICA de los TEXTFIELD
		txtBuscadorTareas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String busqueda = txtBuscadorTareas.getText().trim();
				modeloTareas.clear();
				if(busqueda.equals("")) {
					for (BDTarea t : TareaDAO.getAllTareas()) {
						modeloTareas.addElement(t);
					}
					listaTareas.setModel(modeloTareas);
					
					
				}else {
					for (BDTarea t : TareaDAO.buscarTareasPorNombre(busqueda)) {
						modeloTareas.addElement(t);
					}
					listaTareas.setModel(modeloTareas);
					
			}
			}
		});

		
		//LOGICA LISTAS
		listaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
				 if (evt.getClickCount() == 2) {
					 int index = listaTrabajadores.locationToIndex(evt.getPoint());
	                    if (index >= 0) {
	                        BDTrabajador seleccionado = (BDTrabajador) listaTrabajadores.getSelectedValue();
	                        VVerTrabajadores ventanaVertrabajadores = new  VVerTrabajadores(parent, admin , seleccionado.getId());
	                        ventanaVertrabajadores.setVisible(true);
	                        dispose();
	                    }
					 
					 
				 }
			}
		});
		
		listaTareas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				modeloListaTrabajadores.clear();
				if(!listaTareas.isSelectionEmpty()) {
					BDTarea tarea =(BDTarea) listaTareas.getSelectedValue();
					lblEstadoVar.setText(tarea.getEstado());
					for(BDTrabajador t : TrabajadorDAO.getTrabajadoresDeTarea(tarea.getId())) {
						modeloListaTrabajadores.addElement(t);
						}
					if(!tarea.getEstado().equals("finalizado")) {
					lblTrabajadoresAsignados.setText("Trabajadores asignados:");

					btnAsignarTareas.setEnabled(true);
					}else {
						lblTrabajadoresAsignados.setText("Trabajadores que han realizado la tarea:");
						btnAsignarTareas.setEnabled(false);

					}

					
				}else {
					lblTrabajadoresAsignados.setText("Trabajadores asignados:");
					modeloListaTrabajadores.clear();
					lblEstadoVar.setText("");
					btnAsignarTareas.setEnabled(false);
				}
			}
		});
		
			
	//Boton Paso de Ventana a ASIGNAR_TAREA

	//	GridBagConstraints gbc_btnAsignarTareas = new GridBagConstraints();
	//	gbc_btnAsignarTareas.gridx = 0;
	//	gbc_btnAsignarTareas.gridy = 0;
		// thirdRowCe.add(btnAsignarTareas, gbc_btnAsignarTareas);

		
		//Estilo AppUI
				AppUI.styleBackground(contentPane);
				AppUI.styleCard(centro);
				AppUI.styleTransparent(left);
				AppUI.styleTransparent(right);
				AppUI.styleTransparent(firstRowCe);
				

				txtBuscadorTareas.setColumns(10);
				AppUI.styleTransparent(secondRowCe);
				AppUI.styleTransparent(thirdRowCe);
				AppUI.styleTransparent(forthRowCe);

				AppUI.styleLabel(lblEstado);
				AppUI.styleLabel(lblEstadoVar);
				AppUI.styleLabel(lblTrabajadoresAsignados);
				AppUI.styleLabel(buscadorTareas);
				AppUI.styleTextField(txtBuscadorTareas);
				



				AppUI.styleList(listaTareas);
				AppUI.styleList(listaTrabajadores);
				


				AppUI.stylePrimaryButton(btnVolver);
				AppUI.stylePrimaryButton(btnAsignarTareas);
				
				
				//IMAGEN
				AppUI.establecerIcono(this);
				

	}



	@Override
	protected void onConfirmExit() {
		parent.setVisible(true);
	    parent.repaint();
	    dispose();
	}
}
