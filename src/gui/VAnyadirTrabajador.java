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
        // NO poner lo de setDefaultCloseOperation:
    
        
        
        //DISEÑO
        setBounds(100, 100, 704, 348);
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 1, 0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.rowHeights = new int[] {50, 58, 50};
        gbl_panel.columnWidths = new int[] {30, 200, 200, 200, 50};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
        panel.setLayout(gbl_panel);

        
        //3 paneles
        JPanel left = new JPanel();
        GridBagConstraints gbc_left = new GridBagConstraints();
        gbc_left.fill = GridBagConstraints.BOTH;
        gbc_left.insets = new Insets(0, 0, 0, 5);
        gbc_left.gridx = 1;
        gbc_left.gridy = 1;
        panel.add(left, gbc_left);
        left.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel center = new JPanel();
        GridBagConstraints gbc_center = new GridBagConstraints();
        gbc_center.fill = GridBagConstraints.BOTH;
        gbc_center.insets = new Insets(0, 0, 0, 5);
        gbc_center.gridx = 2;
        gbc_center.gridy = 1;
        panel.add(center, gbc_center);
        center.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2, 1, 0, 10));
        GridBagConstraints gbc_right = new GridBagConstraints();
        gbc_right.fill = GridBagConstraints.BOTH;
        gbc_right.gridx = 3;
        gbc_right.gridy = 1;
        panel.add(right, gbc_right);
        
        
        
        //LABELS
        JLabel lblUsername = new JLabel("Nombre de usuario: ");
        left.add(lblUsername);
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
       

        JLabel lblContrasenya = new JLabel("Contraseña: ");
        lblContrasenya.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(lblContrasenya);


        //TEXTFIELDS
        txtUsername = new JTextField();
        center.add(txtUsername);
        txtUsername.setColumns(10);
      
        txtPassword = new JTextField();
        center.add(txtPassword);
        txtPassword.setColumns(10);
       

        
        //BOTONES
        JButton btnAynadir = new JButton("Añadir");
        right.add(btnAynadir);

        JButton btnVolver = new JButton("Volver");
        right.add(btnVolver);
        
        
        
        //LOGICA de los BOTONES
        btnAynadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username  = txtUsername.getText().trim();
                String contrasenya = txtPassword.getText().trim();

                //Prevencion de campos vacios 
                if (username.isEmpty() || contrasenya.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            VAnyadirTrabajador.this,
                            "Rellena todos los campos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                //Uso de la funcion recursiva
                //Sugiere al usuario una contraseña creada por el sistema
                String suggestedPassword = invertirRecursivo(username);
                
                if (!suggestedPassword.equals(contrasenya)) {
                    int respuesta = JOptionPane.showConfirmDialog(
                            VAnyadirTrabajador.this,
                            ("Nos gustaria sugerirte esta contraseña creada por nuestro algoritmo super seguro: " +suggestedPassword),
                            "RECOMENDACION",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    
                  //Si la respuesta es si...
                    if (respuesta == JOptionPane.YES_OPTION) {
                        contrasenya = suggestedPassword;
                    }
                }

                //Insercion en BBDD
                try {
                    BDTrabajador nuevo = new BDTrabajador(
                            0, username, contrasenya,
                            null, null, null
                    );

                    // 1) Insertar en BD
                    TrabajadorDAO.insertarTrabajador(nuevo);

                    // 2) Añadir al conjunto estático de VPrincipal --> EL login lo pilla de la base(Unai)
                  //  VPrincipal.getTrabajadores().add(nuevo);

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
        
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onConfirmExit();
            }
        });
        
        

        // Estilo AppUI
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(panel);
        AppUI.styleTransparent(right);
        AppUI.styleTransparent(left);
        AppUI.styleLabel(lblUsername);
        AppUI.stylePrimaryButton(btnVolver);
        AppUI.stylePrimaryButton(btnAynadir);
        AppUI.styleLabel(lblContrasenya);
        AppUI.styleTransparent(center);
        AppUI.styleTextField(txtUsername);
        AppUI.styleTextField(txtPassword);
        
        // IMAGEN
        AppUI.establecerIcono(this);

        // Para ajustar el tamaño(Con ayuda de AI studio, no sabemos porque se nos genera asi justo esta ventana)
        //this.pack();
        // Para centrar
        this.setLocationRelativeTo(null);
    }

    
    
    
    
    // Salida dandole a la x:
    @Override
    protected String getMensajeConfirmacionSalida() {
        return "¿Quieres volver al panel de control de administrador?";
    }

    @Override
    protected String getTituloConfirmacionSalida() {
        return "Volver al panel";
    }

    @Override
    protected void onConfirmExit() {
        this.dispose();
        if (parent != null) {
            parent.setVisible(true);
        }
    }
    
    
    //Funcion de RECURSIVIDAD
    public static String invertirRecursivo(String str) { //Funcion del github de Fernando Boto
    	
        // Caso base. Si es el string vacío devolvemos directamente el string vacío.
        if (str.isEmpty()) return "";
        
        // Caso recursivo. Invertir el string actual menos el primer caracter + primer carácter
        return invertirRecursivo(str.substring(1)) + str.charAt(0);
    }
    
    
    
    
}
