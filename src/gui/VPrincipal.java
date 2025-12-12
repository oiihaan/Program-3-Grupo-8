package gui;


//IMPORTS
import gui.ui.AppUI;
import domain.BDAdmin;
import domain.BDTarea;
import domain.BDTrabajador;
import domain.Usuario;
import javax.swing.*;
import bd.AdminDAO;
import bd.TareaDAO;
import bd.TrabajadorDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;


//CONSTRUCTOR
public class VPrincipal extends VentanaConConfirmacion {

    private JPanel contentPane;
    private JPanel card;

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JLabel lblForgot;

    private static HashSet<BDTarea> tareas;
    private static HashSet<Usuario> personal; //Prueba Unai


    public VPrincipal() {


        


     // === Login TITULO ===
    	setTitle("Login");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 555, 491);
        setLocationRelativeTo(null);

        // === FONDO ===
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        // === CARD CENTRAL ===
        card = new JPanel();
        card.setLayout(new GridBagLayout());

        // --- TÍTULO ---
        JLabel lblTitle = new JLabel("Bienvenido");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.gridx = 0;
        gbc_lblTitle.gridy = 0;
        gbc_lblTitle.insets = new Insets(8, 0, 8, 0);
        gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
        card.add(lblTitle, gbc_lblTitle);

        // --- USUARIO ---
        txtUser = new JTextField();
        txtUser.setColumns(15);
        GridBagConstraints gbc_txtUser = new GridBagConstraints();
        gbc_txtUser.gridx = 0;
        gbc_txtUser.gridy = 1;
        gbc_txtUser.insets = new Insets(8, 0, 8, 0);
        gbc_txtUser.fill = GridBagConstraints.HORIZONTAL;
        card.add(txtUser, gbc_txtUser);

        // --- PASSWORD ---
        txtPass = new JPasswordField();
        txtPass.setColumns(15);
        GridBagConstraints gbc_txtPass = new GridBagConstraints();
        gbc_txtPass.gridx = 0;
        gbc_txtPass.gridy = 2;
        gbc_txtPass.insets = new Insets(8, 0, 8, 0);
        gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
        card.add(txtPass, gbc_txtPass);

        // --- BOTÓN LOGIN ---
        btnLogin = new JButton("LOG IN");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 3;
        gbc_btnLogin.insets = new Insets(8, 0, 8, 0);
        gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
        card.add(btnLogin, gbc_btnLogin);
        getRootPane().setDefaultButton(btnLogin); //Para poder entrar haciendo enter tb

        // --- LABEL OLVIDÉ CONTRASEÑA ---
        lblForgot = new JLabel("Olvidé mi contraseña");
        lblForgot.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblForgot = new GridBagConstraints();
        gbc_lblForgot.gridx = 0;
        gbc_lblForgot.gridy = 4;
        gbc_lblForgot.insets = new Insets(8, 0, 8, 0);
        gbc_lblForgot.fill = GridBagConstraints.HORIZONTAL;
        card.add(lblForgot, gbc_lblForgot);

        // --- La card al fondo (centrada) ---
        GridBagConstraints gbc_card = new GridBagConstraints();
        gbc_card.gridx = 0;
        gbc_card.gridy = 0;
        contentPane.add(card, gbc_card);

        // ESTILO CON AppUI
        AppUI.styleBackground(contentPane);
        AppUI.styleCard(card);
        AppUI.styleTitle(lblTitle);
        AppUI.styleTextField(txtUser);
        AppUI.stylePasswordField(txtPass);
        AppUI.stylePrimaryButton(btnLogin);
        AppUI.styleSubtitle(lblForgot);
        
      //IMAGEN
      AppUI.establecerIcono(this);


        // === LÓGICA DEL LOGIN ===
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	personal = new HashSet<Usuario>();
            	cargarPersonal();
            	
                String usuario = txtUser.getText().trim();
                String contraseyna = String.copyValueOf(txtPass.getPassword());
                Boolean entrado = false;
                
                if(usuario.isEmpty() || contraseyna.isEmpty()) {
                	JOptionPane.showMessageDialog(null, "ERROR, el usuario o contraseña estan vacios");
                	return;
                }

                
                for (Usuario u : personal) {
                	if(u.getNombre().equals(usuario) && u.getContraseyna().equals(contraseyna) ) {
                		txtUser.setText("");
                		txtPass.setText("");
                		if (u instanceof BDTrabajador) {
                			VTrabajador1 v = new VTrabajador1(VPrincipal.this, (BDTrabajador) u);
                            v.setVisible(true);
                            VPrincipal.this.setVisible(false);
                            entrado = true;
                		} else if (u instanceof BDAdmin) {
                			VAdmin1 v = new VAdmin1(VPrincipal.this, (BDAdmin) u);
                            v.setVisible(true);
                            VPrincipal.this.setVisible(false);	
                            entrado = true;

						}
                		
                		
                	}
                	
                }
                if (!entrado == true) {
        		JOptionPane.showMessageDialog(null, "El usuario o la contraseña no son válidos" );
                }
            }
            
            
        });
        
        
        //Logica OLVIDE MI CONTRASEÑA
        lblForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Porfavor comuniquese con el departamento de \n recursos humanos: PabloAvila@gmail.com");
            }
        });
    }
	public static void cargarPersonal() {
		for (BDTrabajador t : TrabajadorDAO.getAllTrabajadores()) {
			personal.add(t);
		}
		for(BDAdmin a : AdminDAO.getAllAdmins()) {
			personal.add(a);
		}
	}


	@Override
	protected String getMensajeConfirmacionSalida() {
	    return "¿Quieres cerrar la aplicación?";
	}

	@Override
	protected String getTituloConfirmacionSalida() {
	    return "Salir";
	}

	@Override
	protected void onConfirmExit() {
	   
		Boolean ejecutando = false;
		for (BDTarea t : TareaDAO.getAllTareas()) {
			if(t.getEstado().equals("ejecutando")) {
				ejecutando = true;
				break;
			}
		}
		
		if(ejecutando) {
			
	        int respuesta = JOptionPane.showConfirmDialog(
	                null,                          // componente padre (puede ser null)
	                "Algunas tareas se estan ejecutando si cierras el programa volveran a estar pendientes \n Quieres cerrar el programa?",          // mensaje
	                "Confirmación",                 // título de la ventana
	                JOptionPane.YES_NO_OPTION,      // tipo de opciones (Sí/No)
	                JOptionPane.QUESTION_MESSAGE    // icono de pregunta
	        );
			if(respuesta == JOptionPane.YES_OPTION) {
				for(BDTarea t : TareaDAO.getAllTareas()) {
					if(t.getEstado().equals("ejecutando")) {
						TareaDAO.marcarPendiente(t.getId());
					}
				}
		        System.exit(0);

			}
		}else {
        System.exit(0);
		}
	}
	public JTextField getTxtUser() {
		return txtUser;
	}

	


	
	
}
