package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.BDTrabajador;
import gui.ui.AppUI;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class VTrabajador1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BDTrabajador trabajador;
	private JButton btnDesfichar;
	private JButton btnFichar;
	private JButton btnVerTareas;
	private VPrincipal parent; 
	
	
	// (Danel): LE HE AÑADIDO PARENT PARA PODER CAMBIAR DE TRABAJADOR--> ADMIN
	// PORQUE SINO LOS FICHAJES NO SE GUARDAN Y AL ENTRAR COMO ADMIN LA LISTA DE FICHAJES DE LOS TRABAJADORES ESTARIA VACIA
	// AL INICIAR EL PROGRAMA NO HAY FICHAJES HECHOS (SE PODRIA CREAR UN METODO PARA QUE LEA UN ARCHIVO DE FICHAJES O ASI PARA QUE NO ESTEN VACIOS DE INICIO)
	
	public void controlFichaje(BDTrabajador trabajador){
		
		if (trabajador.getEntrada() != null) {
			btnDesfichar.setEnabled(true);
			btnFichar.setEnabled(false);


		}else {
			btnFichar.setEnabled(true);
			btnDesfichar.setEnabled(false);


		}
		
	}
	
	
	public VTrabajador1(VPrincipal parent, BDTrabajador trabajador) {
		this.parent = parent;
		this.trabajador = trabajador;
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setTitle("Panel del Trabajador");
        setSize(330, 293);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{25, 100, 40, 40, 100, 25, 0};
        gridBagLayout.rowHeights = new int[]{53, 13, 21, 21, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
			// --- LABEL BIENVENIDO ---
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        GridBagConstraints gbc_lbl = new GridBagConstraints();
        gbc_lbl.fill = GridBagConstraints.VERTICAL;
        gbc_lbl.insets = new Insets(0, 0, 5, 0);
        gbc_lbl.gridwidth = 6;
        gbc_lbl.gridx = 0;
        gbc_lbl.gridy = 1;
        getContentPane().add(lbl, gbc_lbl);   
                                              
        // --- BOTÓN DESFICHAR ---
        btnDesfichar = new JButton("Desfichar");
        btnDesfichar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime entrada = trabajador.getEntrada();
        		btnDesfichar.setEnabled(false);
        		btnFichar.setEnabled(true);
        		trabajador.metodoDesfichar();
        		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        		String entradaFormat = entrada.format(formato);
        		String salidaFormtar = LocalDateTime.now().format(formato);

        		JOptionPane.showMessageDialog(null, "Has fichado desde " + entradaFormat + " - " + salidaFormtar );
        		System.out.println(trabajador.getRegistrosFichaje());

        		
        	}
        });
        
                // --- BOTÓN FICHAR ---
                btnFichar = new JButton("Fichar");
                btnFichar.setEnabled(false);
                btnFichar.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		trabajador.metodoFichar();
                		btnDesfichar.setEnabled(true);
                		btnFichar.setEnabled(false);
	
                	}
                });
                
                
              
                
                JButton btnCerrarSesion = new JButton("Cerrar Sesion");
                btnCerrarSesion.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		VTrabajador1.this.dispose();
                		parent.setVisible(true);
                		               		
                	}
                });

                // --- BOTÓN VER TAREAS ---
                btnVerTareas = new JButton("Ver tareas");
                btnVerTareas.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		VTrabajadorTarea VTrabajadorTarea = new VTrabajadorTarea(VTrabajador1.this , trabajador);
                		VTrabajadorTarea.setVisible(true);
                		VTrabajador1.this.setVisible(false);  // No se abre la ventana, no se porque. Falta pasarle el trabajador
                	}
                });
                
                
                
                //--CODIGO LAYOUT--
                GridBagConstraints gbc_btnFichar = new GridBagConstraints();
                gbc_btnFichar.fill = GridBagConstraints.BOTH;
                gbc_btnFichar.insets = new Insets(0, 0, 5, 5);
                gbc_btnFichar.gridx = 1;
                gbc_btnFichar.gridy = 3;
                getContentPane().add(btnFichar, gbc_btnFichar);
                btnDesfichar.setEnabled(false);
                GridBagConstraints gbc_btnDesfichar = new GridBagConstraints();
                gbc_btnDesfichar.anchor = GridBagConstraints.WEST;
                gbc_btnDesfichar.fill = GridBagConstraints.VERTICAL;
                gbc_btnDesfichar.insets = new Insets(0, 0, 5, 5);
                gbc_btnDesfichar.gridx = 4;
                gbc_btnDesfichar.gridy = 3;
                getContentPane().add(btnDesfichar, gbc_btnDesfichar);

                
                
                GridBagConstraints gbc_btnVerTareas = new GridBagConstraints();
                gbc_btnVerTareas.fill = GridBagConstraints.BOTH;
                gbc_btnVerTareas.insets = new Insets(0, 0, 5, 5);
                gbc_btnVerTareas.gridx = 1;
                gbc_btnVerTareas.gridy = 5;
                getContentPane().add(btnVerTareas, gbc_btnVerTareas);
                GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
                gbc_btnCerrarSesion.insets = new Insets(0, 0, 5, 5);
                gbc_btnCerrarSesion.fill = GridBagConstraints.BOTH;
                gbc_btnCerrarSesion.gridx = 4;
                gbc_btnCerrarSesion.gridy = 5;
                getContentPane().add(btnCerrarSesion, gbc_btnCerrarSesion);;
                
                controlFichaje(trabajador);

                //ESTILO CON AppUI
                AppUI.styleBackground((JPanel) getContentPane());
                AppUI.styleTitle(lbl);
                AppUI.stylePrimaryButton(btnFichar);
                AppUI.stylePrimaryButton(btnDesfichar);
                AppUI.stylePrimaryButton(btnVerTareas);
                AppUI.stylePrimaryButton(btnCerrarSesion);

	}
}
