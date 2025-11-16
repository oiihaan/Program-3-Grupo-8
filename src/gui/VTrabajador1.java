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

	//Creo que hacerlo sin parent, queda mejor
	public VTrabajador1(BDTrabajador trabajador) {
        setTitle("Panel del Trabajador");
        setSize(559, 368);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 130};
        gridBagLayout.columnWidths = new int[]{0, 284};
        getContentPane().setLayout(gridBagLayout);

        // --- LABEL BIENVENIDO ---
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        GridBagConstraints gbc_lbl = new GridBagConstraints();
        gbc_lbl.insets = new Insets(10, 10, 10, 10);
        gbc_lbl.gridx = 0;
        gbc_lbl.gridy = 0;
        gbc_lbl.gridwidth = 2;
        getContentPane().add(lbl, gbc_lbl);

        // --- BOTÓN FICHAR ---
        btnFichar = new JButton("Fichar");
        btnFichar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime fichaje = LocalDateTime.now();
        		btnDesfichar.setEnabled(true);
        		btnFichar.setEnabled(false);
        		trabajador.setEntrada(fichaje);
        		
        		
        		
        	}
        });
        GridBagConstraints gbc_btnFichar = new GridBagConstraints();
        gbc_btnFichar.insets = new Insets(10, 10, 10, 10);
        gbc_btnFichar.gridx = 0;
        gbc_btnFichar.gridy = 1;
        getContentPane().add(btnFichar, gbc_btnFichar);

        // --- BOTÓN DESFICHAR ---
        btnDesfichar = new JButton("Desfichar");
        btnDesfichar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime entrada = trabajador.getEntrada();
        		btnDesfichar.setEnabled(false);
        		btnFichar.setEnabled(true);
        		trabajador.metodoFichar();
        		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        		String entradaFormat = entrada.format(formato);
        		String salidaFormtar = LocalDateTime.now().format(formato);

        		JOptionPane.showMessageDialog(null, "Has fichado desde" + entradaFormat + " - " + salidaFormtar );
        		System.out.println(trabajador.getRegistrosFichaje());

        		
        	}
        });
        btnDesfichar.setEnabled(false);
        GridBagConstraints gbc_btnDesfichar = new GridBagConstraints();
        gbc_btnDesfichar.insets = new Insets(10, 10, 10, 10);
        gbc_btnDesfichar.gridx = 1;
        gbc_btnDesfichar.gridy = 1;
        getContentPane().add(btnDesfichar, gbc_btnDesfichar);

        // --- BOTÓN VER TAREAS ---
        btnVerTareas = new JButton("Ver tareas");
        btnVerTareas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VTrabajadorTarea VTrabajadorTarea = new VTrabajadorTarea();
        		VTrabajadorTarea.setVisible(true);
        		VTrabajador1.this.setVisible(false);  // No se abre la ventana, no se porque. Falta pasarle el trabajador
        	}
        });
        GridBagConstraints gbc_btnVerTareas = new GridBagConstraints();
        gbc_btnVerTareas.insets = new Insets(10, 10, 10, 10);
        gbc_btnVerTareas.gridx = 0;
        gbc_btnVerTareas.gridy = 2;
        gbc_btnVerTareas.gridwidth = 2;
        getContentPane().add(btnVerTareas, gbc_btnVerTareas);;
    
	}

}
