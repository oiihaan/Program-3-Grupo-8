package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bd.TrabajadorDAO;
import domain.BDAdmin;
import domain.BDTrabajador;
import gui.ui.AppUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VAnyadirTrabajador extends VentanaConConfirmacion {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtPassword;
    private JTextField txtUsername;
    private VAdmin1 parent;
    private BDAdmin admin;

    public VAnyadirTrabajador(VAdmin1 parent, BDAdmin admin) {
        super();      // importante: llama al constructor de la base
        this.parent = parent;
        this.admin = admin;

        setTitle("Añadir Trabajador");
        // NO poner lo de default:
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{50, 195, 195, 200, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JPanel left = new JPanel();
        GridBagConstraints gbc_left = new GridBagConstraints();
        gbc_left.fill = GridBagConstraints.BOTH;
        gbc_left.insets = new Insets(0, 0, 0, 5);
        gbc_left.gridx = 1;
        gbc_left.gridy = 1;
        panel.add(left, gbc_left);
        AppUI.styleTransparent(left);
        left.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblUsername = new JLabel("Nombre de usuario: ");
        left.add(lblUsername);
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        AppUI.styleLabel(lblUsername);

        JLabel lblContrasenya = new JLabel("Contraseña: ");
        lblContrasenya.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(lblContrasenya);
        AppUI.styleLabel(lblContrasenya);

        JPanel center = new JPanel();
        GridBagConstraints gbc_center = new GridBagConstraints();
        gbc_center.fill = GridBagConstraints.BOTH;
        gbc_center.insets = new Insets(0, 0, 0, 5);
        gbc_center.gridx = 2;
        gbc_center.gridy = 1;
        panel.add(center, gbc_center);
        AppUI.styleTransparent(center);
        center.setLayout(new GridLayout(0, 1, 0, 0));

        txtUsername = new JTextField();
        center.add(txtUsername);
        txtUsername.setColumns(10);
        AppUI.styleTextField(txtUsername);

        txtPassword = new JTextField();
        center.add(txtPassword);
        txtPassword.setColumns(10);
        AppUI.styleTextField(txtPassword);

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2, 1, 0, 10));
        GridBagConstraints gbc_right = new GridBagConstraints();
        gbc_right.fill = GridBagConstraints.BOTH;
        gbc_right.gridx = 3;
        gbc_right.gridy = 1;
        panel.add(right, gbc_right);
        AppUI.styleTransparent(right);

        JButton btnAynadir = new JButton("Añadir");
        right.add(btnAynadir);

        btnAynadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username  = txtUsername.getText().trim();
                String contrasenya = txtPassword.getText().trim();

                if (username.isEmpty() || contrasenya.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            VAnyadirTrabajador.this,
                            "Rellena todos los campos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                try {
                    BDTrabajador nuevo = new BDTrabajador(
                            0, username, contrasenya,
                            null, null, null
                    );

                    // 1) Insertar en BD
                    TrabajadorDAO.insertarTrabajador(nuevo);

                    // 2) Añadir al conjunto estático de VPrincipal
                    VPrincipal.getTrabajadores().add(nuevo);

                    // 3) Avisar de éxito
                    JOptionPane.showMessageDialog(
                            VAnyadirTrabajador.this,
                            "Trabajador añadido correctamente.",
                            "OK",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // 4) Volver al menú admin -> mismo flujo que la X
                    onConfirmExit();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            VAnyadirTrabajador.this,
                            "Error al insertar el trabajador:\n" + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onConfirmExit();
            }
        });
        right.add(btnVolver);

        AppUI.stylePrimaryButton(btnVolver);
        AppUI.stylePrimaryButton(btnAynadir);

        // Estilo AppUI
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(panel);

        // IMAGEN
        AppUI.establecerIcono(this);

        // Para tamaño ajustado
        this.pack();
        // Para centrar
        this.setLocationRelativeTo(null);
    }

    @Override
    protected void onConfirmExit() {
        // Este método solo se ejecuta cuando el usuario ha dicho "Sí"
        // en el VentanaConConfirmacion.
        if (parent != null) {
            parent.setVisible(true); // volvemos al menú admin
        }
        dispose();                  
    }
}
