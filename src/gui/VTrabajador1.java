package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Trabajador;

public class VTrabajador1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Trabajador trabajador;

	//Creo que hacerlo sin parent, queda mejor
	public VTrabajador1(Trabajador trabajador) {
        setTitle("Panel del Trabajador");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new GridBagLayout());

        // --- LABEL BIENVENIDO ---
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        GridBagConstraints gbc_lbl = new GridBagConstraints();
        gbc_lbl.insets = new Insets(10, 10, 10, 10);
        gbc_lbl.gridx = 0;
        gbc_lbl.gridy = 0;
        gbc_lbl.gridwidth = 2;
        getContentPane().add(lbl, gbc_lbl);

        // --- BOTÓN FICHAR ---
        JButton btnFichar = new JButton("Fichar");
        GridBagConstraints gbc_btnFichar = new GridBagConstraints();
        gbc_btnFichar.insets = new Insets(10, 10, 10, 10);
        gbc_btnFichar.gridx = 0;
        gbc_btnFichar.gridy = 1;
        getContentPane().add(btnFichar, gbc_btnFichar);

        // --- BOTÓN DESFICHAR ---
        JButton btnDesfichar = new JButton("Desfichar");
        GridBagConstraints gbc_btnDesfichar = new GridBagConstraints();
        gbc_btnDesfichar.insets = new Insets(10, 10, 10, 10);
        gbc_btnDesfichar.gridx = 1;
        gbc_btnDesfichar.gridy = 1;
        getContentPane().add(btnDesfichar, gbc_btnDesfichar);

        // --- BOTÓN VER TAREAS ---
        JButton btnVerTareas = new JButton("Ver tareas");
        GridBagConstraints gbc_btnVerTareas = new GridBagConstraints();
        gbc_btnVerTareas.insets = new Insets(10, 10, 10, 10);
        gbc_btnVerTareas.gridx = 0;
        gbc_btnVerTareas.gridy = 2;
        gbc_btnVerTareas.gridwidth = 2;
        getContentPane().add(btnVerTareas, gbc_btnVerTareas);;
    
	}

}
