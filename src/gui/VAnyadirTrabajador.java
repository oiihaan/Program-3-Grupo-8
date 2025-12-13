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
        super();      
        this.parent = parent;
        this.admin = admin;

        setTitle("Añadir Trabajador");
    
        
        
        //DISEÑO
        setBounds(100, 100, 704, 348);
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 1, 0, 0));
        
        JPanel panelDeAtras = new JPanel();
        contentPane.add(panelDeAtras);
        GridBagConstraints gbc_panelDeAtras = new GridBagConstraints();
        gbc_panelDeAtras.insets = new Insets(0, 0, 5, 5);
        gbc_panelDeAtras.fill = GridBagConstraints.BOTH;
        gbc_panelDeAtras.gridx = 1;
        gbc_panelDeAtras.gridy = 0;
        GridBagLayout gbl_panelDeAtras = new GridBagLayout();
        gbl_panelDeAtras.columnWidths = new int[] {50, 600, 50};
        gbl_panelDeAtras.rowHeights = new int[] {50, 58, 50};
        gbl_panelDeAtras.columnWeights = new double[]{0.0, 0.0,0.0};
        gbl_panelDeAtras.rowWeights = new double[]{0.0, 0.0,0.0};
        panelDeAtras.setLayout(gbl_panelDeAtras);
        
        

        // Estilo AppUI
        AppUI.styleBackground(contentPane);
        
        JPanel panelCuadrado = new JPanel();
        GridBagLayout gbl_panelCuadrado = new GridBagLayout();
        gbl_panelCuadrado.rowHeights = new int[] {58};
        gbl_panelCuadrado.columnWidths = new int[] {200, 200, 200};
        gbl_panelCuadrado.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
        gbl_panelCuadrado.rowWeights = new double[]{1.0, 0.0, 0.0};
        panelCuadrado.setLayout(gbl_panelCuadrado);
        GridBagConstraints gbc_panelCuadrado = new GridBagConstraints();
        gbc_panelCuadrado.anchor = GridBagConstraints.NORTHWEST;
        gbc_panelCuadrado.gridx = 1;
        gbc_panelCuadrado.gridy = 1;
        panelDeAtras.add(panelCuadrado, gbc_panelCuadrado);
        
                
        //3 paneles
        JPanel left = new JPanel();
        GridBagConstraints gbc_left = new GridBagConstraints();
        gbc_left.fill = GridBagConstraints.BOTH;
        gbc_left.insets = new Insets(0, 0, 5, 5);
        gbc_left.gridx = 0;
        gbc_left.gridy = 0;
        panelCuadrado.add(left, gbc_left);
        left.setLayout(new GridLayout(0, 1, 0, 0));
        
	    JPanel center = new JPanel();
	    GridBagConstraints gbc_center = new GridBagConstraints();
	    gbc_center.fill = GridBagConstraints.BOTH;
	    gbc_center.insets = new Insets(0, 0, 5, 5);
	    gbc_center.gridx = 1;
	    gbc_center.gridy = 0;
	    panelCuadrado.add(center, gbc_center);
	    center.setLayout(new GridLayout(0, 1, 0, 0));
	    
	    JPanel right = new JPanel();
	    right.setLayout(new GridLayout(2, 1, 0, 10));
	    GridBagConstraints gbc_right = new GridBagConstraints();
	    gbc_right.insets = new Insets(0, 0, 5, 5);
	    gbc_right.fill = GridBagConstraints.BOTH;
	    gbc_right.gridx = 2;
	    gbc_right.gridy = 0;
	    panelCuadrado.add(right, gbc_right);
	    
	    
	    
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



                    // 2) Avisar de éxito
                    JOptionPane.showMessageDialog(
                            VAnyadirTrabajador.this,
                            "Trabajador añadido correctamente.",
                            "OK",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // 3) Volver al menú admin -> mismo flujo que la X
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
        AppUI.styleBackground(panelDeAtras);
        AppUI.styleCard(panelCuadrado);
        AppUI.styleTransparent(right);
        AppUI.styleTransparent(left);
        AppUI.styleLabel(lblUsername);
        AppUI.stylePrimaryButton(btnVolver);
        AppUI.stylePrimaryButton(btnAynadir);
        AppUI.styleLabel(lblContrasenya);
        AppUI.styleTransparent(center);
        AppUI.styleTextField(txtUsername);
        AppUI.stylePasswordField(txtPassword);
        AppUI.configurarVentana(this);

        // IMAGEN
        AppUI.establecerIcono(this);

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
        if (parent != null) {
            parent.setVisible(true);
        }
        this.dispose();
    }
    
    
    //Funcion de RECURSIVIDAD
    public static String invertirRecursivo(String str) { //Funcion del github de Fernando Boto
    	
        // Caso base. Si es el string vacío devolvemos directamente el string vacío.
        if (str.isEmpty()) return "";
        
        // Caso recursivo. Invertir el string actual menos el primer caracter + primer carácter
        return invertirRecursivo(str.substring(1)) + str.charAt(0);
    }
    

    
}
