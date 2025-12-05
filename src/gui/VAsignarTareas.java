package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VAsignarTareas extends VentanaConConfirmacion {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private VVerTareas parent;
    private BDAdmin admin;

    public VAsignarTareas(VVerTareas parent, BDAdmin admin) {
        super();                  // importante: llama al constructor de la base
        this.parent = parent;
        this.admin = admin;

        setTitle("Asignar Tareas");
        // NO: setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); -> lo hace la base

        setBounds(100, 100, 604, 426);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{30, 200, 30};
        gbl_contentPane.rowHeights = new int[]{20, 300, 20};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0};
        gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
        contentPane.setLayout(gbl_contentPane);

        // Listas tipadas
        JList<BDTrabajador> listTrabajadores = new JList<>();
        JList<BDTarea> listaTareas = new JList<>();
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel Centro = new JPanel();
        GridBagConstraints gbc_Centro = new GridBagConstraints();
        gbc_Centro.insets = new Insets(0, 0, 5, 5);
        gbc_Centro.fill = GridBagConstraints.BOTH;
        gbc_Centro.gridx = 1;
        gbc_Centro.gridy = 1;
        contentPane.add(Centro, gbc_Centro);
        Centro.setLayout(new BorderLayout(0, 0));

        JPanel centroNorth = new JPanel();
        Centro.add(centroNorth, BorderLayout.NORTH);
        centroNorth.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblTareas = new JLabel("Tareas");
        lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
        centroNorth.add(lblTareas);

        JLabel lblTrabajadores = new JLabel("Trabajadores");
        lblTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
        centroNorth.add(lblTrabajadores);

        JPanel centroSouth = new JPanel();
        FlowLayout flowLayout = (FlowLayout) centroSouth.getLayout();
        flowLayout.setHgap(30);
        Centro.add(centroSouth, BorderLayout.SOUTH);

        JButton btnAsignarTarea = new JButton("Asignar Tarea");
        centroSouth.add(btnAsignarTarea);

        JButton btnVolver = new JButton("Volver");
        centroSouth.add(btnVolver);

        JPanel centroCentro = new JPanel();
        Centro.add(centroCentro, BorderLayout.CENTER);
        centroCentro.setLayout(new GridLayout(1, 2, 0, 0));

        JPanel centroIzq = new JPanel();
        centroCentro.add(centroIzq);
        centroIzq.setPreferredSize(new Dimension(50, 0));

        JScrollPane scrollPaneCentroIzq = new JScrollPane();
        SpringLayout sl_centroIzq = new SpringLayout();
        centroIzq.setLayout(sl_centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.NORTH, scrollPaneCentroIzq, 0, SpringLayout.NORTH, centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.WEST, scrollPaneCentroIzq, 0, SpringLayout.WEST, centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.SOUTH, scrollPaneCentroIzq, 270, SpringLayout.NORTH, centroIzq);
        sl_centroIzq.putConstraint(SpringLayout.EAST, scrollPaneCentroIzq, 257, SpringLayout.WEST, centroIzq);
        centroIzq.add(scrollPaneCentroIzq);
        scrollPaneCentroIzq.setViewportView(listaTareas);

        JPanel centroDe = new JPanel();
        centroCentro.add(centroDe);
        centroDe.setPreferredSize(new Dimension(50, 0));
        SpringLayout sl_centroDe = new SpringLayout();
        centroDe.setLayout(sl_centroDe);

        JScrollPane scrollPaneCentroDe = new JScrollPane();
        sl_centroDe.putConstraint(SpringLayout.NORTH, scrollPaneCentroDe, 0, SpringLayout.NORTH, centroDe);
        sl_centroDe.putConstraint(SpringLayout.WEST, scrollPaneCentroDe, 0, SpringLayout.WEST, centroDe);
        sl_centroDe.putConstraint(SpringLayout.SOUTH, scrollPaneCentroDe, 270, SpringLayout.NORTH, centroDe);
        sl_centroDe.putConstraint(SpringLayout.EAST, scrollPaneCentroDe, 257, SpringLayout.WEST, centroDe);
        centroDe.add(scrollPaneCentroDe);
        scrollPaneCentroDe.setViewportView(listTrabajadores);

        // -- LÓGICA DE LISTAS --
        DefaultListModel<BDTarea> modeloListTarea = new DefaultListModel<>();
        for (BDTarea t : VPrincipal.getTareas()) {
            modeloListTarea.addElement(t);
        }
        listaTareas.setModel(modeloListTarea);

        DefaultListModel<BDTrabajador> modeloListaTrabajadores = new DefaultListModel<>();
        for (BDTrabajador t : VPrincipal.getTrabajadores()) {
            modeloListaTrabajadores.addElement(t);
        }
        listTrabajadores.setModel(modeloListaTrabajadores);

        // Botón Volver -> mismo comportamiento que la X
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirmExit();
            }
        });

        // TODO: aquí iría la lógica real de btnAsignarTarea

        // Estilo AppUI
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(Centro);

        AppUI.styleTransparent(centroNorth);
        AppUI.styleTransparent(centroSouth);
        AppUI.styleTransparent(centroCentro);
        AppUI.styleTransparent(centroIzq);
        AppUI.styleTransparent(centroDe);

        AppUI.styleTitle(lblTareas);
        AppUI.styleTitle(lblTrabajadores);

        AppUI.stylePrimaryButton(btnAsignarTarea);
        AppUI.stylePrimaryButton(btnVolver);

        AppUI.styleList(listaTareas);
        AppUI.styleList(listTrabajadores);

        AppUI.establecerIcono(this);
    }

    @Override
    protected void onConfirmExit() {
        // Este método se llama SOLO si en VentanaConConfirmacion
        // el usuario ha elegido "Sí" en el diálogo.
        if (parent != null) {
            parent.setVisible(true);  // volvemos a la pantalla de "Ver tareas"
        }
        dispose();                    // cerramos esta ventana
    }
}
