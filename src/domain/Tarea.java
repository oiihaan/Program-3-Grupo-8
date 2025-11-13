package domain;

import java.util.HashSet;

public class Tarea {
	
	private HashSet<Trabajador> trabajadoresAsignados;
	private Integer duracion; //En minutos
	private Thread hilo;
	private String estado; // Opciones : pendiente (Si no se ha iniciado) , ejecutando (Si se ha iniciado el hilo) y finalizado (Si el hilo se ha finalizado)
	private Boolean ejecucion; // Por si es necesario para algo luego
	
	public Tarea(HashSet<Trabajador> trabajadoresAsignados, Integer duracion) {
		super();
		this.trabajadoresAsignados = trabajadoresAsignados;
		this.duracion = duracion;
		this.hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				setEstado("ejecutando");
				setEjecucion(true);
				try {
					Thread.sleep(duracion * 60000); // 60000 milisegundos = 1 min
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setEjecucion(null);
				setEstado("finalizado");
				
				
			}
		});
		this.estado = "pendiente";
		this.ejecucion = false;
	}

	public HashSet<Trabajador> getTrabajadoresAsignados() {
		return trabajadoresAsignados;
	}

	public void setTrabajadoresAsignados(HashSet<Trabajador> trabajadoresAsignados) {
		this.trabajadoresAsignados = trabajadoresAsignados;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Thread getHilo() {
		return hilo;
	}

	public void setHilo(Thread hilo) {
		this.hilo = hilo;
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
	
	
	


}
