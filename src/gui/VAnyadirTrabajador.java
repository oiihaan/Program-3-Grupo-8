package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bd.TareaDAO;
import bd.TrabajadorDAO;
import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VAnyadirTrabajador extends VentanaConConfirmacion {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField txtPassword;
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
      
        txtPassword = new JPasswordField();
        txtPassword.setColumns(15);
        txtPassword.setEchoChar('•');
        center.add(txtPassword);
       

        
        //BOTONES
        JButton btnAynadir = new JButton("Añadir");
        right.add(btnAynadir);
        btnAynadir.setEnabled(false);

        JButton btnVolver = new JButton("Volver");
        right.add(btnVolver);
        
        
        //LOGICA TEXTFIELDS
        txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!txtUsername.getText().isEmpty() && txtPassword.getPassword().length > 0) {	
					btnAynadir.setEnabled(true);
				}else {
					btnAynadir.setEnabled(false);
				}
			}
		});
		
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!txtUsername.getText().isEmpty() && txtPassword.getPassword().length > 0) {
					btnAynadir.setEnabled(true);
				}else {
					btnAynadir.setEnabled(false);
				}
			}
		});
        
        
        
        //LOGICA de los BOTONES
        btnAynadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            	String contrasenya = new String(txtPassword.getPassword()).trim();
            	String username = txtUsername.getText().trim();
                
//                if(!nombreSinNumeros(username)) {
//                	JOptionPane.showMessageDialog(VAnyadirTrabajador.this, "El nombre tiene que ser un nombre real");
//                	txtUsername.setText("");
//                	return;
//                }
                
				txtUsername.setText("");
				txtPassword.setText("");
				btnAynadir.setEnabled(false);
			

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
        AppUI.stylePasswordField(txtPassword);
        
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
    
//    public static boolean nombreSinNumeros(String nombre) {
//        for (int i = 0; i < nombre.length(); i++) {
//            char c = nombre.charAt(i);
//            if (Character.isDigit(c)) {   // si encuentra un dígito, no es válido
//                return false;
//            }
//        }
//        return true; // no había dígitos
//    }
    
    
}
