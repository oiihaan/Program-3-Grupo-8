package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VTrabajadorTarea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VTrabajador1 parent;
	
	Thread HiloTiempo;
	



	/**
	 * Create the frame.
	 */
	public VTrabajadorTarea(VTrabajador1 parent,BDTrabajador trabajador ) {
		this.parent = parent;

		setTitle("Tareas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {150, 300, 150};
		gbl_contentPane.rowHeights = new int[] {50, 300, 50};
		gbl_contentPane.columnWeights = new double[]{0.0,0.0,0.0};
		gbl_contentPane.rowWeights = new double[]{0.0,0.0,0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel Centro = new JPanel();
		GridBagConstraints gbc_Centro = new GridBagConstraints();
		gbc_Centro.insets = new Insets(0, 0, 5, 5);
		gbc_Centro.fill = GridBagConstraints.BOTH;
		gbc_Centro.gridx = 1;
		gbc_Centro.gridy = 1;
		contentPane.add(Centro, gbc_Centro);
		GridBagLayout gbl_Centro = new GridBagLayout();
		gbl_Centro.columnWidths = new int[] {150, 150};
		gbl_Centro.rowHeights = new int[] {50, 200, 50};
		gbl_Centro.columnWeights = new double[]{0.0,0.0};
		gbl_Centro.rowWeights = new double[]{0.0,0.0,0.0};
		Centro.setLayout(gbl_Centro);
		
		JPanel centroIz = new JPanel();
		GridBagConstraints gbc_centroIz = new GridBagConstraints();
		gbc_centroIz.insets = new Insets(0, 0, 5, 5);
		gbc_centroIz.fill = GridBagConstraints.BOTH;
		gbc_centroIz.gridx = 0;
		gbc_centroIz.gridy = 1;
		Centro.add(centroIz, gbc_centroIz);
		centroIz.setLayout(new GridLayout(1, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		centroIz.add(scrollPane);
		
		JList list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		scrollPane.setViewportView(list);
		
		JPanel centroDe = new JPanel();
		centroDe.setLayout(null);
		GridBagConstraints gbc_centroDe = new GridBagConstraints();
		gbc_centroDe.insets = new Insets(0, 0, 5, 0);
		gbc_centroDe.fill = GridBagConstraints.BOTH;
		gbc_centroDe.gridx = 1;
		gbc_centroDe.gridy = 1;
		Centro.add(centroDe, gbc_centroDe);
		
		
		JButton btnEmpezarTarea = new JButton("Empezar Tarea");
		btnEmpezarTarea.setEnabled(false);
		btnEmpezarTarea.setBounds(21, 63, 103, 21);
		centroDe.add(btnEmpezarTarea);
		
		JButton btnFinalizarTarea = new JButton("Finalizar Tarea");
		btnFinalizarTarea.setEnabled(false);
		btnFinalizarTarea.setBounds(21, 120, 101, 21);
		centroDe.add(btnFinalizarTarea);
		
		JButton btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnVolver, gbc_btnNewButton);
		
		////////////////////////////////////////////////// ACIONES
		//Creamos el modelo de la lista , le añadimos las tareas del trabajador y añadimos el modelo a la lista
		DefaultListModel<BDTarea> modelo = new DefaultListModel<>();
		
		for (BDTarea tarea : trabajador.getTareasAsignadas()) {
			if(tarea.getEstado().equals("finalizado")) {  //Los finalizados no los metemos al modelo pero siguien en sus tareas para que el jefe pueda verlos
			} else {
				modelo.addElement(tarea);
			}
			
		}
		list.setModel(modelo);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Para que solo pueda selecionar una opcion 
		

		//Hacemos que el boton de empezar tarea se habilite cuando esta algo de la lista selecionado , pero si desabilita la opcion se va
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			BDTarea tareaselect = (BDTarea) list.getSelectedValue();
	
				if(!list.isSelectionEmpty()) {
					if (tareaselect.getEstado().equals("pendiente") ) { //Se esta ejecutando
						btnEmpezarTarea.setEnabled(false);
						btnFinalizarTarea.setEnabled(true);
					} else {
						btnEmpezarTarea.setEnabled(true);
						btnFinalizarTarea.setEnabled(false);


					}
					
				}else {
					btnEmpezarTarea.setEnabled(false);
					btnFinalizarTarea.setEnabled(false);

				}
					
				
			}
		});
		
		//Inicia  el hilo
		btnEmpezarTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			BDTarea tareaselect = (BDTarea) list.getSelectedValue();
	

				tareaselect.getHilo().start();
				btnEmpezarTarea.setEnabled(false);
				btnFinalizarTarea.setEnabled(true);

				
				
			}
		});

		//Finaliza el hilo manualmente y lo borra de la lista
		btnFinalizarTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BDTarea tareaselect = (BDTarea) list.getSelectedValue();
				tareaselect.getHilo().interrupt();
				btnFinalizarTarea.setEnabled(false);
				btnEmpezarTarea.setEnabled(false);
				int indice = modelo.indexOf(tareaselect);
				modelo.removeElementAt(indice);

				
			}
		});
		//Creo hilo para que cada 10 segundo revise si alguna tarea a sido finalizada y los borre del modelo
		HiloTiempo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
				try {
					Thread.sleep(10000); // Duerme 10 seg
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Busca entre todas las tarareas si ahi alguna que esta finalizada y esta dentro del modelo y las borra
				for (BDTarea tarea : trabajador.getTareasAsignadas()) {
					if(tarea.getEstado().equals("finalizado") && modelo.contains(tarea)) {
						System.out.println("Borrando");
		                    btnFinalizarTarea.setEnabled(false);
		                    btnEmpezarTarea.setEnabled(false);
		                    int indice = modelo.indexOf(tarea);
		                    modelo.removeElementAt(indice);
		                
					} 
				}				
			}
			}
		});
		HiloTiempo.start();
		
		//Volver a la pestaña de atras
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VTrabajadorTarea.this.parent.setVisible(true);
				VTrabajadorTarea.this.parent.repaint();
				VTrabajadorTarea.this.dispose();


			}
		});

		
		//Estilo AppUI
		AppUI.styleBackground(contentPane);
		AppUI.styleCard(Centro);

		AppUI.styleTransparent(centroIz);
		AppUI.styleTransparent(centroDe);

		AppUI.styleList(list);

		AppUI.stylePrimaryButton(btnEmpezarTarea);
		AppUI.stylePrimaryButton(btnFinalizarTarea);
		AppUI.stylePrimaryButton(btnVolver);
	
	}
}
