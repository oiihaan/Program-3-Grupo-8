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
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

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
	

	public VTrabajador1(VPrincipal parent, BDTrabajador trabajador) {
        setTitle("Panel del Trabajador");
        setSize(559, 368);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // --- LABEL BIENVENIDO ---
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        lbl.setBounds(182, 53, 107, 13);
        getContentPane().add(lbl);

        // --- BOTÓN FICHAR ---
        btnFichar = new JButton("Fichar");
        btnFichar.setBounds(115, 86, 61, 21);
        btnFichar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime fichaje = LocalDateTime.now();
        		btnDesfichar.setEnabled(true);
        		btnFichar.setEnabled(false);
        		trabajador.setEntrada(fichaje);
        		
        		
        		
        	}
        });
        getContentPane().add(btnFichar);

        // --- BOTÓN DESFICHAR ---
        btnDesfichar = new JButton("Desfichar");
        btnDesfichar.setBounds(285, 86, 75, 21);
        btnDesfichar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime entrada = trabajador.getEntrada();
        		btnDesfichar.setEnabled(false);
        		btnFichar.setEnabled(true);
        		trabajador.metodoFichar();
        		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        		String entradaFormat = entrada.format(formato);
        		String salidaFormtar = LocalDateTime.now().format(formato);

        		JOptionPane.showMessageDialog(null, "Has fichado desde " + entradaFormat + " - " + salidaFormtar );
        		System.out.println(trabajador.getRegistrosFichaje());

        		
        	}
        });
        btnDesfichar.setEnabled(false);
        getContentPane().add(btnDesfichar);
                
                        // --- BOTÓN VER TAREAS ---
                        btnVerTareas = new JButton("Ver tareas");
                        btnVerTareas.setBounds(113, 124, 107, 21);
                        btnVerTareas.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		VTrabajadorTarea VTrabajadorTarea = new VTrabajadorTarea(VTrabajador1.this , trabajador);
                        		VTrabajadorTarea.setVisible(true);
                        		VTrabajador1.this.setVisible(false);  // No se abre la ventana, no se porque. Falta pasarle el trabajador
                        	}
                        });
                        getContentPane().add(btnVerTareas);
                        
                        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
                        btnCerrarSesion.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		VTrabajador1.this.dispose();
                        		parent.setVisible(true);
                        	}
                        });
                        btnCerrarSesion.setBounds(285, 124, 117, 21);
                        getContentPane().add(btnCerrarSesion);;
    
	}
}
