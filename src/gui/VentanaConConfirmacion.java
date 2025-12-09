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
    
    // permite que cada ventana personalice el mensaje
    protected String getExitMessage() {
        return "¿Seguro que quieres salir de la aplicación?";
    }

    private void preguntarYSalir() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                getExitMessage(),
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            onConfirmExit();
        }
        
    }
    // X SIEMPRE LLAMA A preguntarYSalir(), se muestra un JOptionPane
    // Cada ventana hace sus cosas

    /** Qué significa "salir" para esta ventana concreta */
    protected abstract void onConfirmExit();
}
