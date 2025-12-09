package gui;


//IMPORTS
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import bd.Tarea_TrabajadorDAO;
import bd.TrabajadorDAO;
import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VAsignarTareas extends VentanaConConfirmacion {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private VVerTareas parent;
    private BDAdmin admin;
    private BDTarea tarea;
    private JList<BDTrabajador> listTrabajadores;
    private JButton btnDesasignarTarea;
    private JButton btnAsignarTarea;
    private JList<BDTrabajador> listTrabajadoresAsignados;

    
    //CONSTRUCTOR
    public VAsignarTareas(VVerTareas parent, BDAdmin admin , BDTarea tarea)  {
        super();                  // importante: llama al constructor de la base
        this.parent = parent;
        this.admin = admin;
        this.tarea = tarea;

        
        setTitle("Asignador de Trabajadores a la tarea: " + tarea.getNombre() );
        // NO: setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); -> lo hace la base
        setBounds(100, 100, 648, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{30, 200, 30};
        gbl_contentPane.rowHeights = new int[] {30, 350, 30};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0};
        contentPane.setLayout(gbl_contentPane);
        setLocationRelativeTo(null);


        // Listas tipadas
        listTrabajadores = new JList<>();
        listTrabajadoresAsignados = new JList<>();
 
        
        //PANELES
        JPanel Centro = new JPanel();
        GridBagConstraints gbc_Centro = new GridBagConstraints();
        gbc_Centro.insets = new Insets(0, 0, 5, 5);
        gbc_Centro.fill = GridBagConstraints.BOTH;
        gbc_Centro.gridx = 1;
        gbc_Centro.gridy = 1;
        contentPane.add(Centro, gbc_Centro);
        Centro.setLayout(new BorderLayout(0, 0));
        
        //Paneles DENTRO del panel CENTRO
        JPanel centroCentro = new JPanel();
        Centro.add(centroCentro, BorderLayout.CENTER);
        centroCentro.setLayout(new GridLayout(1, 2, 0, 0));
        
        JPanel centroIzq = new JPanel();
        centroCentro.add(centroIzq);
        centroIzq.setPreferredSize(new Dimension(50, 0));
        SpringLayout sl_centroIzq = new SpringLayout();
        centroIzq.setLayout(sl_centroIzq);
        
        JPanel centroDe = new JPanel();
        centroCentro.add(centroDe);
        centroDe.setPreferredSize(new Dimension(50, 0));
        SpringLayout sl_centroDe = new SpringLayout();
        centroDe.setLayout(sl_centroDe);

        JPanel centroNorth = new JPanel();
        Centro.add(centroNorth, BorderLayout.NORTH);
        centroNorth.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel centroSouth = new JPanel();
        FlowLayout flowLayout = (FlowLayout) centroSouth.getLayout();
        flowLayout.setHgap(30);
        Centro.add(centroSouth, BorderLayout.SOUTH);
        
      
        //JLABELS
        JLabel lblAsignados = new JLabel("Trabajadores  asignados ");
        lblAsignados.setHorizontalAlignment(SwingConstants.CENTER);
        centroNorth.add(lblAsignados);

        JLabel lblTrabajadores = new JLabel("Trabajadores restantes");
        lblTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
        centroNorth.add(lblTrabajadores);
        
        btnDesasignarTarea = new JButton("Desasignar tarea");
        btnDesasignarTarea.setEnabled(false);
        centroSouth.add(btnDesasignarTarea);

        JButton btnVolver = new JButton("Volver");
        centroSouth.add(btnVolver);

     

     

       
        //SCROLLPANE
        JScrollPane scrollPaneCentroDe = new JScrollPane();
        sl_centroDe.putConstraint(SpringLayout.NORTH, scrollPaneCentroDe, 0, SpringLayout.NORTH, centroDe);
        sl_centroDe.putConstraint(SpringLayout.WEST, scrollPaneCentroDe, 0, SpringLayout.WEST, centroDe);
        sl_centroDe.putConstraint(SpringLayout.SOUTH, scrollPaneCentroDe, 291, SpringLayout.NORTH, centroDe);
        sl_centroDe.putConstraint(SpringLayout.EAST, scrollPaneCentroDe, 193, SpringLayout.WEST, centroDe);
        centroDe.add(scrollPaneCentroDe);
        scrollPaneCentroDe.setViewportView(listTrabajadores);
        
        JScrollPane scrollPaneCentroIzq = new JScrollPane();
        sl_centroIzq.putConstraint(SpringLayout.NORTH, scrollPaneCentroIzq, 0, SpringLayout.NORTH, centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.WEST, scrollPaneCentroIzq, 0, SpringLayout.WEST, centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.SOUTH, scrollPaneCentroIzq, 291, SpringLayout.NORTH, centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.EAST, scrollPaneCentroIzq, 193, SpringLayout.WEST, centroIzq); 
        centroIzq.add(scrollPaneCentroIzq);
        scrollPaneCentroIzq.setViewportView(listTrabajadoresAsignados);
        
        
        
        //LOGICA de los botones --> Queda esto pendiente

        // -- LÓGICA DE LISTAS --


        // Botón Volver -> mismo comportamiento que la X
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirmExit();
            }
        });

        // TODO: aquí iría la lógica real de btnAsignarTarea
        TrabajadoresListas(); //Añade el modelo a la lista
        
        listTrabajadores.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if(!listTrabajadores.isSelectionEmpty()) {
            		
        			btnAsignarTarea.setEnabled(true);   
            		} else {
            		btnAsignarTarea.setEnabled(false);
            	}
        	
        	}
        });
        listTrabajadoresAsignados.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if(!listTrabajadoresAsignados.isSelectionEmpty()) {
            		
        			btnDesasignarTarea.setEnabled(true);   
            		} else {
            		btnDesasignarTarea.setEnabled(false);
            	}
        	}
        });

        
        // Estilo AppUI
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(Centro);

        AppUI.styleTransparent(centroNorth);
        AppUI.styleTransparent(centroSouth);
        AppUI.styleTransparent(centroCentro);
        AppUI.styleTransparent(centroIzq);
        AppUI.styleTransparent(centroDe);

        AppUI.styleLabel(lblAsignados);
        AppUI.styleLabel(lblTrabajadores);
        AppUI.stylePrimaryButton(btnVolver);
        
                
                //BOTONES
                btnAsignarTarea = new JButton("Asignar tarea");
                btnAsignarTarea.setEnabled(false);
                centroSouth.add(btnAsignarTarea);
                
                btnAsignarTarea.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		Object[] trabajadoresSelecionados = listTrabajadores.getSelectedValues();
                		//DefaultListModel<BDTrabajador> modelParentTrabajadores = (DefaultListModel<BDTrabajador>) parent.getListaTrabajadores().getModel();

                		for(Object t : trabajadoresSelecionados) {
                			BDTrabajador trabajador = (BDTrabajador) t;
                			Tarea_TrabajadorDAO.insertarTrabajadoresATarea(tarea.getId(), trabajador.getId());
                			//modelParentTrabajadores.addElement(trabajador);
                			
                		}
                		TrabajadoresListas();
                		JOptionPane.showMessageDialog(
                		        null,
                		        "Los trabajadores se han añadido correctamente.",
                		        "Información",
                		        JOptionPane.INFORMATION_MESSAGE
                		);
                		

                	}
                });
                
                btnDesasignarTarea.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		Object[] trabajadoresSelecionados = listTrabajadoresAsignados.getSelectedValues();
                		//DefaultListModel<BDTrabajador> modelParentTrabajadores = (DefaultListModel<BDTrabajador>) parent.getListaTrabajadores().getModel();

                		for(Object t : trabajadoresSelecionados) {
                			BDTrabajador trabajador = (BDTrabajador) t;
                			Tarea_TrabajadorDAO.eliminarTrabajadorDeTarea(tarea.getId(), trabajador.getId());
                			//modelParentTrabajadores.removeElement(trabajador);
                			
                		}
                		TrabajadoresListas();
                		JOptionPane.showMessageDialog(
                		        null,
                		        "Los trabajadores se han eliminado de la tarea correctamente.",
                		        "Información",
                		        JOptionPane.INFORMATION_MESSAGE
                		);
                		
                	}
                });
        AppUI.stylePrimaryButton(btnAsignarTarea);
        AppUI.styleList(listTrabajadores);
        AppUI.styleList(listTrabajadoresAsignados);
        AppUI.stylePrimaryButton(btnDesasignarTarea);

        AppUI.establecerIcono(this);
    }

    
    
    
    
    //Funciones FUERA del constructor
    @Override
    protected void onConfirmExit() {
        // Este método se llama SOLO si en VentanaConConfirmacion
        // el usuario ha elegido "Sí" en el diálogo.
        if (parent != null) {
        	
            parent.setVisible(true); 
            parent.listaTrabajadoresActualizar(tarea.getId());
            parent.repaint();// volvemos a la pantalla de "Ver tareas"
        }
        dispose();                    // cerramos esta ventana
    }
    
    private void TrabajadoresListas() {
        DefaultListModel<BDTrabajador> modeloTrabajadoresAsignados = new DefaultListModel<BDTrabajador>();
        for (BDTrabajador t : TrabajadorDAO.getTrabajadoresDeTarea(tarea.getId())) {
        	modeloTrabajadoresAsignados.addElement(t);

        }

        DefaultListModel<BDTrabajador> modeloTrabajadoresNoAsignados = new DefaultListModel<BDTrabajador>();
        for (BDTrabajador tra : TrabajadorDAO.getAllTrabajadores()) {
        	if(!modeloTrabajadoresAsignados.contains(tra)) {
        		modeloTrabajadoresNoAsignados.addElement(tra);

        	}
        }
       listTrabajadores.setModel(modeloTrabajadoresNoAsignados);
       listTrabajadoresAsignados.setModel(modeloTrabajadoresAsignados);
        


	}
}
