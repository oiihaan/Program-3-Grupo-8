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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class VTrabajador1 extends VentanaConConfirmacion {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BDTrabajador trabajador;
	private JButton btnDesfichar;
	private JButton btnFichar;
	private JButton btnVerTareas;
	private JButton btnCerrarSesion;
	JButton btnVerFichajes;
	private VPrincipal parent; 
	
	
	//CONSTRUCTOR
	public VTrabajador1(VPrincipal parent, BDTrabajador trabajador) {
		this.parent = parent;
		this.trabajador = trabajador;
		
		//getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setTitle("Panel del Trabajador");
        setSize(643, 462);
        setLocationRelativeTo(null);
        
        //PANEL PRINCIPAL
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        //LAYOUT PRINCIPAL:
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths  = new int[] {150, 150, 150}; 
        gbl.rowHeights    = new int[] {50, 300, 50};
        gbl.columnWeights = new double[]{0.0, 0.0, 0.0};
        gbl.rowWeights    = new double[]{0.0, 0.0, 0.0};
        contentPane.setLayout(gbl);
      
        //PANEL CENTRO
        JPanel centro = new JPanel();
 
        GridBagConstraints gbc_centro = new GridBagConstraints();
        gbc_centro.anchor = GridBagConstraints.WEST;      // igual que Admin
        gbc_centro.fill = GridBagConstraints.VERTICAL;    // igual que Admin
        gbc_centro.insets = new Insets(0, 0, 5, 5);       // igual que Admin
        gbc_centro.gridx = 1; // Columna central
        gbc_centro.gridy = 1; // Fila central
        contentPane.add(centro, gbc_centro);
        
        centro.setLayout(new BorderLayout(0, 0));
        
     //NORTH (Titulo)
        JPanel centroNorth = new JPanel();
        FlowLayout fl = (FlowLayout) centroNorth.getLayout();
        fl.setVgap(20);
        centro.add(centroNorth, BorderLayout.NORTH);
        
        JLabel lbl = new JLabel("Bienvenido, " + trabajador.getNombre());
        centroNorth.add(lbl);
        
     // CENTRO,2x2 botones 
        JPanel centroCenter = new JPanel();
        centroCenter.setLayout(new GridLayout(2, 2, 10, 30));
        centro.add(centroCenter, BorderLayout.CENTER);

        btnFichar = new JButton("Fichar");
        btnDesfichar = new JButton("Desfichar");
        btnVerTareas = new JButton("Ver tareas");
        btnVerFichajes = new JButton("Ver mis fichajes");
        btnCerrarSesion = new JButton("Cerrar sesión");

        centroCenter.add(btnFichar);
        centroCenter.add(btnDesfichar);
        centroCenter.add(btnVerTareas);
        centroCenter.add(btnVerFichajes);

        
     // CERRAR SESIÓN
        GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
        gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
        gbc_btnCerrar.gridx = 1;
        gbc_btnCerrar.gridy = 2;
        contentPane.add(btnCerrarSesion, gbc_btnCerrar);
        
        
        
        
     // ===== ESTILO ======
        AppUI.stylePrimaryButton(btnFichar);
        AppUI.stylePrimaryButton(btnDesfichar);
        AppUI.stylePrimaryButton(btnVerTareas);
        AppUI.stylePrimaryButton(btnVerFichajes);
        AppUI.stylePrimaryButton(btnCerrarSesion);
        

        // Estilo de fondo a los paneles 
        AppUI.styleBackground(contentPane);
        AppUI.styleBackground(centro);
        AppUI.styleCard(centro); 
         
        AppUI.styleTransparent(centroNorth); 
        AppUI.styleTransparent(centroCenter);
       

        // Estilo del Título
        AppUI.styleTitle(lbl);
        
        AppUI.establecerIcono(this);
        //=================================================
        //====================================== ACTIONS ========================================================
        //================================================================
        //Llamada al metodo de inicializar (esta debajo definido)
        inicializarEstadoFichaje();
        //
        controlFichaje(trabajador);
        
        //===============FICHAR=================
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
        
        //============================DESFICHAR==========================
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
		//======================================================================================
		//para controlar que no se vaya sin fichar:
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		// =============CERRAR SESION===========
        btnCerrarSesion.addActionListener(e -> manejarSalidaYVolverALogin());

        //========================VER FICHAJES==============================
        btnVerFichajes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		TrabajadorVerFichaje VTrabajadorTarea = new TrabajadorVerFichaje(VTrabajador1.this , trabajador , trabajador.getId());
        		VTrabajadorTarea.setVisible(true);
        		VTrabajador1.this.setVisible(false);  // No se abre la ventana, no se porque. Falta pasarle el trabajador
        		
        		
        	}
        });
        
        //=============================VER TAREAS==============================
        btnVerTareas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VTrabajadorTarea VTrabajadorTarea = new VTrabajadorTarea(VTrabajador1.this , trabajador);
        		VTrabajadorTarea.setVisible(true);
        		VTrabajador1.this.setVisible(false);  // No se abre la ventana, no se porque. Falta pasarle el trabajador
        	}
        });
        
      
        
        
     //din de constructor
		
	}
	//METODO QUE PODEMOS USAR:
//	private void configurarFichaje() {
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//	    btnFichar.addActionListener(e -> {
//	        try {
//	            LocalDateTime ahora = LocalDateTime.now();
//	            FichajeDAO.crearFichajeEntrada(trabajador.getId(), ahora);
//	            trabajador.registrarFichajeEntrada(ahora);
//
//	            btnFichar.setEnabled(false);
//	            btnDesfichar.setEnabled(true);
//
//	            JOptionPane.showMessageDialog(this,
//	                    "Has fichado a las " + ahora.toLocalTime().format(formatter));
//	        } catch (Exception ex) {
//	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//	        }
//	    });
//
//	    btnDesfichar.addActionListener(e -> {
//	        try {
//	            LocalDateTime salida = LocalDateTime.now();
//	            FichajeDAO.cerrarFichajeActual(trabajador.getId(), salida);
//	            trabajador.registrarFichajeSalida(salida);
//
//	            btnFichar.setEnabled(true);
//	            btnDesfichar.setEnabled(false);
//
//	            JOptionPane.showMessageDialog(this,
//	                    "Has desfichado a las " + salida.toLocalTime().format(formatter));
//	        } catch (Exception ex) {
//	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//	        }
//	    });
//	}

	
	public void controlFichaje(BDTrabajador trabajador){
			
			if (trabajador.getEntrada() != null) {
				btnDesfichar.setEnabled(true);
				btnFichar.setEnabled(false);
	
	
			}else {
				btnFichar.setEnabled(true);
				btnDesfichar.setEnabled(false);
	
	
			}
			
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

		    
		        // 3--Si se ha llegado hasta aquí:
		        //    No hay fichaje abierto
		        //    No hay tareas ejecutando
		        //    se puedecerrar sesión del trabajador y volver al login.

		        this.dispose();         
		        if (parent != null) {
		            parent.setVisible(true); 
		            parent.getTxtUser().requestFocusInWindow();
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
