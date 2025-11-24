package main;

import bd.InicializadorBaseDatos;
import java.awt.EventQueue;

import gui.VPrincipal;

public class main {
		/**
		 * Launch the application.
		 */
	public static void main(String[] args) {
		bd.InicializadorBaseDatos.init();			//Asegura que la base de datos este creada
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {
						VPrincipal VPrincipal = new VPrincipal();
						VPrincipal.setVisible(true);
						System.out.println("main");
						System.out.println("Funciona git de pablo"); //BORRAR ESTA LINEA
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}
}

	
	//(NO ESTA USABLE PORQUE NO ME ENCUENTRA LA CLASE)