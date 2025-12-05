package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class VTrabajador1 extends JFrame {

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
        btnDesfichar.addActionListener(e -> {
            try {
                int idTrabajador = trabajador.getId();

                // 1) Miramos en BD si hay un fichaje abierto
                BDFichaje fichajeAbierto = FichajeDAO.obtenerFichajeAbierto(idTrabajador);
                if (fichajeAbierto == null) {
                    JOptionPane.showMessageDialog(
                            VTrabajador1.this,
                            "No hay ningún fichaje abierto para este trabajador.",
                            "Desfichaje no posible",
                            JOptionPane.WARNING_MESSAGE
                    );
                    // Por seguridad, dejamos sólo habilitado FICHAR
                    trabajador.setEntrada(null);
                    btnFichar.setEnabled(true);
                    btnDesfichar.setEnabled(false);
                    return;
                }

                // 2) Tenemos un fichaje abierto -> usamos su entrada
                LocalDateTime entrada = fichajeAbierto.getEntrada();
                LocalDateTime salida = LocalDateTime.now();

                // 3) Cerramos en BD
                boolean cerrado = FichajeDAO.cerrarFichajeActual(idTrabajador, salida);
                if (!cerrado) {
                    JOptionPane.showMessageDialog(
                            VTrabajador1.this,
                            "No se ha podido cerrar el fichaje en la base de datos.",
                            "Error BD",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // 4) Actualizamos el modelo en memoria
                trabajador.registrarFichajeSalida(salida);

                // 5) Calculamos la duración para mostrar al usuario
                long minutos = Duration.between(entrada, salida).toMinutes();
                long horas = minutos / 60;
                long minsRest = minutos % 60;

                // 6) Actualizamos botones
                btnFichar.setEnabled(true);
                btnDesfichar.setEnabled(false);

                JOptionPane.showMessageDialog(
                        VTrabajador1.this,
                        "Has desfichado a las " + salida.toLocalTime().withNano(0) +
                        "\nTiempo trabajado en este tramo: " + horas + " h " + minsRest + " min",
                        "Desfichaje",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        VTrabajador1.this,
                        "Error al desfichar: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
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
        		                "Has fichado a las " + ahora.toLocalTime(),
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

        		        // (Opcional) Comprobar si tiene entrada en memoria
        		        LocalDateTime entrada = trabajador.getEntrada();
        		        if (entrada == null) {
        		            JOptionPane.showMessageDialog(this,
        		                    "No hay fichaje de entrada activo.",
        		                    "Aviso",
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
        		                "Has desfichado a las " + salida.toLocalTime() +
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

}
