package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Trabajador;

public class VTrabajador1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VPrincipal parent;
	private Trabajador trabajador;


	public VTrabajador1(VPrincipal parent, Trabajador trabajador) {
		this.parent= parent;
		this.trabajador = trabajador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
