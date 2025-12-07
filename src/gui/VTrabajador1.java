package gui;


//IMPORTS
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import bd.FichajeDAO;
import domain.BDFichaje;
import domain.BDTrabajador;
import gui.ui.AppUI;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class VTrabajador1 extends VentanaConConfirmacion {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BDTrabajador trabajador;
	private JButton btnDesfichar;
	private JButton btnFichar;
	private JButton btnVerTareas;
	private VPrincipal parent; 
	
	
	// (Danel): LE HE AÑADIDO PARENT PARA PODER CAMBIAR DE TRABAJADOR--> ADMIN
	// PORQUE SINO LOS FICHAJES NO SE GUARDAN Y AL ENTRAR COMO ADMIN LA LISTA DE FICHAJES DE LOS TRABAJADORES ESTARIA VACIA
	// AL INICIAR EL PROGRAMA NO HAY FICHAJES HECHOS (SE PODRIA CREAR UN METODO PARA QUE LEA UN ARCHIVO DE FICHAJES O ASI PARA QUE NO ESTEN VACIOS DE INICIO)
	
	public void controlFichaje(BDTrabajador trabajador){
		
		if (trabajador.getEntrada() != null) {
			btnDesfichar.setEnabled(true);
			btnFichar.setEnabled(false);


		}else {
			btnFichar.setEnabled(true);
			btnDesfichar.setEnabled(false);


		}
		
	}
	
	//CONSTRUCTOR
	public VTrabajador1(VPrincipal parent, BDTrabajador trabajador) {
		this.parent = parent;
		this.trabajador = trabajador;
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setTitle("Panel del Trabajador");
        setSize(330, 293);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{25, 100, 40, 40, 100, 25, 0};
        gridBagLayout.rowHeights = new int[]{53, 13, 21, 21, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
			// --- LABEL BIENVENIDO ---
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        GridBagConstraints gbc_lbl = new GridBagConstraints();
        gbc_lbl.fill = GridBagConstraints.VERTICAL;
        gbc_lbl.insets = new Insets(0, 0, 5, 0);
        gbc_lbl.gridwidth = 6;
        gbc_lbl.gridx = 0;
        gbc_lbl.gridy = 1;
        getContentPane().add(lbl, gbc_lbl);   
                                              
        // --- BOTÓN DESFICHAR ---
        btnDesfichar = new JButton("Desfichar");
        
        // --- BOTÓN FICHAR ---
        btnFichar = new JButton("Fichar");
        btnFichar.setEnabled(false);
        btnFichar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		trabajador.metodoFichar();
        		btnDesfichar.setEnabled(true);
        		btnFichar.setEnabled(false);

        	}
        });
        
        //-- BOTON LogOut --
        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VTrabajador1.this.dispose();
        		parent.setVisible(true);
        		               		
        	}
        });

        // --- BOTÓN VER TAREAS ---
        btnVerTareas = new JButton("Ver tareas");
        btnVerTareas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VTrabajadorTarea VTrabajadorTarea = new VTrabajadorTarea(VTrabajador1.this , trabajador);
        		VTrabajadorTarea.setVisible(true);
        		VTrabajador1.this.setVisible(false);  // No se abre la ventana, no se porque. Falta pasarle el trabajador
        	}
        });
        
        
        
        //--CODIGO LAYOUT--
        GridBagConstraints gbc_btnFichar = new GridBagConstraints();
        gbc_btnFichar.fill = GridBagConstraints.BOTH;
        gbc_btnFichar.insets = new Insets(0, 0, 5, 5);
        gbc_btnFichar.gridx = 1;
        gbc_btnFichar.gridy = 3;
        getContentPane().add(btnFichar, gbc_btnFichar);
        btnDesfichar.setEnabled(false);
        GridBagConstraints gbc_btnDesfichar = new GridBagConstraints();
        gbc_btnDesfichar.anchor = GridBagConstraints.WEST;
        gbc_btnDesfichar.fill = GridBagConstraints.VERTICAL;
        gbc_btnDesfichar.insets = new Insets(0, 0, 5, 5);
        gbc_btnDesfichar.gridx = 4;
        gbc_btnDesfichar.gridy = 3;
        getContentPane().add(btnDesfichar, gbc_btnDesfichar);

        
        
        GridBagConstraints gbc_btnVerTareas = new GridBagConstraints();
        gbc_btnVerTareas.fill = GridBagConstraints.BOTH;
        gbc_btnVerTareas.insets = new Insets(0, 0, 5, 5);
        gbc_btnVerTareas.gridx = 1;
        gbc_btnVerTareas.gridy = 5;
        getContentPane().add(btnVerTareas, gbc_btnVerTareas);
        GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
        gbc_btnCerrarSesion.insets = new Insets(0, 0, 5, 5);
        gbc_btnCerrarSesion.fill = GridBagConstraints.BOTH;
        gbc_btnCerrarSesion.gridx = 4;
        gbc_btnCerrarSesion.gridy = 5;
        getContentPane().add(btnCerrarSesion, gbc_btnCerrarSesion);;
        
        controlFichaje(trabajador);

        //ESTILO CON AppUI
        AppUI.styleBackground((JPanel) getContentPane());
        AppUI.styleTitle(lbl);
        AppUI.stylePrimaryButton(btnFichar);
        AppUI.stylePrimaryButton(btnDesfichar);
        AppUI.stylePrimaryButton(btnVerTareas);
        AppUI.stylePrimaryButton(btnCerrarSesion);
        
        
      //IMAGEN
		AppUI.establecerIcono(this);
		
	
	  // Fichajes:
		//Llamada al metodo de inicializar (esta debajo definido)
		inicializarEstadoFichaje();
		
		//Explicacion:Ahora, hora del fichaje
		//Se actualiza el objeto trabajador
		//Se
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		//Conectar botón FICHAR con FichajeDAO
		btnFichar.addActionListener(e -> {
		    try {
		        // 1) Hora actual
		        LocalDateTime ahora = LocalDateTime.now();

		        // 2) Insertar en BD
		        FichajeDAO.crearFichajeEntrada(trabajador.getId(), ahora);

		        // 3) Actualizar el modelo en memoria
		        trabajador.registrarFichajeEntrada(ahora);

		        // 4) Actualizar estado visual
		        btnFichar.setEnabled(false);
		        btnDesfichar.setEnabled(true);

		        JOptionPane.showMessageDialog(this,
		                "Has fichado a las " + ahora.toLocalTime().format(formatter),
		                "Fichaje",
		                JOptionPane.INFORMATION_MESSAGE);

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(this,
		                "Error al fichar: " + ex.getMessage(),
		                "Error BD",
		                JOptionPane.ERROR_MESSAGE);
		    }
		});
		
		//Conectar boton DESfichar con fichajeDAO
		//Se mira la entrada que tenía el objeto trabajador
		//Se cierra la fila abieta en BD
		//Se añade al mapa de registrosFichaje, y se borra la entrada.
		//Se muestra mensaje de: has trabaado: x
		//TODO poner si hay tareas ejecutando y asi
		btnDesfichar.addActionListener(e -> {
		    try {
		        // 1) Hora actual
		        LocalDateTime salida = LocalDateTime.now();

		        // Comprobar si tiene entrada en memoria
		        LocalDateTime entrada = trabajador.getEntrada();
		        if (entrada == null) {
		            JOptionPane.showMessageDialog(this,
		                    "No hay fichaje de entrada activo.",
		                    "Aviso", //Creo que no debería de entrar aquí nunca
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // 2) Actualizar en BD
		        FichajeDAO.cerrarFichajeActual(trabajador.getId(), salida);

		        // 3) Actualizar el modelo en memoria
		        trabajador.registrarFichajeSalida(salida);

		        // 4) Calcular duración aproximada
		        long minutos = java.time.Duration.between(entrada, salida).toMinutes();
		        long horas = minutos / 60;
		        long minsRest = minutos % 60;

		        // 5) Actualizar estado visual
		        btnFichar.setEnabled(true);
		        btnDesfichar.setEnabled(false);

		        JOptionPane.showMessageDialog(this,
		                "Has desfichado a las " + salida.toLocalTime().format(formatter) +
		                "\nTiempo trabajado este tramo: " + horas + " h " + minsRest + " min",
		                "Desfichaje",
		                JOptionPane.INFORMATION_MESSAGE);

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(this,
		                "Error al desfichar: " + ex.getMessage(),
		                "Error BD",
		                JOptionPane.ERROR_MESSAGE);
		    }
		});
		
		// Para controlar que no se vaya sin fichar o que al menos 
		// Se le recuerde que sigue estando sin fichar
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				controlarSalida();
			}
			});


        	  
        		

	}
	//METODO PARA LOS FICHAJES:
	private void inicializarEstadoFichaje() {
	    try {
	        BDFichaje fichajeAbierto = FichajeDAO.obtenerFichajeAbierto(trabajador.getId());

	        if (fichajeAbierto != null) {
	            // Tiene fichaje abierto -> está fichado
	            LocalDateTime entrada = fichajeAbierto.getEntrada();
	            trabajador.setEntrada(entrada);  // sincronizamos el modelo

	            // OPCIONAL: se puede añadir esto para el trabajdor:
	            // trabajador.registrarFichajeEntrada(entrada);

	            btnFichar.setEnabled(false);
	            btnDesfichar.setEnabled(true);
	        } else {
	            // No tiene fichaje abierto -> no está fichado
	            trabajador.setEntrada(null);
	            btnFichar.setEnabled(true);
	            btnDesfichar.setEnabled(false);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this,
	                "Error al consultar el estado de fichaje.\n" + e.getMessage(),
	                "Error BD",
	                JOptionPane.ERROR_MESSAGE);

	        // En caso de error,dejamos los botones en su caso por defecto.
	        btnFichar.setEnabled(true);
	        btnDesfichar.setEnabled(false);
	    }
	}
	
	//METODO PARA MANEJAR SALIDA
	private void controlarSalida() {
	    try {
	        int idTrabajador = trabajador.getId();
	        BDFichaje fichajeAbierto = FichajeDAO.obtenerFichajeAbierto(idTrabajador);

	        if (fichajeAbierto == null) {
	            // No está fichado -> salir normal
	            salirAlLogin();
	            return;
	        }

	        // Está fichado -> preguntamos qué quiere hacer
	        int opcion = JOptionPane.showConfirmDialog(
	                this,
	                "Sigues fichado desde las " +
	                        fichajeAbierto.getEntrada().toLocalTime().withNano(0) +
	                        ".\n¿Quieres desfichar antes de salir?",
	                "Salir sin desfichar",
	                JOptionPane.YES_NO_CANCEL_OPTION,
	                JOptionPane.WARNING_MESSAGE
	        );

	        if (opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
	            // Se arrepiente, no hacemos nada
	            return;
	        }

	        if (opcion == JOptionPane.YES_OPTION) {
	            // Desfichar automáticamente y luego salir
	            realizarDesfichajeUsando(fichajeAbierto);
	        }
	        // Si es NO_OPTION -> no desfichamos, simplemente salimos

	        salirAlLogin();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(
	                this,
	                "Error al comprobar el estado de fichaje antes de salir:\n" + ex.getMessage(),
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	        );
	    }}
	    
	    //Fichaje de salida:
	    private void realizarDesfichajeUsando(BDFichaje fichajeAbierto) throws Exception {
	        LocalDateTime entrada = fichajeAbierto.getEntrada();
	        LocalDateTime salida = LocalDateTime.now();

	        // Cerrar en BD
	        boolean cerrado = FichajeDAO.cerrarFichajeActual(trabajador.getId(), salida);
	        if (!cerrado) {
	            throw new RuntimeException("No se ha podido cerrar el fichaje en la BD.");
	        }

	        // Actualizar modelo en memoria
	        trabajador.registrarFichajeSalida(salida);

	        // Calcular duración
	        long minutos = Duration.between(entrada, salida).toMinutes();
	        long horas = minutos / 60;
	        long minsRest = minutos % 60;

	        // Actualizar botones
	        btnFichar.setEnabled(true);
	        btnDesfichar.setEnabled(false);

	        JOptionPane.showMessageDialog(
	                this,
	                "Has desfichado a las " + salida.toLocalTime().withNano(0) +
	                "\nTiempo trabajado en este tramo: " + horas + " h " + minsRest + " min",
	                "Desfichaje",
	                JOptionPane.INFORMATION_MESSAGE
	        );
	    }
	    
	    private void salirAlLogin() {
	        // Ejemplo, ajusta a tu flujo real:
	        this.dispose();
	        parent.setVisible(true);  // si VTrabajador1 tiene un "parent"
	    }


		@Override
		protected void onConfirmExit() {
			// TODO Auto-generated method stub
			// Esta confirmación ya la ha hecho VentanaConConfirmacion
			 manejarSalidaYVolverALogin();
		}
		
		private void manejarSalidaYVolverALogin() {
		    try {
		        int idTrabajador = trabajador.getId();

		        // 1) Mirar si hay fichaje abierto en BD
		        BDFichaje fichajeAbierto = FichajeDAO.obtenerFichajeAbierto(idTrabajador);

		        if (fichajeAbierto != null) {
		            // 2) Está fichado, entonces preguntamos qué hacer
		            int opcion = JOptionPane.showConfirmDialog(
		                    this,
		                    "Sigues fichado desde las " +
		                            fichajeAbierto.getEntrada().toLocalTime().withNano(0) +
		                            ".\n¿Quieres desfichar antes de salir?",
		                    "Salir estando fichado",
		                    JOptionPane.YES_NO_CANCEL_OPTION,
		                    JOptionPane.WARNING_MESSAGE
		            );

		            if (opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
		                // No salir
		                return;
		            }

		            if (opcion == JOptionPane.YES_OPTION) {
		                // 3) Desfichar automáticamente
		                realizarDesfichajeUsando(fichajeAbierto);
		                // (este método ya debería hacer el update en BD, CREO
		            }
		            // Si NO_OPTION → no desfichamos, pero igualmente salimos al login
		        }

		        // 4) En todos los casos en los que no se ha hecho return, se sale al login:
		        this.dispose();
		        parent.setVisible(true);   // VPrincipal

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(
		                this,
		                "Error al gestionar el fichaje antes de salir:\n" + ex.getMessage(),
		                "Error",
		                JOptionPane.ERROR_MESSAGE
		        );
		    }
		}
		


	


}
