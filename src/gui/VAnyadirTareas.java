package gui;

//IMPORTS
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.TareaDAO;
import domain.BDAdmin;
import domain.BDTarea;
import gui.ui.AppUI;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;


//CONSTRUCTOR
public class VAnyadirTareas extends VentanaConConfirmacion {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreTarea;
	private JTextField txtDuracion;

	private VAdmin1 parent;
	private JButton btnGuardar;
	private JPanel panel;
	private BDAdmin admin;

	
	/**
	 * Create the frame.
	 */
	public VAnyadirTareas(VAdmin1 parent,  BDAdmin admin) {
		super();
		 this.parent = parent;  
		 this.admin = admin;
		 // *** IMPORTANTE: guardar la referencia ***

		 
		setTitle("Añadir Tareas");
		setBounds(100, 100, 700, 387);
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 1, 0, 0));
        
        JPanel panelCuadrado = new JPanel();
        GridBagLayout gbl_panelCuadrado = new GridBagLayout();
        gbl_panelCuadrado.columnWidths = new int[] {50, 600, 50};
        gbl_panelCuadrado.rowHeights = new int[]{50,58,50};
        gbl_panelCuadrado.columnWeights = new double[]{0.0, 0.0, 0.0};
        gbl_panelCuadrado.rowWeights = new double[]{0.0, 0.0,0.0};
        panelCuadrado.setLayout(gbl_panelCuadrado);
        contentPane.add(panelCuadrado);
		
		AppUI.styleBackground(contentPane);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		
        //Contiene las 3 secciones
        JPanel panel_1 = new JPanel();
        //contentPane.add(panel);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.rowHeights = new int[] {58, 0};
        gbl_panel_1.columnWidths = new int[] {200, 200, 200};
        gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0};
        panel_1.setLayout(gbl_panel_1);
        
        panelCuadrado.add(panel_1, gbc_panel_1);
        
                		        	            
                		        	            //3 secciones (Paneles LEFT, CENTER y RIGHT)
        JPanel left = new JPanel();
        GridBagConstraints gbc_left = new GridBagConstraints();
        gbc_left.fill = GridBagConstraints.BOTH;
        gbc_left.insets = new Insets(0, 0, 5, 5);
        gbc_left.gridx = 0;
        gbc_left.gridy = 0;
        panel_1.add(left, gbc_left);
        left.setLayout(new GridLayout(0, 1, 0, 0));
        AppUI.styleTransparent(left);
        
        JLabel lblNombre = new JLabel("Nombre de la tarea:");
        left.add(lblNombre);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        
        AppUI.styleLabel(lblNombre);
        
        
        //LABELS
        JLabel ldlDuracion = new JLabel("Duracion estimada en minutos:");
        left.add(ldlDuracion);
        ldlDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
        AppUI.styleLabel(ldlDuracion);
                
        JPanel center = new JPanel();
        GridBagConstraints gbc_center = new GridBagConstraints();
        gbc_center.fill = GridBagConstraints.BOTH;
        gbc_center.insets = new Insets(0, 0, 5, 5);
        gbc_center.gridx = 1;
        gbc_center.gridy = 0;
        panel_1.add(center, gbc_center);
        center.setLayout(new GridLayout(0, 1, 0, 0));
        AppUI.styleTransparent(center);
        
        txtNombreTarea = new JTextField();
        center.add(txtNombreTarea);
        txtNombreTarea.setColumns(10);
        
        txtNombreTarea.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		if(!txtDuracion.getText().isEmpty() && !txtNombreTarea.getText().isEmpty()) {
        			btnGuardar.setEnabled(true);
        		}else {
        			btnGuardar.setEnabled(false);
        		}
        	}
        });
        AppUI.styleTextField(txtNombreTarea);
        
        

        //TEXTFIELDS
        txtDuracion = new JTextField();
        center.add(txtDuracion);
        txtDuracion.setColumns(10);
        
        
        //LOGICA de los TextField	
        txtDuracion.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		if(!txtDuracion.getText().isEmpty() && !txtNombreTarea.getText().isEmpty()) {
        			btnGuardar.setEnabled(true);
        		}else {
        			btnGuardar.setEnabled(false);
        		}
        	}
        });
        
        		AppUI.styleTextField(txtDuracion);
                
                JPanel right = new JPanel();
                right.setLayout(new GridLayout(2, 1, 0, 10));
                GridBagConstraints gbc_right = new GridBagConstraints();
                gbc_right.insets = new Insets(0, 0, 5, 0);
                gbc_right.fill = GridBagConstraints.BOTH;
                gbc_right.gridx = 2;
                gbc_right.gridy = 0;
                panel_1.add(right, gbc_right);
                
                
                
                
                //BOTONES
                btnGuardar = new JButton("Guardar");
                right.add(btnGuardar);
                btnGuardar.setEnabled(false);
                
	
                
                //LOGICA de los botones
                btnGuardar.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		String nombre = txtNombreTarea.getText().trim();
                		Integer duracion;
                		try {
                			 duracion = Integer.parseInt(txtDuracion.getText().trim());
                		} catch (Exception e2) {
                			JOptionPane.showMessageDialog(null, 
                	                "Error: '" + txtDuracion.getText().trim() + "' no es un número válido.\nIngresa solo números enteros.", 
                	                "Error de formato en la duracion", 
                	                JOptionPane.ERROR_MESSAGE);
                			txtDuracion.setText("");
                			txtNombreTarea.setText("");
                			btnGuardar.setEnabled(false);
                			return;
                			
                		}
                		BDTarea tarea = new BDTarea(  nombre, duracion, "pendiente");
                		TareaDAO.insertarTarea(tarea);
                		JOptionPane.showMessageDialog(null, "Tarea " + tarea.getNombre() +" ha sido guardada correctamente");
                		txtDuracion.setText("");
                		txtNombreTarea.setText("");
                		btnGuardar.setEnabled(false);
                	
                		
                	}
                });
                
                
                
                //Estilo UI
                AppUI.stylePrimaryButton(btnGuardar);
                
                JButton btnVolver = new JButton("Volver");
                right.add(btnVolver);
                
                
                btnVolver.addActionListener(new ActionListener() {
                	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		onConfirmExit();
                		
                	}
                });
                AppUI.stylePrimaryButton(btnVolver);
                AppUI.styleTransparent(right);
                AppUI.styleCard(panel_1);
                AppUI.styleBackground(panelCuadrado);
                AppUI.configurarVentana(this);
                AppUI.establecerIcono(this);
		


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
        
        if (parent != null) {
            parent.setVisible(true);
        }
        this.dispose();
    }
}


