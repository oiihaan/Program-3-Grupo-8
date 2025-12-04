package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class VAnyadirTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreTarea;
	private JTextField txtDuracion;

	private VAdmin1 parent;
	private JButton btnGuardar;
	private JPanel panel;
	private JPanel left;
	private JPanel center;
	private JPanel right;

	/**
	 * Create the frame.
	 */
	public VAnyadirTareas(VAdmin1 parent,  BDAdmin admin) {
		setTitle("Añadir Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {200, 168, 200};
		gbl_panel.rowHeights = new int[] {100, 100, 100};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0,0.0,};
		panel.setLayout(gbl_panel);
		
		left = new JPanel();
		GridBagConstraints gbc_left = new GridBagConstraints();
		gbc_left.insets = new Insets(0, 0, 5, 5);
		gbc_left.fill = GridBagConstraints.BOTH;
		gbc_left.gridx = 0;
		gbc_left.gridy = 1;
		panel.add(left, gbc_left);
		left.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel ldlDuracion = new JLabel("Duracion estimada en minutos:");
		ldlDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		left.add(ldlDuracion);
		AppUI.styleTitle(ldlDuracion);
		
		JLabel lblNombre = new JLabel("Nombre de la tarea: ");
		left.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		

		

		AppUI.styleTitle(lblNombre);
		
		center = new JPanel();
		center.setLayout(null);
		GridBagConstraints gbc_center = new GridBagConstraints();
		gbc_center.insets = new Insets(0, 0, 5, 5);
		gbc_center.fill = GridBagConstraints.BOTH;
		gbc_center.gridx = 1;
		gbc_center.gridy = 1;
		panel.add(center, gbc_center);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(10, 10, 150, 25);
		center.add(txtDuracion);
		txtDuracion.setColumns(10);
		
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
		
		txtNombreTarea = new JTextField();
		txtNombreTarea.setBounds(10, 60, 150, 25);
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
		
		right = new JPanel();
		right.setLayout(null);
		GridBagConstraints gbc_right = new GridBagConstraints();
		gbc_right.insets = new Insets(0, 0, 5, 5);
		gbc_right.fill = GridBagConstraints.BOTH;
		gbc_right.gridx = 2;
		gbc_right.gridy = 1;
		panel.add(right, gbc_right);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 64, 88, 21);
		right.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VAnyadirTareas.this.dispose();
				parent.setVisible(true);
				
			}
		});
		AppUI.stylePrimaryButton(btnVolver);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 10, 88, 21);
		right.add(btnGuardar);
		btnGuardar.setEnabled(false);
		
		//Acciones
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
		
				AppUI.stylePrimaryButton(btnGuardar);
		//Estilo UI
		AppUI.styleBackground(contentPane);

		
		//IMAGEN
		AppUI.establecerIcono(this);

	}

}
