package domain;

import java.util.ArrayList;
import java.util.HashSet;

public class BDTarea {
    private int id;
    private String nombre;
    private int duracion; // En minutos
	private String estado; // Opciones : pendiente (Si no se ha iniciado) , ejecutando (Si se ha iniciado el hilo) y finalizado (Si el hilo se ha finalizado)
	private Boolean ejecucion; // Por si es necesario para algo luego
	private HashSet<BDTrabajador> trabajadoresAsignados;
	private Thread hilo;


	public BDTarea(int id, String nombre, int duracion,  HashSet<BDTrabajador> trabajadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.estado = "pendiente";
		this.ejecucion = false;
		this.trabajadoresAsignados = trabajadores;
		this.hilo =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				setEstado("ejecutando");
				setEjecucion(true);
				System.out.println("Tarea Iniciada " + nombre);

				try {
					Thread.sleep(duracion * 60000); // 60000 milisegundos = 1 min
				} catch (InterruptedException e) {
					System.out.println("hilo terminado" + nombre);
					setEjecucion(null);
					setEstado("finalizado");
					return;
				}
				System.out.println("Tarea finalizada " + nombre);
				setEjecucion(null);
				setEstado("finalizado");
				
				
			}
		});;
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
	public Boolean getEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(Boolean ejecucion) {
		this.ejecucion = ejecucion;
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

	//es necesario para la base de datos (hay que completarlo)
	public boolean isCompletada() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String toString() {
		return nombre;
	}
	
}


