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
import java.awt.BorderLayout;


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
	private JList listaTrabajadores;


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
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JPanel panelCard = new JPanel();
        contentPane.add(panelCard);
        GridBagLayout gbl_panelCard = new GridBagLayout();
        gbl_panelCard.columnWidths = new int[]{50, 500, 50};
        gbl_panelCard.rowHeights = new int[]{50, 500, 50, 50};
        gbl_panelCard.columnWeights = new double[]{0.0, 1.0, 0.0};
        gbl_panelCard.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
        panelCard.setLayout(gbl_panelCard);
		DefaultListModel<BDTarea> modeloTareas = new DefaultListModel<BDTarea>();
		for (BDTarea t : TareaDAO.getAllTareas()) {
			modeloTareas.addElement(t);
		}
	
		DefaultListModel<BDTrabajador> modeloListaTrabajadores = new DefaultListModel<BDTrabajador>();
		


		
		//Estilo AppUI
				
				
				//DISEÑO
				//PANELES
	 JPanel centro = new JPanel();
	    GridBagConstraints gbc_centro_1 = new GridBagConstraints();
	    gbc_centro_1.insets = new Insets(0, 0, 5, 5);
	    gbc_centro_1.gridx = 1;
	    gbc_centro_1.gridy = 1;
	    gbc_centro_1.weightx = 1.0;
	    gbc_centro_1.weighty = 1.0;
	    gbc_centro_1.fill = GridBagConstraints.BOTH;
	    panelCard.add(centro, gbc_centro_1);
	    centro.setLayout(new GridLayout(0, 2, 0, 0));
	
	JPanel left = new JPanel();
	centro.add(left);
	left.setLayout(new GridLayout(1,1,0,0));
	
	JPanel right = new JPanel();
	centro.add(right);
	GridBagLayout gbl_right = new GridBagLayout();
	gbl_right.columnWidths  = new int[] {258};
	gbl_right.rowHeights    = new int[] {100, 50, 100, 30};
	gbl_right.columnWeights = new double[]{1.0};
	gbl_right.rowWeights    = new double[]{ 1.0, 1.0, 1.0};
	right.setLayout(gbl_right);
	
	JPanel firstRowCe = new JPanel();
	GridBagConstraints gbc_firstRowCe = new GridBagConstraints();
	gbc_firstRowCe.fill = GridBagConstraints.BOTH;
	gbc_firstRowCe.insets = new Insets(0, 0, 5, 0);
	gbc_firstRowCe.gridx = 0;
	gbc_firstRowCe.gridy = 0;
	gbc_firstRowCe.weightx = 1.0;
	gbc_firstRowCe.weighty = 0.0;
	right.add(firstRowCe, gbc_firstRowCe);
	firstRowCe.setLayout(null);
	buscadorTareas = new JLabel("Buscador de tareas: ");
	buscadorTareas.setBounds(41, 11, 127, 13);
	firstRowCe.add(buscadorTareas);

	//TEXTFIELDS
	txtBuscadorTareas = new JTextField();
	
			txtBuscadorTareas.setBounds(41, 42, 211, 43);
			firstRowCe.add(txtBuscadorTareas);
			
			
			
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
	
	

	txtBuscadorTareas.setColumns(10);
				
	JPanel secondRowCe = new JPanel();
	GridBagConstraints gbc_secondRowCe = new GridBagConstraints();
	gbc_secondRowCe.fill = GridBagConstraints.BOTH;
	gbc_secondRowCe.insets = new Insets(0, 0, 5, 0);
	gbc_secondRowCe.gridx = 0;
	gbc_secondRowCe.gridy = 1;
	gbc_secondRowCe.weightx = 1.0;
	gbc_secondRowCe.weighty = 0.0;
	right.add(secondRowCe, gbc_secondRowCe);
	secondRowCe.setLayout(null);
	
	
	//LABELS
	lblEstado = new JLabel("Estado de la tarea: ");
	lblEstado.setBounds(41, 5, 173, 18);
	secondRowCe.add(lblEstado);
	
	lblEstadoVar = new JLabel();
	lblEstadoVar.setBounds(41, 33, 99, 28);
	secondRowCe.add(lblEstadoVar);
	
	
	JPanel thirdRowCe = new JPanel();
	GridBagConstraints gbc_thirdRowCe = new GridBagConstraints();
	gbc_thirdRowCe.fill = GridBagConstraints.BOTH;
	gbc_thirdRowCe.gridx = 0;
	gbc_thirdRowCe.gridy = 2;
	gbc_thirdRowCe.weightx = 1.0;
	gbc_thirdRowCe.weighty = 1.0; // esta fila puede crecer
	right.add(thirdRowCe, gbc_thirdRowCe);
	thirdRowCe.setLayout(null);
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(41, 25, 207, 95);
				thirdRowCe.add(scrollPane_1);
				
	listaTrabajadores = new JList();
	scrollPane_1.setViewportView(listaTrabajadores); 
	
	listaTrabajadores.setModel(modeloListaTrabajadores);
				
	lblTrabajadoresAsignados = new JLabel("Trabajadores asignados:");
	lblTrabajadoresAsignados.setBounds(41, 0, 286, 23);
	thirdRowCe.add(lblTrabajadoresAsignados);
			
					
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
					
				
		JPanel south = new JPanel();
		GridBagConstraints gbc_south = new GridBagConstraints();
		gbc_south.insets = new Insets(0, 0, 5, 5);
		gbc_south.fill = GridBagConstraints.BOTH;
		gbc_south.gridx = 1;
		gbc_south.gridy = 2;
		panelCard.add(south, gbc_south);
		left.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		//SCROLLPANE
		JScrollPane scrollPane = new JScrollPane();
		left.add(scrollPane);
		listaTareas = new JList() {

            @Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index == -1) {
                    return null;
                }else {
                  BDTarea t = (BDTarea) getModel().getElementAt(index);

                return "La tarea dura " + t.getDuracion() + " minutos";
                }
            }
				};
				
		//LISTA
		listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaTareas.setModel(modeloTareas);
		scrollPane.setViewportView(listaTareas);
			
		
		
			
											
		//BOTONES
		
		JButton btnAsignarTareas = new JButton("AsignarTareas");
		south.add(btnAsignarTareas);
		btnAsignarTareas.setEnabled(false);
					
			listaTareas.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					modeloListaTrabajadores.clear();
					if(!listaTareas.isSelectionEmpty()) {
						BDTarea tarea =(BDTarea) listaTareas.getSelectedValue();
						lblEstadoVar.setText(tarea.getEstado());
						listaTrabajadoresActualizar(tarea.getId());
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
																

				
				
				
		//IMAGEN
		AppUI.establecerIcono(this);
        AppUI.configurarVentana(this);
        AppUI.styleTransparent(right);
		AppUI.styleTransparent(left);
		AppUI.styleList(listaTareas);
		AppUI.stylePrimaryButton(btnAsignarTareas);
		AppUI.styleTransparent(thirdRowCe);
		AppUI.styleLabel(lblTrabajadoresAsignados);
		AppUI.styleList(listaTrabajadores);
		AppUI.styleTransparent(secondRowCe);						
		AppUI.styleLabel(lblEstado);
		AppUI.styleLabel(lblEstadoVar);
		AppUI.styleBackground(contentPane);
		AppUI.styleBackground(panelCard);
		AppUI.styleTransparent(firstRowCe);
		AppUI.styleLabel(buscadorTareas);
		AppUI.styleTextField(txtBuscadorTareas);
		AppUI.styleCard(centro);
		AppUI.styleTransparent(south);
				
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(21, 61, 120, 41);
		south.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VVerTareas.this.dispose();
			}
		});
		AppUI.stylePrimaryButton(btnVolver);

	}

	public void listaTrabajadoresActualizar(Integer id) {
		DefaultListModel<BDTrabajador> modeloListaTrabajadores=  (DefaultListModel<BDTrabajador>) listaTrabajadores.getModel();
		modeloListaTrabajadores.clear();
		for(BDTrabajador t : TrabajadorDAO.getTrabajadoresDeTarea(id)) {
			modeloListaTrabajadores.addElement(t);
			}

	}

	public JList getListaTrabajadores() {
		return listaTrabajadores;
	}



    // Salida dandole a la x:
    @Override
    protected String getMensajeConfirmacionSalida() {
        return "¿Quieres volver al panel de control de administrador?";
    }

    @Override
    protected String getTituloConfirmacionSalida() {
        return "Volver al panel";
    }

    @Override
    protected void onConfirmExit() {
        this.dispose();
        if (parent != null) {
            parent.setVisible(true);
        }
    }
}
