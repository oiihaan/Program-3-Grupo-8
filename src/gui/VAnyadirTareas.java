package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.BDAdmin;
import gui.ui.AppUI;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VAnyadirTareas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreTarea;
	private JTextField textField;

	private VAdmin1 parent;

	/**
	 * Create the frame.
	 */
	public VAnyadirTareas(VAdmin1 parent,  BDAdmin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 213, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblNombre = new JLabel("Nombre de la tarea: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		contentPane.add(lblNombre, gbc_lblNombre);
		
		txtNombreTarea = new JTextField();
		GridBagConstraints gbc_txtNombreTarea = new GridBagConstraints();
		gbc_txtNombreTarea.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreTarea.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreTarea.gridx = 1;
		gbc_txtNombreTarea.gridy = 2;
		contentPane.add(txtNombreTarea, gbc_txtNombreTarea);
		txtNombreTarea.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 2;
		contentPane.add(btnGuardar, gbc_btnGuardar);
		
		JLabel ldlDuracion = new JLabel("Duracion estimada:");
		GridBagConstraints gbc_ldlDuracion = new GridBagConstraints();
		gbc_ldlDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_ldlDuracion.gridx = 1;
		gbc_ldlDuracion.gridy = 4;
		contentPane.add(ldlDuracion, gbc_ldlDuracion);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VAnyadirTareas.this.dispose();
				parent.setVisible(true);
				
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 2;
		gbc_btnVolver.gridy = 5;
		contentPane.add(btnVolver, gbc_btnVolver);
		
		
		//Estilo UI
		AppUI.styleBackground(contentPane);
		

		

		AppUI.styleSubtitle(lblNombre);
		AppUI.styleTitle(ldlDuracion);

		AppUI.stylePrimaryButton(btnGuardar);
		AppUI.stylePrimaryButton(btnVolver);


	}

}
