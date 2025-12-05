package gui;

import gui.ui.AppUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import bd.FichajeDAO;
import bd.TrabajadorDAO;
import domain.BDAdmin;
import domain.BDFichaje;
import domain.BDTrabajador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VVerTrabajadores extends VentanaConConfirmacion {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private VAdmin1 parent;
    private VPrincipal login;     
    private BDAdmin admin;

    private JLabel lblTrabajador;

    private JList<BDTrabajador> listTrabajadores;
    private DefaultListModel<BDTrabajador> modeloTrabajadores;

    private JTable tablaFichajes;
    private DefaultTableModel modeloFichajes;

    public VVerTrabajadores(VAdmin1 parent, BDAdmin admin, Integer trabajadorIdInicial) {
        this.parent = parent;
        this.login = login;
        this.admin = admin;

        setTitle("Ver empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{100, 600, 100};
        gbl_contentPane.rowHeights = new int[]{50, 350, 50};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0};
        gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
        contentPane.setLayout(gbl_contentPane);

        // ======= LABEL SUPERIOR =======
        lblTrabajador = new JLabel("Ningún trabajador seleccionado");
        GridBagConstraints gbc_lblTrabajador = new GridBagConstraints();
        gbc_lblTrabajador.insets = new Insets(0, 0, 5, 0);
        gbc_lblTrabajador.gridx = 1;
        gbc_lblTrabajador.gridy = 0;
        contentPane.add(lblTrabajador, gbc_lblTrabajador);

        // ======= PANEL PRINCIPAL (CENTRO) =======
        JPanel panelPrincipal = new JPanel();
        GridBagConstraints gbc_panelPrincipal = new GridBagConstraints();
        gbc_panelPrincipal.insets = new Insets(0, 0, 5, 0);
        gbc_panelPrincipal.fill = GridBagConstraints.BOTH;
        gbc_panelPrincipal.gridx = 1;
        gbc_panelPrincipal.gridy = 1;
        contentPane.add(panelPrincipal, gbc_panelPrincipal);

        GridBagLayout gbl_panelPrincipal = new GridBagLayout();
        gbl_panelPrincipal.columnWidths = new int[]{250, 20, 530};
        gbl_panelPrincipal.rowHeights = new int[]{350};
        gbl_panelPrincipal.columnWeights = new double[]{0.3, 0.0, 0.7};
        gbl_panelPrincipal.rowWeights = new double[]{1.0};
        panelPrincipal.setLayout(gbl_panelPrincipal);

        // ======= PANEL IZQUIERDO – LISTA TRABAJADORES =======
        JPanel panelLeft = new JPanel(new BorderLayout());
        GridBagConstraints gbc_panelLeft = new GridBagConstraints();
        gbc_panelLeft.insets = new Insets(0, 0, 0, 5);
        gbc_panelLeft.fill = GridBagConstraints.BOTH;
        gbc_panelLeft.gridx = 0;
        gbc_panelLeft.gridy = 0;
        panelPrincipal.add(panelLeft, gbc_panelLeft);

        modeloTrabajadores = new DefaultListModel<>();
        listTrabajadores = new JList<>(modeloTrabajadores);
        listTrabajadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollListTrab = new JScrollPane(listTrabajadores);
        panelLeft.add(scrollListTrab, BorderLayout.CENTER);

        // Cargamos trabajadores desde la BD
        for (BDTrabajador t : TrabajadorDAO.getAllTrabajadores()) {
            modeloTrabajadores.addElement(t);
        }

        // Selección inicial (si viene id desde otra ventana)
        if (trabajadorIdInicial != null) {
            for (int i = 0; i < modeloTrabajadores.size(); i++) {
                BDTrabajador t = modeloTrabajadores.getElementAt(i);
                if (t.getId() == trabajadorIdInicial) {
                    listTrabajadores.setSelectedIndex(i);
                    break;
                }
            }
        }

        // ======= PANEL DERECHO – TABLA FICHAJES =======
        JPanel panelRight = new JPanel(new BorderLayout());
        GridBagConstraints gbc_panelRight = new GridBagConstraints();
        gbc_panelRight.fill = GridBagConstraints.BOTH;
        gbc_panelRight.gridx = 2;
        gbc_panelRight.gridy = 0;
        panelPrincipal.add(panelRight, gbc_panelRight);

        String[] columnas = {"Día", "Entrada", "Salida", "Horas trabajadas"};
        modeloFichajes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla solo lectura
            }
        };
        tablaFichajes = new JTable(modeloFichajes);
        JScrollPane scrollTabla = new JScrollPane(tablaFichajes);
        panelRight.add(scrollTabla, BorderLayout.CENTER);

        // ======= PANEL INFERIOR – BOTONES =======
        JPanel panelSouth = new JPanel();
        GridBagConstraints gbc_panelSouth = new GridBagConstraints();
        gbc_panelSouth.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelSouth.gridx = 1;
        gbc_panelSouth.gridy = 2;
        contentPane.add(panelSouth, gbc_panelSouth);

        JButton btnDespedir = new JButton("Despedir");
        JButton btnVolver = new JButton("Volver");
        panelSouth.add(btnDespedir);
        panelSouth.add(btnVolver);

        // ======= LISTENER LISTA TRABAJADORES =======
        listTrabajadores.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    BDTrabajador t = listTrabajadores.getSelectedValue();
                    if (t != null) {
                        lblTrabajador.setText("Trabajador seleccionado: " + t.getNombre());
                        actualizarTablaFichajes(t.getId());
                    } else {
                        lblTrabajador.setText("Ningún trabajador seleccionado");
                        modeloFichajes.setRowCount(0);
                    }
                }
            }
        });

        // ======= ACCIONES BOTONES =======
        btnVolver.addActionListener(e -> onConfirmExit());


        btnDespedir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BDTrabajador seleccionado = listTrabajadores.getSelectedValue();

                if (seleccionado == null) {
                    JOptionPane.showMessageDialog(
                            VVerTrabajadores.this,
                            "Selecciona primero un trabajador de la lista.",
                            "Ningún trabajador seleccionado",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int opcion = JOptionPane.showConfirmDialog(
                        VVerTrabajadores.this,
                        "¿Seguro que quieres despedir a '" + seleccionado.getNombre() + "'?",
                        "Confirmar despido",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }

                // Borrar de BD
                TrabajadorDAO.eliminarPorNombre(seleccionado.getNombre());

                // Borrar de estructuras en memoria (si VPrincipal las usa)
                VPrincipal.getTrabajadores().remove(seleccionado);
                VPrincipal.getPersonal().remove(seleccionado);

                // Borrar del modelo de la JList
                modeloTrabajadores.removeElement(seleccionado);

                // Limpiar tabla
                modeloFichajes.setRowCount(0);
                lblTrabajador.setText("Ningún trabajador seleccionado");

                JOptionPane.showMessageDialog(
                        VVerTrabajadores.this,
                        "Trabajador despedido correctamente.",
                        "Despedido",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // ======= ESTILO APPUI =======
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(panelPrincipal);

        AppUI.styleTransparent(panelLeft);
        AppUI.styleTransparent(panelRight);
        AppUI.styleTransparent(panelSouth);

        AppUI.styleLabel(lblTrabajador);
        AppUI.styleList(listTrabajadores);
        AppUI.styleTable(tablaFichajes);

        AppUI.stylePrimaryButton(btnVolver);
        AppUI.stylePrimaryButton(btnDespedir);

        AppUI.establecerIcono(this);
    }

    // ===========================
    //   CARGAR FICHAJES EN TABLA
    // ===========================
    private void actualizarTablaFichajes(int idTrabajador) {
        try {
            List<BDFichaje> fichajes = FichajeDAO.obtenerFichajesTrabajador(idTrabajador);

            modeloFichajes.setRowCount(0);

            for (BDFichaje f : fichajes) {
                LocalDate dia = f.getEntrada().toLocalDate();
                LocalTime entrada = f.getEntrada().toLocalTime();
                String entradaStr = entrada.toString().substring(0, 5);

                String salidaStr = "";
                String horasStr = "";

                if (f.getSalida() != null) {
                    LocalTime salida = f.getSalida().toLocalTime();
                    salidaStr = salida.toString().substring(0, 5);

                    long minutos = Duration.between(f.getEntrada(), f.getSalida()).toMinutes();
                    horasStr = (minutos / 60) + "h " + (minutos % 60) + "min";
                }

                modeloFichajes.addRow(new Object[]{
                        dia.toString(),
                        entradaStr,
                        salidaStr,
                        horasStr
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error cargando fichajes del trabajador.",
                    "Error BD",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    protected void onConfirmExit() {
        if (parent != null) {
            parent.setVisible(true);
            parent.repaint();
        }
        dispose();
    }
}

