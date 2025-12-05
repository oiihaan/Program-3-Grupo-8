package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bd.TareaDAO;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VTrabajadorTarea extends VentanaConConfirmacion {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private VTrabajador1 parent;

    private Thread HiloTiempo;

    public VTrabajadorTarea(VTrabajador1 parent, BDTrabajador trabajador) {
        super();               // usar el constructor de VentanaConConfirmacion
        this.parent = parent;

        setTitle("Tareas");
        // NO: setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 616, 423);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{300};
        gbl_contentPane.rowHeights = new int[]{50, 300, 50};
        gbl_contentPane.columnWeights = new double[]{0.0};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0};
        contentPane.setLayout(gbl_contentPane);

        JPanel Centro = new JPanel();
        GridBagConstraints gbc_Centro = new GridBagConstraints();
        gbc_Centro.insets = new Insets(0, 0, 5, 0);
        gbc_Centro.fill = GridBagConstraints.BOTH;
        gbc_Centro.gridx = 0;
        gbc_Centro.gridy = 1;
        contentPane.add(Centro, gbc_Centro);

        GridBagLayout gbl_Centro = new GridBagLayout();
        gbl_Centro.columnWidths = new int[]{150, 150};
        gbl_Centro.rowHeights = new int[]{50, 200, 50};
        gbl_Centro.columnWeights = new double[]{0.0, 0.0};
        gbl_Centro.rowWeights = new double[]{0.0, 0.0, 0.0};
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

        // Tipar la lista
        JList<BDTarea> list = new JList<>();
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
        btnEmpezarTarea.setBounds(10, 36, 130, 21);
        centroDe.add(btnEmpezarTarea);

        JButton btnFinalizarTarea = new JButton("Finalizar Tarea");
        btnFinalizarTarea.setEnabled(false);
        btnFinalizarTarea.setBounds(10, 68, 130, 21);
        centroDe.add(btnFinalizarTarea);

        // ==== MODELO LISTA TAREAS ====
        DefaultListModel<BDTarea> modelo = new DefaultListModel<>();

        for (BDTarea t : TareaDAO.getTareasDeTrabajador(trabajador.getId())) {
            if (!"finalizado".equals(t.getEstado())) {
                modelo.addElement(t);
            }
        }

        list.setModel(modelo);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Habilitar / deshabilitar botones en función del estado
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list.isSelectionEmpty()) {
                    btnEmpezarTarea.setEnabled(false);
                    btnFinalizarTarea.setEnabled(false);
                    return;
                }

                BDTarea tareaselect = list.getSelectedValue();
                if (tareaselect == null) return;

                if ("pendiente".equals(tareaselect.getEstado())) {
                    btnEmpezarTarea.setEnabled(true);
                    btnFinalizarTarea.setEnabled(false);
                } else {
                    btnEmpezarTarea.setEnabled(false);
                    btnFinalizarTarea.setEnabled(true);
                }
            }
        });

        // Inicia el hilo de la tarea seleccionada
        btnEmpezarTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BDTarea tareaselect = list.getSelectedValue();
                if (tareaselect == null) return;

                tareaselect.getHilo().start();
                btnEmpezarTarea.setEnabled(false);
                btnFinalizarTarea.setEnabled(true);
            }
        });

        // Finaliza el hilo de la tarea seleccionada y la borra de la lista
        btnFinalizarTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BDTarea tareaselect = list.getSelectedValue();
                if (tareaselect == null) return;

                TareaDAO.marcarCompletada(tareaselect.getId());
                tareaselect.getHilo().interrupt();
                btnFinalizarTarea.setEnabled(false);
                btnEmpezarTarea.setEnabled(false);

                int indice = modelo.indexOf(tareaselect);
                if (indice >= 0) {
                    modelo.removeElementAt(indice);
                }
            }
        });

        // Hilo que revisa cada 10 segundos si alguna tarea está finalizada y la borra del modelo
        HiloTiempo = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(10000);

                    for (BDTarea tarea : trabajador.getTareasAsignadas()) {
                        if ("finalizado".equals(tarea.getEstado()) && modelo.contains(tarea)) {
                            System.out.println("Borrando tarea finalizada del modelo");
                            SwingUtilities.invokeLater(() -> {
                                btnFinalizarTarea.setEnabled(false);
                                btnEmpezarTarea.setEnabled(false);
                                int indice = modelo.indexOf(tarea);
                                if (indice >= 0) {
                                    modelo.removeElementAt(indice);
                                }
                            });
                        }
                    }
                }
            } catch (InterruptedException ex) {
                // Sale del bucle cuando se interrumpe
                // No hace falta log aquí si no quieres ruido
            }
        });
        HiloTiempo.start();

        // Estilo AppUI
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(Centro);

        AppUI.styleTransparent(centroIz);
        AppUI.styleTransparent(centroDe);

        AppUI.styleList(list);

        AppUI.stylePrimaryButton(btnEmpezarTarea);
        AppUI.stylePrimaryButton(btnFinalizarTarea);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 161, 130, 23);
        centroDe.add(btnVolver);
        AppUI.stylePrimaryButton(btnVolver);

        // Volver a la pestaña anterior usando el mismo flujo que la X
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onConfirmExit();
            }
        });

        // IMAGEN
        AppUI.establecerIcono(this);
    }

    @Override
    protected void onConfirmExit() {
        // Este método SOLO se llama cuando el usuario ha dicho "Sí"
        // en el diálogo de VentanaConConfirmacion.

        // 1) Paramos el hilo de refresco si sigue vivo
        if (HiloTiempo != null && HiloTiempo.isAlive()) {
            HiloTiempo.interrupt();
        }

        // 2) Volvemos a la ventana padre del trabajador
        if (parent != null) {
            parent.setVisible(true);
            parent.repaint();
        }

        // 3) Cerramos esta ventana
        dispose();
    }
}
