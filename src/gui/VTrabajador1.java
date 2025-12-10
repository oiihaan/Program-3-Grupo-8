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
import bd.TareaDAO;
import domain.BDFichaje;
import domain.BDTarea;
import domain.BDTrabajador;
import gui.ui.AppUI;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.BorderLayout;

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
        setSize(643, 462);
        setLocationRelativeTo(null);
        
			// --- LABEL BIENVENIDO ---
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        GridBagConstraints gbc_lbl = new GridBagConstraints();
        gbc_lbl.fill = GridBagConstraints.VERTICAL;
        gbc_lbl.insets = new Insets(0, 0, 5, 0);
        gbc_lbl.gridwidth = 6;
        gbc_lbl.gridx = 0;
        gbc_lbl.gridy = 1;
   
                                              
        // --- BOTÓN DESFICHAR ---
        btnDesfichar = new JButton("Desfichar");
        btnDesfichar.setBounds(36, 28, 100, 30);
        
        // --- BOTÓN FICHAR ---
        btnFichar = new JButton("Fichar");
        btnFichar.setBounds(44, 28, 85, 31);
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
        btnCerrarSesion.addActionListener(e -> manejarSalidaYVolverALogin());


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

        btnDesfichar.setEnabled(false);
        GridBagConstraints gbc_btnDesfichar = new GridBagConstraints();
        gbc_btnDesfichar.anchor = GridBagConstraints.WEST;
        gbc_btnDesfichar.fill = GridBagConstraints.VERTICAL;
        gbc_btnDesfichar.insets = new Insets(0, 0, 5, 5);
        gbc_btnDesfichar.gridx = 4;
        gbc_btnDesfichar.gridy = 3;

        GridBagConstraints gbc_btnVerTareas = new GridBagConstraints();
        gbc_btnVerTareas.fill = GridBagConstraints.BOTH;
        gbc_btnVerTareas.insets = new Insets(0, 0, 5, 5);
        gbc_btnVerTareas.gridx = 1;
        gbc_btnVerTareas.gridy = 5;

        GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
        gbc_btnCerrarSesion.insets = new Insets(0, 0, 5, 5);
        gbc_btnCerrarSesion.fill = GridBagConstraints.BOTH;
        gbc_btnCerrarSesion.gridx = 4;
        gbc_btnCerrarSesion.gridy = 5;

        
        controlFichaje(trabajador);

        //ESTILO CON AppUI
        AppUI.styleBackground((JPanel) getContentPane());
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {140, 350, 140};
        gridBagLayout.rowHeights = new int[] {50, 200, 50};
        gridBagLayout.columnWeights = new double[]{0.0,0.0,0.0};
        gridBagLayout.rowWeights = new double[]{0.0,0.0,0.0};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel centro = new JPanel();
        GridBagConstraints gbc_centro = new GridBagConstraints();
        gbc_centro.insets = new Insets(0, 0, 5, 5);
        gbc_centro.fill = GridBagConstraints.BOTH;
        gbc_centro.gridx = 1;
        gbc_centro.gridy = 1;
        getContentPane().add(centro, gbc_centro);
        centro.setLayout(new BorderLayout(0, 0));
        
        JPanel north = new JPanel();
        centro.add(north, BorderLayout.NORTH);
        north.add(lbl);
        
        JPanel centroCentro = new JPanel();
        centro.add(centroCentro, BorderLayout.CENTER);
        centroCentro.setLayout(new GridLayout(2, 2, 0, 0));
        
        JPanel topIz = new JPanel();
        centroCentro.add(topIz);
        topIz.setLayout(null);
        topIz.add(btnFichar);
        
        JPanel topDe = new JPanel();
        centroCentro.add(topDe);
        topDe.setLayout(null);
        topDe.add(btnDesfichar);
        
        JPanel downIz = new JPanel();
        centroCentro.add(downIz);
        downIz.add(btnVerTareas);
        
        JPanel downDe = new JPanel();
        centroCentro.add(downDe);
        downDe.add(btnCerrarSesion);
        
        
        AppUI.styleTitle(lbl);
        AppUI.stylePrimaryButton(btnFichar);
        AppUI.stylePrimaryButton(btnDesfichar);
        AppUI.stylePrimaryButton(btnVerTareas);
        AppUI.stylePrimaryButton(btnCerrarSesion);
        AppUI.styleBackground(centro);
        AppUI.styleCard(topDe);
        AppUI.styleCard(topIz);
        AppUI.styleCard(downDe);
        AppUI.styleCard(downIz);
        AppUI.styleCard(north);
        

        
        
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
		
