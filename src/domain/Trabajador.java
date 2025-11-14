package domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Trabajador extends Usuario {
	
	private HashSet<Tarea> tareasAsignadas;
	private LocalDate entrada;
	private Date salida;
	


	public Trabajador(String nombre, String apellidos, String usuario, String contraseyna, HashSet<Tarea> tareasAsignadas, LocalDate entrada,
			Date salida, HashMap registros) {
		super(nombre, apellidos, usuario, contraseyna, registros);
		this.tareasAsignadas = tareasAsignadas;
		this.entrada = entrada;
		this.salida = salida;
	}
	


	public Trabajador(String nombre, String apellidos, String usuario, String contraseyna,
			Map<LocalDate, List<String>> registros) {
		super(nombre, apellidos, usuario, contraseyna, registros);
		this.tareasAsignadas = new HashSet<Tarea>();
		this.entrada= null;
		this.salida = null;
	}



	public HashSet<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(HashSet<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}

	public LocalDate getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
	}

	public Date getSalida() {
		return salida;
	}

	public void setSalida(Date salida) {
		this.salida = salida;
	}
	
	
	
	
	

}
