package domain;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

import bd.TareaDAO;

public class BDTarea {
    private int id;
    private String nombre;
    private int duracion; // En minutos
	private String estado; // Opciones : pendiente (Si no se ha iniciado) , ejecutando (Si se ha iniciado el hilo) y finalizado (Si el hilo se ha finalizado)
	private HashSet<BDTrabajador> trabajadoresAsignados; //Mirar el doc la 23 he puseto algo sobre esto
	private Thread hilo;
	
	//NUEVO
	
	public BDTarea(int id, String nombre, int duracion, String estado,
			HashSet<BDTrabajador> trabajadoresAsignados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.estado = estado;
		this.trabajadoresAsignados = trabajadoresAsignados;
		this.hilo =   new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				setEstado("ejecutando");
		        JOptionPane.showMessageDialog(null, "Tarea Iniciada " + nombre);

				TareaDAO.marcarEjecutando(id);

				try {
					Thread.sleep(duracion * 60000); // 60000 milisegundos = 1 min
				} catch (InterruptedException e) {
					TareaDAO.marcarCompletada(id);
					return;
				}
				TareaDAO.marcarCompletada(id);
				
				
			}
		});
	}
	//Constructor para insetar a la base de datos
	public BDTarea( String nombre, int duracion, String estado) {
		super();
//		this.id = (Integer) null;
		this.nombre = nombre;
		this.duracion = duracion;
		this.estado = estado;
//		this.trabajadoresAsignados = null;
		this.hilo =   new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				setEstado("ejecutando");
		        JOptionPane.showMessageDialog(null, "Tarea Iniciada " + nombre);
				TareaDAO.marcarEjecutando(id);

				try {
					Thread.sleep(duracion * 60000); // 60000 milisegundos = 1 min
				} catch (InterruptedException e) {
					TareaDAO.marcarCompletada(BDTarea.this.getId());
					return;
				}
				TareaDAO.marcarCompletada(id);
				
				
			}
		});
	}
	
	@Override
	public String toString() {
	    return String.format(nombre);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Thread getHilo() {
		return hilo;
	}
	public void setHilo(Thread hilo) {
		this.hilo = hilo;
	}
	public HashSet<BDTrabajador> getTrabajadoresAsignados() {
		return trabajadoresAsignados;
	}
	public void setTrabajadoresAsignados(HashSet<BDTrabajador> trabajadoresAsignados) {
		this.trabajadoresAsignados = trabajadoresAsignados;
	}






	
}