//		addWindowListener(new java.awt.event.WindowAdapter() {
//			public void windowClosing(java.awt.event.WindowEvent e) {
//				controlarSalida();
//			}
//			});


        	  
        		

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
//	private void controlarSalida() {
//	    try {
//	        int idTrabajador = trabajador.getId();
//	        BDFichaje fichajeAbierto = FichajeDAO.obtenerFichajeAbierto(idTrabajador);
//
//	        if (fichajeAbierto == null) {
//	            // No está fichado -> salir normal
//	            salirAlLogin();
//	            return;
//	        }
//
//	        int opcion = JOptionPane.showConfirmDialog(
//	                this,
//	                "Sigues fichado desde las " +
//	                        fichajeAbierto.getEntrada().toLocalTime().withNano(0) +
//	                        ".\n¿Quieres desfichar antes de salir?",
//	                "Salir sin desfichar",
//	                JOptionPane.YES_NO_CANCEL_OPTION,
//	                JOptionPane.WARNING_MESSAGE
//	        );
//
//	        // 
//	        if (opcion == JOptionPane.CANCEL_OPTION ||
//	            opcion == JOptionPane.CLOSED_OPTION ||
//	            opcion == JOptionPane.NO_OPTION) {
//	            return;
//	        }
//
//	        // Solo si ha pulsado SÍ llegamos aquí:
//	        realizarDesfichajeUsando(fichajeAbierto);
//	        salirAlLogin();
//
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	        JOptionPane.showMessageDialog(
//	                this,
//	                "Error al comprobar el estado de fichaje antes de salir:\n" + ex.getMessage(),
//	                "Error",
//	                JOptionPane.ERROR_MESSAGE
//	        );
//	    }
//	}

	    
//	    //Fichaje de salida:
//	    private void realizarDesfichajeUsando(BDFichaje fichajeAbierto) throws Exception {
//	        LocalDateTime entrada = fichajeAbierto.getEntrada();
//	        LocalDateTime salida = LocalDateTime.now();
//
//	        // Cerrar en BD
//	        boolean cerrado = FichajeDAO.cerrarFichajeActual(trabajador.getId(), salida);
//	        if (!cerrado) {
//	            throw new RuntimeException("No se ha podido cerrar el fichaje en la BD.");
//	        }
//
//	        // Actualizar modelo en memoria
//	        trabajador.registrarFichajeSalida(salida);
//
//	        // Calcular duración
//	        long minutos = Duration.between(entrada, salida).toMinutes();
//	        long horas = minutos / 60;
//	        long minsRest = minutos % 60;
//
//	        // Actualizar botones
//	        btnFichar.setEnabled(true);
//	        btnDesfichar.setEnabled(false);
//
//	        JOptionPane.showMessageDialog(
//	                this,
//	                "Has desfichado a las " + salida.toLocalTime().withNano(0) +
//	                "\nTiempo trabajado en este tramo: " + horas + " h " + minsRest + " min",
//	                "Desfichaje",
//	                JOptionPane.INFORMATION_MESSAGE
//	        );
//	    }
	    
//	    private void salirAlLogin() {
//	        this.dispose();
//	        parent.setVisible(true);  // el parent es el login
//	    }

		@Override
		protected String getMensajeConfirmacionSalida() {
		    return "¿Quieres volver al login?";
		}

		@Override
		protected String getTituloConfirmacionSalida() {
		    return "Cerrar sesión";
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

		        // 1️:Comprobar si hay fichaje abierto
		        BDFichaje fichajeAbierto = FichajeDAO.obtenerFichajeAbierto(idTrabajador);

		        if (fichajeAbierto != null) {
		            LocalDateTime inicio = fichajeAbierto.getEntrada(); // Consigo su hora de entrada para decirsela

		            String mensajeFichaje = String.format(
		                "Tienes un fichaje abierto desde %s.\n\n" +
		                "Si sales ahora, se cerrará el fichaje (desfichando) y volverás a la pantalla de login.\n\n" +
		                "¿Quieres salir de la aplicación desfichando?",
		                inicio.toString() // se puede formatear mas bonito con dateTImeFormatter
		            );

		            int opcionFichaje = JOptionPane.showConfirmDialog(
		                    this,
		                    mensajeFichaje,
		                    "Fichaje en curso",
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.WARNING_MESSAGE
		            );

		            if (opcionFichaje == JOptionPane.NO_OPTION ||
		            		opcionFichaje == JOptionPane.CLOSED_OPTION) 
		            {
		                // El trabajador decide NO salir: se queda en la ventana
		                return;
		            }

		            // Si ha dicho que SI, se cierra  el fichaje
		            LocalDateTime fin = LocalDateTime.now();
		            fichajeAbierto.setSalida(fin);
		            FichajeDAO.cerrarFichajeActual(idTrabajador, fin); 
		            
		        }

		        // 2️:Comprobar tareas en ejecución de este trabajador
		        ArrayList<BDTarea> tareasEjecutando = TareaDAO.getTareasEjecutandoDeTrabajador(idTrabajador);

		        if (!tareasEjecutando.isEmpty()) {
		            int numTareas = tareasEjecutando.size();

		            String mensajeTareas = String.format(
		                "Tienes %d tarea(s) en ejecución.\n\n" +
		                "Si sales ahora, estas tareas se pondrán de nuevo en estado 'pendiente'.\n\n" +
		                "¿Quieres salir de todas formas?",
		                numTareas
		            );

		            int opcionTareas = JOptionPane.showConfirmDialog(
		                    this,
		                    mensajeTareas,
		                    "Tareas en ejecución",
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.QUESTION_MESSAGE
		            );

		            if (opcionTareas == JOptionPane.NO_OPTION) {
		                // cuando le da a no
		                return;
		            }

		            // Marcar como pendiente solo las tareas que estaban en ejecutand para este trabajador
		            for (BDTarea t : tareasEjecutando) {
		                TareaDAO.marcarPendiente(t.getId());
		            }
		        }

		        // 3--Si se ha llegado hasta aquí:
		        //    No hay fichaje abierto
		        //    No hay tareas ejecutando
		        //    se puedecerrar sesión del trabajador y volver al login.

		        this.dispose();         
		        if (parent != null) {
		            parent.setVisible(true);   // VPrincipal
		        }

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(
		                this,
		                "Se ha producido un error al intentar cerrar la sesión.",
		                "Error",
		                JOptionPane.ERROR_MESSAGE
		        );
		    }
		}

		


	


}
