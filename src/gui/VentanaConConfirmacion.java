package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// VENTANA PARA GESTIONAR EL LOGOUT DE LA APLICACIÓN, CLASE PADRE DE TODAS LAS DEMÁS
public abstract class VentanaConConfirmacion extends JFrame {

    public VentanaConConfirmacion() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                preguntarYSalir();
            }
        });
    }

    private void preguntarYSalir() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que quieres salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            onConfirmExit();
        }
        // Si NO, no hacemos nada -> la ventana se queda abierta
    }

    /** Qué significa "salir" para esta ventana concreta */
    protected abstract void onConfirmExit();
}
