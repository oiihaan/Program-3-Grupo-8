package domain;

import java.sql.Date;
import java.util.HashSet;

public class Trabajador extends Usuario {
	
	private HashSet<Tarea> tareasAsignadas;
	private Date entrada;
	private Date salida;
	


	public Trabajador(String nombre, String apellidos, String usuario, String contraseyna, HashSet<Tarea> tareasAsignadas, Date entrada,
			Date salida) {
		super(nombre, apellidos, usuario, contraseyna);
		this.tareasAsignadas = tareasAsignadas;
		this.entrada = entrada;
		this.salida = salida;
	}
	

	public Trabajador(String nombre,String apellidos, String usuario, String contraseyna) {
		super(nombre, apellidos, usuario, contraseyna);
		this.tareasAsignadas= new HashSet<Tarea>();
		this.entrada = null;
		this.salida = null;
	}


	public HashSet<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(HashSet<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSalida() {
		return salida;
	}

	public void setSalida(Date salida) {
		this.salida = salida;
	}
	
	
	
	
	

}
