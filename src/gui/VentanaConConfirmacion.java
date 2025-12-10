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
                int opcion = JOptionPane.showConfirmDialog(
                        VentanaConConfirmacion.this,
                        getMensajeConfirmacionSalida(),
                        getTituloConfirmacionSalida(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    onConfirmExit();
                }
            }
        });
    }
    
    
    // X SIEMPRE LLAMA A preguntarYSalir(), se muestra un JOptionPane
    // Cada ventana hace sus cosas

    protected String getMensajeConfirmacionSalida() {
        return "¿Seguro que quieres salir de la aplicación?";
    }

    protected String getTituloConfirmacionSalida() {
        return "Confirmar salida";
    }
    
    /** Qué significa "salir" para esta ventana concreta */
    protected abstract void onConfirmExit();
    
    
    
}
