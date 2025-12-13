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

        //Contiene las 3 secciones
        JPanel panel = new JPanel();
        contentPane.add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.rowHeights = new int[] {50, 58, 50};
        gbl_panel.columnWidths = new int[] {30, 200, 200, 200, 50};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
        panel.setLayout(gbl_panel);
		
		
		//3 secciones (Paneles LEFT, CENTER y RIGHT)
        JPanel left = new JPanel();
        GridBagConstraints gbc_left = new GridBagConstraints();
        gbc_left.fill = GridBagConstraints.BOTH;
        gbc_left.insets = new Insets(0, 0, 0, 5);
        gbc_left.gridx = 1;
        gbc_left.gridy = 1;
        panel.add(left, gbc_left);

        JPanel center = new JPanel();
        GridBagConstraints gbc_center = new GridBagConstraints();
        gbc_center.fill = GridBagConstraints.BOTH;
        gbc_center.insets = new Insets(0, 0, 0, 5);
        gbc_center.gridx = 2;
        gbc_center.gridy = 1;
        panel.add(center, gbc_center);
        center.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2, 1, 0, 10));
        GridBagConstraints gbc_right = new GridBagConstraints();
        gbc_right.fill = GridBagConstraints.BOTH;
        gbc_right.gridx = 3;
        gbc_right.gridy = 1;
        panel.add(right, gbc_right);
		left.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		//LABELS
		JLabel ldlDuracion = new JLabel("Duracion estimada en minutos:");
		ldlDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		left.add(ldlDuracion);
		
		JLabel lblNombre = new JLabel("Nombre de la tarea:");
		left.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		

		//TEXTFIELDS
		txtDuracion = new JTextField();
		center.add(txtDuracion);
		txtDuracion.setColumns(10);
		
		txtNombreTarea = new JTextField();
		center.add(txtNombreTarea);
		txtNombreTarea.setColumns(10);
		
		
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
		
		
		
		
		//BOTONES
		btnGuardar = new JButton("Guardar");
		right.add(btnGuardar);
		btnGuardar.setEnabled(false);
		
		JButton btnVolver = new JButton("Volver");
		right.add(btnVolver);
		
	
		
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
		
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onConfirmExit();
				
			}
		});
		
		
		
		//Estilo UI
		AppUI.stylePrimaryButton(btnGuardar);
		AppUI.stylePrimaryButton(btnVolver);
		
		AppUI.styleBackground(contentPane);
		AppUI.styleCard(panel);
		AppUI.styleTransparent(right);
		AppUI.styleTransparent(center);
        AppUI.styleTransparent(left);
		
		AppUI.styleLabel(lblNombre);
		AppUI.styleLabel(ldlDuracion);

		AppUI.styleTextField(txtDuracion);
	    AppUI.styleTextField(txtNombreTarea);
		
        AppUI.configurarVentana(this);

		//IMAGEN
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


